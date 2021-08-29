# Spendit

Spendit is an expense tracking project that enables the user to keep track of their income, as well as expenses.

# Setup tutorial

>Ideally your environment is Java 8

>Clone/Download the project

>Download node if you haven't yet. This project requires npm.

>Run the following commands inside the cloned repository:

```
cd web

npm install admin-lte@^3.0 --save
```


>Edit web.xml data accordingly. This includes schema url, image folder directories, etc

>When running the project with Tomcat (ideally 7.0 or later), begin with /createTables.action. This initializes the database tables as well as the predefined values

>To get audit trails, go to /downloadlogs.action

>Enjoy
