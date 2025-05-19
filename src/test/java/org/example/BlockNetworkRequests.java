package org.example;

import com.google.common.collect.ImmutableList;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v136.network.Network;

import java.util.Optional;

public class BlockNetworkRequests extends TestCase  {

    public BlockNetworkRequests(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BlockNetworkRequests.class);
    }

    public void testBlockNetwork() {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver= new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }


}
