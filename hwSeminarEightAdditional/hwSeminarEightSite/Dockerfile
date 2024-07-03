FROM alpine:3.18 AS builder
RUN apk update && apk --no-cache add openjdk17-jdk maven=3.9.2-r0
COPY . .
RUN mvn package

FROM alpine:3.18
RUN apk update && apk --no-cache add openjdk17-jre curl tzdata
ENV TZ=Europe/Moscow
COPY --from=builder /target/*.jar /app/app.jar

WORKDIR /app
RUN mkdir logs && chown 1000:1000 logs
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
