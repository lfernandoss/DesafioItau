FROM maven:3.8.3-openjdk-17

ENV PROJECT_HOME /usr/src/transferencia
ENV JAR_NAME api-transferencia-0.0.1.jar

RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

COPY . .

RUN mvn clean install -DskipTests

RUN mv $PROJECT_HOME/target/$JAR_NAME $PROJECT_HOME/

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "api-transferencia-0.0.1.jar"]