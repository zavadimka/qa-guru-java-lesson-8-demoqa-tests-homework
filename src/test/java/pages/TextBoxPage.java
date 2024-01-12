package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TextBoxPage {
    private final SelenideElement userForm = $("#userForm"),
            mainHeader = $(".main-header"),
            fullNameInput = $("#userName"),
            emailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit"),
            output = $("#output");

    String url = "/text-box",
            mainHeaderText = "Text Box";

    @Step("Open registration form page")
    public TextBoxPage openPage() {
        open(url);
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Check the empty text box page")
    public TextBoxPage checkEmptyTextBoxPage() {
        mainHeader.shouldBe(visible).shouldHave(text(mainHeaderText));
        fullNameInput.shouldBe(empty);

        return this;
    }

    @Step("Set full name")
    public TextBoxPage setFullName(String firstName, String lastName) {
        fullNameInput.setValue(firstName + " " + lastName);

        return this;
    }

    @Step("Set email")
    public TextBoxPage setEmail(String email) {
        emailInput.setValue(email);

        return this;
    }

    @Step("Set current address")
    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);

        return this;
    }

    @Step("Set permanent address")
    public TextBoxPage setPermanentAddress(String permanentAddress) {
        permanentAddressInput.setValue(permanentAddress);

        return this;
    }

    @Step("Submit form ")
    public void submitForm() {
        submitButton.click();
    }

    @Step("Check output: {label}")
    public TextBoxPage checkOutput(String label, String value) {
        output.$(byText(label))
                .shouldHave(text(label + value));

        return this;
    }
}
