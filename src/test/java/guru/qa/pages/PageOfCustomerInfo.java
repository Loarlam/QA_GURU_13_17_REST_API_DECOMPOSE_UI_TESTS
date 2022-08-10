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
    private SelenideElement email = $("#Email");

    @Step("API для изменения данных пользователя")
    public PageOfCustomerInfo changingAPI(String headerCookie, String authCookieName, String authCookieValue, String bodyCookie, String gender, String firstName,
                                          String lastName, String email) {
        given()
                .filter(withCustomTemplates())
                .cookie("__RequestVerificationToken", headerCookie)
                .cookie(authCookieName, authCookieValue)
                .formParam("__RequestVerificationToken", bodyCookie)
                .formParam("Gender", gender)
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
    public PageOfCustomerInfo checkingResultOfChangeData(String expectedText) {
        email.shouldHave(attribute("value", expectedText));
        return this;
    }
}
