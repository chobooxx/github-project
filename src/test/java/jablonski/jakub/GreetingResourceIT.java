package jablonski.jakub;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusIntegrationTest
class GreetingResourceIT extends GreetingResourceTest {
    @Test
    public void testGetRepositories_HappyPath() {
        String username = "quarkusio";

        given()
                .pathParam("username", username)
                .when()
                .get("/github/repos/{username}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", not(empty()))
                .body("size()", greaterThan(0))
                .body("[0].name", not(emptyOrNullString()))
                .body("[0].owner", equalTo(username))
                .body("[0].branches", not(empty()))
                .body("[0].branches[0].name", not(emptyOrNullString()))
                .body("[0].branches[0].lastCommitSha", not(emptyOrNullString()));
    }
}
