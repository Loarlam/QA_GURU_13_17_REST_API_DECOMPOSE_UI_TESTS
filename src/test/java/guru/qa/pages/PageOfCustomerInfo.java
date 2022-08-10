package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class PageOfCustomerInfo {
    private SelenideElement name = $("#FirstName"),
            lastName = $("#LastName"),
            email = $("#Email");

    @Step("API для изменения данных пользователя")
    public PageOfCustomerInfo changingAPI(
            String authCookieName,
            String authCookieValue,
            String headerCookie,
            String bodyCookie,
            String firstName,
            String lastName,
            String email) {
        given()
                .filter(withCustomTemplates())
                .cookie(authCookieName, authCookieValue)
                .cookie("__RequestVerificationToken", headerCookie)
                .formParam("__RequestVerificationToken", bodyCookie)
                .formParam("FirstName", firstName)
                .formParam("LastName", lastName)
                .formParam("Email", email)
                .formParam("save-info-button", "Save")
                .log().all()
                .when()
                .post("/customer/info")
                .then()
                .log().all()
                .statusCode(302);

        return this;
    }

    @Step("Открываем минимальный элемент на странице для проверки работоспособности сайта")
    public PageOfCustomerInfo openingMinimalContentInSite() {
        open("/Themes/DefaultClean/Content/images/top-menu-triangle.png");
        return this;
    }

    @Step("Открываем страницу для проверки смены данных")
    public PageOfCustomerInfo openingWebsiteAfterChangeData(String cookieName, String cookieValue) {
        getWebDriver().manage().addCookie(new Cookie(cookieName, cookieValue));
        open("/customer/info");
        return this;
    }

    @Step("Проверяем наличие \"{expectedText}\" в результатах общего вывода")
    public PageOfCustomerInfo checkingResultOfChangeData(String expectedName, String expectedLastName) {
        name.shouldHave(attribute("value", expectedName));
        lastName.shouldHave(attribute("value", expectedLastName));
        return this;
    }
}
