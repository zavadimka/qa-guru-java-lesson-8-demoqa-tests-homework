package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testData.TestUser;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

public class TextBoxTestsWithFakerAndRandomUtils extends TestBase {

    @Test
    @DisplayName("Text Box form filling test")
    void textBoxFormFillingTest() throws IOException {

        TextBoxPage textBoxPage = new TextBoxPage();
        TestUser testUser = TestUser.getRandomTestUserData();

        step("### Fill the form", () -> {
            textBoxPage.openPage()
                    .checkEmptyTextBoxPage()
                    .setFullName(testUser.firstName, testUser.lastName)
                    .setEmail(testUser.email)
                    .setCurrentAddress(testUser.currentAddress)
                    .setPermanentAddress(testUser.permanentAddress)
                    .submitForm();
        });

        step("Check output", () -> {
            textBoxPage.checkOutput("Name:", testUser.firstName + " " + testUser.lastName)
                    .checkOutput("Email:", testUser.email)
                    .checkOutput("Current Address :", testUser.currentAddress)
                    .checkOutput("Permananet Address :", testUser.permanentAddress);
        });
    }
}
