package tests;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;

public class RegistrationForm {

    // открыть страницу формы
    void openPage(String url) {
        step("Open registration form page", () ->
                Selenide.open(url));
    }

    // проверить, что открылась страница формы
    void checkHeader() {
        step("Check the registration form header", () ->
                $(".practice-form-wrapper")
                        .$("h5").shouldBe(visible).shouldHave(text("Student Registration Form")));
    }

    // // проверить, что поле First Name пустое
    void checkEmptyFirstName() {
        step("Check empty First Name", () ->
                $("#firstName").shouldBe(empty));
    }

    // удалить рекламные баннеры и футер
    void removeBanners() {
        step("Remove banners and footer", () -> {
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });
    }

    // прикрепить файл
    void uploadImage(String imagePath) {
        step("Upload image", () ->
                $("#uploadPicture").uploadFromClasspath(imagePath));
    }

    // прокрутить экран ниже
    void scrollScreenDown() {
        step("Scroll screen down", () ->
                executeJavaScript("window.scrollTo(0, 1000);"));
    }

    // подтвердить заполнение формы
    void submitRegistration() {
        step("Submit registration", () ->
                $("#submit").click());
    }


    // ввести имя
    void setFirstName(String firstName) {
        step("Set first name", () ->
                $("#firstName").setValue(firstName));
    }

    // ввести фамилию
    void setLastName(String lastName) {
        step("Set last name", () ->
                $("#lastName").setValue(lastName));
    }

    // ввести e-mail
    void setEmail(String email) {
        step("Set email", () ->
                $("#userEmail").setValue(email));
    }

    // выбрать пол
    void setGender(String gender) {
        step("Set gender", () ->
                $("#genterWrapper").$(byText(gender)).click());
    }

    // ввести номер мобильного
    void setMobileNumber(String mobileNumber) {
        step("Set mobile number", () ->
                $("#userNumber").setValue(mobileNumber));
    }

    // выбрать дату рождения
    void setDateOfBirth(String yearOfBirthday, String monthOfBirthday, String dayOfBirthday) {
        step("Set date of birth", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").click();
            $(".react-datepicker__year-select").scrollTo().selectOptionByValue(yearOfBirthday);
            $(".react-datepicker__year-select").selectOptionByValue(yearOfBirthday);
            $(".react-datepicker__month-select").click();
            $(".react-datepicker__month-select").selectOption(monthOfBirthday);
            $(".react-datepicker__day--0" + dayOfBirthday + ":not(.react-datepicker__day--outside-month)").click();
        });
    }

    // ввести subjects
    void setSubject(String subject) {
        step("Set subject", () -> {
            $("#subjectsContainer").click();
            $("#subjectsInput").setValue(subject).pressEnter();
        });
    }

    // выбрать хобби
    void setHobby(String hobby) {
        step("Set hobby", () ->
                $("#hobbiesWrapper").$(byText(hobby)).click());
    }

    // ввести current address
    void setCurrentAddress(String currentAddress) {
        step("Set current address", () ->
                $("#currentAddress").setValue(currentAddress));
    }

    // выбрать штат
    void setState(String state) {
        step("Set state", () -> {
            $("#state").click();
            $(byText(state)).click();
        });
    }

    // выбрать город
    void setCity(String city) {
        step("Set city", () -> {
            $("#city").click();
            $(byText(city)).click();
        });
    }


    // проверить, что появилась заполненная таблица
    void checkSummaryTableOpening() {
        step("Check summary table opening", () -> {
            $(".modal-content").shouldBe(visible);
            $("#example-modal-sizes-title-lg")
                    .shouldBe(visible)
                    .shouldHave(text("Thanks for submitting the form"));
        });
    }


    // проверить корректность данных в заполненной таблице
    void summaryTableVerification(String label, String value) {
        step("Summary table verification:" + label, () -> {
            $(".table-responsive").$(byText(label))
                    .parent().shouldHave(text(value));
        });
    }

    void nameVerification(String firstName, String lastName) {
        step("Summary table verification: Student Name", () -> {
            $(".table-responsive").$(byText("Student Name"))
                    .parent().shouldHave(text(firstName + " " + lastName));
        });
    }

    void dateOfBirthVerification(String yearOfBirthday, String monthOfBirthday, String dayOfBirthday) {
        step("Summary table verification: Date of Birth", () -> {
            $(".table-responsive").$(byText("Date of Birth"))
                    .parent().shouldHave(text(dayOfBirthday + " " + monthOfBirthday + "," + yearOfBirthday));
        });
    }

    void stateAndCityVerification(String state, String city) {
        step("Summary table verification: State and City", () -> {
            $(".table-responsive").$(byText("State and City"))
                    .parent().shouldHave(text(state + " " + city));
        });
    }

    // закрыть заполненную таблицу
    void closeSummaryTable() {
        step("Close summary table", () ->
                $("#closeLargeModal").click());
    }
}
