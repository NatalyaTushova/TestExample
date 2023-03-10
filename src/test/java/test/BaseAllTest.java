package test;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static environment.Environment.API_URL;
import static io.restassured.RestAssured.given;

public class BaseAllTest {
    protected String firstName, lastName, userName, password;

    {
        generateData();
    }

    protected void generateData() {
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userName = faker.name().username();
        password = faker.regexify("[a-z][A-Z][1-9]{6}[!]");
    }

    //prepare requests
    JSONObject requestBody = new JSONObject()
        .put("userName", userName)
        .put("password", password);

    private  RequestSpecification postRequestSpec = new RequestSpecBuilder()
            .setBaseUri(API_URL)
            .setContentType("application/json")
            .setBody(requestBody.toString())
            .build();

    private  RequestSpecification delRequestSpec = new RequestSpecBuilder()
            .setBaseUri(API_URL)
            .setContentType("application/json")
            .build();

    //parameters from requests
    private static Response createUserResponse, createTokenResponse;
    private static String uidd, token;

    // create user
    protected void createUser() {

        createUserResponse = given().spec(postRequestSpec)
                .post("/Account/v1/User")
                .then().statusCode(201).extract().response();
    }

    //get UUID from user creation
    protected void getUserId() {

        uidd =  createUserResponse.path("userID");
    }

    // create user token
    protected void createToken() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("userName", userName);
        requestBody.put("password", password);


        createTokenResponse = given().spec(postRequestSpec)
                .post("/Account/v1/GenerateToken")
                .then().statusCode(200).extract().response();
    }

    //get user token from token creation
    protected void getUserToken() {

        token =  createTokenResponse.path("token");
    }


    // delete user
    protected void deleteUser() {
        given().spec(delRequestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", uidd)
                .delete("/Account/v1/User/{UUID}")
                .then().statusCode(204);
    }
}
