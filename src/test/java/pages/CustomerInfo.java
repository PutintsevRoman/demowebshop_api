package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class CustomerInfo {

    String RequestVerificationTokenName ="__RequestVerificationToken";
    String RequestVerificationTokenValue ="HQF2PR8Xvcsrulh3r1TAwKd41dY5dj_G18GpH2HxIiIPcpmRlNib6CkIS7n0" +
            "-HOpKgmOKhWcjxyPHSJ7UgxWmuXqhQFYGilznqLb2MjNG-w1";
    String RequestVerificationTokenParamValue ="GHZc_WDjY_tZVrEuKDrykx-5JHxrHRbvk5nps-J63ezW_B-" +
            "_DLyAkOhSXzeFvC3TyYfh6T6UtbEc3qaJfdoKQfMjBAJfIOZamf8tZrKJTgy0LPst8vPxXP-IU4KCtVm20";

    @Step("Открываем страницу редактирования данных пользователя")
    public void openCustomerInfoPage() {

        open("http://demowebshop.tricentis.com/customer/info");
    }
    @Step("Редактируем Имя и Фамилию через REST API")
    public void changeCustomerNames(Cookie authCookie,String firsName,String lastName, String email ){
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .filter(withCustomTemplates())
                .cookie(String.valueOf(authCookie))
                .cookie(RequestVerificationTokenName, RequestVerificationTokenValue)
                .formParam(RequestVerificationTokenName,RequestVerificationTokenParamValue )
                .formParam("FirstName", firsName)
                .formParam("LastName", lastName)
                .formParam("Email", email)
                .when()
                .post("http://demowebshop.tricentis.com/customer/info")
                .then()
                .statusCode(302);
    }
    @Step("Проверяем результат работы через Web")
    public void checkCustomerInfo(String firsName,String lastName, String email){
        $("#FirstName").shouldHave(attribute("value",firsName));
        $("#LastName").shouldHave(attribute("value",lastName));
        $("#Email").shouldHave(attribute("value",email));
    }
}
