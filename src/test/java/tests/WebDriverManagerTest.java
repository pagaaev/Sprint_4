package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverManagerTest {

    @Test
    public void testWebDriverManager() {
        // Позволяем WebDriverManager сам выбрать нужную версию ChromeDriver
//        WebDriverManager.chromedriver().setup();

        // Создаем новый экземпляр ChromeDriver
        WebDriver driver = new ChromeDriver();

//        FirefoxOptions options = new FirefoxOptions();
//        driver = new FirefoxDriver(options);

        // Ваши тесты
        driver.get("https://www.google.com");

        // Закрыть браузер после завершения теста
        driver.quit();
    }
}
