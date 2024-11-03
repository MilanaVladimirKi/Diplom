package ru.topacademy.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topacademy.data.DataHelper;
import ru.topacademy.page.PayPage;

import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import io.qameta.allure.selenide.AllureSelenide;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayTests {
    @BeforeEach
    public void clearDatabase() {
        DataHelper.clearDatabase();
    }

    @AfterAll
    public static void testAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeAll
    public static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    public void successPay() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber = DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastPaymentStatus());
    }

    @Test
    public void successPayAgain() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber = DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastPaymentStatus());
        payPage.clearForm();
        cardNumber = DataHelper.getRightCardNumber();
        owner = DataHelper.getValidOwnerName();
        cvv = DataHelper.getValidCvv();
        month = DataHelper.getCurrentMonth();
        year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastPaymentStatus());
    }

    // 1.2.1
    @Test
    public void unValidOwnerCyrillic() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerCyrillic();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showOwnerError("Неверный формат");
    }

    // 1.2.2
    @Test
    public void emptyOwner() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = "";
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showOwnerError("Поле обязательно для заполнения");
    }

    // 1.2.3
    @Test
    public void unValidOwnerSigns() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerSings();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showOwnerError("Неверный формат");
    }

    // 1.2.4
    @Test
    public void unValidOwnerNumbers() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerNumbers();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showOwnerError("Неверный формат");
    }

    // 1.2.5
    @Test
    public void unValidOwnerFirstName() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerFirstName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showOwnerError("Неверный формат");
    }

    // 1.2.6
    @Test
    public void unValidOwnerLastName() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerLastName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showOwnerError("Неверный формат");
    }

    // 1.2.7
    @Test
    public void unValidOwner3Name() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwner3Name();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showOwnerError("Неверный формат");
    }

    // 1.3.1
    @Test
    public void randomCardNumber() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRandomCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.error();
    }

    // 1.3.2
    @Test
    public void unValidCardNumber() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getUnValidCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showCardNumberError("Неверный формат");
    }

    // 1.3.3
    @Test
    public void payDeclinedCard() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getDeclinedCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.error();
        assertEquals(DataHelper.declinedStatus(), DataHelper.getLastPaymentStatus());
    }

    // 1.3.4
    @Test
    public void emptyCardNumber() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  "";
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showCardNumberError("Поле обязательно для заполнения");
    }

    //1.3.5
    @Test
    public void emptyCvv() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = "";
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showCvvError("Поле обязательно для заполнения");
    }

    // 1.3.6
    @Test
    public void unValidCvv() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getUnValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showCvvError("Неверный формат");
    }

    // 1.3.7
    @Test
    public void emptyAll() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber = "";
        String owner = "";
        String cvv = "";
        String month = "";
        String year = "";
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showCardNumberError("Поле обязательно для заполнения");
        payPage.showOwnerError("Поле обязательно для заполнения");
        payPage.showCvvError("Поле обязательно для заполнения");
        payPage.showMonthError("Поле обязательно для заполнения");
        payPage.showYearError("Поле обязательно для заполнения");
    }

    // 1.3.8
    @Test
    public void emptyMonth() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = "";
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showMonthError("Поле обязательно для заполнения");
    }

    // 1.3.9
    @Test
    public void emptyYear() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = "";
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showYearError("Поле обязательно для заполнения");
    }

    // 1.3.10
    @Test
    public void unValidMonth() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getUnValidMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showMonthError("Неверный формат");
    }

    // 1.3.11
    @Test
    public void unValidYear() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getUnValidYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showYearError("Неверный формат");
    }

    // 1.3.12
    @Test
    public void lastYear() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getLastYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showYearError("Истёк срок действия карты");
    }

    // 1.3.13
    @Test
    public void over5Year() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getOver5Year();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showYearError("Неверно указан срок действия карты");
    }

    // 1.4.1
    @Test
    public void over12Month() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getOver12Month();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showMonthError("Неверно указан срок действия карты");
    }

    //    1.4.2
    @Test
    public void nextMonth() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getNextMonth();
        String year = DataHelper.getCurrentYear();
        if (month.equals("01")) {
            year = DataHelper.getNextYear();
        }
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastPaymentStatus());
    }

    // 1.4.3
    @Test
    public void currentMonth() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastPaymentStatus());
    }

    // 1.4.4
    @Test
    public void zeroMonth() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getZeroMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.showMonthError("Неверно указан срок действия карты");
    }

    // 1.4.5
    @Test
    public void lastMonth() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getLastMonth();
        String year = DataHelper.getCurrentYear();
        if (month.equals("12")) {
            year = DataHelper.getLastYear();
        }
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        if (month.equals("12")) {
            payPage.showYearError("Истёк срок действия карты");
        } else  {
            payPage.showMonthError("Неверно указан срок действия карты");
        }
    }

    // 1.5.1
    @Test
    public void hideErrorCardNumber() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  "";
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.visibleCardNumberError();
        cardNumber = DataHelper.getRightCardNumber();
        payPage.clearForm();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        payPage.hideCardNumberError();
    }

    // 1.5.2
    @Test
    public void hideErrorOwner() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = "";
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.visibleOwnerError();
        payPage.clearForm();
        owner = DataHelper.getValidOwnerName();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        payPage.hideOwnerError();
    }

    // 1.5.3
    @Test
    public void hideErrorCvv() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = "";
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.visibleCvvError();
        payPage.clearForm();
        cvv = DataHelper.getValidCvv();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        payPage.hideCvvError();
    }

    // 1.5.4
    @Test
    public void hideErrorMonth() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = "";
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.visibleMonthError();
        payPage.clearForm();
        month = DataHelper.getCurrentMonth();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        payPage.hideMonthError();
    }

    // 1.5.5
    @Test
    public void hideErrorYear() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = "";
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.visibleYearError();
        payPage.clearForm();
        year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        payPage.hideYearError();
    }

    // 1.5.6
    @Test
    public void hideErrorAll() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  "";
        String owner = "";
        String cvv = "";
        String month = "";
        String year = "";
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.visibleCardNumberError();
        payPage.visibleOwnerError();
        payPage.visibleCvvError();
        payPage.visibleMonthError();
        payPage.visibleYearError();
        payPage.clearForm();
        cardNumber =  DataHelper.getRightCardNumber();
        owner = DataHelper.getValidOwnerName();
        cvv = DataHelper.getValidCvv();
        month = DataHelper.getCurrentMonth();
        year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        payPage.hideCardNumberError();
        payPage.hideOwnerError();
        payPage.hideCvvError();
        payPage.hideMonthError();
        payPage.hideYearError();
    }
}