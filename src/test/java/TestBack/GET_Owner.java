package TestBack;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class GET_Owner extends AbstractTest {

    @Test
    void DESC_MyPosts(){
        given()
                .spec(getRequestSpecificationGetMy())
                .formParam("order", "DESC")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(getResponseSpecificationGetMy())
                .statusCode(200);

    }
    @Test
    void DESC_MyPostsWithoutAuth() {
        given()
                .spec(getRequestSpecificationGetMy())
                .header("X-Auth-Token", "")
                .formParam("order", "DESC")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .spec(getResponseSpecificationGetMy())
                .statusCode(401);
    }
        @Test
        void DESC_MyPosts_negative() {
            given()
                    .spec(getRequestSpecificationGetMy())
                    .formParam("order", "DESC")
                    .formParam("page", -1)
                    .when()
                    .get(getBaseUrl()+"api/posts")
                    .then()
                    .spec(getResponseSpecificationGetMy())
                    .statusCode(400);

        }

    @Test
    void ASC_MyPosts(){
        given()
                .spec(getRequestSpecificationGetMy())
                .formParam("order", "ASC")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(getResponseSpecificationGetMy())
                .statusCode(200);

    }
    @Test
    void ASC_MyPostsWithoutAuth() {
        given()
                .spec(getRequestSpecificationGetMy())
                .header("X-Auth-Token", "")
                .formParam("order", "ASC")
                .formParam("page", 1)
                .when()
                .get(getBaseUrl() + "api/posts")
                .then()
                .spec(getResponseSpecificationGetMy())
                .statusCode(401);
    }
    @Test
    void ASC_MyPosts_negative() {
        given()
                .spec(getRequestSpecificationGetMy())
                .formParam("order", "ASC")
                .formParam("page", -1)
                .when()
                .get(getBaseUrl()+"api/posts")
                .then()
                .spec(getResponseSpecificationGetMy())
                .statusCode(400);

    }




}
