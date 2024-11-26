# BH beca

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=antonbakalets_beca-demo&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=antonbakalets_beca-demo)

A small backend project.

### Execute

Prerequisites to build and to run the project are:

- Java 17 is installed
- Maven is installed 
- Node.js 20 is installed


To start the project run commands:

```shell
cd beca-frontend
npm install
npm run build
cd ..
mkdir beca-web/src/main/resources/static
cp -r beca-frontend/dist/beca-frontend/browser/* beca-web/src/main/resources/static/ -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml

mvn clean verify

mvn spring-boot:run
```

The project should be available at [localhost:8080](http://localhost:8080)

See also:

 - https://github.com/antonbakalets/beca-demo/actions - GitHub Actions
 - https://sonarcloud.io/project/overview?id=antonbakalets_beca-demo Sonar Cloud