package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class AddingNewPetTest {

    @Test
    public void testAddNewPet() {
        String id = "8";
        String name = "doggie";

        // Set base URI
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

        // Define request body for adding a new pet
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
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        Assertions.assertEquals(Integer.valueOf(id), jsonPathEvaluator.get("id"));
        Assertions.assertEquals(name, jsonPathEvaluator.get("name"));
    }
}