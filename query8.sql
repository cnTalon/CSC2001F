select orderNumber,
       if(shippedDate is null,
          if(requiredDate is null, orderDate, requiredDate),
          shippedDate) as day
from orders