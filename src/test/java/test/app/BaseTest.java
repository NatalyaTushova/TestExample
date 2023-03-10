package test.app;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import test.BaseAllTest;

import static com.codeborne.selenide.Selenide.open;
import static environment.Environment.SITE_URL;

public class BaseTest extends BaseAllTest {

    @BeforeAll
    public static void setUp() {
        open(SITE_URL + "/login");
        Configuration.timeout = 15000;
        Configuration.baseUrl= SITE_URL;
    }

    @BeforeEach
    public void prepareEnvironment() {
        createUser();
    }

    @AfterEach
    public void clearEnvironment() {
        getUserId();
        createToken();
        getUserToken();
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
