//package base;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.*;
//
//import java.time.Duration;
//
//public class Base {
//
//    public static WebDriver driver;
//    public static WebDriverWait wait;
//
//    public void setup() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        driver.get("https://springexplorers.vercel.app/");
//    }
//
//    public void tearDown() {
//        driver.quit();
//    }
//
//    // Delay helper
//    public void pause(int ms) {
//        try {
//            Thread.sleep(ms);
//        } catch (InterruptedException e) {}
//    }
//}

//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//
//public class Base {
//
//    public static WebDriver driver;
//    public static WebDriverWait wait;
//
//    public void setup() {
//
//        System.out.println("🚀 Launching Browser...");
//
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        System.out.println("Opening ToDo Application...");
//        driver.get("https://springexplorers.vercel.app/");
//
//        pause(2000);
//        System.out.println("Application Loaded Successfully");
//    }
//
//    public void tearDown() {
//        System.out.println("Closing Browser...");
//        driver.quit();
//    }
//
//    public void pause(int ms) {
//        try { Thread.sleep(ms); } catch (Exception e) {}
//    }
//}



package base;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ConfigReader;

import java.time.Duration;

public class Base {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public void setup() {

        String browser = ConfigReader.getBrowser();

        System.out.println("---> Launching Browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        else {
            System.out.println("Invalid browser, defaulting to Chrome");
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Opening ToDo Application...");
        driver.get("https://springexplorers.vercel.app/");

        pause(2000);
        System.out.println("Application Loaded Successfully");
    }

    public void tearDown() {
        System.out.println("Closing Browser...");
        driver.quit();
    }

    public void pause(int ms) {
        try { 
        	Thread.sleep(ms);
        	} catch (Exception e) {}
    }
}