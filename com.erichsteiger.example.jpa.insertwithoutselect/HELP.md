# Getting Started

### Adjust application.properties
change the following properties with your jdbc url, username and password

- spring.datasource.url=
- spring.datasource.username=
- spring.datasource.password=

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
