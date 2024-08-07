package ru.netology.Alfa;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AlfaCardTest {
    //с использованием Selenide
    private WebDriver driver;


    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void testFormCard() {
        driver.get("http://localhost:9999");
    }


}

