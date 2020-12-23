package com.epam.framework.page;

import com.epam.framework.model.Product;
import com.epam.framework.service.ProductHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AbstractPage {
    public final String BASE_URL = "https://superlama.by/index.php?route=product/search&description=true";

    @FindBy(css = "input[name=search]")
    private WebElement searchField;

    @FindBy(css = ".product-grid .name")
    private List<WebElement> products;

    @FindBy(css = ".product-grid .cat-info .price")
    private List<WebElement> prices;

    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SearchPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public SearchPage search(String text) {
        searchField.sendKeys(text + "\n");
        return this;
    }

    public boolean isSomethingFound() {
        return !isElementPresent(By.xpath("//img[@class='img-responsive']"));
    }

    public ArrayList<Product> getProducts() {
        return ProductHandler.createProductList(products.stream().map(WebElement::getText).toArray(String[]::new),
                prices.stream().map(WebElement::getText).toArray(String[]::new));
    }

    public boolean isElementPresent(By element) {
        try {
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
