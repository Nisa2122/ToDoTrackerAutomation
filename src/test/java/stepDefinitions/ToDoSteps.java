//package stepDefinitions;
//
//import base.Base;
//import pages.ToDoPage;
//import dataDriven.ExcelUtils;
//
//import io.cucumber.java.en.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//
//import java.util.List;
//
//public class ToDoSteps extends Base {
//
//    ToDoPage page;
//
//    @Given("User opens the application")
//    public void openApp() {
//        setup();
//        page = new ToDoPage(driver, wait);
//    }
//
//    // ✅ CREATE TASKS (POI)
//    @When("User creates tasks from Excel")
//    public void createTasks() {
//
//        System.out.println("📥 Reading data from Excel...");
//
//        int rows = ExcelUtils.getRowCount();
//
//        for (int i = 1; i <= rows; i++) {
//
//            System.out.println("➡ Creating Task Row: " + i);
//
//            page.enterTask(ExcelUtils.getData(i, 0));
//            pause(1000);
//
//            page.setDate();
//            pause(1000);
//
//            page.setTime();
//            pause(1000);
//
//            page.setDuration(
//                    ExcelUtils.getData(i, 1),
//                    ExcelUtils.getData(i, 2),
//                    ExcelUtils.getData(i, 3)
//            );
//            pause(1000);
//
//            page.selectPriority(ExcelUtils.getData(i, 4));
//            pause(1000);
//
//            page.clickAdd();
//            pause(3000);
//        }
//
//        List<WebElement> tasks = driver.findElements(By.xpath("//ul[@id='taskList']/li"));
//        Assert.assertTrue(tasks.size() > 0, "❌ No tasks created");
//    }
//
//    // ✅ UPDATE
//    @When("User updates a task")
//    public void updateTask() {
//
//        System.out.println("✏ Updating Task...");
//
//        page.clickEdit();
//        pause(2000);
//
//        page.enterTask("Updated Meeting");
//        page.selectPriority("Medium");
//        page.clickAdd();
//
//        pause(3000);
//    }
//
//    // ✅ COMPLETE
//    @When("User marks task as complete")
//    public void completeTask() {
//
//        System.out.println("✅ Completing Task...");
//        page.clickComplete();
//        pause(3000);
//    }
//
//    // ✅ TOTAL
//    @When("User calculates total duration")
//    public void calcTotal() {
//
//        System.out.println("🧮 Calculating Total...");
//        page.calculateTotal();
//        pause(2000);
//    }
//
//    @Then("Total duration should be displayed")
//    public void validateTotal() {
//
//        String total = page.getTotal();
//        System.out.println("📊 Total: " + total);
//
//        Assert.assertNotNull(total);
//    }
//
//    // ✅ COUNTDOWN
//    @Then("Countdown should be visible")
//    public void checkCountdown() {
//
//        String text = driver.findElement(By.id("countdownInfo")).getText();
//        System.out.println("⏳ Countdown: " + text);
//
//        Assert.assertTrue(text.length() > 0);
//    }
//
//    // ✅ DELETE
//    @Then("User deletes a task")
//    public void deleteTask() {
//
//        System.out.println("🗑 Deleting Task...");
//        page.clickDelete();
//        pause(3000);
//
//        tearDown();
//    }
//}


package stepDefinitions;

import base.Base;

import pages.ToDoPage;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class ToDoSteps extends Base {

    ToDoPage page;

    @Given("User opens the application")
    public void openApp() {
        setup();
        page = new ToDoPage(driver, wait);
    }

    @When("User creates tasks from Excel")

    public void createTasks() {
        page.createTasksFromExcel();
    }

    @When("User updates a task")
    public void updateTask() {

        page.clickEdit();
        pause(2000);

        page.enterTask("Updated Meeting");
        page.selectPriority("Medium");
        page.clickAdd();
    }

    @When("User marks task as complete")
    public void completeTask() {
        page.clickComplete();
    }

    @When("User calculates total duration")
    public void calcTotal() {
        page.calculateTotal();
    }

    @Then("Total duration should be displayed")
    public void validateTotal() {

        String total = page.getTotal();
        System.out.println("Total: " + total);

        Assert.assertNotNull(total);
    }

    @Then("Countdown should be visible")
    public void checkCountdown() {

        String text = page.getCountdown(); 

        System.out.println("Countdown: " + text);

        Assert.assertTrue(text.length() > 0);
    }

    @Then("User deletes a task")
    public void deleteTask() {

        page.clickDelete();
        pause(2000);
        tearDown();
    }
}
