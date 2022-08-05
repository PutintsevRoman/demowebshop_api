package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class MainPage {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("http://demowebshop.tricentis.com");
    }
}
