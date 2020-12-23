package com.epam.framework.page;

import com.epam.framework.model.Product;
import com.epam.framework.service.ProductHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends AbstractPage {
    public final String BASE_URL = "https://superlama.by/";
    public final String BASE_CATEGORY = "значки";

    private By selectSelector = By.xpath("//*[@id=\"content\"]/div[1]/div[1]/div[3]/select");

    @FindBy(css = "option")
    private List<WebElement> options;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    @Override
    public ProductsPage openPage(){
        return openPage(BASE_CATEGORY);
    }

    public ProductsPage openPage(String sectionName) {
        driver.navigate().to(BASE_URL);
        List<WebElement> allMainCategories = driver.findElements(By.xpath("//*[@id=\"menu\"]/ul/li/a"));
        for (WebElement mainCategory : allMainCategories){
            Actions action = new Actions(driver);
            action.moveToElement(mainCategory).build().perform();;
            List<WebElement> allCategories = driver.findElements(By.xpath("//*[@id=\"menu\"]/ul/li/div/ul/li/a"));
            for (WebElement category : allCategories) {
                if (category.getText().toLowerCase().equals(sectionName.toLowerCase())) {
                    category.click();
                    return this;
                }
            }
            }
        return null;
    }

    public ArrayList<Product> getProducts(){
        List<WebElement> allProductsNames = driver.findElements(By.xpath("//div[@class='product-grid']/div/div[@class='name']/a"));
        List<WebElement> allProductsPrices =  driver.findElements(By.xpath("//div[@class='product-grid']/div/div[@class='price']/a"));
        return ProductHandler.createProductList(allProductsNames.stream().map(WebElement::getText).toArray(String[]::new),
                allProductsPrices.stream().map(WebElement::getText).toArray(String[]::new));
    }

    public ArrayList<Product> getProductsSortByPriceInc() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(selectSelector))
                .click();
        options.get(1).click();
        return getProducts();
    }

    public ArrayList<Product> getProductsSortByPriceDec() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(selectSelector))
                .click();
        options.get(2).click();
        return getProducts();
    }

}
