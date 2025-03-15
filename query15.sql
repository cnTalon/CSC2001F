select reportsTo as manager, count(*) as managing
from employees
group by reportsTo
having count(*) > 3;