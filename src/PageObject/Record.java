package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;

public class Record
{
    private final WebDriver driver;

    public Record(WebDriver driver)
    {
        this.driver = driver;
    }



    public int getPrice(int childNumber)
    {
        //try {
        String tmp = (driver.findElement(By.xpath("id(\"catalog_goods_block\")/div[1]/div[" + childNumber + "]/div[1]/div[1]/div[1]"))).findElement(new By.ByClassName("g-price-uah")).getText();

        int i = Integer.valueOf(tmp
            .substring(0,tmp.length()-3)
            .replace("\u2009","")
            .replace(" ",""));
        return i;
    }
}