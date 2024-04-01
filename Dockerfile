FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/account-management-service-0.0.1-SNAPSHOT.jar /app/account-management-service.jar

EXPOSE 8091

CMD ["java", "-jar", "account-management-service.jar"]