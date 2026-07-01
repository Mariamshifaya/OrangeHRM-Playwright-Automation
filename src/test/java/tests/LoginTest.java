package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelDataProvider;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseClass {

    @Test(
            priority = 1, dataProvider = "loginData",
            dataProviderClass = ExcelDataProvider.class
    )
    public void validLoginTest(String username, String password) {

        System.out.println("Username = [" + username + "]");
        System.out.println("Password = [" + password + "]");

        LoginPage loginPage = new LoginPage(getPage());
        DashboardPage dashboardPage = new DashboardPage(getPage());

        loginPage.login(username, password);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard is not displayed."
        );
    }
    @Test(priority = 2, retryAnalyzer = retry.RetryAnalyzer.class)
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(getPage());

        loginPage.login("Admin", "WrongPassword");

        String actualError = loginPage.getErrorMessage();

        Assert.assertTrue(
                actualError.contains("Invalid credentials"),
                "Invalid Login Test Failed"
        );

        System.out.println("✅ Invalid Login Passed");
    }

    @Test(priority = 3,retryAnalyzer = retry.RetryAnalyzer.class)
    public void blankLoginTest() {

        getPage().click("button[type='submit']");

        Assert.assertTrue(
                getPage().getByText("Required").first().isVisible(),
                "Required message is not displayed."
        );

        System.out.println("✅ Blank Login Passed");
    }
}