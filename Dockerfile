FROM openjdk:8
EXPOSE 8080
ADD target/docker-blood-donation.jar docker-blood-donation.jar
ENTRYPOINT ["java","-jar","/docker-blood-donation.jar"]