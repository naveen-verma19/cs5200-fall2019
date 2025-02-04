-- INSERTING DATA INTO ENUMNS
INSERT INTO priviledge VALUES('create');
INSERT INTO priviledge VALUES('read');
INSERT INTO priviledge VALUES('update');
INSERT INTO priviledge VALUES('delete');

INSERT INTO role VALUES('owner');
INSERT INTO role VALUES('admin');
INSERT INTO role VALUES('writer');
INSERT INTO role VALUES('editor');
INSERT INTO role VALUES('reviewer');

INSERT INTO widget_type VALUES('youtube');
INSERT INTO widget_type VALUES('image');
INSERT INTO widget_type VALUES('heading');
INSERT INTO widget_type VALUES('html');
-- FINISHED INSERTING DATA INTO ENUMNS


INSERT INTO person(id,first_name,last_name,username,password,email) VALUES(12,'Alice','Wonder','alice','alice','alice@wonder.com');
INSERT INTO person(id,first_name,last_name,username,password,email) VALUES(23,'Bob','Marley','bob','bob','bob@marley.com');
INSERT INTO person(id,first_name,last_name,username,password,email) VALUES(34,'Charles','Garcia','charlie','charlie','chuch@garcia.com');
INSERT INTO person(id,first_name,last_name,username,password,email) VALUES(45,'Dan','Martin','dan','dan','dan@martin.com');
INSERT INTO person(id,first_name,last_name,username,password,email) VALUES(56,'Ed','Karaz','ed','ed','ed@kar.com');



INSERT INTO developer VALUES(12,'4321rewq');
INSERT INTO developer VALUES(23,'5432trew');
INSERT INTO developer VALUES(34,'6543ytre');

INSERT INTO user(id) VALUES(45);
INSERT INTO user(id) VALUES(56);
INSERT INTO phone(phone,`primary`,person_id) VALUES('123-234-3456',TRUE,12);
INSERT INTO phone(phone,`primary`,person_id) VALUES('234-345-4566',FALSE,12);
INSERT INTO phone(phone,`primary`,person_id) VALUES('345-456-5677',TRUE,23);
INSERT INTO phone(phone,`primary`,person_id) VALUES('321-432-5435',TRUE,34);

-- alice-12 bob-23 charlie 34
INSERT INTO address(street1,street2,city,zip,`primary`,person_id) VALUES('123','Adam St.','Alton','01234',TRUE,12);
INSERT INTO address(street1,street2,city,zip,`primary`,person_id) VALUES('234','Birch St.','Boston','02345',FALSE,12);
INSERT INTO address(street1,street2,city,zip,`primary`,person_id) VALUES('345','Charles St.','Chelms','03455',TRUE,23);
INSERT INTO address(street1,street2,city,zip,`primary`,person_id) VALUES('456','Down St.','Dalton','04566',FALSE,23);
INSERT INTO address(street1,street2,city,zip,`primary`,person_id) VALUES('543','East St.','Everett','01112',FALSE,23);
INSERT INTO address(street1,street2,city,zip,`primary`,person_id) VALUES('654','Frank St.','Foulton','04322',TRUE,34);


INSERT INTO website(id,`name`,description,visits) VALUES(123,'Facebook','an online social media and social networking service',1234234);
INSERT INTO website(id,`name`,description,visits) VALUES(234,'Twitter','an online news and social networking service',4321543);
INSERT INTO website(id,`name`,description,visits) VALUES(345,'Wikipedia','a free online encyclopedia',3456654);
INSERT INTO website(id,`name`,description,visits) VALUES(456,'CNN','an American basic cable and satellite television news channel',6543345);
INSERT INTO website(id,`name`,description,visits) VALUES(567,'CNET','an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics',5433455);
INSERT INTO website(id,`name`,description,visits) VALUES(678,'Gizmodo','a design, technology, science and science fiction website that also writes articles on politics',4322345);

-- alice-12 bob-23 charlie 34
INSERT INTO website_role(developer_id,website_id,role) VALUES(12,123,'owner');
INSERT INTO website_role(developer_id,website_id,role) VALUES(23,123,'editor');
INSERT INTO website_role(developer_id,website_id,role) VALUES(34,123,'admin');

