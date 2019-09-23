# CUBA example: multiple DB support

This example shows how to provide generate a CUBA application that support multiple database management systems.

The deployment scenario is the following:

One "war" file is generated at build time. it contains drivers to all DBMS variants. At deployment time, the Tomcat
server configures the information which DBMS is used and what are the connection settings to the database server.

## Example

```
[cuba-example-multiple-db-support] tree
.
├── cuba-app
│   ├── build
│   │   ├── distributions
│   ├── build.gradle
│   ├── etc
│   │   └── war-logback.xml
├── generate-war.sh
├── start-tomcat.sh
├── stop-tomcat.sh
├── target-tomcat
│   ├── bin
│   ├── conf
│   │   ├── Catalina
│   │   ├── app
│   │   ├── app-core
│   ├── lib
│   │   ├── hsqldb-2.4.1.jar
│   │   ├── mysql-connector-java-8.0.15.jar
│   │   └── postgresql-42.2.5.jar
│   ├── logs
│   │   ├── app.log
│   │   ├── catalina.out
│   ├── temp
│   ├── webapps
│   │   ├── app
│   │   ├── app-core
│   │   ├── app-core.war
│   │   └── app.war
└── tomcat-logs.sh
```

0. ensure tomcat is stopped (`./stop-tomcat.sh`)
1. generate war files through `./generate-war.sh` which will generate & copy wars to the target tomcat (webapps directory)
2. change DBMS settings
2.1. change database server connection settings via context.xml: `target-tomcat/conf/Catalina/localhost/app-core.xml`
2.2. change DBMS type via local.app.properties: `target-tomcat/conf/app-core/local.app.properties`
3. start tomcat (`./start-tomcat.sh`)


## Example Screenshots


#### local.app.properties Postgres
![local-app-properties-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/local-app-properties-postgres.png)

#### Context.xml Postgres
![context-xml-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/context-xml-postgres.png)


#### UI Postgres
![ui-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/ui-postgres.png)


#### local.app.properties MySQL
![local-app-properties-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/local-app-properties-mysql.png)

#### Context.xml MySQL
![context-xml-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/context-xml-mysql.png)


#### UI MySQL
![ui-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/ui-mysql.png)

## Starting DBMS systems

#### PostgreSQL
`docker run --name postgres -d -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres postgres:alpine`

Studio settings:
* JDBC: `jdbc:postgres//localhost/postgres`
* user: `postgres`
* pass: `postgres`

#### MySQL
`docker run --name mysql -e MYSQL_ROOT_PASSWORD=mysql -e MYSQL_DATABASE=cuba -e MYSQL_USER=cuba -e MYSQL_PASS=cuba -p 3306:3306 -d mysql`

Studio settings:
* JDBC: `jdbc:mysql//localhost/cuba`
* connection params: `?useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true`
* user: `root`
* pass: `mysql`
