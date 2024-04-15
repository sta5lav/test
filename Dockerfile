FROM amazoncorretto:17
COPY target/*.jar demo3-0.0.1-SNAPSHOT.jar
CMD java -jar demo3-0.0.1-SNAPSHOT.jar