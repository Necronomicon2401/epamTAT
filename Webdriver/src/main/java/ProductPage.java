package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ProductPage{
    WebDriver driver;
    final static String PAGE_URL = "https://superlama.by/kulon-harry-potter";

    @FindBy(id = "button-cart")
    private WebElement addingInBasketButton;

    @FindBy(xpath = "//button[@style='float: right']")
    private WebElement goToOrderPageButton;

    @FindBy(xpath = "//span[@id='mainprice']")
    public WebElement totalPrice;

    //Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public OrderPage addingItemsInBasket(){
        addingInBasketButton.click();
        waitForVisibility(goToOrderPageButton);
        goToOrderPageButton.click();
        return new OrderPage(driver);
    }

    private void waitForVisibility(WebElement element) throws Error{
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(element));
    }
}