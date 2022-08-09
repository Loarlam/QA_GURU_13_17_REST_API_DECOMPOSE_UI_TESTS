package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PageOfCustomerInfo {
    private SelenideElement email = $("#Email");

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
