package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageOfRegistrationForm {
    private SelenideElement name = $("#FirstName"),
            lastName = $("#LastName"),
            email = $("#Email"),
            password = $("#Password"),
            confirmPassword = $("#ConfirmPassword"),
            registerButton = $("register-button"),
            resultValue = $(".result");

    @Step("Открываем минимальный элемент на странице для проверки работоспособности сайта")
    public PageOfRegistrationForm openingMinimalContentInSite() {
        open("/Themes/DefaultClean/Content/images/logo.png");
        return this;
    }

    @Step("Открываем страницу с полями для ввода данных и убираем рекламу")
    public PageOfRegistrationForm openingWebsiteRegisterPage() {
        open("/register");
        return this;
    }

    @Step("Выбираем \"{userGender}\" близ \"Gender\"")
    public PageOfRegistrationForm settingGender(String userGender) {
        $(String.format("#gender-%s", userGender)).sendKeys(" ");
        return this;
    }

    @Step("Вводим \"{userName}\" в поле \"First name\"")
    public PageOfRegistrationForm settingFirstName(String userName) {
        name.setValue(userName);
        return this;
    }

    @Step("Вводим \"{userLastName}\" в поле \"Last name\"")
    public PageOfRegistrationForm settingSurname(String userLastName) {
        lastName.setValue(userLastName);
        return this;
    }

    @Step("Вводим \"{userEmail}\" в поле \"Email\"")
    public PageOfRegistrationForm settingEmail(String userEmail) {
        email.setValue(userEmail);
        return this;
    }

    @Step("Вводим \"{userPassword}\" в поле \"Password\"")
    public PageOfRegistrationForm settingPassword(String userPassword) {
        password.setValue(userPassword);
        return this;
    }

    @Step("Вводим \"{confirmPassword}\" в поле \"Confirm password\"")
    public PageOfRegistrationForm settingConfirmPassword(String confirmPassword) {
        password.setValue(confirmPassword);
        return this;
    }

    @Step("Клик по кнопке \"Register\"")
    public PageOfRegistrationForm clickingOnRegisterButton() {
        registerButton.click();
        return this;
    }

    @Step("Проверяем наличие \"{expectedText}\" в результатах общего вывода")
    public PageOfRegistrationForm checkingResultOfRegistration(String expectedText) {
        resultValue.shouldHave(text(expectedText));
        return this;
    }
}