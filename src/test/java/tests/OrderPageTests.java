package tests;

import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.MainPage;
import pageObjects.OrderPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;

/**
 * Тест для проверки всего флоу позитивного сценария оформления заказа
 */
@RunWith(Parameterized.class)
public class OrderPageTests {
    /** Веб-драйвер */
    private WebDriver webDriver;

    /** URL тестируемой страницы */
    private final String mainPageUrl = "https://qa-scooter.praktikum-services.ru";

    /** Переменные для параметров теста - данных для оформления заказа */
    private final String name, surname, address, metro, phone, date, term, color, comment;

    /** Сообщение об успешном оформлении заказа */
    private final String expectedOrderSuccessText = "Заказ оформлен";

    /**
     * Контруктор класса OrderPageTests
     */
    public OrderPageTests(
            String name,
            String surname,
            String address,
            String metro,
            String phone,
            String date,
            String term,
            String color,
            String comment
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.term = term;
        this.color = color;
        this.comment = comment;
    }

    /**
     * Параметры для запуска теста
     * @return массив параметров
     */
    @Parameterized.Parameters(name = "Оформление заказа. Позитивный сценарий. Пользователь: {0} {1}")
    public static Object[][] setDataForOrder() {
        return new Object[][] {
                {"Клава", "Птичкина", "Москва, ул. Дорожная, д. 12, кв. 34", "Сокол", "81234567890", "01.05.2023", "четверо суток", "чёрный жемчуг", "Коммент!"},
                {"Иван", "Петров", "Москва, ул. Скобелевская, д. 26, кв. 1", "Улица Скобелевская", "89876543210", "21.05.2023", "трое суток", "серая безысходность", "Привезите в первой половине дня"},
        };
    }

    @Before
    public void startUp() {
        this.webDriver = new ChromeDriver();
        this.webDriver.get(mainPageUrl);
    }

    @After
    public void tearDown() {
        this.webDriver.quit();
    }

    @Test
    public void orderWithHeaderButtonWhenSuccess() {
        MainPage mainPage = new MainPage(this.webDriver);
        OrderPage orderPage = new OrderPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();
        mainPage.clickOrderButtonHeader();
        orderPage.makeOrder(this.name, this.surname, this.address, this.metro, this.phone, this.date, this.term, this.color, this.comment);

        MatcherAssert.assertThat(
                "Problem with creating a new order",
                orderPage.getNewOrderSuccessMessage(),
                containsString(this.expectedOrderSuccessText)
        );
    }

    @Test
    public void orderWithBodyButtonWhenSuccess() {
        MainPage mainPage = new MainPage(this.webDriver);
        OrderPage orderPage = new OrderPage(this.webDriver);

        mainPage.clickOnCookieAcceptButton();
        mainPage.clickOrderButtonBody();
        orderPage.makeOrder(this.name, this.surname, this.address, this.metro, this.phone, this.date, this.term, this.color, this.comment);

        MatcherAssert.assertThat(
                "Problem with creating a new order",
                orderPage.getNewOrderSuccessMessage(),
                containsString(this.expectedOrderSuccessText)
        );
    }
}
