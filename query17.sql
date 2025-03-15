select employeeNumber, city
from employees
join offices on employees.officeCode = offices.officeCode;