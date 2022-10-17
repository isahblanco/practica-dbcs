FROM openjdk:11
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/users-0.0.1-SNAPSHOT.jar practica2dbcs.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar practica2dbcs.jar