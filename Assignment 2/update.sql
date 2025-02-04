UPDATE phone
SET phone='333-444-5555'
WHERE person_id=(SELECT id FROM person WHERE username='charlie') AND `primary`=TRUE;

-- -------------------------------------------------------------------------------------------------
SET @pageId:=(SELECT page_id FROM widget WHERE name='head345');
SET @curId:=(SELECT id FROM widget WHERE name='head345');
SET @nextId:=(SELECT id FROM widget WHERE `order`=2 AND page_id=@pageId);
SET @nextId2:=(SELECT id FROM widget WHERE `order`=3 AND page_id=@pageId);
UPDATE widget 
SET `order`=3 WHERE id=@curId;
UPDATE widget 
SET `order`=4 WHERE id=@nextId;
UPDATE widget 
SET `order`=5 WHERE id=@nextId2;
-- -------------------------------------------------------------------------------------------------

UPDATE page
SET title=CONCAT('CNET - ',title)
WHERE website_id =(SELECT id FROM website WHERE name ='CNET');
-- -------------------------------------------------------------------------------------------------

SET @charlie:=(SELECT id FROM developer_details WHERE username='charlie');
SET @bob:=(SELECT id FROM developer_details WHERE username='bob');
SET @cnet:=(SELECT id FROM website WHERE name='CNET');
SET @cnet_home_id:=(SELECT id FROM page WHERE website_id=@cnet AND title='Home');
SET @charlie_role:=(SELECT role FROM page_role WHERE developer_id=@charlie AND page_id= @cnet_home_id);
SET @bob_role:=(SELECT role FROM page_role WHERE developer_id=@bob AND page_id= @cnet_home_id);

UPDATE page_role
SET role=@bob_role WHERE developer_id=@charlie AND page_id=@cnet_home_id;

UPDATE page_role
SET role=@charlie_role WHERE developer_id=@bob AND page_id=@cnet_home_id;
-- -------------------------------------------------------------------------------------------------


