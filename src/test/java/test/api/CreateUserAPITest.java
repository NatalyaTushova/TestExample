package test.api;

import org.junit.jupiter.api.Test;
import utils.RequestSpecUtil;

import static io.restassured.RestAssured.given;

public class CreateUserAPITest extends BaseTest{
    private static RequestSpecUtil requestSpecUtil;

    @Test
    public void alreadyRegisteredUser()
    {
        given(requestSpecUtil.NonAuthRequestSpecWithBody("{\"userName\":\""+userName+
                "\",\"password\":\""+password+"\"}"))
                .post("/Account/v1/User")
                .then().statusCode(406);
    }

    @Test
    public void checkJSONschema()
    {

    }
}
