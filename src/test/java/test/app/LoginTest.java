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

        //check login is successful
        profilePage.checkUserName(userName);
    }

    @Test
    public void incorrectPasswordLogin()
    {
        LoginPage loginPage = new LoginPage();

        //login
        loginPage.fillInUserName(userName)
                .fillInPassword(password+"1")
                .loginButtonClick();

        //check login is unsuccessful
        loginPage.incorrectDataLabelCheck();
    }

    @Test
    public void incorrectUsernameLogin()
    {
        LoginPage loginPage = new LoginPage();

        //login
        loginPage.fillInUserName(userName+"1")
                .fillInPassword(password)
                .loginButtonClick();

        //check login is unsuccessful
        loginPage.incorrectDataLabelCheck();
    }
}
