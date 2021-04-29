FROM java:8
COPY build/libs/EmployeeService-0.0.1.jar .
EXPOSE 80
CMD java -jar EmployeeService-0.0.1.jar