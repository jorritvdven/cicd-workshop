### Dependencies
compile:
 - Spring boot 2.3.0.RELEASE
 - org.springframework.boot:spring-boot-starter-web
 - org.projectlombok:lombok:1.18.12

Test Dependencies:
 - org.springframework.boot:spring-boot-starter-test

### Java 11 users
When using Spring Boot, you automatically get some build plugins as transitive dependencies.
To let it work with Java 11, explicitly add the following build plugins to your pom:
- maven-surefire-plugin: 2.22.1
- maven-site-plugin: 3.7.1
