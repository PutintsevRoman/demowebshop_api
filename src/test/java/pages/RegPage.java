package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import tests.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class RegPage {

    @Step("Открываем страницу регистрации")
    public RegPage openPage() {
        open("http://demowebshop.tricentis.com/register");
        return this;
    }
    //id="gender-male"
    @Step("Выбриаем мужской пол")
    public RegPage setMaleGender() {
        $("#gender-male").click();
        return this;
    }
    @Step("Выбриаем женский пол")
    public RegPage setFemaleGender() {
        $("#gender-female").click();
        return this;
    }
    @Step("Простовляем значение имени")
    public RegPage setFirstName(String firstName) {
        $("#FirstName").setValue(firstName);
        return this;
    }
    @Step("Простовляем значение фамилии")
    public RegPage setLastName(String lastName) {
        $("#LastName").setValue(lastName);
        return this;
    }
    //Email
    @Step("Простовляем значение email")
    public RegPage setEmail(String email) {
        $("#Email").setValue(email);
        return this;
    }
    //Password
    //ConfirmPassword
    @Step("Проставляем значение паролей")
    public RegPage setPasswords(String password) {
        $("#Password").setValue(password);
        $("#ConfirmPassword").setValue(password);
        return this;
    }
    //register-button
    @Step("Нажимаем на кнопку")
    public RegPage registerButtonClick() {
        $("#register-button").click();
        return this;
    }
    //result
    @Step("Проверяем что регистрация закончена")
    public RegPage checkResult() {
        $(".result").shouldHave(Condition.text("Your registration completed\n"));
        return this;
    }

}
