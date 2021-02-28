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
    Put request.
    http://127.0.0.1:8080/api/score/add?name=test001&score=99&time="2021-02-03 12:31:10"
  
2. Query by ID
    Get request
    http://127.0.0.1:8080/api/score/id/93
  
3. Delete by ID
    Delete request
    http://127.0.0.1:8080/api/score/id/67
    
4. Query by name
    Get request
    http://127.0.0.1:8080/api/scores/player/test001
    pagable
    http://127.0.0.1:8080/api/scores/player/test001?page=1&pagesize=10
    
5. Query by names
    Get request with start date
    http://127.0.0.1:8080/api/scores/players?players=test001,test002&startdate=2019-04-03 13:31:35
    
    Get request with end date
    http://127.0.0.1:8080/api/scores/players?players=test001,test002&enddate=2019-04-03 13:31:35
    
    Get request with start and end date
    http://127.0.0.1:8080/api/scores/players?players=test001,test002&startdate=2019-04-03 13:31:35&enddate=2021-04-13 13:00:00
    
    All above 3 get requests with pagable
    http://127.0.0.1:8080/api/scores/players?players=test001,test002&startdate=2019-04-03 13:31:35&page=1&pagesize=10
    http://127.0.0.1:8080/api/scores/players?players=test001,test002&enddate=2019-04-03 13:31:35&page=1&pagesize=10
    http://127.0.0.1:8080/api/scores/players?players=test001,test002&startdate=2019-04-03 13:31:35&enddate=2021-04-13 13:00:00&page=1&pagesize=10
    
6. History query for a player
    Get request to get all score list
    http://127.0.0.1:8080/api/history/player/test001
    
    Get request to get top score
    http://127.0.0.1:8080/api/history/top/player/test001
    
    Get request to get low score
    http://127.0.0.1:8080/api/history/low/player/test001
    
    Get request to get avg score
    http://127.0.0.1:8080/api/history/avg/player/test001
    
    
7. Error mapping

    code=400 parameter error;
    
    code=500 system error;
    
    code=4001 Score value shoule be 0-100;
    
    code=4002 Same player with the same input time was posted;
    
    code=4003 id was not found from database;
    
    code=4004 Query missing startdate or enddate;
    
    code=4005 startdate or enddate does not provide;
    
