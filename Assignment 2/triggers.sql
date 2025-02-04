DROP TRIGGER IF EXISTS before_website_role_insert;
DROP TRIGGER IF EXISTS before_website_role_delete;
DROP TRIGGER IF EXISTS before_website_role_update;
DROP TRIGGER IF EXISTS before_page_role_insert;
DROP TRIGGER IF EXISTS before_page_role_delete;
DROP TRIGGER IF EXISTS before_page_role_update;


DELIMITER $$
CREATE TRIGGER before_website_role_insert
	BEFORE INSERT ON website_role
	FOR EACH ROW
BEGIN	
	INSERT INTO website_priviledge
    SET 
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='read';
	IF NEW.role IN ('owner','admin','writer','editor')
		THEN INSERT INTO website_priviledge
    SET 	
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='update';
    END IF;
    
    IF NEW.role IN ('owner','admin','writer')
		THEN INSERT INTO website_priviledge
    SET 
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='create';
    END IF;
    
    IF NEW.role IN ('owner','admin')
		THEN INSERT INTO website_priviledge
    SET 
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='delete';
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_website_role_delete
	BEFORE DELETE ON website_role
	FOR EACH ROW
BEGIN
	DELETE FROM website_priviledge WHERE developer_id=OLD.developer_id AND website_id=OLD.website_id;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_website_role_update
	BEFORE UPDATE ON website_role
	FOR EACH ROW
BEGIN
	DELETE  FROM website_priviledge WHERE developer_id=OLD.developer_id AND website_id=OLD.website_id;
	
	INSERT INTO website_priviledge
    SET 
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='read';
	IF NEW.role IN ('owner','admin','writer','editor')
		THEN INSERT INTO website_priviledge
    SET 	
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='update';
    END IF;
    
    IF NEW.role IN ('owner','admin','writer')
		THEN INSERT INTO website_priviledge
    SET 
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='create';
    END IF;
    
    IF NEW.role IN ('owner','admin')
		THEN INSERT INTO website_priviledge
    SET 
		developer_id=NEW.developer_id,
        website_id= NEW.website_id,
        priviledge='delete';
    END IF;
		
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_page_role_insert
	BEFORE INSERT ON page_role
	FOR EACH ROW
BEGIN	
	INSERT INTO page_priviledge
    SET 
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='read';
	IF NEW.role IN ('owner','admin','writer','editor')
		THEN INSERT INTO page_priviledge
    SET 	
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='update';
    END IF;
    
    IF NEW.role IN ('owner','admin','writer')
		THEN INSERT INTO page_priviledge
    SET 
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='create';
    END IF;
    
    IF NEW.role IN ('owner','admin')
		THEN INSERT INTO page_priviledge
    SET 
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='delete';
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_page_role_delete
	BEFORE DELETE ON page_role
	FOR EACH ROW
BEGIN
	DELETE FROM page_priviledge WHERE developer_id=OLD.developer_id AND page_id=OLD.page_id;		
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_page_role_update
	BEFORE UPDATE ON page_role
	FOR EACH ROW
BEGIN
	DELETE  FROM page_priviledge WHERE developer_id=OLD.developer_id AND page_id=OLD.page_id;
	
	INSERT INTO page_priviledge
    SET 
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='read';
	IF NEW.role IN ('owner','admin','writer','editor')
		THEN INSERT INTO page_priviledge
    SET 	
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='update';
    END IF;
    
    IF NEW.role IN ('owner','admin','writer')
		THEN INSERT INTO page_priviledge
    SET 
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='create';
    END IF;
    
    IF NEW.role IN ('owner','admin')
		THEN INSERT INTO page_priviledge
    SET 
		developer_id=NEW.developer_id,
        page_id= NEW.page_id,
        priviledge='delete';
    END IF;
		
END $$
DELIMITER ;
