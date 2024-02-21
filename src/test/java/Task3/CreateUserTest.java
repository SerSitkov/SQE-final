package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {


    @Test
    public void testCreateUser() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String requestBody = """
                {
                  "id": 1,
                  "username": "TestUser",
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "testuser@example.com",
                  "password": "password",
                  "phone": "123456789",
                  "userStatus": 0
                }""";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("1"));
    }
}
