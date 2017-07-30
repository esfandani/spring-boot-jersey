Spring boot with Jersey: A Simple Reminder Service
===================

Summary
-------------
This is simple reminder app with crud/find operations. Database for this project is in-memory database h2. In order to use JAX-RS, Jersey configuration is added to the project.

Setup
-------------

In order to run the project, run the following commands:



 `git clone https://github.com/esfandani/spring-boot-jersey.git`
`mvn spring-boot:run`

 Now the project is listening on port 8004.
To see the inmemory database in action go to url http://localhost:8004/h2-console/ . For Driver class put `org.h2.Driver`  and for jdbc url put  `jdbc:h2:~/test`  and Username sa          

----------

Endpoints
-------------
There are endpoints for save/update/delete/find operations. Here are some samples:

Save:

    curl -X POST \
      http://localhost:8004/v1/reminders \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: ce618b9d-fb0d-f3f7-b1ac-e8ce99648388' \
      -d '{"id":null,"name":"name","description":"desc","dueDate":"2017-07-28T12:31:45.288","status":"DONE"}'
      
Update for existing object:


    curl -X PUT \
      http://localhost:8004/v1/reminders \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: cec60772-c53f-2c69-9678-cf40aad6b29a' \
      -d '{"id":1,"name":"update","description":"desc","dueDate":"2017-07-28T12:31:45.288","status":"DONE"}	'
      
Deleting an existing object:

    curl -X DELETE \
      http://localhost:8004/v1/reminders/1 \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: 9980acf0-41bf-d018-f3fc-ba100e593713'
      
Find by id of an object:

    curl -X GET \
      http://localhost:8004/v1/reminders/2 \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: cac285c7-b201-816d-bfd4-22c80bc7d2f9'
     
Find based on some parameters like from date, to date, status

    curl -X GET \
      'http://localhost:8004/v1/reminders?from=2017-07-28T12%3A31%3A45.287&to=2018-07-28T12%3A35%3A45.288&status=NOTDONE' \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: a5f8b6ac-f196-f2fa-a04a-23edcec4c644' \
      
If non of the "from","to" and "status" set, the endpoint returns everything in database.


    curl -X GET \
      'http://localhost:8004/v1/reminders' \
      -H 'cache-control: no-cache' \
      -H 'content-type: application/json' \
      -H 'postman-token: a5f8b6ac-f196-f2fa-a04a-23edcec4c644' \
      
      

