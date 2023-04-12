FROM openjdk:11

WORKDIR /app

COPY target/production-report-0.0.1.jar .

EXPOSE 8080

ENTRYPOINT ["java","-jar","production-report-0.0.1.jar"]
