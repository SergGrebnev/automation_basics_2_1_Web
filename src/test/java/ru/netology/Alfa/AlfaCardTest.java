//Для запуска тестируемого приложения, находясь в корне проекта,
// выполните в терминале команду: java -jar ./artifacts/app-order.jar

package ru.netology.Alfa;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AlfaCardTest {
    //с использованием Selenium
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testFormCardHappyPath() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79130000000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();

        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim()); //.trim() - удаляет лишние пробелы в начале и в конце строки

    }


    @Test
    public void testFormCardInvalidName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Serg");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79130000000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim()); //.trim() - удаляет лишние пробелы в начале и в конце строки

    }


    @Test
    public void testFormCardEmptyName() {
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79130000000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();

        assertEquals("Поле обязательно для заполнения", text.trim()); //.trim() - удаляет лишние пробелы в начале и в конце строки

    }

    @Test
    public void testFormCardInvalidPhone() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("15-480");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim()); //.trim() - удаляет лишние пробелы в начале и в конце строки

    }

    @Test
    public void testFormCardEmptyPhone() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();

        assertEquals("Поле обязательно для заполнения", text.trim()); //.trim() - удаляет лишние пробелы в начале и в конце строки

    }

    @Test
    public void testFormCardNoCheckbox() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сергей");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79130000000");
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid .checkbox__text")).getText();

        assertEquals("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй", text.trim()); //.trim() - удаляет лишние пробелы в начале и в конце строки

    }


}

