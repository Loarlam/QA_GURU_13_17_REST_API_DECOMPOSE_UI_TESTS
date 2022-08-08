package guru.qa.tests;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;


public class PersonalDataRegistrationFormTest extends BaseTest {
    DataForTheTest dataForTheTest;

    @Test
    @Tag("demoWebShop")
    @Owner("Loarlam")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Участие Jenkins в процессе регистрации на сайте")
    @Story("Jenkins тянет код автотеста регистрации на сайте из гита, при отработке которого формируется отчёт в Allure Report")
    @Description("Методика запуска процедуры регистрации на сайте посредством подтягивания кода из github в Jenkins, с выводом отчёта Allure, " +
            "позволяет решить проблему привязки к локальной машине")
    @Link(name = "DemoWebShop", url = "http://demowebshop.tricentis.com")
    @DisplayName("Регистрация на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации")
    void registeringOnSite() {
        dataForTheTest = new DataForTheTest();

        pageOfRegistrationForm.openingMinimalContentInSite()
                .openingWebsiteRegisterPage()
                .settingGender(dataForTheTest.GENDER_FOR_REGISTRATION)
                .settingFirstName(dataForTheTest.FIRST_NAME_FOR_REGISTRATION)
                .settingSurname(dataForTheTest.LAST_NAME_FOR_REGISTRATION)
                .settingEmail(dataForTheTest.EMAIL_FOR_REGISTRATION)
                .settingPassword(dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .settingConfirmPassword(dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .clickingOnRegisterButton()
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration);
    }

    @Test
    @Tag("demoWebShop")
    @Owner("Loarlam")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Участие Jenkins в процессе регистрации, авторизации и смены данных пользователя на сайте")
    @Story("Jenkins тянет код автотеста процесса регистрации, авторизации и смены данных пользователя из гита, " +
            "при отработке которого формируется отчёт в Allure Report")
    @Description("Методика запуска процесса регистрации, авторизации и смены данных пользователя посредством подтягивания кода из github в Jenkins, " +
            "с выводом отчёта Allure, позволяет решить проблему привязки к локальной машине")
    @Link(name = "DemoWebShop", url = "http://demowebshop.tricentis.com")
    @DisplayName("Регистрация, авторизация и смена данных пользователя на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации")
    void registrationAndAuthOnSiteWithCheckingResult() {
        dataForTheTest = new DataForTheTest();

        pageOfRegistrationForm.openingMinimalContentInSite()
                .openingWebsiteRegisterPage()
                .settingGender(dataForTheTest.GENDER_FOR_REGISTRATION)
                .settingFirstName(dataForTheTest.FIRST_NAME_FOR_REGISTRATION)
                .settingSurname(dataForTheTest.LAST_NAME_FOR_REGISTRATION)
                .settingEmail(dataForTheTest.EMAIL_FOR_REGISTRATION)
                .settingPassword(dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .settingConfirmPassword(dataForTheTest.PASSWORD_FOR_REGISTRATION)
                .clickingOnRegisterButton()
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration);
    }
}