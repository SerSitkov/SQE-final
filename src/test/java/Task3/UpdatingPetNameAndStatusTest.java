package Task3;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdatingPetNameAndStatusTest {

    @Test
    public void testUpdatePetNameAndStatus() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        int petId = 228;
        String name = "Garfield";
        String status = "Sold";


        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", name)
                .formParam("status", status)
                .when()
                .post("/pet/{petId}", petId)
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo(String.valueOf(petId)));

    }
}
