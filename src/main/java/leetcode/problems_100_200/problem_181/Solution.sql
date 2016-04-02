select A.Name
from Employee A , Employee B
where A.ManagerId = B.Id and A.Salary > B.Salary;

select A.Name as Employee from Employee A join Employee B on A.ManagerId = B.Id and A.Salary > B.Salary;
