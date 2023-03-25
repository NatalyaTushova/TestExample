package test.app;

import org.junit.jupiter.api.Test;
import pages.ProfilePage;

public class ProfileTest extends BaseTest{

    @Test
    public void checkBookSearch(){
        getUserTokens();
        getIdOfBook();
        addBookToCollection();
        setCookies();

        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfile()
                .fillInSearchLine(getNameOfBook())
                .checkBookInCollection(getNameOfBook());
    }

    @Test
    public void checkBookIsDeleted(){
        getUserTokens();
        getIdOfBook();
        addBookToCollection();
        setCookies();

    }
}
