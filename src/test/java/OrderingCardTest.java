import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


class ChromeTest {

    WebDriver driver;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    //    static void setupClass() {
        //        System.setProperty("webdriver.chrome.driver", "C:/Users/79829/IdeaProjects/OrderingCard/driver/win/chromedriver.exe");
        //    }

    @BeforeEach
    void setupTest() {
//        driver = new ChromeDriver();
       ChromeOptions options = new ChromeOptions();
       options.addArguments("--disable-dev-shm-usage");
       options.addArguments("--no-sandbox");
       options.addArguments("--headless");
       driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
   }

    @AfterEach
    void teardown() {
        driver.quit();
        driver = null;
    }

        @Test
        void validOfTheFirstAndLastName() {
            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иван Иванов");
            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998887766");
            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
            driver.findElement(By.cssSelector("[type=button]")).click();
            String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
            Assertions.assertEquals(expected, actual);
        }
    }
