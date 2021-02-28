# Top-Score-Ranking
A demo project shows how a springboot runs with restful API style, along with junit5 for unit and integration test.

SpringBoot 2.5
JDK 15
Junit5
Gradle 6

1. Please download code with url: https://github.com/Burderly001/Top-Score-Ranking.git, then use a terminal to enter the project folder.
2. Install mysql server on your computer, and create a new user with password along with a database as following, grant all rights to user from database you created, and put below to resource/application.properties file.
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/scores
    spring.datasource.username=user
    spring.datasource.password=Password!#
3. Once you finished below two steps, then you can run with gradlew command, below as a Mac laptop as an example.
  ./gradlew build
  ./gradlew bootrun 
  
4. Now you can use postman tool or curl command to do some test.


URL for verify:
1. Append a new record to database:
  http://127.0.0.1:8080/api/score/add
