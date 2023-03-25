package test.app;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import test.BaseAllTest;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static environment.Environment.SITE_URL;
import static io.restassured.RestAssured.given;

public class BaseTest extends BaseAllTest {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl= SITE_URL;

        //fix chrome bug with 403 error
        System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
    }

   @BeforeEach
    public void prepareEnvironment() {
        open("/login");
        createUser();
    }

   @AfterEach
    public void clearEnvironment() {
        getUserTokens();
        deleteUser();
    }

    public void getUserTokens(){
        getUserId();
        createToken();
        getUserToken();
    }

    public void setCookies(){
        getWebDriver().manage().addCookie(new Cookie("token",getUserToken()));
        getWebDriver().manage().addCookie(new Cookie("userID",getUserId()));
        getWebDriver().manage().addCookie(new Cookie("userName",userName));
        getWebDriver().manage().addCookie(new Cookie("expires",getExpiresTokenData()));
    }


    @AfterEach
    public void tearDown() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }


}
