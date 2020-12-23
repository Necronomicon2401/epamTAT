package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage{
    WebDriver driver;

    @FindBy(xpath = "//span[@class='simplecheckout-cart-total-value']")
    public WebElement totalPriceInBasket;

    //Constructor
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}