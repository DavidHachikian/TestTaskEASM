package TestTask;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class TestBase {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "");
        driver = new FirefoxDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        driver.get("http://tl.af-ctf.ru/#inputForAuth");
    }

    protected void passwordVisibility() {
        WebElement errText= driver.findElement(By.xpath("//*[contains(text(), 'Ваши пароли не совпадают')]"));
        if (errText.isDisplayed()) {
            System.out.println("Password error is visible");
        } else {
            System.out.println("Password error is not visible");
        }
    }

    protected void submitUserForm() {
        driver.findElement(By.id("submitLogin")).click();
    }

    protected void clickCheckbox() {
        driver.findElement(By.xpath("//form[@id='inputForAuth']/div[3]/label")).click();
    }

    protected void fillUserForm(UserData userData) {
        driver.findElement(By.id("userName")).sendKeys(userData.name());
        driver.findElement(By.id("email")).sendKeys(userData.email());
        driver.findElement(By.id("phoneNumber")).sendKeys(userData.phone());
        driver.findElement(By.id("password")).sendKeys(userData.password1());
        driver.findElement(By.id("passwordValidation")).sendKeys(userData.password2());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
