## Project Description
* "Spring Boot Microservice" depicting the inter communication among Messgae, ClientCase and Channel objects with Open API 3.0 specification.
* The following technologies have been used for the development,
   * Java: 8 
   * Spring Tool Suite: 4
   * Spring Boot: 2.4.2 
   * Spring Framework: 5.3.0
   
## How to Run
* Using GitBash/GitGui
  * Clone the project `git clone -b main https://github.com/sangita-git/innso-test-project.git`
  * Build the aplication with mvn clean package
  * Then run the application using the command, java -jar target/InnsoTest-0.0.1-SNAPSHOT.jar
* Using Spring Tool Suite
  * File -> Import -> Git -> Projects from Git -> Clone URI -> URI:<Respective_URI> User:<GitHub_UserName> Password:<GitHub_Password> -> Follow the rest.
  * <Respective_Application> -> right-click -> Run as -> Spring Boot App
* The app will start running at http://localhost:8080.

## Defined Rest APIs
* POST /messages <br />  
  Example: http://localhost:8080/messages <br />
  Request Body: <br />
    { <br />
       "authorName":"Jérémie Durand", <br />
       "msgContent":"Hello, I have an issue with my new phone", <br />
       "channel":{"id":1} <br />
    } <br />
  Note: <br />
   * Message Id and Creation Time is auto generated.
   * Only Channel ID needs to be passed in the request.
   
* Post /clientcases <br />
  Example: http://localhost:8080/clientcases <br />
  Request Body: <br />
    { <br />
       "clientName":"Jérémie Durand", <br />
       "message":[{"id":101}] <br />
    } <br />
  Note: <br />
   * Channel Id and Creation Time is auto generated.
   * Only Message ID needs to be passed in the request.
   
 * Patch /clientcases/{clientcase-id}  <br /> 
   Content-Type: application/json-patch+json <br />
   Example: http://localhost:8080/clientcases/1001 <br />
   Request Body: <br />
   [ <br />
     [{"op":"add","path":"/message/0","value":{"id":102}}, {"op":"replace","path":"/reference","value":"KA-18B6"}] <br />
   ] <br />
   Note: <br />
    * JSON patch object has been used to modify a single ClientCase record.
    * USing JSON patch object more than one field can be updated at the same time just like the above example.
    * To add a message the "op (represents operation)" should be "add" and "path" should be "/message/0" that means the new message will be added at "0th" position.
    
 * Get /clientcases <br />  
   Example: http://localhost:8080/clientcases

## Default Database
* H2-in-memory database has been used. The context path is /h2-console.  
* Channel table with default value from "src/main/resources/data.sql" is loaded once the application starts running.

## Documentation End points
* To check OPEN API 3.0 documenation run http://localhost:8080/tes-api-docs
* To check Swagger UI run http://localhost:8080/test-swagger-ui.html
