FROM maven:3.9.10-eclipse-temurin-21

WORKDIR /opt/document
COPY pom.xml .
RUN mvn dependency:go-offline

COPY --chmod=544 entrypoint.sh .
COPY src /opt/document/src
RUN mvn clean install
RUN cp target/*.jar ./document.jar

ENTRYPOINT ["./entrypoint.sh"]