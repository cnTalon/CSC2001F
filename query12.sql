select round(((MSRP-buyprice)/buyprice)*100) as markup
from products
where productVendor = "Red Start Diecast";