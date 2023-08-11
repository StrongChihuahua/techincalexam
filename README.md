# Backend Technical Exam

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Maven
- MySQL (v5.7 or higher)


## Run Locally

Clone the project

```bash
  git clone git@github.com:StrongChihuahua/techincalexam.git
```

Go to the project directory

```bash
  cd techincalexam
```

Build the project

```bash
  mvn clean install
```

## Configuration

Open the `src/main/resources/application.properties` file and add/modify these lines:

```
spring.datasource.url=jdbc:mysql://<server>:<port>/<database_name>?
spring.datasource.username=<username>
spring.datasource.password=<username>
```

Replace all fields with `<field>` with the corresponding value of your DB.


## Run the application

```bash
  mvn spring-boot:run
```

Access the application

Open your web browser and navigate to http://localhost:8080 or http://localhost:8080/employee

For the GraphQL playground, please visit http://localhost:8080/graphiql



## Default Credentials
USER

`username: user`

`password: user`

ADMIN

`username: admin`

`password: admin`
