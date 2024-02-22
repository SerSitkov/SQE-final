package Task3;

import io.opentelemetry.sdk.logs.data.Body;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

public class LoginUserTest {

    @Test
    public void testLogin() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String username = "TestUser";
        String password = "password";

        Map<String, String> queryParams = Map.ofEntries(
                Map.entry("username", username),
                Map.entry("password", password));
        given()
                .contentType(ContentType.JSON)
                .queryParams(queryParams)
                .when()
                .get("/user/login")
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"));
    }
}