package com.automation.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class BadLoginPivotalTest {

    WebDriver driver;
    String email = "";

    @Test
    public void loginWithInvalidCredentials() {
        // Configure chrome driver.
        WebDriverManager.chromedriver().setup();

        // Initialize chrome driver.
        driver = new ChromeDriver();

        // Load URL.
        driver.get("https://www.pivotaltracker.com/signin");

        // Locate Username webelement
        WebElement usernameTextBox = driver.findElement(By.cssSelector("#credentials_username"));

        // Fill username on webelement
        usernameTextBox.sendKeys(email);

        // Locate action button
        WebElement actionButton = driver.findElement(By.cssSelector(".app_signin_action_button"));

        // Clicks on action button
        actionButton.click();

        // Locate Password webelement
        WebElement passwordTextBox = driver.findElement(By.cssSelector("#credentials_password"));

        // Fill password on webelement
        passwordTextBox.sendKeys("12341234123123");

        // Locate login button
        WebElement loginButton = driver.findElement(By.cssSelector(".app_signin_action_button"));

        // Click on login
        loginButton.click();

        // Locate message error
        WebElement errorMessage = driver.findElement(By.cssSelector(".app_signin_error"));

        // Assert message error
        String actualErrorMessage = errorMessage.getText();
        String expectedErrorMessage = "Invalid username/password";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @AfterMethod
    public void quitDriver() {
        driver.close();
    }
}
