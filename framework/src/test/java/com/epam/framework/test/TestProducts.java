package com.epam.framework.test;


import com.epam.framework.page.ProductsPage;
import com.epam.framework.service.ProductHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class TestProducts extends CommonConditions {
    @DataProvider
    public Object[] categories() {
        return new Object[]{
                "Значки",
                "Линзы",
                "Парики"
        };
    }
    @Test (dataProvider = "categories")
    public void testSortByPriceInc(String category) {
        Assert.assertTrue(ProductHandler
                .isSortedByPriceInc(new ProductsPage(driver)
                        .openPage(category)
                        .getProductsSortByPriceInc()));
    }

    @Test (dataProvider = "categories")
    public void testSortByPriceDec(String category) {
        Assert.assertTrue(ProductHandler
                .isSortedByPriceDec(new ProductsPage(driver)
                        .openPage(category)
                        .getProductsSortByPriceDec()));
    }
}
