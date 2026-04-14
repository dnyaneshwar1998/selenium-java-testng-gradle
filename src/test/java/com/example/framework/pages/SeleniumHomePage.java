package com.example.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumHomePage extends BasePage {
    @FindBy(css = "h1")
    private WebElement header;

    public SeleniumHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {
        return getText(header);
    }
}
