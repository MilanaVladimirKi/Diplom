package ru.topacademy.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import java.util.Locale;


public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {}

    public static String generateValidOwnerName() { return faker.elderScrolls().firstName() + " " + faker.elderScrolls().lastName(); }

    public static String getRightCardNumber() { return "4444 4444 4444 4441"; }
}
