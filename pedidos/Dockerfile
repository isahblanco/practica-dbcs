FROM openjdk:11
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/pedidos-0.0.1-SNAPSHOT.jar practica3dbcs.jar
EXPOSE 8082
ENTRYPOINT exec java $JAVA_OPTS -jar practica3dbcs.jar