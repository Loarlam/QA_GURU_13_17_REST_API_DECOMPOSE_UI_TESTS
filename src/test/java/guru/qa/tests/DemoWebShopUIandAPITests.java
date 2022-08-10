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

        String authHeaderCookieForRegister = pageOfRegistrationForm.registeringAPI(
                credentialsConfig.cookieForHeaderRegistration(),
                credentialsConfig.cookieForBodyRegistration(),
                dataForTheTest.genderForRegistration,
                dataForTheTest.firstNameForRegistration,
                dataForTheTest.lastNameForRegistration,
                dataForTheTest.emailForRegistration,
                dataForTheTest.passwordForRegistration,
                dataForTheTest.passwordForRegistration);

        pageOfRegistrationForm.openingMinimalContentInSite()
                .openingWebsiteAfterRegisterPage("NOPCOMMERCE.AUTH", authHeaderCookieForRegister)
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration)
                .clickingOnLogoutButton();

        String authHeaderCookieForChangeData = pageOfRegistrationForm.authingAPI(
                dataForTheTest.emailForRegistration,
                dataForTheTest.passwordForRegistration);

        pageOfCustomerInfo.changingAPI(credentialsConfig.cookieForHeaderChangeData(), authHeaderCookieForChangeData,
                        credentialsConfig.cookieForBodyChangeData(), dataForTheTest.genderForRegistration,
                        dataForTheTest.firstNameForRegistration, dataForTheTest.lastNameForRegistration,
                        dataForTheTest.emailForEdit)
                .openingMinimalContentInSite()
                .openingWebsiteAfterChangeData("NOPCOMMERCE.AUTH", authHeaderCookieForChangeData)
                .checkingResultOfChangeData(dataForTheTest.emailForEdit);
    }
}