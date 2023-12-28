package tests;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationForm {

    // открыть страницу формы
    @Step("Open registration form page")
    void openPage(String url) {
        open(url);
    }

    // проверить, что открылась страница формы
    @Step("Check the registration form header")
    void checkHeader() {
        $(".practice-form-wrapper")
                .$("h5").shouldBe(visible).shouldHave(text("Student Registration Form"));
    }

    // // проверить, что поле First Name пустое
    @Step("Check empty First Name")
    void checkEmptyFirstName() {
        $("#firstName").shouldBe(empty);
    }

    // удалить рекламные баннеры и футер
    @Step("Remove banners and footer")
    void removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    // прикрепить файл
    @Step("Upload image")
    void uploadImage(String imagePath) {
        $("#uploadPicture").uploadFromClasspath(imagePath);
    }

    // прокрутить экран ниже
    @Step("Scroll screen down")
    void scrollScreenDown() {
        executeJavaScript("window.scrollTo(0, 1000);");
    }

    // подтвердить заполнение формы
    @Step("Submit registration")
    void submitRegistration() {
        $("#submit").click();
    }


    // ввести имя
    @Step("Set first name")
    void setFirstName(String firstName) {
        $("#firstName").setValue(firstName);
    }

    // ввести фамилию
    @Step("Set last name")
    void setLastName(String lastName) {
        $("#lastName").setValue(lastName);
    }

    // ввести e-mail
    @Step("Set email")
    void setEmail(String email) {
        $("#userEmail").setValue(email);
    }

    // выбрать пол
    @Step("Set gender")
    void setGender(String gender) {
        $("#genterWrapper").$(byText(gender)).click();
    }

    // ввести номер мобильного
    @Step("Set mobile number")
    void setMobileNumber(String mobileNumber) {
        $("#userNumber").setValue(mobileNumber);
    }

    // выбрать дату рождения
    @Step("Set date of birth")
    void setDateOfBirth(String yearOfBirthday, String monthOfBirthday, String dayOfBirthday) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").scrollTo().selectOptionByValue(yearOfBirthday);
        $(".react-datepicker__year-select").selectOptionByValue(yearOfBirthday);
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirthday);
        $(".react-datepicker__day--0" + dayOfBirthday + ":not(.react-datepicker__day--outside-month)").click();
    }

    // ввести subjects
    @Step("Set subject")
    void setSubject(String subject) {
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue(subject).pressEnter();
    }

    // выбрать хобби
    @Step("Set hobby")
    void setHobby(String hobby) {
        $("#hobbiesWrapper").$(byText(hobby)).click();
    }

    // ввести current address
    @Step("Set current address")
    void setCurrentAddress(String currentAddress) {
        $("#currentAddress").setValue(currentAddress);
    }

    // выбрать штат
    @Step("Set state")
    void setState(String state) {
        $("#state").click();
        $(byText(state)).click();
    }

    // выбрать город
    @Step("Set city")
    void setCity(String city) {
        $("#city").click();
        $(byText(city)).click();
    }


    // проверить, что появилась заполненная таблица
    @Step("Check summary table opening")
    void checkSummaryTableOpening() {
        $(".modal-content").shouldBe(visible);
        $("#example-modal-sizes-title-lg")
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));
    }

    // проверить корректность данных в заполненной таблице
    @Step("Summary table verification: {label}")
    void summaryTableVerification(String label, String value) {
        $(".table-responsive").$(byText(label))
                .parent().shouldHave(text(value));
    }

    @Step("Summary table verification: Student Name")
    void nameVerification(String firstName, String lastName) {
        $(".table-responsive").$(byText("Student Name"))
                .parent().shouldHave(text(firstName + " " + lastName));
    }

    @Step("Summary table verification: Date of Birth")
    void dateOfBirthVerification(String yearOfBirthday, String monthOfBirthday, String dayOfBirthday) {
        $(".table-responsive").$(byText("Date of Birth"))
                .parent().shouldHave(text(dayOfBirthday + " " + monthOfBirthday + "," + yearOfBirthday));
    }

    @Step("Summary table verification: State and City")
    void stateAndCityVerification(String state, String city) {
        $(".table-responsive").$(byText("State and City"))
                .parent().shouldHave(text(state + " " + city));
    }

    // закрыть заполненную таблицу
    @Step("Close summary table")
    void closeSummaryTable() {
        $("#closeLargeModal").click();
    }
}
