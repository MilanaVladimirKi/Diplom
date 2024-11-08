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
        // выполняется клик по кнопке Купить в кредит
        creditButton.click();
        // проверяется видимость заголовка Кредит по данным карты
        creditHeader.shouldBe(visible, Duration.ofSeconds(1));
    }

    // Метод заполнения полей и отправки формы
    public void fillForm(String cardNumber, String month, String year, String owner, String cvv) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(cvv);
        submitButton.click();
    }

    // Метод очистки полей формы
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

    // Метод проверяющий вывод сообщение об успешной оплате
    public void success() {
        successMessage.shouldBe(visible, Duration.ofSeconds(30));
    }

    // Метод проверяющий вывод сообщения об отклонении операции банком
    public void error() {
        errorMessage.shouldBe(visible, Duration.ofSeconds(30));
    }

    // метод проверяет вывод предупреждение и текст предупреждения, при некоректном заполнении поля Номер карты
    public void showCardNumberError(String message) {
        visibleCardNumberError();
        cardNumberError.shouldHave(exactText(message));
    }

    // метод проверяет вывод предупреждение и текст предупреждения, при некоректном заполнении поля Месяц
    public void showMonthError(String message) {
        visibleMonthError();
        monthError.shouldBe(exactText(message));
    }

    // метод проверяет вывод предупреждение и текст предупреждения, при некоректном заполнении поля Год
    public void showYearError(String message) {
        visibleYearError();
        yearError.shouldBe(exactText(message));
    }

    // метод проверяет вывод предупреждение и текст предупреждения, при некоректном заполнении поля Владелец карты
    public void showOwnerError(String message) {
        visibleOwnerError();
        ownerError.shouldBe(exactText(message));
    }

    // метод проверяет вывод предупреждение и текст предупреждения, при некоректном заполнении поля CVV
    public void showCvvError(String message) {
        visibleCvvError();
        cvvError.shouldBe(exactText(message));
    }

    // метод проверяет скрытие предупреждения  (после корректного заполнения)
    public void hideCardNumberError() {
        cardNumberError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    // метод проверяет скрытие предупреждения о некоректном заполнении поля Месяц (после исправления)
    public void hideMonthError() {
        monthError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    // метод проверяет скрытие предупреждения о некоректном заполнении поля Год (после исправления)
    public void hideYearError() {
        yearError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    // метод проверяет скрытие предупреждения о некоректном заполнении поля Владелец карты (после исправления)
    public void hideOwnerError() {
        ownerError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    // метод проверяет скрытие предупреждения о некоректном заполнении поля CVV (после исправления)
    public void hideCvvError() {
        cvvError.shouldBe(hidden, Duration.ofSeconds(1));
    }

    // метод проверяет вывод предупреждения при некоректном заполнении поля Номер карты
    public void visibleCardNumberError() {
        cardNumberError.shouldBe(visible, Duration.ofSeconds(1));
    }

    // метод проверяет вывод предупреждения при некоректном заполнении поля Месяц
    public void visibleMonthError() {
        monthError.shouldBe(visible, Duration.ofSeconds(1));
    }

    // метод проверяет вывод предупреждения при некоректном заполнении поля Год
    public void visibleYearError() {
        yearError.shouldBe(visible, Duration.ofSeconds(1));
    }

    // метод проверяет вывод предупреждения при некоректном заполнении поля Владелец карты
    public void visibleOwnerError() {
        ownerError.shouldBe(visible, Duration.ofSeconds(1));
    }

    // метод проверяет вывод предупреждения при некоректном заполнении поля CVV
    public void visibleCvvError() {
        cvvError.shouldBe(visible, Duration.ofSeconds(1));
    }
}
