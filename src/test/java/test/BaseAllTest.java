package test;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
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

    private  RequestSpecification nonAuthRequestSpec = new RequestSpecBuilder()
            .setBaseUri(API_URL)
            .setContentType("application/json")
            .setBody(requestBody.toString())
            .build();

    private  RequestSpecification withAuthRequestSpec = new RequestSpecBuilder()
            .setBaseUri(API_URL)
            .setContentType("application/json")
            .build();

    //parameters from requests
    private static Response createUserResponse, createTokenResponse, bookListResponse;
    private static String uidd, token, expires, isbn, bookName;

    // create user
    protected void createUser() {

        createUserResponse = given().spec(nonAuthRequestSpec)
                .post("/Account/v1/User")
                .then().statusCode(201).extract().response();
    }

    //get UUID from user creation
    protected String getUserId() {
        uidd =  createUserResponse.path("userID");
        return uidd;
    }


    // create user token
    protected void createToken() {
        createTokenResponse = given().spec(nonAuthRequestSpec)
                .post("/Account/v1/GenerateToken")
                .then().statusCode(200).extract().response();
    }

    //get user token from token creation
    protected String getUserToken() {
        token =  createTokenResponse.path("token");
        return token;
    }

    protected String getExpiresTokenData(){
        expires =  createTokenResponse.path("expires");
        return expires;
    }

    // delete user
    protected void deleteUser() {
        given().spec(withAuthRequestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", uidd)
                .delete("/Account/v1/User/{UUID}")
                .then().statusCode(204);
    }

    private void getBookList(){
       bookListResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API_URL+"/BookStore/v1/Books")
                .then()
                .extract().response();
    }

    protected String getIdOfBook(){
        getBookList();
        isbn = bookListResponse.path("books.isbn[0]");
        return isbn;
    }

    protected String getNameOfBook(){
        getBookList();
        bookName = bookListResponse.path("books.title[0]");
        return bookName;
    }

    protected void addBookToCollection() {
        given().spec(withAuthRequestSpec)
                .header("Authorization", "Bearer " + token)
                .body("{\"userId\":\""+uidd+
                        "\",\"collectionOfIsbns\":[{\"isbn\":\""+isbn+"\"}]}")
                .post("BookStore/v1/Books")
                .then().statusCode(201);
    }
}
