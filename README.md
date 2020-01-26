# backend


## Etape d'instalation:

* Clonez l'application 

  `https://github.com/fredostar/backend.git`

* Créez la base de données Postgresql

  `CREATE DATABASE test
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.UTF-8'
    LC_CTYPE = 'en_US.UTF-8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;`



* Voici la requête SQL permettant de créer la table **Article** :

    `CREATE TABLE Post(
    id bigint,
    title character varying(200),
    body character varying(200),
    PRIMARY KEY(id)
    );`

* Changez les propriétés **username** et **password** Postgresql selon votre installation:

`spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.datasource.url= jdbc:postgresql://localhost:5432/db-test
spring.datasource.username=postgres
spring.datasource.password=postgres`



