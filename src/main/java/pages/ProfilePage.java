package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    //labels
    private final SelenideElement USER_NAME = $(By.cssSelector("#userName-value"));

    public ProfilePage checkUserName(String username) {
        USER_NAME.shouldHave(Condition.text(username));
        return this;
    }
}
