package com.example.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumHomePage extends BasePage {
    @FindBy(css = "h1")
    private WebElement header;

    @FindBy(xpath = "//a/span[text()='Blog']")
    private WebElement blogLink;

    public SeleniumHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getHeaderText() {
        return getText(header);
    }

    public void clickBlogLink() {
        System.out.println("Clicking blog link");
        click(blogLink);
    }
}
