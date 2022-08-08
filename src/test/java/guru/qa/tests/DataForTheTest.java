package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.enumvalues.Genders;

import java.util.Locale;
import java.util.Random;

public class DataForTheTest {
    Faker fakerData = new Faker(Locale.FRANCE);
    Random random = new Random();

    int userGenderDigit = random.nextInt(Genders.values().length) + 1;

    final String DEFAULT_GENDER = Genders.values()[userGenderDigit - 1].getName(),
            DEFAULT_FIRST_NAME = fakerData.funnyName().name(),
            DEFAULT_LAST_NAME = fakerData.name().lastName(),
            DEFAULT_EMAIL = fakerData.internet().emailAddress(),
            DEFAULT_PASSWORD = fakerData.internet().password();

    String userFirstName = fakerData.funnyName().name(),
            userLastName = fakerData.name().lastName(),
            userEmail = fakerData.internet().safeEmailAddress(),
            userPassword = fakerData.internet().password(),
            userGenderName = Genders.values()[userGenderDigit - 1].getName(),
            resultOfRegistration = "Your registration completed";
}