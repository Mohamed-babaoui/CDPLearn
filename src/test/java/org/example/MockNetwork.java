package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.fetch.Fetch;

import java.util.Optional;

public class MockNetwork extends TestCase {
    public MockNetwork(String testName) {
        super(testName);
    }
    public static Test suite() {
        return new TestSuite(BlockNetworkRequests.class);
    }
    public void testMockingNetwork() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools=driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(),Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), request->

        {
            if(request.getRequest().getUrl().contains("shetty"))
            {
                String mockedUrl =request.getRequest().getUrl().replace("=shetty", "=BadGuy");

                System.out.println(mockedUrl);


                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(),Optional.empty()));
            }
            else {

                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(),Optional.empty()));

            }

        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Thread.sleep(3000);

        System.out.println(driver.findElement(By.cssSelector("p")).getText());




    }
}
