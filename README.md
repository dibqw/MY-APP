In this application is used Spring Boot, Spring Timeleaf and Spring security for the authentication and authorization.

This is a simple MVC app using local MySQL DB. There are 3 tables, one for the users roles, one for the users and one for the relations be twin the users and the roles. Each user can be created only with one role, "user". All passwords are saved and encrypted with bcript in the DB. Admin user can add a user to the admin users or delete an existing user.

