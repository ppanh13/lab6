import PageObject.GooglePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.List;

public class GooglePage_Test
{

//    public static WebDriver phantom;
    private GooglePage page;
    Actions action ;
    public String keyword = "утята киев";
    public String company_name_for_first_page = "agronet";
    public String company_name_for_next_pages = "reprisa";
    public String bad_company = "xgfxgfxgx";
    public PhantomJSDriver phantom;
    @Before
    public void start()
    {
        String URL = "https://google.com";
        page = new GooglePage(URL);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "phantomjs");
        phantom = new PhantomJSDriver(capabilities);
//        page.driver.manage().window().maximize();

    }

    @After
    public void end()
    {
        page.exit();
    }



    @Test
    public void test_company_first_page_search_google() throws InterruptedException {
        page.typeSearch(keyword);

        List<String> list_of_results = page.get_Names_Page();
        for (int i = 0; i< list_of_results.size(); i++){
            if (list_of_results.get(i).contains(company_name_for_first_page)){
//                action = new Actions(page.driver);
//                action.moveToElement((page.driver.findElements(By.className("g"))).get(i)).build().perform();
                try {
                    Thread.sleep(2000);
                    page.Screen(keyword,1,phantom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            }

    }
    @Test
    public void test_company_next_pages_search_google() throws InterruptedException, IOException {
        page.typeSearch(keyword);
        page.nextPage_click();
        exitlabel:for (int j=0; j<10;j++){
            List<String> list_of_results = page.get_Names_First_Page();
            for (int i = 0; i< list_of_results.size(); i++) {
                if (list_of_results.get(i).contains(company_name_for_next_pages)){
                    action = new Actions(page.driver);
                    action.moveToElement((page.driver.findElements(By.className("g"))).get(i)).build().perform();
                    page.Screen(keyword,j+2,phantom);
                    break exitlabel;

            }}
            try {
                page.nextPage_click();
            }
            catch(Exception e){

                }
            }
    }


    @Test
    public void test_badcompany_search_google() throws InterruptedException, IOException {
        page.typeSearch(keyword);
//        page.nextPage_click();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setJavascriptEnabled(true);
//        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/local/bin/phantomjs");
//        phantom = new PhantomJSDriver(capabilities);
//        driver.manage().window().maximize();
        exitlabel: for (int j=0; j<10;j++){
            List<String> list_of_results = page.get_Names_Page();
            for (int i = 0; i< list_of_results.size(); i++) {
                if (!list_of_results.get(i).contains(bad_company)){
//                    action = new Actions(page.driver);
//                    action.moveToElement((page.driver.findElements(By.className("g"))).get(i)).build().perform();
                    page.Screen(keyword,j+2,phantom);
//

                }
                else{
                    break exitlabel;
                }
            }
            try {
                page.nextPage_click();
            }
            catch(Exception e){
                break;
            }
        }
    }
}




