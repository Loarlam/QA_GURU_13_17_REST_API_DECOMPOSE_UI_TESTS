package guru.qa.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

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
    @Feature("Участие Jenkins в процессе регистрации на сайте (API + UI)")
    @Story("Jenkins тянет код автотеста процесса регистрации, при отработке которого формируется отчёт в Allure Report (API + UI)")
    @Description("Методика запуска процесса регистрации посредством подтягивания кода из github в Jenkins, " +
            "с выводом отчёта Allure, позволяет решить проблему привязки к локальной машине (API + UI)")
    @DisplayName("Регистрация на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации (API + UI)")
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
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration);
    }

    @Test
    @Tag("demoWebShop3")
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Участие Jenkins в процессе авторизации и смены данных пользователя на сайте (API + UI)")
    @Story("Jenkins тянет код автотеста процесса авторизации и смены данных пользователя из гита, " +
            "при отработке которого формируется отчёт в Allure Report (API + UI)")
    @Description("Методика запуска процесса авторизации и смены данных пользователя посредством подтягивания кода из github в Jenkins, " +
            "с выводом отчёта Allure, позволяет решить проблему привязки к локальной машине (API + UI)")
    @DisplayName("Авторизация и смена данных пользователя на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации (API + UI)")
    void authAndChangedAPIandIUI() {
        dataForTheTest = new DataForTheTest();

        String authHeaderCookieForChangeData = pageOfRegistrationForm.authingAPI(
                credentialsConfig.userEmail(),
                credentialsConfig.userPassword());

        pageOfCustomerInfo.openingMinimalContentInSite()
                .changingAPI(
                        "NOPCOMMERCE.AUTH",
                        authHeaderCookieForChangeData,
                        credentialsConfig.cookieForHeaderChangeData(),
                        credentialsConfig.cookieForBodyChangeData(),
                        dataForTheTest.firstNameForEdit,
                        dataForTheTest.lastNameForEdit,
                        credentialsConfig.userEmail())
                .openingWebsiteAfterChangeData("NOPCOMMERCE.AUTH", authHeaderCookieForChangeData)
                .checkingResultOfChangeData(dataForTheTest.firstNameForEdit, dataForTheTest.lastNameForEdit);
    }
}