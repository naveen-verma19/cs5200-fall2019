CREATE DEFINER=`naveenverma088`@`%` PROCEDURE `endorsedUsersForWeek`(IN startWeek date, IN endWeek date)
BEGIN
DECLARE startDateOfWeek date;
DECLARE endDateOfWeek date;

SET startDateOfWeek = startWeek;
SET endDateOfWeek = endWeek;

SELECT 
    Sub2.postedBy AS 'userId',
    CONCAT(p.first_name, ' ', p.last_name) AS 'Full Name'
FROM
    (SELECT 
        COUNT(Sub1.AId) AS count, Sub1.postedBy
    FROM
        (SELECT 
        q.id AS QId, a.id AS AId, a.postedBy
    FROM
        question q
    JOIN answer a ON q.id = a.question
        AND a.correctAnswer = 1
        AND q.postedOn BETWEEN startDateOfWeek AND endDateOfWeek) AS Sub1
    GROUP BY Sub1.postedBy) AS Sub2
        JOIN
    person p ON p.id = Sub2.postedBy
ORDER BY Sub2.count DESC , p.first_name ASC
LIMIT 5;
END