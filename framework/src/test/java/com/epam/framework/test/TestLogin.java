package com.epam.framework.test;

import com.epam.framework.model.User;
import com.epam.framework.page.LoginPage;
import com.epam.framework.service.ReadEnvSpecificData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({TestListener.class})
public class TestLogin extends CommonConditions{
    @DataProvider
    public Object[] users() {
        return new Object[]{
                new User("email1@gmail.com", "123456789"),
                new User("email2@gmail.com", "987654321")
        };
    }
    @Test
    public void loginUserTestNative() {
        User user = new User(ReadEnvSpecificData.getTestData("test_data.user.email"), ReadEnvSpecificData.getTestData("test_data.user.password"));
        LoginPage loginPage = new LoginPage(driver).openPage().loginUser(user);
        boolean actual = loginPage.getCurrentUrl().equals("https://superlama.by/my-account");
        Assert.assertTrue(actual);
    }

    @Test(expectedExceptions = AssertionError.class, dataProvider = "users")
    public void loginUserTestFails(User user) {
        LoginPage loginPage = new LoginPage(driver).openPage().loginUser(user);
        boolean actual = loginPage.getCurrentUrl().equals("https://superlama.by/my-account");
        Assert.assertTrue(actual);
    }
}
