package ru.topacademy.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class PayPage {
    private SelenideElement cardNumberField = $$(".input__inner").find(exactText("Номер карты")).find("input");
    private SelenideElement monthField = $$(".input__inner").find(exactText("Месяц")).find("input");
    private SelenideElement yearField = $$(".input__inner").find(exactText("Год")).find("input");
    private SelenideElement ownerField = $$(".input__inner").find(exactText("Владелец")).find("input");
    private SelenideElement cvvField = $$(".input__inner").find(exactText("CVC/CVV")).find("input");

    private SelenideElement submitButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement payButton = $$("button").find(exactText("Купить"));

    private SelenideElement successMessage = $(".notification_status_ok");
    private SelenideElement errorMessage = $(".notification_status_error");

    private SelenideElement cardNumberError = $$(".input__inner").find(exactText("Номер карты")).find(".input__sub");
    private SelenideElement monthError = $$(".input__inner").find(exactText("Месяц")).find(".input__sub");
    private SelenideElement yearError = $$(".input__inner").find(exactText("Год")).find(".input__sub");
    private SelenideElement ownerError = $$(".input__inner").find(exactText("Владелец")).find(".input__sub");
    private SelenideElement cvvError = $$(".input__inner").find(exactText("CVC/CVV")).find(".input__sub");

    public PayPage() {
        payButton.click();
        cardNumberField.shouldBe(visible);
    }

    public void fillForm(String cardNumber, String month, String year, String owner, String cvv) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(cvv);
        submitButton.click();
    }

    public void success() {
        successMessage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void error() {
        errorMessage.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void showCardNumberError(String message) {
        cardNumberError.shouldBe(visible, Duration.ofSeconds(1));
        cardNumberError.shouldHave(exactText(message));
    }

    public void showMonthError(String message) {
        monthError.shouldBe(visible, Duration.ofSeconds(1));
        monthError.shouldBe(exactText(message));
    }

    public void showYearError(String message) {
        yearError.shouldBe(visible, Duration.ofSeconds(1));
        yearError.shouldBe(exactText(message));
    }

    public void showOwnerError(String message) {
        ownerError.shouldBe(visible, Duration.ofSeconds(1));
        ownerError.shouldBe(exactText(message));
    }

    public void showSvvError(String message) {
        cvvError.shouldBe(visible, Duration.ofSeconds(1));
        cvvError.shouldBe(exactText(message));
    }
}
