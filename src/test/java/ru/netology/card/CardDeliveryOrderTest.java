package ru.netology.card;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryOrderTest {
    @Test
    void registrationForCardDelivery() {

        Configuration.headless = true;
        open("http://localhost:9999/");
        $("[data-test-id=city] [class='input__control']").setValue("Белгород");
        String planningDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id='date'] [class='input__control']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] [class='input__control']").setValue(planningDate);
        $("[data-test-id=name] [class='input__control']").setValue("Лукьянова Дарья");
        $("[data-test-id=phone] [class='input__control']").setValue("+79999999999");
        $("[data-test-id=agreement] [class='checkbox__box']").click();
        $("[class='button__content']").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

}
