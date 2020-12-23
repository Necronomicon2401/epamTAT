package com.epam.framework.service;

import com.epam.framework.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Float.parseFloat;

public class ProductHandler {
    public static float parseStringedPrice(String stringedPrice) {
        return parseFloat(stringedPrice.
                replaceAll("\n", ".").
                replaceAll("руб", "").
                replaceAll(" ", ""));
    }

    public static ArrayList<Product> createProductList(String[] names, String[] stringedPrices) {
        ArrayList outArray = new ArrayList<Product>();
        for (int i = 0; i < names.length; i++) {
            outArray.add(new Product(names[i], parseStringedPrice(stringedPrices[i])));
        }
        return outArray;
    }

    public static boolean isSortedByPriceInc(ArrayList<Product> originalList) {
        ArrayList productList = new ArrayList<>(originalList);
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        return productList.equals(originalList);
    }

    public static boolean isSortedByPriceDec(ArrayList<Product> originalList) {
        ArrayList productList = new ArrayList<>(originalList);
        productList.sort(Comparator.comparingDouble(Product::getPrice));
        Collections.reverse(productList);
        return productList.equals(originalList);
    }

    public static boolean containsProductWithName(ArrayList<Product> productList, String name) {
        return productList.stream().filter(x -> x.getName().toLowerCase().contains(name.toLowerCase())).toArray().length > 0;
    }
}
