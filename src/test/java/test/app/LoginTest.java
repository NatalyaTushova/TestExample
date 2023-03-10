package test.app;

import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;

public class LoginTest extends BaseTest{

    @Test
    public void successLogin()
    {
        LoginPage loginPage = new LoginPage();
        ProfilePage profilePage = new ProfilePage();

        //login
        loginPage.fillInUserName(userName)
                .fillInPassword(password)
                .loginButtonClick();

        //check login is success
        profilePage.checkUserName(userName);

    }
}
