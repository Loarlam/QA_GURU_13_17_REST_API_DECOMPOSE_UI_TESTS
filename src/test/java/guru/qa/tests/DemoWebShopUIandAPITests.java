package guru.qa.tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;

public class DemoWebShopUIandAPITests extends BaseTest {
    @Test
    @Tag("demoWebShop1")
    @Owner("Loarlam")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Участие Jenkins в процессе регистрации на сайте (UI)")
    @Story("Jenkins тянет код автотеста регистрации на сайте из гита, при отработке которого формируется отчёт в Allure Report")
    @Description("Методика запуска UI процесса регистрации на сайте посредством подтягивания кода из github в Jenkins, с выводом отчёта Allure, " +
            "позволяет решить проблему привязки к локальной машине")
    @DisplayName("Регистрация на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации (UI)")
    void registeringUI() {
        dataForTheTest = new DataForTheTest();

        pageOfRegistrationForm.openingMinimalContentInSite()
                .openingWebsiteRegisterPage()
                .settingFirstName(dataForTheTest.firstNameForRegistration)
                .settingSurname(dataForTheTest.lastNameForRegistration)
                .settingEmail(dataForTheTest.emailForRegistration)
                .settingPassword(dataForTheTest.passwordForRegistration)
                .settingConfirmPassword(dataForTheTest.passwordForRegistration)
                .clickingOnRegisterButton()
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration);
    }

    @Test
    @Tag("demoWebShop2")
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Участие Jenkins в процессе регистрации, выхода из аккаунта, авторизации и смены данных пользователя на сайте (API)")
    @Story("Jenkins тянет код автотеста процесса регистрации, выхода из аккаунта, авторизации и смены данных пользователя из гита, " +
            "при отработке которого формируется отчёт в Allure Report")
    @Description("Методика запуска процесса регистрации, выхода из аккаунта, авторизации и смены данных пользователя посредством подтягивания кода из github в Jenkins, " +
            "с выводом отчёта Allure, позволяет решить проблему привязки к локальной машине (API)")
    @DisplayName("Регистрация, выход из аккаунта, авторизация и смена данных пользователя на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации (API)")
    void registeringAndChangedAPIandUI() {
        dataForTheTest = new DataForTheTest();

        String cookieValueForRegister = given()
                .filter(withCustomTemplates())
                .cookie("__RequestVerificationToken", credentialsConfig.cookieForHeaderRegistration())
                .formParam("__RequestVerificationToken", credentialsConfig.cookieForBodyRegistration())
                .formParam("Gender", dataForTheTest.genderForRegistration)
                .formParam("FirstName", dataForTheTest.firstNameForRegistration)
                .formParam("LastName", dataForTheTest.lastNameForRegistration)
                .formParam("Email", dataForTheTest.emailForRegistration)
                .formParam("Password", dataForTheTest.passwordForRegistration)
                .formParam("ConfirmPassword", dataForTheTest.passwordForRegistration)
                .formParam("register-button", dataForTheTest.buttonForRegistration)
                .when()
                .post("/register")
                .then()
                .statusCode(302)
                .extract()
                .cookie("NOPCOMMERCE.AUTH");

        pageOfRegistrationForm.openingMinimalContentInSite()
                .openingWebsiteAfterRegisterPage("NOPCOMMERCE.AUTH", cookieValueForRegister)
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration)
                .clickingOnLogoutButton();


        given()
                .filter(withCustomTemplates())
                .cookie("NOPCOMMERCE.AUTH", cookieValueForRegister)
                .cookie("__RequestVerificationToken", credentialsConfig.cookieForHeaderChangeData())
                .formParam("__RequestVerificationToken", credentialsConfig.cookieForBodyChangeData())
                .formParam("Email", dataForTheTest.emailForEdit)
                .formParam("save-info-button", dataForTheTest.buttonForChangeData)
                .when()
                .post("/customer/info")
                .then()
                .statusCode(302);

        pageOfCustomerInfo.openingMinimalContentInSite()
                .openingWebsiteAfterChangeData("NOPCOMMERCE.AUTH", cookieValueForRegister)
                .checkingResultOfChangeData(dataForTheTest.emailForEdit);
    }
}