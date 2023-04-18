package test.api;

import api.BookDataAPI;
import api.UserDataAPI;
import data_provider.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected String userName, password, uidd, token;
    UserDataAPI userDataAPI = new UserDataAPI();
    UserData userData = new UserData();
    BookDataAPI bookDataAPI = new BookDataAPI();

    @BeforeEach
    public void setUp() {
        userName = userData.getUserName();
        password = userData.getPassword();

        userDataAPI.createUser(userName, password);
        uidd = userDataAPI.getUserId();
        userDataAPI.createToken(userName, password);
        token = userDataAPI.getUserToken();
    }

    @AfterEach
    public void tearDown() {
        userDataAPI.deleteUser();
    }
}
