package ru.topacademy.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topacademy.data.DataHelper;
import ru.topacademy.page.CreditPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTests {
    @Test
    public void successCredit() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber = DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastCreditStatus());
    }

    @Test
    public void successCreditAgain() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber = DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastCreditStatus());
        creditPage.clearForm();
        cardNumber = DataHelper.getRightCardNumber();
        owner = DataHelper.getValidOwnerName();
        cvv = DataHelper.getValidCvv();
        month = DataHelper.getCurrentMonth();
        year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastCreditStatus());
    }

    // 2.2.1
    @Test
    public void unValidOwnerCyrillic() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerCyrillic();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showOwnerError("Неверный формат");
    }

    // 2.2.2
    @Test
    public void emptyOwner() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = "";
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showOwnerError("Поле обязательно для заполнения");
    }

    // 2.2.3
    @Test
    public void unValidOwnerSigns() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerSings();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showOwnerError("Неверный формат");
    }

    // 2.2.4
    @Test
    public void unValidOwnerNumbers() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerNumbers();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showOwnerError("Неверный формат");
    }

    // 2.2.5
    @Test
    public void unValidOwnerFirstName() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerFirstName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showOwnerError("Неверный формат");
    }

    // 2.2.6
    @Test
    public void unValidOwnerLastName() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwnerLastName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showOwnerError("Неверный формат");
    }

    // 2.2.7
    @Test
    public void unValidOwner3Name() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getUnValidOwner3Name();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showOwnerError("Неверный формат");
    }

    // 2.3.1
    @Test
    public void randomCardNumber() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRandomCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.error();
    }

    // 2.3.2
    @Test
    public void unValidCardNumber() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getUnValidCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showCardNumberError("Неверный формат");
    }

    // 2.3.3
    @Test
    public void payDeclinedCard() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getDeclinedCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.error();
        assertEquals(DataHelper.declinedStatus(), DataHelper.getLastCreditStatus());
    }

    // 2.3.4
    @Test
    public void emptyCardNumber() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  "";
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showCardNumberError("Поле обязательно для заполнения");
    }

    //2.3.5
    @Test
    public void emptyCvv() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = "";
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showCvvError("Поле обязательно для заполнения");
    }

    // 2.3.6
    @Test
    public void unValidCvv() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getUnValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showCvvError("Неверный формат");
    }

    // 2.3.7
    @Test
    public void emptyAll() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber = "";
        String owner = "";
        String cvv = "";
        String month = "";
        String year = "";
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showCardNumberError("Поле обязательно для заполнения");
        creditPage.showOwnerError("Поле обязательно для заполнения");
        creditPage.showCvvError("Поле обязательно для заполнения");
        creditPage.showMonthError("Поле обязательно для заполнения");
        creditPage.showYearError("Поле обязательно для заполнения");
    }

    // 2.3.8
    @Test
    public void emptyMonth() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = "";
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showMonthError("Поле обязательно для заполнения");
    }

    // 2.3.9
    @Test
    public void emptyYear() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = "";
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showYearError("Поле обязательно для заполнения");
    }

    // 2.3.10
    @Test
    public void unValidMonth() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getUnValidMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showMonthError("Неверный формат");
    }

    // 2.3.11
    @Test
    public void unValidYear() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getUnValidYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showYearError("Неверный формат");
    }

    // 2.3.12
    @Test
    public void lastYear() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getLastYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showYearError("Истёк срок действия карты");
    }

    // 2.3.13
    @Test
    public void over5Year() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getOver5Year();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showYearError("Неверно указан срок действия карты");
    }

    // 2.4.1
    @Test
    public void over12Month() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getOver12Month();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showMonthError("Неверно указан срок действия карты");
    }

    // 2.4.2
    @Test
    public void nextMonth() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getNextMonth();
        String year = DataHelper.getCurrentYear();
        if (month.equals("01")) {
            year = DataHelper.getNextYear();
        }
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastCreditStatus());
    }

    // 2.4.3
    @Test
    public void currentMonth() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getCurrentYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        assertEquals(DataHelper.approvedStatus(), DataHelper.getLastCreditStatus());
    }

    // 2.4.4
    @Test
    public void zeroMonth() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getZeroMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.showMonthError("Неверно указан срок действия карты");
    }

    // 2.4.5
    @Test
    public void lastMonth() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getLastMonth();
        String year = DataHelper.getCurrentYear();
        if (month.equals("12")) {
            year = DataHelper.getLastYear();
        }
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        if (month.equals("12")) {
            creditPage.showYearError("Истёк срок действия карты");
        } else  {
            creditPage.showMonthError("Неверно указан срок действия карты");
        }
    }

    // 2.5.1
    @Test
    public void hideErrorCardNumber() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  "";
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.visibleCardNumberError();
        cardNumber = DataHelper.getRightCardNumber();
        creditPage.clearForm();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        creditPage.hideCardNumberError();
    }

    // 2.5.2
    @Test
    public void hideErrorOwner() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = "";
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.visibleOwnerError();
        creditPage.clearForm();
        owner = DataHelper.getValidOwnerName();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        creditPage.hideOwnerError();
    }

    // 2.5.3
    @Test
    public void hideErrorCvv() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = "";
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.visibleCvvError();
        creditPage.clearForm();
        cvv = DataHelper.getValidCvv();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        creditPage.hideCvvError();
    }

    // 2.5.4
    @Test
    public void hideErrorMonth() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = "";
        String year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.visibleMonthError();
        creditPage.clearForm();
        month = DataHelper.getCurrentMonth();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        creditPage.hideMonthError();
    }

    // 2.5.5
    @Test
    public void hideErrorYear() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = "";
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.visibleYearError();
        creditPage.clearForm();
        year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        creditPage.hideYearError();
    }

    // 2.5.6
    @Test
    public void hideErrorAll() {
        var creditPage = open("http://localhost:8080", CreditPage.class);
        String cardNumber =  "";
        String owner = "";
        String cvv = "";
        String month = "";
        String year = "";
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.visibleCardNumberError();
        creditPage.visibleOwnerError();
        creditPage.visibleCvvError();
        creditPage.visibleMonthError();
        creditPage.visibleYearError();
        creditPage.clearForm();
        cardNumber =  DataHelper.getRightCardNumber();
        owner = DataHelper.getValidOwnerName();
        cvv = DataHelper.getValidCvv();
        month = DataHelper.getCurrentMonth();
        year = DataHelper.getNextYear();
        creditPage.fillForm(cardNumber, month, year, owner, cvv);
        creditPage.success();
        creditPage.hideCardNumberError();
        creditPage.hideOwnerError();
        creditPage.hideCvvError();
        creditPage.hideMonthError();
        creditPage.hideYearError();
    }
}
