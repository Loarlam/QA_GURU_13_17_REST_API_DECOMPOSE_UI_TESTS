package guru.qa.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class PageOfCustomerInfo {
    @Step("Открываем страницу редактирования данных пользователя")
    public PageOfCustomerInfo openingWebsiteCustomerInfo() {
        open("/customer/info");
        return this;
    }

    @Step("Изменяем персональные данные пользователя")
    public PageOfCustomerInfo changingCustomerPersonalData(Cookie authCookie, String firsName, String lastName, String email ) {
    }
}
