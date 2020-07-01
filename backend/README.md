# Overview
This application is a simple web application with [Springboot](https://spring.io/guides/gs/spring-boot/)(version 1.5.8.RELEASE)
# Entity Relationship Diagram
![Image of Entity Relationship](https://serving.photos.photobox.com/803057381e1ca9a8a11e175755305ee0ce9644f80b22fd526b4391ca65bfe9f57cf6c6e9.jpg)
# Technical Stacks
- Spring-boot(1.5.8.RELEASE) - standalone rest-api application
- Spring-data-jpa - Implementing a data access layer of an application
- Mapstruct 1.3.1.Final - Mapping POJO Entity and Dto
- H2db - In-memory datable
- Liquibase - Migration tool
- FasterXML(Mapping POJO to Json or XML)
- Maven(3.5.3) - bundle application
- Java8
- Springfox-swagger2(2.6.1) - Document Api
- Junit4(Unit-test, Integration-test) - Testing strategy
# Deploy
Service need to start-up by following command:
## Demo Application:
> cd demo  
> mvn clean package  
> java -jar demo/target/demo.0.0.1.SNAPSHOT.jar
![](https://serving.photos.photobox.com/22242737870f099147ff4c94cebab1500d0a669a3253117e17e468c33e04c2af0ad5ea80.jpg)
# APIs
## Upload File :
 The api http://localhost:8080/upload allow user can upload their position with .gpx extenion file
> curl \
  -F "file=<path_to_your_file>" \
  http://localhost:8080/upload
  
## Tracks :
 The api http://localhost:8080/tracks  allow user get latest tracks(by default) 
 Noted: This api supported pagination pattern 
> curl \
 -X GET \
  http://localhost:8080/tracks
 ![](https://serving.photos.photobox.com/899689373d51a2dd9d6b25cc6c6e3ee7f2c8cd92bf9952241013af58228b98302c7f2493.jpg)
## Track Information
The api http://localhost:8080/tracks/{trackId} allow user get track information by trackId
> curl \
 -X GET \
  http://localhost:8080/tracks/50
![](https://serving.photos.photobox.com/55850830672101559efa79f0977e913827a84528b70ab96c7f617cd7114eaac6326464ba.jpg)
# Licensing
MIT
#
