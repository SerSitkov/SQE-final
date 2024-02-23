package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LogOutUserTest {

    @Test
    public void testLogOut() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/user/logout")
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo("ok"));
    }
}