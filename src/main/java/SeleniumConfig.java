import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedList;
import java.util.Queue;

public class SeleniumConfig {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "/Users/utkarsh/driver/chromedriver");
        WebDriver driver = new ChromeDriver();
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
        Queue<String> queue = addList();
        int i = 1;
        while (!queue.isEmpty()) {
            driver.findElement(By.id("add")).click();
            driver.findElement(By.name("description")).sendKeys(queue.poll());
            WebElement date = driver.findElement(By.name("targetDate"));
            date.sendKeys(queue.poll());
            driver.findElement(By.id("save")).click();
            System.out.println("Items added: " + i);
            i++;
        }

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

    private static Queue<String> addList() {
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
