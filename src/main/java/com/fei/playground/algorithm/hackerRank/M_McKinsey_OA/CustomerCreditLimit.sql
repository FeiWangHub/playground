-- Customers Credit Limit
--
-- A company maintains the data of its customers in the CUSTOMER table. Write a
-- query to print the IDs and the NAMEs of the customers who are from
-- the USA and whose credit limit is greater than 100000, ordered by increasing ID
-- number.
--
-- Input Format: CUSTOMER TABLE
--
-- COL-NAME  | TYPE | DESC
-- ID | Int | inclusive in range [1,1000], PK
-- NAME | String | [1 to 100] characters inclusive
-- COUNTRY | String |
-- CREDITS | Int | credit limit of customer
--
-- Output Format
-- The result should print the IDs and the NAMEs of those customers who are
-- from the USA and whose credit limit is greater than 100000, in ascending /D
-- order and in the following format:
--
-- CUSTOMER.ID CUSTOMER. NAME

select ID, NAME
from CUSTOMER
where COUNTRY = 'USA'
  and CREDITS > 100000
order by ID ASC;


-- Bill Payment Query
-- A database of small retail store contains customer billing records stored in two tables, FAMILIES and BILLS.
-- Determine the maximum AMOUNT in the BILLS tables, then print the NAMEs of all customers who have a bill of
-- that amount. The names may be in any order
--
-- SCHEMA
-- FAMILY
-- Col | Type | Desc
-- NAME | String | name
-- BILL_ID | String | related to BILL.ID
--
-- BILLS
-- Col | Type | Desc
-- ID | String | id of the bill
-- AMOUNT | Int | amount
select distinct(f.name)
from family f
inner join bills b on b.id = f.bill_id
where b.amount = (select max(amount) max from bills);

select distinct(f.name)
from family f
inner join
(select * from bills where amount = (select max(amount) from bills)) b on b.id = f.bill_id;


-- Examination Data
# There is a database with exam scores. Write a query to print the names of the students who scored an even number
# marks. The names should be listed in uppercase, alphabetically ascending. The result should be in the following forr
# NAME MARKS
select upper(name), upper(marks)
from exam
where marks % 2 = 0
order by name asc;


-- Professor Names and Salaries
# A university maintains data on professors and departments in two tables: PROFESSOR and DEPARTMENT.
# print the NAME and SALARY for each professor who satisfies the following two requirements:
# • The professor does not work in the Arts and Humanities department.
# • The professor's salary is greater than the smallest salary of any professor in the Arts and Humanities departm
# The name must be printed before the salary, but row order does not matter.

select name, salary
from professor
where salary > (select min(salary) from professor)
and department_id != (select id from department where name='Arts');

-- Student Subject
SELECT A.ID, E.SUBJECT, COUNT(E.SUBJECT)
FROM student A
inner join examination E ON A.ID = E.STUDENTID
GROUP BY A.ID, E.SUBJECT;
