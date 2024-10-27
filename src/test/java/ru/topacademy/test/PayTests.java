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
}
