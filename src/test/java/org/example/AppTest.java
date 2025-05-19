package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v136.emulation.Emulation;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testApp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width",600);
        deviceMetrics.put("height",1000);
        deviceMetrics.put("deviceScaleFactor",50);
        deviceMetrics.put("mobile",true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");

        driver.findElement(By.cssSelector(".navbar-toggler")).click();
        Thread.sleep(3000);

        driver.findElement(By.linkText("Library")).click();
    }
}
