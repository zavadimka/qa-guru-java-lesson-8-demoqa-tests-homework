package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class RegistrationFormTests extends TestBase {

    @Test
    @DisplayName("Successful registration test")
    void successfulRegistrationFormFillingTest() throws IOException {

        RegistrationForm registrationForm = new RegistrationForm();

        String pageAddress = "/automation-practice-form",
                firstName = "Ivan",
                lastName = "Ivanov",
                email = "ivanov@ivanov.com",
                gender = "Male",
                mobileNumber = "0123456789",
                dayOfBirthday = "02",
                monthOfBirthday = "June",
                yearOfBirthday = "1985",
                subject = "Maths",
                hobby = "Sports",
                imageName = "testImage.png",
                imagePath = "testData/img/testImage.png",
                currentAddress = "Montenegro",
                state = "NCR",
                city = "Delhi";


        // заполнение формы регистрации
        step("### Fill the form", () -> {

            // открыть страницу формы
            registrationForm.openPage(pageAddress);

            // проверить, что открылась страница формы
            registrationForm.checkHeader();

            // удалить рекламные баннеры и футер
            registrationForm.removeBanners();

            // ввести имя
            registrationForm.setFirstName(firstName);

            // ввести фамилию
            registrationForm.setLastName(lastName);

            // ввести e-mail
            registrationForm.setEmail(email);

            // выбрать пол
            registrationForm.setGender(gender);

            // ввести номер мобильного
            registrationForm.setMobileNumber(mobileNumber);

            // выбрать дату рождения
            registrationForm.setDateOfBirth(yearOfBirthday, monthOfBirthday, dayOfBirthday);

            // ввести subjects
            registrationForm.setSubject(subject);

            // выбрать хобби
            registrationForm.setHobby(hobby);

            // прикрепить файл
            registrationForm.uploadImage(imagePath);

            // ввести адрес
            registrationForm.setCurrentAddress(currentAddress);

            // выбрать штат
            registrationForm.setState(state);

            // выбрать город
            registrationForm.setCity(city);

            // прокрутить экран ниже
            registrationForm.scrollScreenDown();

            sleep(1000);

            // подтвердить заполнение формы
            registrationForm.submitRegistration();
        });


        // проверка корректности отражения
        step("### Verify the registration form summary table", () -> {

            // проверить, что появилась заполненная таблица
            registrationForm.checkSummaryTableOpening();

            // проверить корректность данных в заполненной таблице
            registrationForm.nameVerification(firstName, lastName);
            registrationForm.summaryTableVerification("Student Email", email);
            registrationForm.summaryTableVerification("Gender", gender);
            registrationForm.summaryTableVerification("Mobile", mobileNumber);
            registrationForm.dateOfBirthVerification(yearOfBirthday, monthOfBirthday, dayOfBirthday);
            registrationForm.summaryTableVerification("Subjects", subject);
            registrationForm.summaryTableVerification("Hobbies", hobby);
            registrationForm.summaryTableVerification("Picture", imageName);
            registrationForm.summaryTableVerification("Address", currentAddress);
            registrationForm.stateAndCityVerification(state, city);

            sleep(2000);

            // закрыть заполненную таблицу
            registrationForm.closeSummaryTable();
        });


        // проверить, что после закрытия выполняется переход на пустую страница формы регистрации
        step("### Check back to the empty registration form page", () -> {

            // проверить, что открылась страница формы
            registrationForm.checkHeader();

            // проверить, что поле First Name пустое
            registrationForm.checkEmptyFirstName();
        });
    }
}
