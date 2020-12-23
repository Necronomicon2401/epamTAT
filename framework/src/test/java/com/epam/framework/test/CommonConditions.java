package com.epam.framework.test;


import com.epam.framework.drive.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

//@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    private Logger log = LogManager.getRootLogger();
    @BeforeMethod()
    public void setUp()
    {
        driver = DriverManager.getDriver();
        log.debug("Driver is set up");
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser()
    {
        DriverManager.closeDriver();
    }
}
