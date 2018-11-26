please import mysql db schema from the file vendortaskdb_vendor_task.sql

MVC screens are as follows:
To add new vendors or edit/ delete existing vendors
http://localhost:8080/SpringMVCHibernate/vendors 


To add new tasks or edit/delete existing tasks	
http://localhost:8080/SpringMVCHibernate/tasks

Restful services are as follows:

1)To allocate tasks to vendors based on given json percentage
http://localhost:8080/SpringMVCHibernate/shareWorkLoad
method: POST

Request:
{
"vendorTaskRequest":
[{
"vendor" : "1",
"percentage" : "20"
},
{
"vendor" : "2",
"percentage" : "20"
},
{
"vendor" : "3",
"percentage" : "20"
},
{
"vendor" : "4",
"percentage" : "40"
}
]
}


2) To list the vendors with allocated tasks to each vendor 

http://localhost:8080/SpringMVCHibernate/showVendorWorkLoad
