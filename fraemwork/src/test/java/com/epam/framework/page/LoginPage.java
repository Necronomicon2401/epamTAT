package com.epam.framework.page;

import com.epam.framework.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    public final String BASE_URL = "https://superlama.by/login";

    @FindBy(css = "input[name=email]")
    private WebElement emailInput;

    @FindBy(css = "input[name=password]")
    private WebElement passwordInput;

    @FindBy(css = "input[type=submit]")
    private WebElement enterButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public LoginPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public LoginPage loginUser(User user) {
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        enterButton.click();
        return this;
    }
}