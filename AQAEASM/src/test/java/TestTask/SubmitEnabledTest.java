package TestTask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class SubmitEnabledTest {
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
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        js = (JavascriptExecutor) driver;
        driver.get("http://tl.af-ctf.ru/#inputForAuth");
    }

    @Test
    public void testSubmitEnabled() throws Exception {
        fillUserForm("Пушкин Александр Сергеевич", "pushkin@gmail.com", "+79055553535", "12345678", "12345678");
        selectCheckbox();
        submitButtonEnabled();
    }

    private void submitButtonEnabled() {
        WebElement submitButton = driver.findElement(By.id("submitLogin"));
        if(submitButton.isEnabled()) {
            System.out.println("Submit button is in enable state");
        } else {
            System.out.println("Submit button is in disable state");
        }
    }

    private void selectCheckbox() {
        WebElement checkBox = driver.findElement(By.xpath("//form[@id='inputForAuth']/div[3]/label"));
        if(checkBox.isSelected()) {
            System.out.println("Checkbox is selected. Return: " +checkBox.isSelected());
        } else {
            System.out.println("Checkbox is not selected. Return: " +checkBox.isSelected());
        }
    }

    private void fillUserForm(String name, String email, String phone, String password1, String password2) {
        driver.findElement(By.id("userName")).sendKeys(name);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("phoneNumber")).sendKeys(phone);
        driver.findElement(By.id("password")).sendKeys(password1);
        driver.findElement(By.id("passwordValidation")).sendKeys(password2);
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
