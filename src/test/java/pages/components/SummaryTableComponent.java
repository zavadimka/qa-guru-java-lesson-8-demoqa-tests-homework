package pages.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SummaryTableComponent {

    private final SelenideElement modalWindow = $(".modal-content"),
            modalHeader = $(".modal-title"),
            valueLabel = $(".table-responsive"),
            closeButton = $("#closeLargeModal");

    @Step("Check summary table opening")
    public SummaryTableComponent checkSummaryTableOpening() {
        modalWindow.shouldBe(visible);
        modalHeader.shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    @Step("Summary table verification: {label}")
    public SummaryTableComponent valueVerification(String label, String value) {
        valueLabel.$(byText(label))
                .parent().shouldHave(text(value));

        return this;
    }

    @Step("Close summary table")
    public void closeSummaryTable() {
        closeButton.click();
    }
}
