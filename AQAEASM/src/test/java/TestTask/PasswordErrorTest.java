package TestTask;

import org.testng.annotations.Test;

public class PasswordErrorTest extends TestBase {

    @Test
    public void testPasswordError() throws Exception {
        fillUserForm(new UserData("Пушкин Александр Сергеевич", "pushkin@gmail.com", "89355553535", "12345678", "123456789"));
        clickCheckbox();
        submitUserForm();
        passwordVisibility();
    }

}
