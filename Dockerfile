From openjdk:17
#RUN mvn clean install
copy target/AppointmentService-0.0.1-SNAPSHOT.jar AppointmentService-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","AppointmentService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8091
