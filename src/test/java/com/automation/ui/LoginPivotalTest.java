package com.automation.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginPivotalTest {

    WebDriver driver;
    String username = "";
    String email = "";
    String password = "";

    @Test
    public void loginWithValidCredentials() {
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
        passwordTextBox.sendKeys(password);

        // Locate login button
        WebElement loginButton = driver.findElement(By.cssSelector(".app_signin_action_button"));

        // Click on login
        loginButton.click();

        // Locate profile dropdown
        WebElement profileDropdown = driver.findElement(By.cssSelector("*[aria-label='Profile Dropdown']"));

        // Assert username
        String actualUsername = profileDropdown.getText();
        String expectedUsername = username;
        Assert.assertTrue(expectedUsername.equalsIgnoreCase(actualUsername));
    }

    @AfterMethod
    public void quitDriver() {
        driver.close();
    }
}
