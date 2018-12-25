import PageObject.Rozetka_Search_Page;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class RozetkaPage_Test
{
    private Rozetka_Search_Page page;

    @Before
    public void start()
    {
        String URL = "https://rozetka.com.ua/mobile-phones/c80003/";
        page = new Rozetka_Search_Page(URL);
    }

    @After
    public void end()
    {
        page.exit();
    }



    @Test
    public void test_prices_rozetka() throws InterruptedException {
        int i = 0;

            int min_price = 9999;
            page.typeMinPrice(String.valueOf(min_price));
            page.EnterOk_Price_filter();
            for ( i = 1; i < 34; i++){
                if (i == 7){continue;}
                if (page.get_price(i)< min_price) {
                    throw new InterruptedException();
            }
            }

    }


}
