package ru.topacademy.data;

import com.github.javafaker.Faker;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.openqa.selenium.devtools.v85.database.Database;

import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ru.topacademy.data.DataMySql;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {}

    public static String currentDatabase() { return "mysql"; }

    public static String getValidOwnerName() { return faker.name().firstName() + " " + faker.name().lastName(); }

    public static String getRightCardNumber() { return "4444 4444 4444 4441"; }

    public static String getDeclinedCardNumber() { return "4444 4444 4444 4442"; }

    public static String getRandomCardNumber() { return "5551 5551 5553 5555"; }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLastYear() {
        return LocalDate.now().plusYears(-1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getOver5Year() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNextMonth() {
        String month = getCurrentMonth();
        if (month.equals("12")) {
            month = "01";
        } else {
            month = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        }
        return month;
    }

    public static String getLastMonth() {
        String month = getCurrentMonth();
        if (month.equals("01")) {
            month = "12";
        } else {
            month = LocalDate.now().plusMonths(-1).format(DateTimeFormatter.ofPattern("MM"));
        }
        return month;
    }

    public static String getOver12Month() { return "13"; }

    public static String getZeroMonth() { return "00"; }

    public static String getValidCvv() { return "123"; }

    public static String getUnValidCardNumber() { return "4444 4444 4444 444"; }

    public static String getUnValidOwnerCyrillic() { return "Грейс Хоппер"; }

    public static String getUnValidOwnerSings() { return "!!#$% @#!%&*"; }

    public static String getUnValidOwnerNumbers() { return "Gra1e Ho2per"; }

    public static String getUnValidOwnerFirstName() { return faker.name().firstName(); }

    public static String getUnValidOwnerLastName() { return faker.name().lastName(); }

    public static String getUnValidOwner3Name() {
        return faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String getUnValidMonth() { return "0"; }

    public static String getNotExistMonth() { return "13"; }

    public static String getUnValidYear() { return "0"; }

    public static String getUnValidCvv() { return "12"; }


    public static String approvedStatus() { return "APPROVED"; }

    public static String declinedStatus() {  return "DECLINED"; }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionStatus {
        String status;
    }

    public static String getLastPaymentStatus() {
        if (DataHelper.currentDatabase().equals("mysql")) {
            var lastPaymentStatusObject = DataMySql.getLastPaymentStatus();
            return lastPaymentStatusObject.getStatus();
        }
        return "";
    }
}
