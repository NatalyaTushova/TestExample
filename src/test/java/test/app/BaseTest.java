package test.app;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import api.UserDataAPI;
import data_provider.UserData;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static environment.Environment.SITE_URL;

public class BaseTest {

    {
        UserData userData = new UserData();
        userName = userData.getUserName();
        password = userData.getPassword();
    }


    protected String uidd, token;
    protected String userName, password;

    UserDataAPI userDataAPI = new UserDataAPI();

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl= SITE_URL;

        //fix chrome bug with 403 error
        //System.setProperty("chromeoptions.args", "--remote-allow-origins=*");
    }

   @BeforeEach
    public void prepareEnvironment() {
        open("/login");
        userDataAPI.createUser(userName, password);
        uidd = userDataAPI.getUserId();
        userDataAPI.createToken(userName, password);
        token = userDataAPI.getUserToken();

    }

    @AfterEach
    public void clearEnvironment() {

        userDataAPI.deleteUser();
        clearBrowserLocalStorage();
        clearBrowserCookies();
    }

    public void setCookies(){
        getWebDriver().manage().addCookie(new Cookie("userID",uidd));
        getWebDriver().manage().addCookie(new Cookie("userName",userName));
        getWebDriver().manage().addCookie(new Cookie("token",token));
        getWebDriver().manage().addCookie(new Cookie("expires",userDataAPI.getExpiresTokenData()));
    }


   // @AfterEach
   // public void tearDown() {    }


}
