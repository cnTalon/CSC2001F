select customerNumber, offices.city
from customers
join employees on customers.salesRepEmployeeNumber = employees.employeeNumber
join offices on employees.officeCode = offices.officeCode
;