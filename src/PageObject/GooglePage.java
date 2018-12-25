package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GooglePage
{
    private static  WebElement Search_field ;
    private static WebElement Nextpage_link;
    public final WebDriver driver;
    private final RecordGoogle record;
    public int sleepTime = 500;
    public static  WebDriver phantom;


    public GooglePage(String URL)
    {
        System.setProperty("webdriver.chrome.driver", "Selenium WebDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(URL);
        record = new RecordGoogle(driver);
    }

    public void typeSearch (String KeyWord) throws InterruptedException
    {
        Search_field  = this.driver.findElement(By.xpath("//input[@name=\"q\"]"));
        Search_field.click();
        Search_field.sendKeys(KeyWord);
        Search_field.sendKeys(Keys.ENTER);
        Thread.sleep(sleepTime);
    }

    public List<String> get_Names_First_Page() {
         List<String> names_list = new ArrayList<>();
        WebElement [] el_arr = driver.findElements(By.cssSelector("div#rso > div > div > div > div > div > div > a ")).toArray(new WebElement[0]);
        for (int i=0; i<el_arr.length;i++) {
            names_list.add(el_arr[i].getAttribute("href"));

        }
        return names_list;
    }

    public List<String> get_Names_Page() {
        List<String> names_list = new ArrayList<>();
        WebElement [] el_arr = driver.findElements(By.cssSelector("div#rso > div > div > div > div > div > div > a ")).toArray(new WebElement[0]);
        for (int i=0; i<el_arr.length;i++) {
            names_list.add(el_arr[i].getAttribute("href"));

        }
        return names_list;
    }


    public void nextPage_click() throws InterruptedException
    {
        Nextpage_link = this.driver.findElement(By.id("pnnext"));
        Nextpage_link.click();
        Thread.sleep(sleepTime);
    }



    public void Screen(String tital, int number, PhantomJSDriver phantom) throws IOException {
;


        phantom.get(driver.getCurrentUrl());
        String fileWithPath = tital+String.valueOf(number)+".png";
//        PhantomJSDriver driver = (PhantomJSDriver) new Augmenter().augment(this.driver);
//        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1)).takeScreenshot(driver);
//        ImageIO.write(screenshot.getImage(),"PNG",new File(fileWithPath));
        File SrcFile = ((TakesScreenshot)(phantom)).getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);

    }



    public void exit()
    {
        driver.quit();
    }


}
