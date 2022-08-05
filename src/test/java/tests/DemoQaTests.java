package tests;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pages.CustomerInfo;
import pages.LoginPage;
import pages.MainPage;
import pages.RegPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class DemoQaTests extends TestBase {

    TestData testData = new TestData();
    RegPage regPage = new RegPage();
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    CustomerInfo customerInfo = new CustomerInfo();

    @Test
    @Order(1)
    @DisplayName("Registration UI Test")
    void registrationTest(){

        regPage.openPage()
                .setMaleGender()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setPasswords(testData.password)
                .registerButtonClick()
                .checkResult();
        testData.isRegistration = true;
    }

    @Test
    @DisplayName("Login API + UI Test")
    void loginTest(){

        Cookie authCookie = loginPage.authorize(testData);
        loginPage.openLoginPage();
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        mainPage.openMainPage();
        loginPage.checkAuthorize(testData);
    }

    @Test
    @DisplayName("Change Name and Last Name with API + UI Test")
    void changeCustomerNamesTest(){

        Cookie authCookie = loginPage.authorize(testData);

        String email = testData.standardEmail;
        String firsName =testData.firstName +"R";
        String lastName =testData.lastName +"R";

        customerInfo.changeCustomerNames(authCookie,firsName,lastName,email);

        mainPage.openMainPage();
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        customerInfo.openCustomerInfoPage();

        customerInfo.checkCustomerInfo(firsName,lastName,email);
    }



}
