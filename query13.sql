select officeCode, count(*) as size
from employees
group by officeCode;