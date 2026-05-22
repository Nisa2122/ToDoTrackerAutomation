package pages;

import org.openqa.selenium.*;

import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

import utils.ExcelUtils;

public class ToDoPage {

    WebDriver driver;
    WebDriverWait wait;

    public ToDoPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

        // ✅ IMPORTANT
        PageFactory.initElements(driver, this);
    }

    // ✅ ELEMENTS

    @FindBy(id = "taskInput")
    WebElement taskInput;

    @FindBy(id = "addBtn")
    WebElement addBtn;

    @FindBy(id = "calcBtn")
    WebElement calcBtn;

    @FindBy(xpath = "(//i[contains(@class,'fa-edit')])[1]")
    WebElement editBtn;

    @FindBy(xpath = "(//i[contains(@class,'fa-check')])[1]")
    WebElement completeBtn;

    @FindBy(xpath = "(//i[contains(@class,'fa-trash')])[1]")
    WebElement deleteBtn;

    @FindBy(id = "priorityInput")
    WebElement priorityDropdown;

    @FindBy(id = "routineInput")
    WebElement routineCheckbox;

    @FindBy(id = "totalDuration")
    WebElement totalDuration;

    @FindBy(id = "countdownInfo")
    WebElement countdown;
    
   
    @FindBy(id="hoursInput")
    WebElement hoursInput;
    @FindBy(id="minutesInput")
    WebElement minutesInput;
    @FindBy(id="secondsInput")
    WebElement secondsInput;
    
    
    
    // ✅ ACTIONS
    public void slow() {
        try { 
        	Thread.sleep(1200); 
        } catch (Exception e) {}
    }

    public void enterTask(String name) {
        System.out.println("Entering Task: " + name);
        wait.until(ExpectedConditions.visibilityOf(taskInput)).clear();
        taskInput.sendKeys(name);
        slow();
    }

    public void setDate() {
        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('dateInput').value='2026-05-10'");
        slow();
    }

    public void setTime() {
        ((JavascriptExecutor) driver)
                .executeScript("document.getElementById('startTimeInput').value='10:00'");
        slow();
    }

    public void setDuration(String h, String m, String s) {
//        driver.findElement(By.id("hoursInput")).sendKeys(h);
//        driver.findElement(By.id("minutesInput")).sendKeys(m);
//        driver.findElement(By.id("secondsInput")).sendKeys(s);
    	
    	
    	hoursInput.sendKeys(h);
    	minutesInput.sendKeys(m);
    	secondsInput.sendKeys(s);
        slow();
    }

    public void selectPriority(String value) {
        new Select(priorityDropdown).selectByVisibleText(value);
        slow();
    }

    public void selectRoutineIfNeeded(String taskName) {
        if (taskName.equalsIgnoreCase("travel")) {
            System.out.println("Selecting Routine Task");

            if (!routineCheckbox.isSelected()) {
                routineCheckbox.click();
            }
        }
        slow();
    }

    public void clickAdd() {
        addBtn.click();
        slow();
    }

    public void clickEdit() {
        wait.until(ExpectedConditions.visibilityOf(editBtn)).click();
        slow();
    }

    public void clickComplete() {
        wait.until(ExpectedConditions.visibilityOf(completeBtn)).click();
        slow();
    }

    public void clickDelete() {
        wait.until(ExpectedConditions.visibilityOf(deleteBtn)).click();
        slow();
    }

    public void calculateTotal() {
        calcBtn.click();
        slow();
    }

    public String getTotal() {
        return totalDuration.getAttribute("value");
        
    }

    public String getCountdown() {
        return countdown.getText();
        
    }

    // ✅ MAIN FLOW (DATA DRIVEN)

    public void createTasksFromExcel() {

        int rows = ExcelUtils.getRowCount();

        for (int i = 1; i <= rows; i++) {

            String name = ExcelUtils.getData(i, 0);
            String h = ExcelUtils.getData(i, 1);
            String m = ExcelUtils.getData(i, 2);
            String s = ExcelUtils.getData(i, 3);
            String priority = ExcelUtils.getData(i, 4);

            if (name == null || name.trim().isEmpty()) continue;

            System.out.println("Creating Task: " + name);

            enterTask(name);
            setDate();
            setTime();
            setDuration(h, m, s);
            selectPriority(priority);
            selectRoutineIfNeeded(name);
            clickAdd();
        }
    }
    
    

}





