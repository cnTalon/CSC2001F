select customerNumber, city
from customers
where salesRepEmployeeNumber is null
order by city;