package guru.qa.pages;

import io.qameta.allure.Step;

public class PageOfCustomerInfo {
    @Step("Клик по кнопке \"Register\"")
    public PageOfRegistrationForm clickingOnRegisterButton() {
        registerButton.click();
        return this;
    }
}
