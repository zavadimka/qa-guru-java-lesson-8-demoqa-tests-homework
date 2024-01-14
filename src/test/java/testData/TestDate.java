package testData;

import com.github.javafaker.Faker;

import java.util.Date;

public class TestDate {

    Faker faker = new Faker();
    Date randomDate = faker.date().birthday(14, 105);
    String day = String.valueOf(randomDate.getDate());
    String month = switch (randomDate.getMonth()) {
        case 1:
            yield "January";
        case 2:
            yield "February";
        case 3:
            yield "March";
        case 4:
            yield "April";
        case 5:
            yield "May";
        case 6:
            yield "June";
        case 7:
            yield "July";
        case 8:
            yield "August";
        case 9:
            yield "September";
        case 10:
            yield "October";
        case 11:
            yield "November";
        case 12:
            yield "December";
        default:
            throw new IllegalArgumentException();
    };
    String year = String.valueOf(randomDate.getYear() + 1900);
}