INSERT INTO website_role(developer_id,website_id,role) VALUES(23,234,'owner');
INSERT INTO website_role(developer_id,website_id,role) VALUES(34,234,'editor');
INSERT INTO website_role(developer_id,website_id,role) VALUES(12,234,'admin');

INSERT INTO website_role(developer_id,website_id,role) VALUES(34,345,'owner');
INSERT INTO website_role(developer_id,website_id,role) VALUES(12,345,'editor');
INSERT INTO website_role(developer_id,website_id,role) VALUES(23,345,'admin');

INSERT INTO website_role(developer_id,website_id,role) VALUES(12,456,'owner');
INSERT INTO website_role(developer_id,website_id,role) VALUES(23,456,'editor');
INSERT INTO website_role(developer_id,website_id,role) VALUES(34,456,'admin');

INSERT INTO website_role(developer_id,website_id,role) VALUES(23,567,'owner');
INSERT INTO website_role(developer_id,website_id,role) VALUES(34,567,'editor');
INSERT INTO website_role(developer_id,website_id,role) VALUES(12,567,'admin');

INSERT INTO website_role(developer_id,website_id,role) VALUES(34,678,'owner');
INSERT INTO website_role(developer_id,website_id,role) VALUES(12,678,'editor');
INSERT INTO website_role(developer_id,website_id,role) VALUES(23,678,'admin');




INSERT INTO page VALUES(123,'Home','Landing page','2019-09-04','2019-10-09',123434,567);
INSERT INTO page VALUES(234,'About','Website description','2019-09-04','2019-10-09',234545,678);
INSERT INTO page VALUES(345,'Contact','Addresses, phones, and contact info','2019-09-04','2019-10-09',345656,345);
INSERT INTO page VALUES(456,'Preferences','Where users can configure their preferences','2019-09-04','2019-10-09',456776,456);
INSERT INTO page VALUES(567,'Profile','Users can configure their personal information','2019-09-04','2019-10-09',567878,567);



INSERT INTO page_role(developer_id,page_id,role) VALUES(12,123,'editor'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(23,123,'reviewer'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(34,123,'writer'); 

INSERT INTO page_role(developer_id,page_id,role) VALUES(23,234,'editor'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(34,234,'reviewer'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(12,234,'writer'); 

INSERT INTO page_role(developer_id,page_id,role) VALUES(34,345,'editor'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(12,345,'reviewer'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(23,345,'writer');  

INSERT INTO page_role(developer_id,page_id,role) VALUES(12,456,'editor'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(23,456,'reviewer'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(34,456,'writer'); 

INSERT INTO page_role(developer_id,page_id,role) VALUES(23,567,'editor'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(34,567,'reviewer'); 
INSERT INTO page_role(developer_id,page_id,role) VALUES(12,567,'writer'); 



INSERT INTO widget(id, dtype,`name`,`text`,`order`,width,height,youtube_url,page_id) 
		  VALUES (123,'heading','head123','Welcome',0,NULL,NULL,NULL,123);

INSERT INTO widget(id, dtype,`name`,`text`,`order`,width,height,youtube_url,page_id) 
		  VALUES (234,'html','post234','<p>Lorem</p>',0,NULL,NULL,NULL,234);
          
INSERT INTO widget(id, dtype,`name`,`text`,`order`,width,height,youtube_url,page_id) 
		  VALUES (345,'heading','head345','Hi',1,NULL,NULL,NULL,345);   
          
INSERT INTO widget(id, dtype,`name`,`text`,`order`,width,height,youtube_url,page_id) 
		  VALUES (456,'html','intro456','<h1>Hi</h1>',2,NULL,NULL,NULL,345);            

INSERT INTO widget(id, dtype,`name`,`text`,`order`,width,height,image_src,youtube_url,page_id) 
		  VALUES (567,'image','image345',NULL,3,50,100,'/img/567.png',NULL,345); 

INSERT INTO widget(id, dtype,`name`,`text`,`order`,width,height,image_src,youtube_url,page_id) 
		  VALUES (678,'youtube','video456',NULL,0,400,300,NULL,'https://youtu.be/h67VX51QXiQ',456); 
          