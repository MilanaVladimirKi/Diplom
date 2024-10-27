package ru.topacademy.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.topacademy.data.DataHelper;
import ru.topacademy.page.PayPage;
import ru.topacademy.page.CreditPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import ru.topacademy.page.PayPage;
import ru.topacademy.data.DataHelper;

public class PayTests {
    @Test
    public void successPay() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
    }

    @Test
    public void successPayAgain() {
        var payPage = open("http://localhost:8080", PayPage.class);
        String cardNumber =  DataHelper.getRightCardNumber();
        String owner = DataHelper.getValidOwnerName();
        String cvv = DataHelper.getValidCvv();
        String month = DataHelper.getCurrentMonth();
        String year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
        cardNumber =  DataHelper.getRightCardNumber();
        owner = DataHelper.getValidOwnerName();
        cvv = DataHelper.getValidCvv();
        month = DataHelper.getCurrentMonth();
        year = DataHelper.getNextYear();
        payPage.fillForm(cardNumber, month, year, owner, cvv);
        payPage.success();
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


}
