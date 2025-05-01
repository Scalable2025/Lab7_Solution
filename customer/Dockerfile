FROM openjdk:25-ea-4-jdk-oraclelinux9

WORKDIR /app

COPY target/ target/

ENTRYPOINT ["java","-jar","target/customer.jar"]