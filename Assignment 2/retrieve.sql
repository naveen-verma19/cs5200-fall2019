-- I AM USING THIS VIEW FOR BELOW QUERIES
DROP VIEW IF EXISTS developer_details;
CREATE VIEW developer_details AS
    SELECT 
        d.id,
        p.first_name AS first_name,
        p.last_name AS last_name,
        p.username AS username,
        p.email AS email,
        d.developer_key
    FROM
        person p
            JOIN
        developer d ON p.id = d.id;

-- RETRIEVE DEVELOPERS
-- a)
SELECT * FROM developer_details;
-- b)
SELECT * FROM developer_details WHERE id=34;
-- c)
SELECT * FROM developer_details WHERE id IN 
(SELECT developer_id FROM website_role WHERE website_id=(SELECT id FROM website WHERE name='Twitter') AND role<>'owner');
-- d)
SELECT * FROM developer_details WHERE id IN 
(SELECT developer_id FROM page_role WHERE page_id IN (SELECT id FROM page WHERE views <300000) AND role ='reviewer');
-- e)
SELECT * FROM developer_details WHERE id IN (
SELECT developer_id FROM page_role WHERE page_id IN (SELECT id FROM page WHERE id IN (SELECT page_id FROM widget WHERE dtype='heading') AND title='Home' AND website_id=(SELECT id FROM website WHERE name='CNET'))
AND role='writer');


-- RETRIEVE WEBSITES
-- a)
SELECT * FROM website ORDER BY visits LIMIT 0,1;
-- b)
SELECT name FROM website WHERE id=678;
-- c)
SELECT * FROM website WHERE id IN
(SELECT website_id FROM page WHERE id IN
(SELECT page_id FROM page_role WHERE page_id IN (SELECT page_id FROM widget WHERE dtype='youtube') AND role='reviewer' AND developer_id=(SELECT id FROM developer_details WHERE username='bob'))
); 
-- d)
SELECT * FROM website WHERE id IN
(
SELECT website_id FROM website_role WHERE role='owner' AND developer_id=(SELECT id FROM developer_details WHERE username='alice')
);
-- e)
SELECT * FROM website WHERE visits > 6000000 AND id IN
(
SELECT website_id FROM website_role WHERE role='admin' AND developer_id=(SELECT id FROM developer_details WHERE username='charlie')
);

-- RETRIEVE PAGES
-- a)
SELECT * FROM page ORDER BY views desc LIMIT 0,1;
-- b)
SELECT title FROM page WHERE id=234;
-- c)
SELECT * FROM page WHERE id IN(
SELECT page_id FROM page_role WHERE role='editor' AND developer_id=(SELECT id FROM developer_details WHERE username='alice')
);
-- d)
SELECT SUM(views)
FROM page WHERE website_id=(SELECT id FROM website WHERE name ='CNET');
-- e)
SELECT AVG(views)
FROM page WHERE website_id=(SELECT id FROM website WHERE name ='Wikipedia');

-- RETRIEVE WIDGETS
-- a)
SELECT * FROM widget WHERE page_id IN (SELECT id FROM page WHERE title='Home' AND website_id=(SELECT id FROM website WHERE name ='CNET'));
-- b)
SELECT * FROM widget WHERE dtype='youtube' AND page_id IN (SELECT id FROM page WHERE website_id=(SELECT id FROM website WHERE name ='CNN'));
-- c)
SELECT * FROM widget WHERE dtype='image' AND page_id IN (SELECT page_id FROM page_role WHERE developer_id=(SELECT id FROM developer_details WHERE username='alice') AND role='reviewer');
-- d)
SELECT COUNT(*) FROM widget WHERE page_id IN (SELECT id FROM page WHERE website_id=(SELECT id FROM website WHERE name ='Wikipedia'));

-- TO VERIFY the page and website triggers written earlier function properly:
-- a)
SELECT name FROM website WHERE id IN (SELECT website_id FROM website_priviledge WHERE priviledge='delete' AND developer_id=(SELECT id FROM developer_details WHERE username='bob'));
-- b)
SELECT title FROM page WHERE id IN (SELECT page_id FROM page_priviledge WHERE priviledge='create' AND developer_id=(SELECT id FROM developer_details WHERE username='charlie'));