import PageObject.Rozetka_Search_Page;
import org.openqa.selenium.WebDriver;

public class Main
{
    private static WebDriver driver;
    public static void main(String[] args)
    {
        try
        {
            Rozetka_Search_Page P = new Rozetka_Search_Page("https://rozetka.com.ua/mobile-phones/c80003/preset=smartfon;price=15000-99999/");
            System.out.println(P.get_price(1));
            System.out.println("huiiii");

        }
        catch (Exception E)
        {

        }
    }
}
