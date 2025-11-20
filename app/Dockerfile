FROM gradle:8.14.3-jdk21-alpine

WORKDIR /app/

COPY build/libs/lesson32-0.0.1-SNAPSHOT.jar ./

CMD java -jar lesson32-0.0.1-SNAPSHOT.jar --spring.profiles.active=student
