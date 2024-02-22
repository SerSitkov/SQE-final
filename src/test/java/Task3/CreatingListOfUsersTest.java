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

        String requestBody = "[\n" +
                             "  {\n" +
                             "    \"id\": 1,\n" +
                             "    \"username\": \"AM\",\n" +
                             "    \"firstName\": \"Arthur\",\n" +
                             "    \"lastName\": \"Morgan\",\n" +
                             "    \"email\": \"art@mor.com\",\n" +
                             "    \"password\": \"armor\",\n" +
                             "    \"phone\": \"123\",\n" +
                             "    \"userStatus\": 1\n" +
                             "  },\n" +
                             "  {\n" +
                             "    \"id\": 2,\n" +
                             "    \"username\": \"MB\",\n" +
                             "    \"firstName\": \"Mika\",\n" +
                             "    \"lastName\": \"Bell\",\n" +
                             "    \"email\": \"mike@bell.com\",\n" +
                             "    \"password\": \"mikbe\",\n" +
                             "    \"phone\": \"456\",\n" +
                             "    \"userStatus\": 2\n" +
                             "  },\n" +
                             "  {\n" +
                             "    \"id\": 3,\n" +
                             "    \"username\": \"DV\",\n" +
                             "    \"firstName\": \"Dutch\",\n" +
                             "    \"lastName\": \"Van Der Linde\",\n" +
                             "    \"email\": \"du@linde.com\",\n" +
                             "    \"password\": \"dutch\",\n" +
                             "    \"phone\": \"789\",\n" +
                             "    \"userStatus\": 3\n" +
                             "  }\n" +
                             "]\n";

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