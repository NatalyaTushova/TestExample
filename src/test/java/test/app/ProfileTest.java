package test.app;

import api.BookDataAPI;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

public class ProfileTest extends BaseTest{

    BookDataAPI bookDataAPI = new BookDataAPI();

    @Test
    public void checkBookSearch(){
        bookDataAPI.getIdOfBook();
        bookDataAPI.addBookToCollection(token, uidd);
        setCookies();

        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfile()
                .fillInSearchLine(bookDataAPI.getNameOfBook())
                .checkBookInCollection(bookDataAPI.getNameOfBook());
    }

    @Test
    public void checkBookIsDeleted(){
        bookDataAPI.getIdOfBook();
        bookDataAPI.addBookToCollection(token, uidd);
        setCookies();

        ProfilePage profilePage = new ProfilePage();
        profilePage.openProfile()
                .deleteBookFromCollection()
                .approveDeletingBook()
                .checkBookNotInCollection(bookDataAPI.getNameOfBook());

    }

}
