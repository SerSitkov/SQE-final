package Task3;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class DeletingPetTest {

    @Test
    public void testDeletePet() {

        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        String petId = "13";


        given()
                .contentType("application/json")
                .body(petId)
                .when()
                .delete("/pet/{petId}", petId)
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo(petId));
    }
}