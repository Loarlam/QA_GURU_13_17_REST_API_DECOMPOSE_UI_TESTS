package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.attachments.AttachmentsForReport;
import guru.qa.config.CredentialsConfig;
import guru.qa.pages.PageOfCustomerInfo;
import guru.qa.pages.PageOfRegistrationForm;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
    PageOfRegistrationForm pageOfRegistrationForm = new PageOfRegistrationForm();
    PageOfCustomerInfo pageOfCustomerInfo = new PageOfCustomerInfo();
    DataForTheTest dataForTheTest;
    static CredentialsConfig credentialsConfig = ConfigFactory.create(CredentialsConfig.class);

    @BeforeEach
    void beforeAllTests() {
        SelenideLogger.addListener("Allure", new AllureSelenide());
        RestAssured.baseURI = "http://demowebshop.tricentis.com";

//        Configuration.remote = String.format("https://%s:%s@%s", credentialsConfig.login(), credentialsConfig.password(), credentialsConfig.remoteLink());
        Configuration.remote = String.format("https://%s:%s@%s", credentialsConfig.login(), credentialsConfig.password(), System.getProperty("remoteLink"));
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
        Configuration.browserPosition = "0x0";
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("version", "100");
        Configuration.browserSize = System.getProperty("resolution", "1920x1080");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void afterAllTests() {
        AttachmentsForReport.attachScreenshot();
        AttachmentsForReport.pageSource();
        AttachmentsForReport.browserConsoleLogs();
        AttachmentsForReport.addVideo();
    }
}