import org.apache.tools.ant.types.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import page.*;


public class SuperlamaTest extends Assertions {
    public static final String FIRST_LINK = "https://superlama.by/kulon-harry-potter";

   @Test
    public void testAddedLikedItemsWithoutClasses() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(FIRST_LINK);

        WebElement inputQuantity = driver.findElement(By.xpath("//input[@class='quantity-productcart']"));
        inputQuantity.sendKeys("0");

        WebElement addingInBasketButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='button-cart']")));
        addingInBasketButton.click();

        WebElement goToOrderPageButton = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@style='float: right']")));
        goToOrderPageButton.click();

        WebElement itemsInOrder = driver.findElement(By.xpath("//input[@name='quantity[2433]']"));

        Assert.assertEquals("10", itemsInOrder.getAttribute("value"));

    }

    @Test
    public void testAddingItemsInBasket() throws Exception{
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ProductPage productPage = new ProductPage(new ChromeDriver());
        String expected = productPage.totalPrice.getText();
        expected.trim();
        OrderPage orderPage = productPage.addingItemsInBasket();
        String actual = orderPage.totalPriceInBasket.getText();
        Assert.assertEquals(expected, actual);
    }
}