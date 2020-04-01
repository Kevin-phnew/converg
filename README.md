[TOC]

## How to build

First, you need to run the following command to build the project
```bash
mvn clean package -Dmaven.test.skip=true
```

## How to Use Help
```bash
java -jar schema-extractor-1.0.jar --help
```


## How to Run the jar

Meaning of all the parameters

| parameter               | description                                                  | required |
| ----------------------- | ------------------------------------------------------------ | -------- |
| db_name                 | the database name                                            | yes      |
| schema                  | the schema name                                              | yes      |
| table                   | the name of the table to be exported, if this parameter is not present, all the tables under the shema will be exported; you can use comma seperated tabble names or % as wildcard                     | no      |
| database                | the jdbc url, for example, "jdbc:postgresql://host:port/db_name" | yes      |
| db_engine               | the db engine, for example, "postgres" or "oracle" | yes      |
| driver_jar              | we put postgres jar in lib folder, you can point to the path where you store the driver jar file | no      |
| driver_class            | for postgres is "org.postgresql.Driver"              | no      |
| username                | the username used to connect to database, you can set  username in your environment if you do not give the parameter | no       |
| password                  | the password used to connect to database,you can set  password in your environment if you do not give the parameter | no       |
| output_file             | the path to store the exported schema files, default will be the current folder                            | no       |
| camelcase_to_underscore | y: change camelcase to underscore，default y | no       |
| spaces_to_underscore    | y: change spaces to underscore, otherwise will add backtick, default y | no       |

you can run the following command to quickly start test, you just need to change the <code>driver_jar</code> directory in your operation system


```bash

java -jar \
-Ddb_name=testdb \
-Dschema=public \
-Dtable=company \
-Doutput_file=/tmp/schema \
-Ddatabase=jdbc:postgresql://hostname:5432/testdb \
-Ddb_engine=postgres \
-Ddriver_jar=../lib/9.1-901-1.jdbc4/postgresql-9.1-901-1.jdbc4.jar \
-Ddriver_class=org.postgresql.Driver \
-Dusername=user \
-Dpassword=pwd \
schema-extractor-1.0.jar

```

## Get the result

a sample export schema file:

```bash
domain "zyilcfad" {
  schema "test" {
    relation "spaceincolumn" {
      relation_type = base
      attributes {
        attribute "n n" {
          required = false
          data_type = integer
        }
      }
    }
    relation "spaceincolumn" {
      relation_type = derived {
        source = "spaceincolumn"
      }
      attributes {
        attribute "n n" {
          required = false
          data_type = integer
          expression = "`n n`"
        }
      }
    }
  }
}


```


