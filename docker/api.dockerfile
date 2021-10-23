FROM openjdk:11
VOLUME /tmp
ADD ./jar/api.jar /opt/api/
EXPOSE 8080
WORKDIR /opt/api/
CMD ["java", "-jar", "api.jar"]