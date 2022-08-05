package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import tests.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class LoginPage {

    @Step("Открываем страницу авторизации")
    public void openLoginPage() {
        open("http://demowebshop.tricentis.com/login");
    }

    @Step("Rest API авторизация")
    public Cookie authorize(TestData testData)
    {
        String authCookieName = "NOPCOMMERCE.AUTH";
        String email = (testData.isRegistration ? testData.email :testData.standardEmail);
        String password = (testData.isRegistration ? testData.password :testData.standardPassword);

        String authCookieValue = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .filter(withCustomTemplates())
                .formParam("Email", email)
                .formParam("Password", password)
                .when()
                .post("http://demowebshop.tricentis.com/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract()
                .cookie(authCookieName);
        return new Cookie(authCookieName, authCookieValue);
    }
    @Step("Web проверка авторизации")
    public void checkAuthorize(TestData testData)
    {
        $(".account").shouldHave(text(testData.isRegistration ? testData.email :testData.standardEmail));
    }
}
