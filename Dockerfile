FROM amazoncorretto:17-alpine-jdk
ADD target/users-crud.jar users-crud.jar
ENTRYPOINT ["java",  "-Dspring.profiles.active=prod", "-jar", "users-crud.jar"]
