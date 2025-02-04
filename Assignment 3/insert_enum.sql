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
INSERT INTO widget_type VALUES('question');
INSERT INTO widget_type VALUES('answer');

INSERT INTO module(name)
values('Project1');

INSERT INTO module(name)
values('Project2');

INSERT INTO module(name)
values('Assignment1');

INSERT INTO module(name)
values('Assignment2');

INSERT INTO module(name)
values('Quiz1');

INSERT INTO module(name)
values('Exam');

INSERT INTO module(name)
values('Logistics');
-- FINISHED INSERTING DATA INTO ENUMNS
