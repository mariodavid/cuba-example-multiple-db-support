# CUBA example: multiple DB support

This example shows how to provide generate a CUBA application that support multiple database management systems.

The deployment scenario is the following:

One "war" file is generated at build time. it contains drivers to all DBMS variants. At deployment time, the Tomcat
server configures the information which DBMS is used and what are the connection settings to the database server.

## Example

The following things have to be taken into consideration:

#### Database drivers
JDBC driver jars have to be added to the target tomcat. The jars go under `target-tomcat/lib/` directory in the example below.
Additionally the drivers are added as [dependencies of the core module](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/cuba-app/build.gradle#L91):

```
dependencies {
    compile(globalModule)
    compileOnly(servletApi)
    jdbc(postgres)
    testRuntime(postgres)
    jdbc(mysql)
    testRuntime(mysql)
}
```


#### buildWar gradle task

The `build.gradle` contains [buildWar](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/cuba-app/build.gradle#L210) task:

```
task buildWar(type: CubaWarBuilding) {
    includeJdbcDriver = true
    appProperties = ['cuba.automaticDatabaseUpdate': true]
    appHome = '../app_home'
    logbackConfigurationFile = 'etc/war-logback.xml'
    singleWar = false
}
```


### Structure of the Example

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


## Example Postgres

#### local.app.properties
![local-app-properties-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/local-app-properties-postgres.png)

#### context.xml
![context-xml-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/context-xml-postgres.png)


#### Running application
![ui-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/ui-postgres.png)


## Example MySQL

#### local.app.properties
![local-app-properties-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/local-app-properties-mysql.png)

#### context.xml
![context-xml-postgres.png](https://github.com/mariodavid/cuba-example-multiple-db-support/blob/master/img/context-xml-mysql.png)


#### Running application
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
