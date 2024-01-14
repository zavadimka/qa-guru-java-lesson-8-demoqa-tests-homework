package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.SummaryTableComponent;
import testData.TestUser;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    SummaryTableComponent summaryTableComponent = new SummaryTableComponent();

    private final SelenideElement uploadImageButton = $("#uploadPicture"),
            submitButton = $("#submit"),
            registrationPageHeader = $(".practice-form-wrapper").$("h5"),
            userForm = $("form"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            mobilePhoneInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbyInput = $("#hobbiesWrapper"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city");

    String url = "/automation-practice-form",
            registrationPageHeaderText = "Student Registration Form";

    @Step("Open registration form page")
    public RegistrationPage openPage() {
        open(url);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Check the empty registration page")
    public RegistrationPage checkEmptyRegistrationPage() {
        registrationPageHeader.shouldBe(visible).shouldHave(text(registrationPageHeaderText));
        firstNameInput.shouldBe(empty);

        return this;
    }

    @Step("Set first name")
    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);

        return this;
    }

    @Step("Set last name")
    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);

        return this;
    }

    @Step("Set email")
    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    @Step("Set gender")
    public RegistrationPage setGender(String gender) {
        genderInput.$(byText(gender)).click();

        return this;
    }

    @Step("Set mobile number")
    public RegistrationPage setMobileNumber(String mobileNumber) {
        mobilePhoneInput.setValue(mobileNumber);

        return this;
    }

    @Step("Set date of birth")
    public RegistrationPage setDateOfBirth(String yearOfBirthday, String monthOfBirthday, String dayOfBirthday) {
        calendarComponent.setDate(dateOfBirthInput, yearOfBirthday, monthOfBirthday, dayOfBirthday);

        return this;
    }

    @Step("Set subject")
    public RegistrationPage setSubject(String subject) {
        subjectInput.setValue(subject).pressEnter();

        return this;
    }

    @Step("Set hobby")
    public RegistrationPage setHobby(String hobby) {
        hobbyInput.$(byText(hobby)).click();

        return this;
    }

    @Step("Upload image")
    public RegistrationPage uploadImage(String imagePath) {
        uploadImageButton.uploadFromClasspath(imagePath);

        return this;
    }

    @Step("Set current address")
    public RegistrationPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    @Step("Set state")
    public RegistrationPage setState(String state) {
        stateInput.click();
        $(byText(state)).click();

        return this;
    }

    @Step("Set city")
    public RegistrationPage setCity(String city) {
        cityInput.click();
        $(byText(city)).click();

        return this;
    }

    @Step("Scroll screen down")
    public RegistrationPage scrollScreenDown() {
        executeJavaScript("window.scrollTo(0, 1000);");

        return this;
    }

    @Step("Submit registration")
    public void submitRegistration() {
        submitButton.click();
    }

    @Step("Check summary table with all input data")
    public void checkSummaryTableWithAllInputData(TestUser testUser) {
        summaryTableComponent.checkSummaryTableOpening()
                .valueVerification("Student Name", testUser.lastName)
                .valueVerification("Student Email", testUser.email)
                .valueVerification("Gender", testUser.gender)
                .valueVerification("Mobile", testUser.mobileNumber)
                .valueVerification("Date of Birth", testUser.dayOfBirthday + " " + testUser.monthOfBirthday + "," + testUser.yearOfBirthday)
                .valueVerification("Subjects", testUser.subject)
                .valueVerification("Hobbies", testUser.hobby)
                .valueVerification("Picture", testUser.imageName)
                .valueVerification("Address", testUser.currentAddress)
                .valueVerification("State and City", testUser.state + " " + testUser.city)
                .closeSummaryTable();
    }

    @Step("Check summary table with minimal input data")
    public void checkSummaryTableWithMinInputData(TestUser testUser) {
        summaryTableComponent.checkSummaryTableOpening()
                .valueVerification("Student Name", testUser.lastName)
                .valueVerification("Gender", testUser.gender)
                .valueVerification("Mobile", testUser.mobileNumber)
                .closeSummaryTable();
    }

    @Step("Check required fields red warnings")
    public void checkRequiredFieldsWarnings() {
        userForm.shouldHave(cssClass("was-validated"));
        firstNameInput.shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        lastNameInput.shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        genderInput.$("label[for='gender-radio-1']").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        genderInput.$("label[for='gender-radio-2']").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        genderInput.$("label[for='gender-radio-3']").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        mobilePhoneInput.shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
    }
}