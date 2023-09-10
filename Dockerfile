FROM openjdk:19

WORKDIR /app

COPY build/libs/*.jar ToDo.jar

CMD ["java", "-jar", "ToDo.jar"]

