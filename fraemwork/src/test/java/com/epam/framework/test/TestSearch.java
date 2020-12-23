package com.epam.framework.test;

import com.epam.framework.page.SearchPage;
import com.epam.framework.service.ProductHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class TestSearch extends CommonConditions {
    @DataProvider
    public Object[][] differentRegister() {
        return new Object[][]{
                {"зНаЧоК", "значок"},
                {"ЗНАЧОК", "значок"},
        };
    }

    @Test(dataProvider = "differentRegister")
    public void searchTestWithDifferentRegister(String toTest, String expected) {
        SearchPage page = new SearchPage(driver).openPage().search(toTest);
        Assert.assertTrue(page.isSomethingFound());
        Assert.assertTrue(ProductHandler.containsProductWithName(page.getProducts(), expected));
    }

    @DataProvider
    public Object[][] unusual() {
        return new Object[][]{
                {"раствор", "линзы"},
        };
    }
    @Test(dataProvider = "unusual")
    public void searchTestWithUnusualNames(String toTest, String expected) {
        SearchPage page = new SearchPage(driver).openPage().search(toTest);
        Assert.assertTrue(page.isSomethingFound());
        Assert.assertTrue(ProductHandler.containsProductWithName(page.getProducts(), expected));
    }
}
