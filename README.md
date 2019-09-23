# CUBA example: multiple DB support




## Starting different DBMS

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
