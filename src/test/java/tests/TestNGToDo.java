package tests;

import org.testng.Assert;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.ToDoPage;
import base.Base;

public class TestNGToDo extends Base {

	ToDoPage page;

	@BeforeClass
	public void openApp() {
		setup();
		page = new ToDoPage(driver, wait);
	}

	@Test(priority = 1)
	public void createTasks() {
		page.createTasksFromExcel();
	}

	@Test(dependsOnMethods = "createTasks")
	public void updateTask() {

		page.clickEdit();
		pause(2000);

		page.enterTask("Updated Meeting");
		page.selectPriority("Medium");
		page.clickAdd();
	}

	@Test(dependsOnMethods = "updateTask")
	public void completeTask() {
		page.clickComplete();
	}

	@Test(priority = 2, dependsOnMethods = "completeTask")
	public void calcTotal() {
		page.calculateTotal();
	}

	public void validateTotal() {

		String total = page.getTotal();
		System.out.println("Total: " + total);

		Assert.assertNotNull(total);
	}

	@Test(priority = 10)
	public void checkCountdown() {

		String text = page.getCountdown();

		System.out.println("Countdown: " + text);

		Assert.assertTrue(text.length() > 0);
	}

	@AfterClass
	public void deleteTask() {

		page.clickDelete();
		pause(2000);
		tearDown();
	}

}
