import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.LinkedList;
import java.util.Queue;

public class ToDoAppSeleniumTest {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = getWebDriver();

        // Load and Login TodoApp
        System.out.println("Welcome to Todo App");
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.id("login"));
        password.sendKeys("dummy");
        System.out.println("Attempting to Login...");
        login.click();
        System.out.println("Logged in");
        Thread.sleep(3000);

        // TodoList Page
        System.out.println("Navigating to todo page");
        driver.findElement(By.id("todo")).click();
        Thread.sleep(3000);


        // Adding items to TodoList
        System.out.println("Adding Items to TodoList");
        addItems(driver);
        System.out.println("Items added!");


        // Deleting Items
        Thread.sleep(3000);
        System.out.println("Deleting item...");
        driver.findElement(By.id("delete")).click();
        System.out.println("Item Deleted!");

        // Updating Items
        System.out.println("Updating item...");
        Thread.sleep(3000);
        driver.findElement(By.id("update")).click();
        WebElement description = driver.findElement(By.id("updateDesc"));
        description.clear();
        Thread.sleep(3000);
        description.sendKeys(" Updated");
        WebElement date = driver.findElement(By.id("updateTarget"));
        date.sendKeys("07072020");
        driver.findElement(By.id("save")).click();
        System.out.println("Item Updated!");

        // Navigate to Home
        System.out.println("Navigating to home page..");
        Thread.sleep(3000);
        driver.findElement(By.linkText("Home")).click();
        Thread.sleep(3000);

        // Welcome Message
        System.out.println("Welcome message clicked...");
        driver.findElement(By.id("welcome")).click();
        System.out.println("Welcome message displayed");
        Thread.sleep(3000);

        // Logout
        System.out.println("Logging out .....");
        driver.findElement(By.linkText("Logout")).click();
        System.out.println("Logged out!");

    }

    private static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/utkarsh/driver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:4200/");
        return driver;
    }

    private static void addItems(WebDriver driver) {
        Queue<String> items = getItems();
        int i = 0;

        while (!items.isEmpty()) {

            i++;
            driver.findElement(By.id("add")).click();
            driver.findElement(By.id("updateDesc")).sendKeys(items.poll());
            WebElement date = driver.findElement(By.id("updateTarget"));
            date.sendKeys(items.poll());
            driver.findElement(By.id("save")).click();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Item " + i + " added. ");

        }
    }

    private static Queue<String> getItems() {

        Queue<String> queue = new LinkedList<String>();
        queue.add("File Tax");
        queue.add("07072020");

        queue.add("Pay Bill");
        queue.add("08082020");

        queue.add("Pay Rent");
        queue.add("09092020");

        return queue;

    }

}
