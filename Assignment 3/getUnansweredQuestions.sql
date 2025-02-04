CREATE DEFINER=`naveenverma088`@`%` PROCEDURE `getUnansweredQuestions`()
BEGIN
SELECT Joined.module, Joined.text,MAX(Joined.ansCount) AS numOfAnswers
FROM
(SELECT q.id , COUNT(a.id) AS ansCount, q.text,q.module
FROM question q JOIN answer a
ON q.id=a.question AND q.id NOT IN (SELECT DISTINCT(question)  FROM answer WHERE correctAnswer=1)
GROUP BY q.id) Joined
GROUP BY Joined.module;
END