

CREATE VIEW EMPLOYEE_VIEW AS
SELECT *
FROM employee;

DROP VIEW EMPLOYEE_VIEW;

SELECT * FROM EMPLOYEE_VIEW;

CREATE VIEW PAYROLL_VIEW AS
SELECT *
FROM payroll;

CREATE VIEW DEPARTMENT_VIEW AS
SELECT *
FROM department;

CREATE VIEW DEPENDANT_VIEW AS
SELECT *
FROM dependant;

CREATE VIEW FULLTIME_VIEW AS
SELECT *
FROM FULLTIME;

CREATE VIEW HR_VIEW AS
SELECT *
FROM HR;

CREATE VIEW LOCATIONS_VIEW AS
SELECT *
FROM locations;

CREATE VIEW LOGIN_VIEW AS
SELECT *
FROM login;

CREATE VIEW LOWER_LEVEL_EMPLOYEE_VIEW AS
SELECT *
FROM lower_level_employee;

CREATE VIEW MANAGER_VIEW AS
SELECT *
FROM manager;

CREATE VIEW PARTTIME_VIEW AS
SELECT *
FROM parttime;

CREATE VIEW PAYMENT_VIEW AS
SELECT *
FROM payment;

SELECT *
FROM employee;

SELECT E_name, 'has an employee ID of ', EMP_ID
FROM employee;

SELECT * 
FROM employee
WHERE E_Salary >= 20000
ORDER BY E_Salary DESC;

SELECT * 
FROM fulltime
WHERE
MONTHLYRATE < 10000
AND EMP_ID > 150;

SELECT dependant.birthdate FROM dependant
ORDER by birthdate desc;

SELECT E_name, sum(EMP_ID)
FROM employee
GROUP BY E_name;

SELECT DEP_NAME, COUNT(NumberOfEmployees)
FROM department
GROUP BY DEP_NAME;

SELECT * FROM EMPLOYEE_VIEW;

SELECT DISTINCT city FROM DEPENDANT;

SELECT COUNT(DISTINCT city) FROM DEPENDANT;

SELECT DISTINCT south_department FROM locations;

SELECT DISTINCT COUNT(south_department) FROM locations;

SELECT DISTINCT birthdate FROM dependant;

SELECT DISTINCT COUNT(birthdate) FROM dependant;

/* Inner join relatonship between employee id, payment id and their corresponding biweekly salary */
SELECT employee.EMP_ID, payment.PAY_ID, payment.BiWeeklySalary
FROM employee
INNER JOIN payment ON employee.pay_id_foreign=payment.pay_id;

/* Display employees names and birthday oldest to youngest */
SELECT employee.E_name, dependant.birthdate 
FROM employee
INNER JOIN dependant ON employee.EMP_ID=dependant.emp_id
ORDER BY dependant.birthdate asc;

/*Display the city of all the employee's who make over 100k */
SELECT  employee.E_name, employee.E_salary, dependant.city
FROM   employee
INNER JOIN dependant ON employee.EMP_ID=dependant.emp_id
WHERE employee.E_salary > 100000
ORDER BY employee.e_salary asc;

insert into payroll values (9, 3000000.69, 4000000.43, 'Cash', 'Payed  Late');
insert into payment values (33, 9, 450.75, 20.25, 0, 0, 0);
insert into login values ('kayrodgers1@gmail.com', 'karodgers1234', 'KRodgers5', 'What city were you born in?', 'Toronto');
insert into department values (99, 255, 'Cleaning Department', 'Rachel Davis');
insert into employee values (121, 33, 99, 'kayrodgers1@gmail.com','kaylie',25000.00,'2021-08-05');
insert into dependant values (121, '1984-06-20', 05, 'George Street', 'P3D P3W', 'Toronto', 'Canada');
insert into parttime values(121, 12.82, 'Intern');
insert into locations values(99, NULL,  '20 Eagle Ave', NULL, '12 Dundas St');

/* list all employees who are in the soft_dept in the location of 12 dundas st AND are full time */

SELECT EMP_ID, E_name
FROM employee 
WHERE EXISTS
(SELECT DEP_NAME
FROM department
WHERE DEP_NAME = 'Software Department');





