package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RequestSpecUtil;

import static environment.Environment.API_URL;
import static io.restassured.RestAssured.given;

public class BookDataAPI {

    //parameters from requests
    private static Response bookListResponse;
    private static String isbn, bookName;
    private static RequestSpecUtil requestSpecUtil;

    //requests
    private void getBookList(){
        bookListResponse = given()
                .contentType(ContentType.JSON)
                .when()
                .get(API_URL+"/BookStore/v1/Books")
                .then()
                .extract().response();
    }

    public void addBookToCollection(String token, String uidd) {
        given(requestSpecUtil.AuthRequestSpecWithBody(token,
                "{\"userId\":\""+uidd+
                        "\",\"collectionOfIsbns\":[{\"isbn\":\""+isbn+"\"}]}"))
                .post("BookStore/v1/Books")
                .then().statusCode(201);
    }

    //getters
    public String getIdOfBook(){
        getBookList();
        isbn = bookListResponse.path("books.isbn[0]");
        return isbn;
    }

    public String getNameOfBook(){
        getBookList();
        bookName = bookListResponse.path("books.title[0]");
        return bookName;
    }
}
