package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    //labels
    private final SelenideElement USER_NAME = $(By.cssSelector("#userName-value"));
    private final SelenideElement SEARCH_FIELD = $(By.cssSelector("#searchBox"));
    private final SelenideElement DELETE_BOOK_ICON = $(By.xpath("//*[@role=\"rowgroup\"][1]//*[@title=\"Delete\"]"));
    private SelenideElement nameOfBook;
    private String bookInTableName;
    private final SelenideElement APPROVE_DELETING_BOOK_BUTTON = $(By.cssSelector("#closeSmallModal-ok"));

    public ProfilePage openProfile(){
        open("/profile");
        return this;
    }

    public ProfilePage checkUserName(String username) {
        USER_NAME.shouldHave(Condition.text(username));
        return this;
    }

    public ProfilePage fillInSearchLine(String bookName){
        SEARCH_FIELD.setValue(bookName);
        return this;
    }

    public ProfilePage checkBookInCollection(String bookName){
        nameOfBook = $(By.cssSelector("span[id=\"see-book-"+bookName+"\"]"));
        nameOfBook.shouldBe(Condition.exist);
        return this;
    }

    public ProfilePage checkBookNotInCollection(String bookName){
        nameOfBook = $(By.cssSelector("span[id=\"see-book-"+bookName+"\"]"));
        nameOfBook.shouldNotBe(Condition.exist);
        return this;
    }

    public ProfilePage deleteBookFromCollection(){
        DELETE_BOOK_ICON.click();
        return this;
    }

    public ProfilePage approveDeletingBook(){
        APPROVE_DELETING_BOOK_BUTTON.click();
        Selenide.switchTo().alert().accept();
        return this;
    }
}
