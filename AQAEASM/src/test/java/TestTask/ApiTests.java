package TestTask;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.*;


public class ApiTests extends UserProfileApi {

    @Description(value = "Тест, проверяющий приходит ли статус 200 при вызове метода calluserforsignup при заполненных полях")
    @Severity(value = SeverityLevel.CRITICAL)
    @Test
    public void apiTestSignUp() {
        RestAssured
                .given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(userProfile)
                .post("https://tl.af-ctf.ru/calluserforsignup")
                .then()
                .statusCode(200);
    }

    @Description(value = "Негативный тест, проверяющий наличие респонса 'Неправильно указан номер телефона получателя'")
    @Severity(value = SeverityLevel.MINOR)
    @Test
    public void apiTestPhone() {
        RestAssured
                .given()
                .log().all()
                .when()
                .contentType(ContentType.JSON)
                .body(userProfile)
                .post("https://tl.af-ctf.ru/calluserforsignup")
                .then()
                .body("type", Matchers.is(false));
    }
}
