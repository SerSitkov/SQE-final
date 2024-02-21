package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreatingListOfUsersTest {

    @Test
    public void testListOfUsers() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String requestBody = """
                [
                  {
                    "id": 1,
                    "username": "AM",
                    "firstName": "Arthur",
                    "lastName": "Morgan",
                    "email": "art@mor.com",
                    "password": "armor",
                    "phone": "123",
                    "userStatus": 1
                  },
                  {
                    "id": 2,
                    "username": "MB",
                    "firstName": "Mika",
                    "lastName": "Bell",
                    "email": "mike@bell.com",
                    "password": "mikbe",
                    "phone": "456",
                    "userStatus": 2
                  },
                  {
                    "id": 3,
                    "username": "DV",
                    "firstName": "Dutch",
                    "lastName": "Van Der Linde",
                    "email": "du@linde.com",
                    "password": "dutch",
                    "phone": "789",
                    "userStatus": 3
                  }
                ]
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user/createWithList")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("ok"));
    }
}
