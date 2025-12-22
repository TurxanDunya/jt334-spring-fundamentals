FROM gradle:8.14.3-jdk21-alpine

ARG SOME_KEY=123

WORKDIR /app/

#RUN apk add --no-cache gradle

COPY build/libs/jt334-spring-fundamentals-0.0.1-SNAPSHOT.jar ./

RUN echo $SOME_KEY

EXPOSE 8080

ENV TZ=Asia/Baku

CMD java -jar lesson32-0.0.1-SNAPSHOT.jar --spring.profiles.active=student
