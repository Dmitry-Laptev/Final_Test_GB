package TestBack;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class GET_notOwner extends AbstractTest {
    @Test
    void DESC_NotMyPosts(){
        given()
                .spec(getRequestSpecificationGetNotMy())
                .formParam("order", "DESC")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(getResponseSpecificationGetNotMy())
                .statusCode(200);

    }

    @Test
    void DESC_NotMyPosts_negative() {
        given()
                .spec(getRequestSpecificationGetNotMy())
                .formParam("order", "DESC")
                .formParam("page", -1)
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(getResponseSpecificationGetNotMy())
                .statusCode(400);

    }
    @Test
    void ASC_NotMyPosts(){
        given()
                .spec(getRequestSpecificationGetNotMy())
                .formParam("order", "ASC")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(getResponseSpecificationGetNotMy())
                .statusCode(200);

    }
    @Test
    void ASC_NotMyPostsWithoutAuth() {
        given()
                .spec(getRequestSpecificationGetNotMy())
                .header("X-Auth-Token", "")
                .formParam("order", "ASC")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .spec(getResponseSpecificationGetNotMy())
                .statusCode(401);
    }


    @Test
    void ALL_NotMyPosts(){
        given()
                .spec(getRequestSpecificationGetNotMy())
                .formParam("order", "ALL")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(getResponseSpecificationGetNotMy())
                .statusCode(200);

    }
    @Test
    void ALL_NotMyPostsWithoutAuth() {
        given()
                .spec(getRequestSpecificationGetNotMy())
                .header("X-Auth-Token", "")
                .formParam("order", "ALL")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .spec(getResponseSpecificationGetNotMy())
                .statusCode(401);
    }


}
