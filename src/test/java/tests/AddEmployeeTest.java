package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;

public class AddEmployeeTest extends BaseClass {

    @Test

    public void verifyAddEmployee() {

        LoginPage loginPage = new LoginPage(getPage());

        DashboardPage dashboardPage = new DashboardPage(getPage());

        PIMPage pimPage = new PIMPage(getPage());

        // Login
        loginPage.login();
        getPage().waitForTimeout(3000);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard not displayed."
        );

        // Open PIM
        pimPage.openPIM();

        // Add Employee
        pimPage.clickAddEmployee();

        String firstName = "John" + System.currentTimeMillis();
        String lastName = "Smith";

        pimPage.addEmployee(firstName, lastName);

        // Verify Employee Created
        Assert.assertTrue(
                pimPage.isEmployeeCreated(),
                "Employee was not created."
        );

        System.out.println("✅ Employee Added Successfully");

    }

}