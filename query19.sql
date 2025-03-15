select customerNumber, creditLimit
from customers
where creditLimit < (select creditLimit from customers where customerNumber = 103);