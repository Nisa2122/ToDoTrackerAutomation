package singleClassSeleAutoToDo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;


public class ToDoAutomationTest {

    static WebDriver driver;
    static WebDriverWait wait;

    // Delay utility
    static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }

    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://springexplorers.vercel.app/");
        System.out.println("Application opened");
        pause(2000);

        // Create 3 tasks
        createTask("Meeting");
        createTask("Workout");
        createTask("Study");

        // Perform CRUD on FIRST task only
        editTask();
        updateTask();
        markTaskComplete();

        calculateTotalDuration();
        checkCountdown();

        verifyPersistence();

        deleteTask();

        validateOtherTasks();

        driver.quit();
        System.out.println("Automation completed");
    }

    // =========================
    // CREATE TASK
    // =========================
    static void createTask(String taskName) {

        System.out.println("Creating task: " + taskName);

        WebElement taskInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taskInput")));
        taskInput.clear();
        taskInput.sendKeys(taskName);
        pause(1000);

        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('dateInput').value='2026-05-10'");
        pause(1000);

        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('startTimeInput').value='10:00'");
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

        System.out.println("Task created: " + taskName);
    }

    // =========================
    // EDIT FIRST TASK
    // =========================
    static void editTask() {

        System.out.println("Editing first task");

        try {
            WebElement editBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='taskList']/li[1]/div/i[1]")
                    ));
            editBtn.click();
            pause(2000);

            System.out.println("Edit clicked");

        } catch (Exception e) {
            System.out.println("Edit button issue");
        }
    }

    // =========================
    // UPDATE FIRST TASK
    // =========================
    static void updateTask() {

        System.out.println("Updating first task");

        WebElement task = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("taskInput")));

        task.clear();
        pause(1000);
        task.sendKeys("Updated Meeting");
        pause(1000);

        Select priority = new Select(driver.findElement(By.id("priorityInput")));
        priority.selectByVisibleText("Medium");
        pause(1000);

        driver.findElement(By.id("addBtn")).click();
        pause(2000);

        System.out.println("Task updated");
    }

    // =========================
    // COMPLETE FIRST TASK
    // =========================
    static void markTaskComplete() {

        System.out.println("Marking first task complete");

        try {
            WebElement completeBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='taskList']/li[1]/div/i[2]")
                    ));
            completeBtn.click();
            pause(2000);

            System.out.println("Task marked complete");

        } catch (Exception e) {
            System.out.println("Complete button issue");
        }
    }

    // =========================
    // TOTAL DURATION
    // =========================
    static void calculateTotalDuration() {

        System.out.println("Calculating total duration");

        driver.findElement(By.id("calcBtn")).click();
        pause(1500);

        String total = driver.findElement(By.id("totalDuration")).getAttribute("value");

        System.out.println("Total Duration: " + total);
    }

    // =========================
    // COUNTDOWN
    // =========================
    static void checkCountdown() {

        System.out.println("Checking countdown");

        pause(1500);

        String text = driver.findElement(By.id("countdownInfo")).getText();

        System.out.println("Countdown: " + text);
    }

    // =========================
    // PERSISTENCE
    // =========================
    static void verifyPersistence() {

        System.out.println("Checking persistence");

        driver.navigate().refresh();
        pause(2000);

        List<WebElement> tasks = driver.findElements(By.xpath("//ul[@id='taskList']/li"));

        boolean found = false;

        for (int i = 0; i < tasks.size(); i++) {
            String text = tasks.get(i).getText();
            if (text.contains("Updated Meeting")) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Persistence passed");
        } else {
            System.out.println("Persistence failed");
        }

        Object storage = ((JavascriptExecutor) driver)
                .executeScript("return localStorage.getItem('tasks')");

        System.out.println("LocalStorage: " + storage);
    }

    // =========================
    // DELETE FIRST TASK
    // =========================
    static void deleteTask() {

        System.out.println("Deleting first task");

        try {
            WebElement deleteBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[@id='taskList']/li[1]/div/i[3]")
                    ));
            deleteBtn.click();
            pause(2000);

            System.out.println("Task deleted");

        } catch (Exception e) {
            System.out.println("Delete button issue");
        }
    }

    // =========================
    // VALIDATE OTHER TASKS
    // =========================
    static void validateOtherTasks() {

        System.out.println("Checking remaining tasks");

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
            System.out.println("Other Task are correct");
        } else {
            System.out.println("Some tasks are missing");
        }
    }
}






