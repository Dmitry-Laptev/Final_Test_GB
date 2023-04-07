package TestBack;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class LoginTest extends AbstractTest {
    @Test
    void postValid_Username() {
        given()
                .body("{\n"
                        + " \"username\": GB202303ebb5d1012,\n"
                        + " \"password\":c5f5e28ab5,\n"
                        + "}")
                .contentType(ContentType.JSON)

                .when()
                .post(getBaseUrl() + "login")
                .then()
                .statusCode(200)
                .body(containsString("token"))
                .contentType("text/html");
    }


    @Test
    void postInValid_Empty(){

        given()
                .spec(getRequestSpecificationPost())
                .queryParam("username", "")
                .queryParam("password", "")
                .when()
                .post(getBaseUrl() + "login")
                .then()
                .spec(getResponseSpecificationPost())
                //.body(containsString("token"))
                .statusCode(401);

    }
    @Test
    void postInValid_Username(){

        given()
                .spec(getRequestSpecificationPost())
                .queryParam("username", "jhgfv@#$bnjhf1234gfbch65")
                .queryParam("password", "sdfg465d4fg5")
                .when()
                .post(getBaseUrl() + "login")
                .then()
                .spec(getResponseSpecificationPost())
                //.body(containsString("token"))
                .statusCode(401);

    }


}
