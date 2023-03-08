package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    //buttons
    private final SelenideElement LOGIN_BUTTON = $(By.cssSelector("#login"));

    //fields
    private final SelenideElement USERNAME_FIELD = $(By.cssSelector("#userName"));
    private final SelenideElement PASSWORD_FIELD = $(By.cssSelector("#password"));

    public LoginPage fillInLoginFields(String email, String password) {
        USERNAME_FIELD.setValue(email);
        PASSWORD_FIELD.setValue(password);
        return this;
    }
}
