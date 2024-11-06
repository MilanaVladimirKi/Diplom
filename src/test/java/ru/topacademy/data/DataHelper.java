package ru.topacademy.data;

import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {}

    // метод возвращает идентификатор текущей базы данных
    public static String currentDatabase() {
        return "mysql";
        // return "postgresql";
    }

    // метод возвращает корректные (латиницей имя и фамилию) данные владельца карты
    public static String getValidOwnerName() { return faker.name().firstName() + " " + faker.name().lastName(); }

    // метод возвращает номер действующей карты
    public static String getRightCardNumber() { return "4444 4444 4444 4441"; }

    // метод возвращает номер заблокированной карты
    public static String getDeclinedCardNumber() { return "4444 4444 4444 4442"; }

    public static String getRandomCardNumber() { return "5551 5551 5553 5555"; }

    // метод возвращает текущий год
    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    // метод возвращает год следующий, после текущего
    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    // метод возвращает предыдущий год, до текущего
    public static String getLastYear() {
        return LocalDate.now().plusYears(-1).format(DateTimeFormatter.ofPattern("yy"));
    }

    // метод возвращает год, через 5 лет после текущего
    public static String getOver5Year() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    // метод возвращает текущий месяц
    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    // метод возвращает следующий месяц
    public static String getNextMonth() {
        String month = getCurrentMonth();
        if (month.equals("12")) {
            month = "01";
        } else {
            month = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        }
        return month;
    }

    // метод возвращает предыдущий месяц
    public static String getLastMonth() {
        String month = getCurrentMonth();
        if (month.equals("01")) {
            month = "12";
        } else {
            month = LocalDate.now().plusMonths(-1).format(DateTimeFormatter.ofPattern("MM"));
        }
        return month;
    }

    // метод возвращает несуществующий 13 месяц
    public static String getOver12Month() { return "13"; }

    // метод возвращает несуществующий нулевой месяц
    public static String getZeroMonth() { return "00"; }

    // метод возвращает код из 3 цифр
    public static String getValidCvv() { return "123"; }

    // метод возвращает непоный номер карты
    public static String getUnValidCardNumber() { return "4444 4444 4444 444"; }

    // метод возвращает невалидные данные владельца карты (кириллица)
    public static String getUnValidOwnerCyrillic() { return "Грейс Хоппер"; }

    public static String getUnValidOwnerSings() { return "!!#$% @#!%&*"; }

    public static String getUnValidOwnerNumbers() { return "Gra1e Ho2per"; }

    public static String getUnValidOwnerFirstName() { return faker.name().firstName(); }

    public static String getUnValidOwnerLastName() { return faker.name().lastName(); }

    public static String getUnValidOwner3Name() {
        return faker.name().firstName() + " " + faker.name().lastName() + " " + faker.name().firstName();
    }

    // метод возвращает невалидный месяц (из одной цифры)
    public static String getUnValidMonth() { return "0"; }

    // метод возвращает невалидный год (из одной цифры)
    public static String getUnValidYear() { return "0"; }

    // метод возвращает код из 2 цифр (невалидный)
    public static String getUnValidCvv() { return "12"; }

    // метод возвращает статус оплаты (успешный)
    public static String approvedStatus() { return "APPROVED"; }

    // метод возвращает статус оплаты (отклоненный)
    public static String declinedStatus() {  return "DECLINED"; }

    // внутренний класс для получения статус оплаты из базы даанных
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TransactionStatus {
        String status;
    }

    // метод получения статуса последней оплаты
    public static String getLastPaymentStatus() {
        if (currentDatabase().equals("mysql")) {
            var lastPaymentStatusObject = DataMySql.getLastPaymentStatus();
            return lastPaymentStatusObject.getStatus();
        } else if (currentDatabase().equals("postgresql")) {
            var lastPaymentStatusObject = DataPostgress.getLastPaymentStatus();
            return lastPaymentStatusObject.getStatus();
        }
        return "";
    }

    // метод получения статуса последней оплаты в кредит
    public static String getLastCreditStatus() {
        if (currentDatabase().equals("mysql")) {
            var lastCreditStatusObject = DataMySql.getLastCreditStatus();
            return lastCreditStatusObject.getStatus();
        } else if (currentDatabase().equals("postgresql")) {
            var lastCreditStatusObject = DataPostgress.getLastCreditStatus();
            return lastCreditStatusObject.getStatus();
        }
        return "";
    }

    // метод очистки базы данных
    public static void clearDatabase() {
        if (currentDatabase().equals("mysql")) {
            DataMySql.clearDataBase();
        } else if (currentDatabase().equals("postgresql")) {
            DataPostgress.clearDataBase();
        }
    }
}