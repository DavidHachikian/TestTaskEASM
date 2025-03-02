package TestTask;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class PasswordErrorTest extends TestBase {

    @Description (value = "Негативный тест для проверки отображения текста ошибки при некорректном повторном вводе пароля")
    @Severity(value = SeverityLevel.NORMAL)
    @Test
    public void testPasswordError() throws Exception {
        fillUserForm(new UserData("Пушкин Александр Сергеевич", "pushkin@gmail.com", "89355553535", "12345678", "123456789"));
        clickCheckbox();
        submitUserForm();
        passwordVisibility();
    }

}
