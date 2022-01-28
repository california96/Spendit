# Spendit

## Spendit is an expense tracking project that enables the user to keep track of their income, as well as expenses.

# Tools for the job

Java SDK 8

https://www.oracle.com/ph/java/technologies/javase/javase8-archive-downloads.html

Tomcat (9.0 or later. This project is on Servlet 3.0)

https://tomcat.apache.org/

MySQL Community Server

https://dev.mysql.com/downloads/mysql/

Node

https://nodejs.org/en/

# Setup tutorial

>Clone/Download the project

>Run the following commands inside the cloned repository:

```
cd web

npm install admin-lte@^3.0 --save
```


>Edit web.xml data accordingly. This includes schema url, image folder directories, etc

>When running the project with Tomcat (9.0 or later), begin with /createTables.action. This initializes the database tables as well as the predefined values

>To get audit trails, go to /downloadlogs.action

>Enjoy

# Future Patches in the Cards

>Admin authentication of audit download via web.xml

>Implement audit logging using Log4j 2.17

>Implementation of SSL

>Redesigning of email template for forgotten password requests
