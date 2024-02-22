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

        String requestBody = "{\n" +
                             "  \"id\": 1,\n" +
                             "  \"username\": \"TestUser\",\n" +
                             "  \"firstName\": \"John\",\n" +
                             "  \"lastName\": \"Doe\",\n" +
                             "  \"email\": \"testuser@example.com\",\n" +
                             "  \"password\": \"password\",\n" +
                             "  \"phone\": \"123456789\",\n" +
                             "  \"userStatus\": 0\n" +
                             "}";

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