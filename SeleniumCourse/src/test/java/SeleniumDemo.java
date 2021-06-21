import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SeleniumDemo {
    @Test
    public void firstTest() throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        System.out.print("Opening Chrome...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");
        System.out.println("● Opened www.google.com.tr");

        Thread.sleep(1000);

        String current_url=driver.getCurrentUrl();
        String expected_url= "https://www.google.com/";

        System.out.println(current_url);
        if (current_url==expected_url){
            System.out.println("● Verified it’s Google TR > https://www.google.com/");
        }

        System.out.println("● Searched for “Hipo Labs”");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Hipo Labs");
        element.submit();


        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.partialLinkText("Hipo - iOS, Android, Web"))));
        System.out.println("● Verified hipolabs.com is listed.");

        driver.findElement(By.partialLinkText("Hipo - iOS, Android, Web")).click();
        System.out.println("● Opened the website");

        driver.findElement(By.linkText("TEAM")).click();
        System.out.println("● Team menu");


        JavascriptExecutor js = (JavascriptExecutor) driver;

        System.out.println("● Found & followed “APPLY FOR JOBS” link.");
        //Find element by link text and store in variable "Element"
        WebElement scroll = driver.findElement(By.id("pageTeamApplynowButton"));

        //This will scroll the page till the element is found
        js.executeScript("arguments[0].scrollIntoView();", scroll);

        scroll.click();

        //Find element by link text and store in variable "Element"
        WebElement applynowButton = driver.findElement(By.partialLinkText("APPLY NOW"));
        js.executeScript("arguments[0].scrollIntoView();", applynowButton);
        System.out.println("● Verified that page has “APPLY NOW” text.");



        Thread.sleep(3000);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(scrFile, new File("C:\\Users\\HP\\Pictures\\Screenshots\\ElementScreenshot.png"));
        Thread.sleep(3000);

        System.out.println("● Took a Screenshot");

        driver.quit();

    }
}

