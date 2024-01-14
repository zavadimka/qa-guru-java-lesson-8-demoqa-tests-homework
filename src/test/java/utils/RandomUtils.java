package utils;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomString(int length){
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

    public static String getRandomEmail(){

        return getRandomString(8) + "@" + getRandomString(8) + ".com";
    }

    public static String getRandomPhoneNumber(int length){
        String AB = "0123456789";

        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));

        return sb.toString();
    }

    public static String getRandomValue(String[] values){
        return values[getRandomInt(0, values.length - 1)];
    }

    public static String getRandomCity(String state){
        String city = "";

        switch (state) {
            case "NCR":
                String[] citiesOfNcr = {
                        "Delhi",
                        "Gurgaon",
                        "Noida"
                };
                city = getRandomValue(citiesOfNcr);
            case "Uttar Pradesh":
                String[] citiesOfUttarPradesh = {
                        "Agra",
                        "Lucknow",
                        "Merrut"
                };
                city = getRandomValue(citiesOfUttarPradesh);
            case "Haryana":
                String[] citiesOfHaryana = {
                        "Karnal",
                        "Panipat"
                };
                city = getRandomValue(citiesOfHaryana);
            case "Rajasthan":
                String[] citiesOfRajasthan = {
                        "Jaipur",
                        "Jaiselmer"
                };
                city = getRandomValue(citiesOfRajasthan);

        };

        return city;
    }
}
