#Base image
FROM openjdk:17

#Add application jar file in the container
ADD target/employee_assignment_image.jar employee_assignment_image.jar

ENTRYPOINT ["java", "-jar", "employee_assignment_image.jar"]

#Run
#docker build -t <imagename> .
#to build the docker image