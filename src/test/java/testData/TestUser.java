package testData;

import com.github.javafaker.Faker;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import utils.RandomUtils;

import java.io.FileReader;
import java.io.IOException;

public class TestUser {
    public String firstName,
            lastName,
            email,
            gender,
            mobileNumber,
            dayOfBirthday,
            monthOfBirthday,
            yearOfBirthday,
            subject,
            hobby,
            imageName,
            imagePath,
            currentAddress,
            permanentAddress,
            state,
            city;

    String[] genders = {
            "Male",
            "Female",
            "Other"
    };

    String[] subjects = {
            "Accounting",
            "Arts",
            "Biology",
            "Chemistry",
            "Civics",
            "Commerce",
            "Computer Science",
            "Economics",
            "English",
            "Hindi",
            "History",
            "Maths",
            "Physics",
            "Social Studies"
    };

    String[] hobbies = {
            "Music",
            "Reading",
            "Sports"
    };

    String[] states = {
            "NCR",
            "Uttar Pradesh",
            "Haryana",
            "Rajasthan"
    };

    public static TestUser getTestUserDataFromCsv() {
        TestUser testUser = new TestUser();

        try (CSVReader reader = new CSVReader(new FileReader("src/test/resources/testUser.csv"))) {
            String[] testUserData = reader.readNext();

            testUser.firstName = testUserData[0];
            testUser.lastName = testUserData[1];
            testUser.email = testUserData[2];
            testUser.gender = testUserData[3];
            testUser.mobileNumber = testUserData[4];
            testUser.dayOfBirthday = testUserData[5];
            testUser.monthOfBirthday = testUserData[6];
            testUser.yearOfBirthday = testUserData[7];
            testUser.subject = testUserData[8];
            testUser.hobby = testUserData[9];
            testUser.imageName = testUserData[10];
            testUser.imagePath = testUserData[11];
            testUser.currentAddress = testUserData[12];
            testUser.permanentAddress = testUserData[13];
            testUser.state = testUserData[14];
            testUser.city = testUserData[15];

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return testUser;
    }

    public static TestUser getRandomTestUserData() {
        Faker faker = new Faker();
        TestUser testUser = new TestUser();
        TestDate testDate = new TestDate();

        testUser.firstName = faker.name().firstName();
        testUser.lastName = faker.name().lastName();
        testUser.email = RandomUtils.getRandomEmail();
        testUser.gender = faker.options().option(testUser.genders);
        testUser.mobileNumber = faker.number().digits(10);
        testUser.dayOfBirthday = testDate.day;
        testUser.monthOfBirthday = testDate.month;
        testUser.yearOfBirthday = testDate.year;
        testUser.subject = faker.options().option(testUser.subjects);
        testUser.hobby = faker.options().option(testUser.hobbies);
        testUser.imageName = "testImage.png";
        testUser.imagePath = "img/testImage.png";
        testUser.currentAddress = faker.address().streetAddress();
        testUser.permanentAddress = faker.address().secondaryAddress();
        testUser.state = faker.options().option(testUser.states);
        testUser.city = RandomUtils.getRandomCity(testUser.state);

        return testUser;
    }
}
