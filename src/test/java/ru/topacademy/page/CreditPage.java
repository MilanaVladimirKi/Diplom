package ru.topacademy.page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.*;

import org.openqa.selenium.Keys;

public class CreditPage {
    private SelenideElement cardNumberField = $$(".input__top").find(exactText("Номер карты")).parent().find("input");
    private SelenideElement monthField = $$(".input__top").find(exactText("Месяц")).parent().find("input");
    private SelenideElement yearField = $$(".input__top").find(exactText("Год")).parent().find("input");
    private SelenideElement ownerField = $$(".input__top").find(exactText("Владелец")).parent().find("input");
    private SelenideElement cvvField = $$(".input__top").find(exactText("CVC/CVV")).parent().find("input");

    private SelenideElement submitButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement creditButton = $$("button").find(exactText("Купить в кредит"));

    private SelenideElement successMessage = $(".notification_status_ok");
    private SelenideElement errorMessage = $(".notification_status_error");

    private SelenideElement cardNumberError = $$(".input__top").find(exactText("Номер карты")).parent().find(".input__sub");
    private SelenideElement monthError = $$(".input__top").find(exactText("Месяц")).parent().find(".input__sub");
    private SelenideElement yearError = $$(".input__top").find(exactText("Год")).parent().find(".input__sub");
    private SelenideElement ownerError = $$(".input__top").find(exactText("Владелец")).parent().find(".input__sub");
    private SelenideElement cvvError = $$(".input__top").find(exactText("CVC/CVV")).parent().find(".input__sub");

    private SelenideElement creditHeader = $$("h3").find(exactText("Кредит по данным карты"));

    public CreditPage() {
        creditButton.click();
        creditHeader.shouldBe(visible);
        cardNumberField.shouldBe(visible);
        ownerField.shouldBe(visible);
        cvvField.shouldBe(visible);
        monthField.shouldBe(visible);
        yearField.shouldBe(visible);
        submitButton.shouldBe(visible);
    }

    public void fillForm(String cardNumber, String month, String year, String owner, String cvv) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(cvv);
        submitButton.click();
    }

    public void clearForm() {
        cardNumberField.sendKeys(Keys.CONTROL + "a");
        cardNumberField.sendKeys(Keys.BACK_SPACE);
        monthField.sendKeys(Keys.CONTROL + "a");
        monthField.sendKeys(Keys.BACK_SPACE);
        yearField.sendKeys(Keys.CONTROL + "a");
        yearField.sendKeys(Keys.BACK_SPACE);
        ownerField.sendKeys(Keys.CONTROL + "a");
        ownerField.sendKeys(Keys.BACK_SPACE);
        cvvField.sendKeys(Keys.CONTROL + "a");
        cvvField.sendKeys(Keys.BACK_SPACE);
    }

    public void success() {
        successMessage.shouldBe(visible, Duration.ofSeconds(30));
    }

    public void error() {
        errorMessage.shouldBe(visible, Duration.ofSeconds(30));
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

    public void showCvvError(String message) {
        cvvError.shouldBe(visible, Duration.ofSeconds(1));
        cvvError.shouldBe(exactText(message));
    }

    public void hideCardNumberError() {
        cardNumberError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    public void hideMonthError() {
        monthError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    public void hideYearError() {
        yearError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    public void hideOwnerError() {
        ownerError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    public void hideCvvError() {
        cvvError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    public void visibleCardNumberError() {
        cardNumberError.shouldBe(visible, Duration.ofSeconds(1));
    }

    public void visibleMonthError() {
        monthError.shouldBe(visible, Duration.ofSeconds(1));
    }

    public void visibleYearError() {
        yearError.shouldBe(visible, Duration.ofSeconds(1));
    }

    public void visibleOwnerError() {
        ownerError.shouldBe(visible, Duration.ofSeconds(1));
    }

    public void visibleCvvError() {
        cvvError.shouldBe(visible, Duration.ofSeconds(1));
    }
}
