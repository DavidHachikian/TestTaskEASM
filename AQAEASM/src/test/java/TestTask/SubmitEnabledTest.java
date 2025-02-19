package TestTask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

public class SubmitEnabledTest extends TestBase{

    @Test
    public void testSubmitEnabled() throws Exception {
        fillUserForm(new UserData("Пушкин Александр Сергеевич", "pushkin@gmail.com", "89355553535", "12345678", "12345678"));
        selectCheckbox();
        submitButtonEnabled();
    }

}
