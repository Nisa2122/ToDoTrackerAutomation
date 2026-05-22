package withTestngSingleClass;

import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class ToDoTestNG {

    WebDriver driver;
    WebDriverWait wait;

    // Delay utility
    void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }

   
    // SETUP (runs first)
    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://springexplorers.vercel.app/");
        System.out.println("Application opened");
        pause(2000);
    }

    // CREATE TASKS
    
    @Test(priority = 1)
    public void createTasks() {

        createTask("Meeting");
        createTask("Workout");
        createTask("Study");

        System.out.println("All tasks created");
    }

    // Reusable method (NOT a test)
    public void createTask(String taskName) {

        System.out.println("Creating task: " + taskName);

        WebElement taskInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taskInput")));
        taskInput.clear();
        taskInput.sendKeys(taskName);
        pause(1000);

        ((JavascriptExecutor) driver).executeScript("document.getElementById('dateInput').value='2026-05-10'");
        pause(1000);

        ((JavascriptExecutor) driver).executeScript("document.getElementById('startTimeInput').value='10:00'");
        pause(1000);

        driver.findElement(By.id("hoursInput")).clear();
        driver.findElement(By.id("minutesInput")).clear();
        driver.findElement(By.id("secondsInput")).clear();

        driver.findElement(By.id("hoursInput")).sendKeys("1");
        driver.findElement(By.id("minutesInput")).sendKeys("10");
        driver.findElement(By.id("secondsInput")).sendKeys("5");
        pause(1000);

        Select priority = new Select(driver.findElement(By.id("priorityInput")));
        priority.selectByVisibleText("High");
        pause(1000);

        driver.findElement(By.id("addBtn")).click();
        pause(2000);
    }

    // EDIT
    
    @Test(priority = 2)
    public void editTask() {

        System.out.println("Editing first task");

        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
        		By.xpath("//*[@id='taskList']/li[1]/div/i[1]") ));
        editBtn.click();
        pause(2000);
    }

    // =========================
    // UPDATE
    // =========================
    @Test(priority = 3)
    public void updateTask() {

        System.out.println("Updating first task");

        WebElement task = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("taskInput")));

        task.clear();
        task.sendKeys("Updated Meeting");
        pause(1000);

        Select priority = new Select(driver.findElement(By.id("priorityInput")));
        priority.selectByVisibleText("Medium");
        pause(1000);

        driver.findElement(By.id("addBtn")).click();
        pause(2000);
    }

    // =========================
    // COMPLETE
    // =========================
    @Test(priority = 4)
    public void markTaskComplete() {

        System.out.println("Marking task complete");

        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='taskList']/li[1]/div/i[2]")
                ));
        btn.click();
        pause(2000);
    }

    // =========================
    // TOTAL DURATION
    // =========================
    @Test(priority = 5)
    public void calculateTotalDuration() {

        driver.findElement(By.id("calcBtn")).click();
        pause(1500);

        String total = driver.findElement(By.id("totalDuration")).getAttribute("value");
        System.out.println("Total Duration: " + total);
    }

    // =========================
    // COUNTDOWN
    // =========================
    @Test(priority = 6)
    public void checkCountdown() {

        pause(1500);
        String text = driver.findElement(By.id("countdownInfo")).getText();

        System.out.println("Countdown: " + text);
    }

    // =========================
    // PERSISTENCE
    // =========================
    @Test(priority = 7)
    public void verifyPersistence() {

        driver.navigate().refresh();
        pause(2000);

        List<WebElement> tasks = driver.findElements(By.xpath("//ul[@id='taskList']/li"));

        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getText().contains("Updated Meeting")) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Persistence passed");
        } else {
            System.out.println("Persistence failed");
        }
    }

    // =========================
    // DELETE
    // =========================
    @Test(priority = 8)
    public void deleteTask() {

        WebElement deleteBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='taskList']/li[1]/div/i[3]")
                ));
        deleteBtn.click();
        pause(2000);

        System.out.println("Task deleted");
    }

    // =========================
    // VALIDATION
    // =========================
    @Test(priority = 9)
    public void validateOtherTasks() {

        List<WebElement> tasks = driver.findElements(By.xpath("//ul[@id='taskList']/li"));

        boolean workoutFound = false;
        boolean studyFound = false;

        for (int i = 0; i < tasks.size(); i++) {

            String text = tasks.get(i).getText();

            if (text.contains("Workout")) {
                workoutFound = true;
            }

            if (text.contains("Study")) {
                studyFound = true;
            }
        }

        if (workoutFound && studyFound) {
            System.out.println("Other tasks are correct");
        } else {
            System.out.println("Some tasks are missing");
        }
    }

    // =========================
    // TEARDOWN
    // =========================
    @AfterClass
    public void teardown() {
        driver.quit();
        System.out.println("Browser closed");
    }
}