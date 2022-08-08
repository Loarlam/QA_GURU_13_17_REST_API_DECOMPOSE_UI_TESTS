package guru.qa.tests;

import static guru.qa.helpers.CustomApiListener.withCustomTemplates;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;


public class PersonalDataRegistrationFormTest extends BaseTest {
    @Test
    @Tag("demoWebShop")
    @Owner("Loarlam")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature("Удаленные запуски автотестов с помощью Jenkins")
    @Story("Jenkins тянет код из гита, при отработке которого формируется отчёт в Allure Report")
    @Description("Методика запуска автотестов посредством подтягивания кода из github в Jenkins, с выводом отчёта Allure, позволяет решить проблему привязки к локальной машине")
    @Link(name = "DemoWebShop", url = "http://demowebshop.tricentis.com")
    @DisplayName("Регистрация на сайте demowebshop.tricentis.com с последующей проверкой результата регистрации")
    void successfulTest() {
        pageOfRegistrationForm.openingMinimalContentInSite()
                .openingWebsiteRegisterPage()
                .settingGender(dataForTheTest.DEFAULT_GENDER)
                .settingFirstName(dataForTheTest.DEFAULT_FIRST_NAME)
                .settingSurname(dataForTheTest.DEFAULT_LAST_NAME)
                .settingEmail(dataForTheTest.DEFAULT_EMAIL)
                .settingPassword(dataForTheTest.DEFAULT_PASSWORD)
                .settingConfirmPassword(dataForTheTest.DEFAULT_PASSWORD)
                .clickingOnRegisterButton()
                .checkingResultOfRegistration(dataForTheTest.resultOfRegistration);
    }
}