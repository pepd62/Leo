import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class LeoTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("https://leonardo.ru/");
        getWebDriver().manage().window().maximize();
        $x("//button[contains(.,'Позже')]").click();
    }
    @Test
    void test01CartTest(){
        $x("//span[.='каталог']/parent::button[contains(@class,'header__nav-item')]").click();
        $x("//a[@href='/ishop/tree_3812270609/']").click();
        $x("//div[contains(@class,'widgets__item')]/a[contains(.,'Кисти')]").click();
        $x("//span[.='В корзину']/parent::a[contains(@class,'btn btn_primary btn_h44 goods-preview__add-to-cart-btn')]").click();
        $x("//a[@href='/cart/']").click();
        $x("//div[@id='cart-list-1']").shouldHave(text("Кисть синтетика \"VISTA-ARTISTA\" 50231-01 круглая короткая ручка №01"));
    }
    @Test
    void test02SearchTest(){
        $x("//span[.='Поиск']/parent::button[contains(@class,'btn-search btn-reset header__dashboard-item header__dashboard-item--visible--desctop dashboard__item')]").click();
        $x("//input[@class='search-form__input search-control searchinput']").setValue("акварель белая");
        $x("//button[@class='search-form__submit-btn']").click();
        $x("//span[@class='show-gcounts-mob']").shouldHave(text("133 товара"));
    }
    @Test
    void test03PriceFilterTest(){
        $x("//span[.='каталог']/parent::button[contains(@class,'header__nav-item')]").click();
        $x("//a[@href='/ishop/tree_3812270609/']").click();
        $x("//div[contains(@class,'widgets__item')]/a[contains(.,'Кисти')]").click();
        $x("//input[@id='range-begin']").sendKeys(Keys.CONTROL + "a");
        $x("//input[@id='range-begin']").sendKeys(Keys.DELETE);
        $x("//input[@id='range-begin']").setValue("500");
        $x("//input[@id='range-finish']").sendKeys(Keys.CONTROL + "a");
        $x("//input[@id='range-finish']").sendKeys(Keys.DELETE);
        $x("//input[@id='range-finish']").setValue("1500");
        $x("//input[@id='range-finish']").sendKeys(Keys.ENTER);
        $x("//button[@class='liketag sfilter remove filtertopremove']").shouldHave(text("Цена 500-1500"));
    }
}