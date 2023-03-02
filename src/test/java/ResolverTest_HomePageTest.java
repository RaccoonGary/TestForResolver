import com.beust.jcommander.Parameter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class ResolverTest_HomePageTest {

    WebDriver driver;
    ResolverTest_HomePage hm;
    String path = System.getProperty("user.dir") + "\\QE-index.html";
    static ExtentTest test;
    static ExtentReports report;

    @BeforeSuite
    public void initializeBrowser() {
        report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
        test = report.startTest("Home Page Test");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @BeforeTest
    public void openBrowser() {
        driver.get(path);
        hm = new ResolverTest_HomePage(driver);
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
        report.endTest(test);
        report.flush();
    }

    @Test
    public void Test1() throws InterruptedException {
        Assert.assertTrue(hm.test1());
        test.log(LogStatus.PASS, "In Test1, email address, password input field and login button are present");
        test.log(LogStatus.PASS, "In Test1, you have successfully entered a new set of email and password ");
    }

    @Test
    @Parameters({"listNumber","itemValue"})
    public void Test2(int listNumber,int itemValue) throws InterruptedException {
        int userSelectedListNumber=listNumber;
        String expectedItemValue="List Item "+userSelectedListNumber;
        int badgeValue = (hm.test2(userSelectedListNumber,expectedItemValue ));
        Assert.assertEquals(badgeValue,itemValue);
        System.out.println("In Test2, the badge value for the list you have selected is "+ badgeValue);
        test.log(LogStatus.PASS, "In Test2, the badge value for the list you have selected is "+ badgeValue);
}

    @Test
    @Parameters("option")
    public void Test3(int option) throws InterruptedException {
        String defaultOption=hm.test3(option);
        Assert.assertEquals(defaultOption, "Option 1");
        System.out.println("In Test3, the option selected by default is "+ defaultOption );
        test.log(LogStatus.PASS, "In Test3, the option selected by default is "+ defaultOption );
    }

    @Test
    public void Test4() throws InterruptedException {
        Assert.assertTrue(hm.test4());
        System.out.println("In Test4, it has been validated that first button is enabled and that the second button is disabled");
        test.log(LogStatus.PASS, "In Test4, it has been validated that first button is enabled and that the second button is disabled");
    }

    @Test
    public void Test5() throws InterruptedException {
        String successMessage_Test5 = hm.test5();
        Assert.assertTrue(successMessage_Test5.equals("You clicked a button!"),"In Test5, when user clicks the button, user receives the message : "+successMessage_Test5);
        System.out.println("In Test5, when user clicks the button, user sees the following success message displayed : "+successMessage_Test5);
        test.log(LogStatus.PASS, "In Test5, when user clicks the button, user sees the following success message displayed : "+successMessage_Test5);
    }

    @Test
    @Parameters({"rowNumber","columnNumber","expectedCellValue" })
    public void Test6(int rowNumber, int columnNumber, String expectedCellValue) throws InterruptedException {
        int userInputRow=rowNumber;
        int userInputColumn =columnNumber;
        String cellValue=hm.test6(userInputRow, userInputColumn);
        Assert.assertEquals(cellValue,expectedCellValue,"The expected value matches the value being returned from the grid");
        System.out.println("In Test6, the value at the cell for cell coordinates, row :"+userInputRow+" ,column :"+userInputColumn+ " is: "+cellValue );
        test.log(LogStatus.PASS, "In Test6, the value at the cell for cell coordinates, row :"+userInputRow+" ,column :"+userInputColumn+ " is: "+cellValue);
    }


}

