package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private final SelenideElement yearSelector = $(".react-datepicker__year-select"),
            monthSelector = $(".react-datepicker__month-select"),
            daySelector = $(".react-datepicker__month:not(.react-datepicker__day--outside-month");

    public void setDate(SelenideElement dateInputLocator, String year, String month, String day) {

        dateInputLocator.click();
        yearSelector.selectOption(year);
        monthSelector.selectOption(month);
        daySelector.$(byText(day)).click();
    }
}