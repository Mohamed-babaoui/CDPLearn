package org.example;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v136.network.model.ConnectionType;
import org.openqa.selenium.devtools.v136.network.Network;


public class NetworkSpeed {

    public static void main(String[] args) {
        // TODO Auto-generated method stub


        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        //log file ->

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        //Network.emulateNetworkConditions
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        devTools.send(Network.emulateNetworkConditions(
                false,                            // offline
                3000,                             // latency (ms)
                20000,                            // download throughput (bytes/sec)
                100000,                           // upload throughput (bytes/sec)
                Optional.of(ConnectionType.ETHERNET),  // connection type
                Optional.empty(),                // maxTotalBufferSize (Optional)
                Optional.empty(),                // maxResourceBufferSize (Optional)
                Optional.empty()                 // maxConcurrentRequests (Optional)
        ));

        devTools.addListener(Network.loadingFailed(), loadingFailed->
        {
            System.out.println(loadingFailed.getErrorText());
            System.out.println(loadingFailed.getTimestamp());


        });
        long startTime = System.currentTimeMillis();
       /* driver.get("http://google.com");
        driver.findElement(By.name("q")).sendKeys("netflix", Keys.ENTER);
        driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
        String title =driver.findElement(By.cssSelector(".our-story-card-title")).getText();
        System.out.println(title);*/
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        driver.close();
        //14960  2054











    }

}
