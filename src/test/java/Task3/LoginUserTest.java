package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginUserTest {

    @Test
    public void testLogin() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String username = "TestUser";
        String password = "password";

        given()
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"));
                //.body("message", equalTo("logged in user session: " + username));
    }
}
