package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    //buttons
    private final SelenideElement LOGIN_BUTTON = $(By.cssSelector("#login"));

    //fields
    private final SelenideElement USERNAME_FIELD = $(By.cssSelector("#userName"));
    private final SelenideElement PASSWORD_FIELD = $(By.cssSelector("#password"));
    private final SelenideElement INCORRECT_DATA_LABEL = $(By.xpath("//*[@id='name']"));

    public LoginPage fillInUserName(String userName) {
        USERNAME_FIELD.setValue(userName);
        return this;
    }

    public LoginPage fillInPassword(String password) {
        PASSWORD_FIELD.setValue(password);
        return this;
    }

    public LoginPage loginButtonClick() {
        LOGIN_BUTTON.click();
        return this;
    }

    public LoginPage incorrectDataLabelCheck(){
        INCORRECT_DATA_LABEL.shouldHave(Condition.text("Invalid username or password!"));
        return this;
    }
}
