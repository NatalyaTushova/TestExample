package test.app;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.BaseAllTest;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;
import static environment.Environment.SITE_URL;

public class BaseTest extends BaseAllTest {

    @Test
    public void openSite() {

      //  open(SITE_URL + "/books");
    }

 /*   @BeforeAll
    public static void setUp() {
        open(SITE_URL);
        Configuration.timeout = 15000;
        Configuration.baseUrl= SITE_URL;
    }
*/
    @BeforeEach
    public void prepareEnvironment() {
        createUser();
    }

    @AfterEach
    public void clearEnvironment() {
        deleteUser();
    }
/*
    @AfterEach
    public void tearDown() {
        clearBrowserLocalStorage();
        clearBrowserCookies();
        open("");
    }*/
}
