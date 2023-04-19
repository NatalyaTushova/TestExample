package data_provider;

import com.github.javafaker.Faker;

public class UserData {

    private static String userName, password;

    {
        if (userName==null) {
            Faker faker = new Faker();
            userName = faker.name().username();
            password = faker.regexify("[a-z][A-Z][1-9]{6}[!]");
        }
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword() {
        return password;
    }

}
