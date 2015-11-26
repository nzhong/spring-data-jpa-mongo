README
======

This README describes the spring-data-jpa-mongo starter template.

What is this?
---------------------------------------------------------
This repo is a variation of this: https://github.com/nzhong/spring-data-jpa-h2

* The good thing about the H2 version is that it's embedded. no outside dependencies
* With minimal code change, the H2 version can support MySQL, PostgresSQL, etc.
* But it does not easily support List<X>, Map<A,B> in the Java class
* The MongoDB version (this repo) natively serialize java collections (List/Map) to JSON value


How to run?
---------------------------------------------------------
* (You will need a mongo instance already installed and running on the same machine)
* ```mvn clean package```
* ```java -jar target/app.jar```


Note: if you need to adapt the code to your own path, make sure that you change the line in SpringMongoConfig.java, <br />
```@ComponentScan("com.learn.sdjpa")```
