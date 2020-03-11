[TOC]

# Exapmle

## How to build

first, you can run the following command to build the project
```bash
mvn clean package -Dmaven.test.skip=true
```



## Run the jar

second, you need to understand the meaning of each parameter

| parameter    | description                                                  |
| ------------ | ------------------------------------------------------------ |
| db_name      | the database name you created in postgreSQL                  |
| schema       | the schema name you created in postgreSQL                    |
| table        | the table name you created in postgreSQL                     |
| database     | the jdbc url, for example, "jdbc:postgresql://host:port/db_name" |
| db_engine    | the engine, just support postgreSQL, for example, "postgres" |
| driver_jar   | we have support in lib directory, you need change it your own directory |
| driver_class | the driver class , you do not need to change it              |
| userName     | the username in your postgreSQL, you need to change it       |
| passwd       | the password for you account in postgreSQL                   |

you can run the following command to quickly start test, you just need to change the <code>driver_jar</code> directory in your operation system


```bash

java -jar \
-Ddb_name=testdb \
-Dschema=public \
-Dtable=company \
-Ddatabase=jdbc:postgresql://114.67.96.244:5432/testdb \
-Ddb_engine=postgres \
-Ddriver_jar=/Users/guguoyu/Documents/app/maven-repo/postgresql/postgresql/9.1-901-1.jdbc4/postgresql-9.1-901-1.jdbc4.jar \
-Ddriver_class=org.postgresql.Driver \
-DuserName=postgres \
-Dpasswd=123456 \
schemaExtractor-1.0-SNAPSHOT.jar

```

 the console will print "Please provide path for ConvergDB schema output:"

then input the schema output direction you want, for example:
```bash

/Users/guguoyu/Documents/boyan/converg/src/main/resources/outschema.txt

```

## Get the result

finally, you will get the result after open the outschema.txt

```bash

domain "testdb" {
  schema "public" {
    relation "company" {
      relation_type = base
      attributes {
        attribute "id" {
          required = true
          data_type = int(4)
        }
        attribute "name" {
          required = true
          data_type = not supported
        }
        attribute "age" {
          required = true
          data_type = int(4)
        }
        attribute "address" {
          required = false
          data_type = char(50)
        }
        attribute "salary" {
          required = false
          data_type = float
        }
      }
    }
  }
}


```


