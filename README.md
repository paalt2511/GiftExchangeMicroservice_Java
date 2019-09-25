# Gift Exchange Service

## General info
This project provides gift exchange services to family members.

## REST API using Spring Boot

## GET /members -- list the family members
## GET /members/{id} -- get a single family member
## POST /members -- add a family member
## PUT /members/{id} -- updates a family member
## DELETE /members/{id} -- delete a family member
## GET /gift_exchange -- lists members along with the member id they will be gifting to

## Technologies
Project is created with:
* Java 8
* Junit
* Maven
* Lombok - 1.18.10 ( This is used to remove boilerplate code for getter and setters of POJO)
* Spring Boot - 2.1.5.RELEASE

## Project Setup
## Usage
To assemble the project run:

```
$ mvn clean install
```

To run JUnit tests:
```
$ mvn test
```

## Steps Of Execution :

* For first hit to the REST Api we are assuming gift exchange happening for first year.
* And so the similar assumption is second hit corresponds to gifts exchange for second year.


