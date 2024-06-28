# Getting Started

### Adjust application.properties
change the following properties with your jdbc url, username and password if you want to use any other DB then h2.
By default h2 is configured, so you just can run it without change anything. Dependency to Oracle is already added, for all other RDBMS, just add the dependency in the build.gradle file.

- spring.datasource.url=jdbc:h2:mem:testdb
- spring.datasource.driverClassName=org.h2.Driver
- spring.datasource.username=sa
- spring.datasource.password=password
- spring.jpa.database-platform=org.hibernate.dialect.H2Dialect



### Need to know before start
The applications creates or changes eventually the following tables if they exists automatically. If the datasource contains one of these tables, it might add columns and indexes if possible. Eventually the Application does not start if DDL cannot be executed.

- MY_BUSINESS_OBJECT
- MY_BUSINESS_OBJECT_WITH_NEW_FIELD
- MY_BUSINESS_OBJECT_WITH_VERSION

### Guides
Start InsertWithoutSelectApplication

### Additional Links
These additional references should also help you:

* (https://medium.com/predictly-on-tech/insert-without-select-using-jpa-a88e5db46e85)
