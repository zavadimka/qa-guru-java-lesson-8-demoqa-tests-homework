package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.IOException;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class RegistrationTests extends TestBase {

    @Test
    @DisplayName("Successful registration test with all input data")
    void successfulRegistrationFormFillingTestWithAllInputData() throws IOException {

        RegistrationPage registrationPage = new RegistrationPage();

        TestUser testUser = new TestUser();
        testUser.firstName = "Ivan";
        testUser.lastName = "Ivanov";
        testUser.email = "ivanov@ivanov.com";
        testUser.gender = "Male";
        testUser.mobileNumber = "0123456789";
        testUser.yearOfBirthday = "1985";
        testUser.monthOfBirthday = "June";
        testUser.dayOfBirthday = "02";
        testUser.subject = "Maths";
        testUser.hobby = "Sports";
        testUser.imageName = "testImage.png";
        testUser.imagePath = "testData/img/testImage.png";
        testUser.currentAddress = "Montenegro";
        testUser.state = "NCR";
        testUser.city = "Delhi";

        step("### Fill the form", () -> {
            registrationPage.openPage()
                    .checkEmptyRegistrationPage()
                    .setFirstName(testUser.firstName)
                    .setLastName(testUser.lastName)
                    .setEmail(testUser.email)
                    .setGender(testUser.gender)
                    .setMobileNumber(testUser.mobileNumber)
                    .setDateOfBirth(testUser.yearOfBirthday, testUser.monthOfBirthday, testUser.dayOfBirthday)
                    .setSubject(testUser.subject)
                    .setHobby(testUser.hobby)
                    .uploadImage(testUser.imagePath)
                    .setCurrentAddress(testUser.currentAddress)
                    .setState(testUser.state)
                    .setCity(testUser.city)
                    .scrollScreenDown()
                    .submitRegistration();
        });

        step("### Verify the registration form summary table", () -> {
            registrationPage.checkSummaryTableWithAllInputData(testUser);
        });

        step("### Check back to the empty registration form page", () -> {
            registrationPage.checkEmptyRegistrationPage();
        });
    }


    @Test
    @DisplayName("Successful registration test with minimal input data")
    void successfulRegistrationFormFillingTestWithMinInputData() throws IOException {

        RegistrationPage registrationPage = new RegistrationPage();

        TestUser testUser = new TestUser();
        testUser.firstName = "Ivan";
        testUser.lastName = "Ivanov";
        testUser.email = "ivanov";
        testUser.gender = "Male";
        testUser.mobileNumber = "0123456789";
        testUser.yearOfBirthday = "02";
        testUser.monthOfBirthday = "June";
        testUser.dayOfBirthday = "1985";
        testUser.subject = "Maths";
        testUser.hobby = "Sports";
        testUser.imageName = "testImage.png";
        testUser.imagePath = "testData/img/testImage.png";
        testUser.currentAddress = "Montenegro";
        testUser.state = "NCR";
        testUser.city = "Delhi";

        step("### Fill the form", () -> {
            registrationPage.openPage()
                    .checkEmptyRegistrationPage()
                    .setFirstName(testUser.firstName)
                    .setLastName(testUser.lastName)
                    .setGender(testUser.gender)
                    .setMobileNumber(testUser.mobileNumber)
                    .submitRegistration();
        });

        step("### Verify the registration form summary table", () -> {
            registrationPage.checkSummaryTableWithMinInputData(testUser);
        });

        step("### Check back to the empty registration form page", () -> {
            registrationPage.checkEmptyRegistrationPage();
        });
    }


    @Test
    @DisplayName("Negative registration test")
    void negativeRegistrationFormFillingTest() throws IOException {

        RegistrationPage registrationPage = new RegistrationPage();

        step("### Fill the form without required fields", () -> {
            registrationPage.openPage()
                    .submitRegistration();
        });

        step("### Check incomplete registration", () -> {
            registrationPage.checkEmptyRegistrationPage()
                    .checkRequiredFieldsWarnings();
        });
    }
}
