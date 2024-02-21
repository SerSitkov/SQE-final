package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AddingNewPetTest {

    @Test
    public void testAddNewPet() {
        // Set base URI
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Define request body for adding a new pet
        String requestBody = """
                {
                  "id": 8,
                  "category": {
                    "id": 0,
                    "name": "string"
                  },
                  "name": "doggie",
                  "photoUrls": [
                    "string"
                  ],
                  "tags": [
                    {
                      "id": 0,
                      "name": "string"
                    }
                  ],
                  "status": "available"
                }""";

        // Send POST request to add a new pet
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/pet")
                .then()
                .statusCode(200)
                .body("name", equalTo("doggie")); // Assert the name of the added pet
    }
}
