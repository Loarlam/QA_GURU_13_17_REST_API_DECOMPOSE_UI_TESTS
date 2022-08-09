package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.enumvalues.Genders;
import org.aeonbits.owner.ConfigFactory;
import org.json.JSONObject;

import java.util.Locale;
import java.util.Random;

public class DataForTheTest {
    Faker fakerData = new Faker(Locale.FRANCE);
    Random random = new Random();

    int userGenderDigit = random.nextInt(Genders.values().length) + 1;

    final String GENDER_FOR_REGISTRATION = Genders.values()[userGenderDigit - 1].getName(),
            FIRST_NAME_FOR_REGISTRATION = fakerData.funnyName().name(),
            LAST_NAME_FOR_REGISTRATION = fakerData.name().lastName(),
            EMAIL_FOR_REGISTRATION = fakerData.internet().emailAddress(),
            PASSWORD_FOR_REGISTRATION = fakerData.internet().password(),
            BUTTON_FOR_REGISTRATION = "Register",
            endPoint = "/register";

    String genderForEdit = Genders.values()[userGenderDigit - 1].getName(),
            firstNameForEdit = fakerData.funnyName().name(),
            lastNameForEdit = fakerData.name().lastName(),
            emailForEdit = fakerData.internet().safeEmailAddress(),
            resultOfRegistration = "Your registration completed",
            cookieNameForAuth = "NOPCOMMERCE.AUTH";

    JSONObject jsonBodyToAuth = new JSONObject()
            .put("FirstName", FIRST_NAME_FOR_REGISTRATION)
            .put("LastName", LAST_NAME_FOR_REGISTRATION)
            .put("Email", EMAIL_FOR_REGISTRATION)
            .put("Password", PASSWORD_FOR_REGISTRATION)
            .put("ConfirmPassword", PASSWORD_FOR_REGISTRATION);
}