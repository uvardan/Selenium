import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.LinkedList;
import java.util.Queue;

public class ToDoAppSeleniumTest {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/utkarsh/driver/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        driver.get("http://localhost:4200/");

        System.out.println("Welcome to Todo App");
        WebElement password = driver.findElement(By.name("password"));
        WebElement login = driver.findElement(By.id("login"));
        password.sendKeys("dummy");

        System.out.println("Logging in");
        login.click();
        System.out.println("Logged in");
        Thread.sleep(1000);

        System.out.println("Navigating to todo page");
        driver.findElement(By.id("todo")).click();
        Thread.sleep(1000);

        System.out.println("Adding Todo Items");
        addItems(driver);

        System.out.println("Deleting item");
        Thread.sleep(3000);
        driver.findElement(By.id("delete")).click();
        Thread.sleep(1000);

        System.out.println("Updating item");
        driver.findElement(By.id("update")).click();
        WebElement description = driver.findElement(By.id("updateDesc"));
        description.clear();
        Thread.sleep(3000);
        description.sendKeys(" Updated");
        WebElement date = driver.findElement(By.id("updateTarget"));
        date.sendKeys("07072020");
        driver.findElement(By.id("save")).click();

        System.out.println("Navigating to home page..");
        Thread.sleep(2000);
        driver.findElement(By.linkText("Home")).click();
        Thread.sleep(2000);


        System.out.println("Welcome message clicked...");
        driver.findElement(By.id("welcome")).click();
        System.out.println("Welcome message displayed");
        Thread.sleep(3000);

        System.out.println("Logging out .....");
        driver.findElement(By.linkText("Logout")).click();
        System.out.println("Logged out!");

    }

    private static void addItems(WebDriver driver) {
        Queue<String> queue = itemList();
        int i = 0;

        while (!queue.isEmpty()) {

            i++;
            driver.findElement(By.id("add")).click();
            driver.findElement(By.id("updateDesc")).sendKeys(queue.poll());
            WebElement date = driver.findElement(By.id("updateTarget"));
            date.sendKeys(queue.poll());
            driver.findElement(By.id("save")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Item " + i + " added. ");

        }
    }

    private static Queue<String> itemList() {

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
