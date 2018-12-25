package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class Rozetka_Search_Page
{
    private static  WebElement Min_Price_field ;
    private static WebElement OK_price_filter;
    private final WebDriver driver;
    private final Record record;
    public int sleepTime = 500;



    public Rozetka_Search_Page(String URL)
    {
        System.setProperty("webdriver.chrome.driver", "Selenium WebDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(URL);
        record = new Record(driver);
    }

    public void typeMinPrice(String Price) throws InterruptedException
    {
        Min_Price_field = this.driver.findElement(By.xpath("id(\"price[min]\")"));
        Min_Price_field.click();
        Min_Price_field.sendKeys(Price);
        Thread.sleep(sleepTime);
    }

    public void EnterOk_Price_filter() throws InterruptedException
    {
        OK_price_filter = this.driver.findElement(By.xpath("id(\"submitprice\")"));
        OK_price_filter.click();
        Thread.sleep(sleepTime);
    }


    public int get_price(int childNumber) throws InterruptedException
    {
        return this.record.getPrice(childNumber);
        //Thread.sleep(sleepTime);
    }



    public void exit()
    {
        driver.quit();
    }
}
