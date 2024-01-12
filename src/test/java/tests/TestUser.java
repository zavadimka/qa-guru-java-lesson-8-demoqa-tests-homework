package tests;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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
            state,
            city;

    public static TestUser getTestUserDataFromCsv() {
        TestUser testUser = new TestUser();

        try (CSVReader reader = new CSVReader(new FileReader("src/test/resources/testData/testUser.csv"))) {
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
            testUser.state = testUserData[13];
            testUser.city = testUserData[14];

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return testUser;
    }
}
