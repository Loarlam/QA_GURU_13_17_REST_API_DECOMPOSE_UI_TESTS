package guru.qa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;


public class DemoWebShopTests extends BaseTest {
    @Test
    @Tag("demoWebShop1")
    @Owner("Loarlam")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Участие Jenkins в процессе регистрации на сайте (UI)")
    @Story("Jenkins тянет код автотеста регистрации на сайте из гита, при отработке которого формируется отчёт в Allure Report")
    @Description("Методика запуска UI процесса регистрации на сайте посредством подтягивания кода из github в Jenkins, с выводом отчёта Allure, " +
            "позволяет решить проблему привязки к локальной машине")
    @Link(name = "DemoWebShop", url = "http://demowebshop.tricentis.com")
    @DisplayName("Регистрация на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации (UI)")
    void registeringOnSite() {
        dataForTheTest = new DataForTheTest();

        pageOfRegistrationForm.openingMinimalContentInSite()
                .openingWebsiteRegisterPage()
                .settingFirstName(dataForTheTest.FIRST_NAME_FOR_REGISTRATION)
                .settingSurname(dataForTheTest.LAST_NAME_FOR_REGISTRATION)
                .settingEmail(dataForTheTest.EMAIL_FOR_REGISTRATION)
                .settingPassword(dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .settingConfirmPassword(dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .clickingOnRegisterButton()
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration)
                .clickingOnLogoutButton();
    }

    @Test
    @Tag("demoWebShop2")
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Участие Jenkins в процессе регистрации, авторизации и смены данных пользователя на сайте (API)")
    @Story("Jenkins тянет код автотеста процесса регистрации, авторизации и смены данных пользователя из гита, " +
            "при отработке которого формируется отчёт в Allure Report")
    @Description("Методика запуска процесса регистрации, авторизации и смены данных пользователя посредством подтягивания кода из github в Jenkins, " +
            "с выводом отчёта Allure, позволяет решить проблему привязки к локальной машине (API)")
    @Step("Прохождение регистрации на сайте")
    @DisplayName("Регистрация, авторизация и смена данных пользователя на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации (API)")
    void registrationAndAuthOnSiteWithCheckingResult() {
        dataForTheTest = new DataForTheTest();

        given()
                .filter(withCustomTemplates())
                .cookie("__RequestVerificationToken", credentialsConfig.cookieForHeaderRegistration())
                .formParam("__RequestVerificationToken", credentialsConfig.cookieForBodyRegistration())
                .formParam("Gender", dataForTheTest.GENDER_FOR_REGISTRATION)
                .formParam("FirstName", dataForTheTest.FIRST_NAME_FOR_REGISTRATION)
                .formParam("LastName", dataForTheTest.LAST_NAME_FOR_REGISTRATION)
                .formParam("Email", dataForTheTest.EMAIL_FOR_REGISTRATION)
                .formParam("Password", dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .formParam("ConfirmPassword", dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .formParam("register-button", dataForTheTest.BUTTON_FOR_REGISTRATION)
                .when()
                .post("/register")
                .then()
                .statusCode(302);

        pageOfRegistrationForm.openingWebsiteAfterRegisterPage();


    }
}