# poc-spring

This is POC project for spring Request Mapping and async rest request

Please install the project using gradle build and run using bootRun



Test using following APIs

Please see file RequestMappingDemoController.java in package hello.

Urls to call


localhost:8080/greeting

localhost:8080/greeting?name=admin

localhost:8080/greeting?user

localhost:8080/greeting?user&name=sushil





Please see file AsyncDemoController.java in package hello

Async vanilla

WebClient internally calls to https://reqres.in 

localhost:8080/asyncrestdemo/


Async Delayed

WebClient internally calls  https://reqres.in with DELAYED Response.

localhost:8080/asyncrestdemo/delayed (Default delay 10 secs)

localhost:8080/asyncrestdemo/delayed?delay=8


Async Multiple

WebClient internally calls  https://reqres.in with combination of above two Calls. (Please note the order of response.)

localhost:8080/asyncrestdemo/multiple (Default delay 10 secs)

localhost:8080/asyncrestdemo/multiple?delay=8



