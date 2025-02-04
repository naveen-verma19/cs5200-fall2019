DELETE FROM address 
WHERE
    `primary` = TRUE
    AND person_id = (SELECT id FROM  person WHERE username = 'alice');
-- -------------------------------------------------------------------------------------------------
    
DELETE FROM widget 
WHERE page_id=(SELECT id FROM page WHERE title='Contact')
ORDER BY `order` DESC
LIMIT 1;
-- -------------------------------------------------------------------------------------------------

DELETE FROM page 
WHERE website_id=(SELECT id FROM website WHERE name='Wikipedia')
ORDER BY updated DESC
LIMIT 1;
-- -------------------------------------------------------------------------------------------------

SET @cnet:=(SELECT id FROM website WHERE name ='CNET');
DELETE FROM website WHERE id=@cnet;
-- deleting a website automatically deletes related roles,priviledges, pages, page-roles, page-priviledges, page-widgets
-- -------------------------------------------------------------------------------------------------

 