select orderNumber, quantityOrdered as "sum(quantityOrdered)"
from orderdetails
where priceEach < 30;