package ru.topacademy.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {}

    public static String getValidOwnerName() { return faker.name().firstName() + " " + faker.name().lastName(); }

    public static String getRightCardNumber() { return "4444 4444 4444 4441"; }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getValidCvv() {
        return "123";
    }

    public static String getUnValidCardNumber() {
        return "4444 4444 4444 444";
    }

    public static String getUnValidOwnerCyrillic() {
        return "Грейс Хоппер";
    }

    public static String getUnValidOwnerSings() {
        return "!!#$% @#!%&*";
    }

    public static String getUnValidOwnerNumbers() {
        return "Gra1e Ho2per";
    }

    public static String getUnValidOwnerFirstName() {
        return faker.name().firstName();
    }

    public static String getUnValidOwnerLastName() {
        return faker.name().lastName();
    }

    public static String getUnValidOwner3Name() {
        return faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().firstName();
    }
}
