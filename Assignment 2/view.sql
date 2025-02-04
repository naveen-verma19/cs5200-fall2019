DROP VIEW IF EXISTS deleveloper_roles_and_privileges;
CREATE VIEW deleveloper_roles_and_privileges AS
    SELECT 
        d.id AS id,
        p.first_name AS first_name,
        p.last_name AS last_name,
        p.username AS username,
        p.email AS email,
        w.name AS website_name,
        w.visits AS visits,
        w.updated AS website_updated,
        wr.role AS website_role,
        wp.priviledge AS webiste_priviledge,
        pg.title AS page_title,
        pg.views AS views,
        pg.updated AS page_updated,
        pr.role AS page_role,
        pp.priviledge AS page_priviledge
    FROM
        developer d
            JOIN
        person p ON p.id = d.id
            JOIN
        website_role wr ON wr.developer_id = d.id
            JOIN
        website w ON w.id = wr.website_id
            JOIN
        website_priviledge wp ON wp.developer_id = d.id
            AND wp.website_id = w.id
            JOIN
        page pg ON pg.website_id = w.id
            JOIN
        page_role pr ON pr.developer_id = d.id
            AND pr.page_id = pg.id
            JOIN
        page_priviledge pp ON pp.developer_id = d.id
            AND pp.page_id = pg.id
    ORDER BY first_name , w.name , pg.title;
    






