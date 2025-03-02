package TestTask;

import io.qameta.allure.Step;
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

    @Step (value = "Проверить отображение текста ошибки")
    protected void passwordVisibility() {
        WebElement errText= driver.findElement(By.xpath("//*[contains(text(), 'Ваши пароли не совпадают')]"));
        if (errText.isDisplayed()) {
            System.out.println("Password error is visible");
        } else {
            System.out.println("Password error is not visible");
        }
    }

    @Step (value = "Нажать кнопку 'Получить'")
    protected void submitUserForm() {
        driver.findElement(By.id("submitLogin")).click();
    }

    @Step (value = "Нажать на чекбокс 'Принимаю условия обработки...'")
    protected void clickCheckbox() {
        driver.findElement(By.xpath("//form[@id='inputForAuth']/div[3]/label")).click();
    }

    @Step (value = "Заполнить поля ввода")
    protected void fillUserForm(UserData userData) {
        driver.findElement(By.id("userName")).sendKeys(userData.name());
        driver.findElement(By.id("email")).sendKeys(userData.email());
        driver.findElement(By.id("phoneNumber")).sendKeys(userData.phone());
        driver.findElement(By.id("password")).sendKeys(userData.password1());
        driver.findElement(By.id("passwordValidation")).sendKeys(userData.password2());
    }

    @Step (value = "Проверить доступность кнопки 'Получить'")
    protected void submitButtonEnabled() {
        WebElement submitButton = driver.findElement(By.id("submitLogin"));
        if(submitButton.isEnabled()) {
            System.out.println("Submit button is in enable state");
        } else {
            System.out.println("Submit button is in disable state");
        }
    }

    @Step (value = "Проверить выбран ли чекбокс 'Принимаю условия обработки...'")
    protected void selectCheckbox() {
        WebElement checkBox = driver.findElement(By.xpath("//form[@id='inputForAuth']/div[3]/label"));
        if(checkBox.isSelected()) {
            System.out.println("Checkbox is selected. Return: " +checkBox.isSelected());
        } else {
            System.out.println("Checkbox is not selected. Return: " +checkBox.isSelected());
        }
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
