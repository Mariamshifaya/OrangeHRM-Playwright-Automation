package pages;

import com.microsoft.playwright.Page;
import utils.ConfigReader;

public class LoginPage {

    private Page page;

    ConfigReader config = new ConfigReader();

    // Constructor
    public LoginPage(Page page) {
        this.page = page;
    }

    // Locators
    private String txtUsername = "input[name='username']";
    private String txtPassword = "input[name='password']";
    private String btnLogin = "button[type='submit']";
    private String errorMessage = ".oxd-alert-content-text";

    // Enter Username
    public void enterUsername(String username) {
        page.waitForSelector(txtUsername);
        page.fill(txtUsername, username);
    }

    // Enter Password
    public void enterPassword(String password) {
        page.waitForSelector(txtPassword);
        page.fill(txtPassword, password);
    }

    // Click Login
    public void clickLogin() {
        page.waitForSelector(btnLogin);
        page.click(btnLogin);
    }

    // Login using values from config.properties
    public void login() {

        enterUsername(config.getProperty("username"));
        enterPassword(config.getProperty("password"));
        clickLogin();

    }

    // Login using custom credentials
    public void login(String username, String password) {


            page.fill(txtUsername, username);
            page.fill(txtPassword, password);

            page.click(btnLogin);

            page.waitForTimeout(3000);

        //enterUsername(username);
        //enterPassword(password);
       // clickLogin();

    }

    // Read Invalid Login Message
    public String getErrorMessage() {

        page.waitForSelector(errorMessage);

        return page.locator(errorMessage).textContent();

    }

}