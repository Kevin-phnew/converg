# Exapmle

## Step1 Build

```bash
mvn clean package
```



## Step2 Run java
```bash

java -jar -Ddb_name=testdb \
-Dschema=public \
-Dtable=company \
-Ddatabase=jdbc:postgresql://hadoop001:5432/testdb \
-Ddb_engine=postgres \
-Ddriver_jar=/Users/guguoyu/Documents/app/maven-repo/postgresql/postgresql/9.1-901-1.jdbc4/postgresql-9.1-901-1.jdbc4.jar \
-Ddriver_class=org.postgresql.Driver \
-DuserName=postgres \
-Dpasswd=123456 \
schemaExtractor-1.0-SNAPSHOT.jar

```



then input the schema output direction you want
```bash

/Users/guguoyu/Documents/boyan/converg/src/main/resources/outschema.txt

```

finally, you will get the result

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


