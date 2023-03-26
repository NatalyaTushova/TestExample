package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static environment.Environment.API_URL;
import static io.restassured.RestAssured.given;

public class UserDataAPI {

    //parameters from requests
    private static Response createUserResponse, createTokenResponse;
    private static String uidd, token, expires;


    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(API_URL)
            .setContentType("application/json")
            .build();


    // create user
    public void createUser(String userName, String password) {
        createUserResponse = given().spec(requestSpec)
                .body("{\"userName\":\""+userName+
                        "\",\"password\":\""+password+"\"}")
                .post("/Account/v1/User")
                .then().statusCode(201).extract().response();
    }

    // create user token
    public void createToken(String userName, String password) {
        createTokenResponse = given().spec(requestSpec)
                .body("{\"userName\":\""+userName+
                        "\",\"password\":\""+password+"\"}")
                .post("/Account/v1/GenerateToken")
                .then().statusCode(200).extract().response();
    }

    // delete user
    public void deleteUser() {
        given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", uidd)
                .delete("/Account/v1/User/{UUID}")
                .then().statusCode(204);
    }

    //getters
    public String getUserToken() {
        token =  createTokenResponse.path("token");
        return token;
    }

    public String getExpiresTokenData(){
        expires =  createTokenResponse.path("expires");
        return expires;
    }

    //get UUID from user creation
    public String getUserId() {
        uidd =  createUserResponse.path("userID");
        return uidd;
    }
}
