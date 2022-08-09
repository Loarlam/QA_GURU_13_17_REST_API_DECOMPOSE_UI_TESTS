package guru.qa.tests;

import com.github.javafaker.Faker;
import guru.qa.enumvalues.Genders;

import java.util.Locale;
import java.util.Random;

public class DataForTheTest {
    Faker fakerData = new Faker(Locale.FRANCE);
    Random random = new Random();

    int userGenderDigit = random.nextInt(Genders.values().length) + 1;

    String genderForRegistration = Genders.values()[userGenderDigit - 1].getName(),
            firstNameForRegistration = fakerData.funnyName().name(),
            lastNameForRegistration = fakerData.name().lastName(),
            emailForRegistration = fakerData.internet().emailAddress(),
            passwordForRegistration = fakerData.internet().password(),
            buttonForRegistration = "Register",
            firstNameForEdit = fakerData.funnyName().name(),
            lastNameForEdit = fakerData.name().lastName(),
            emailForEdit = fakerData.internet().safeEmailAddress(),
            resultOfRegistration = "Your registration completed";
}