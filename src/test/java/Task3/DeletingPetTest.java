package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class DeletingPetTest {
    String id = "22";
    String name = "shmoggie";

    @Before
    public void createPet() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        String requestBody = String.format("{\n" +
                                           "  \"id\": %s,\n" +
                                           "  \"category\": {\n" +
                                           "    \"id\": 0,\n" +
                                           "    \"name\": \"string\"\n" +
                                           "  },\n" +
                                           "  \"name\": \"%s\",\n" +
                                           "  \"photoUrls\": [\n" +
                                           "    \"string\"\n" +
                                           "  ],\n" +
                                           "  \"tags\": [\n" +
                                           "    {\n" +
                                           "      \"id\": 0,\n" +
                                           "      \"name\": \"string\"\n" +
                                           "    }\n" +
                                           "  ],\n" +
                                           "  \"status\": \"available\"\n" +
                                           "}", id, name);

        // Send POST request to add a new pet
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/pet");
        Assertions.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void testDeletePet() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        given()
                .contentType("application/json")
                .body(id)
                .when()
                .delete("/pet/{petId}", id)
                .then()
                .statusCode(200)
                .body("type", equalTo("unknown"))
                .body("message", equalTo(id));
    }
}