package tests;

import com.github.javafaker.Faker;

public class TestData {

    //New User
    Faker faker = new Faker();
    String firstName =faker.name().firstName();
    String lastName =faker.name().lastName();
    public String email = faker.internet().emailAddress();
    public String password = faker.internet().password();
    public Boolean isRegistration = false;

    //Standard user
    public String standardEmail = "qweqwe@mail.com";
    public String standardPassword = "123456";

}
