FROM openjdk:8-jdk

LABEL autor="Chris Wohlbrecht"

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} shoppinglist.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/shoppinglist.jar"]
