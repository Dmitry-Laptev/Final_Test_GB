package TestBack;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String token;
    private static String baseUrl;
    private static String username;
    private static String password;
    protected static RequestSpecification requestSpecificationPost;
    protected static ResponseSpecification responseSpecificationPost;
    protected static RequestSpecification requestSpecificationGetMy;
    protected static ResponseSpecification responseSpecificationGetMy;
    protected static RequestSpecification requestSpecificationGetNotMy;
    protected static ResponseSpecification responseSpecificationGetNotMy;




    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/myProperty.property");
        prop.load(configFile);
        token =  prop.getProperty("token");
        baseUrl= prop.getProperty("base_url");
        username =  prop.getProperty("username");
        password =  prop.getProperty("password");


        baseUrl = prop.getProperty("base_url");

        requestSpecificationPost = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded; charset-UTF-8")
                .log(LogDetail.HEADERS)
                .build();

        responseSpecificationPost = new ResponseSpecBuilder()
                .expectContentType("text/html")
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.HEADERS)
                .build();

        requestSpecificationGetMy = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded; charset-UTF-8")
                .addFormParam("sort", "createdAt")
                .addHeader("X-Auth-Token",token)
                .log(LogDetail.HEADERS)
                .build();

        responseSpecificationGetMy = new ResponseSpecBuilder()
                // .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .expectHeader("Server", "nginx/1.23.1")
                .log(LogDetail.HEADERS)
                //.expectBody()
                .build();

        requestSpecificationGetNotMy = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded; charset-UTF-8")
                .addFormParam("sort", "createdAt")
                .addFormParam("owner", "notMy")
                .addHeader("X-Auth-Token",token)
                .log(LogDetail.HEADERS)
                .build();

        responseSpecificationGetNotMy = new ResponseSpecBuilder()
                // .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.HEADERS)
                .build();

    }



    public static String getBaseUrl() {
        return baseUrl;
    }
    public RequestSpecification getRequestSpecificationPost(){
        return requestSpecificationPost;
    }
    public ResponseSpecification getResponseSpecificationPost(){
        return responseSpecificationPost;
    }
    public RequestSpecification getRequestSpecificationGetMy(){
        return requestSpecificationGetMy;
    }
    public ResponseSpecification getResponseSpecificationGetMy(){
        return responseSpecificationGetMy;
    }
    public RequestSpecification getRequestSpecificationGetNotMy(){
        return requestSpecificationGetNotMy;
    }
    public ResponseSpecification getResponseSpecificationGetNotMy(){
        return responseSpecificationGetNotMy;
    }

}
