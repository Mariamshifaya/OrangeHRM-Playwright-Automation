package pages;

import com.microsoft.playwright.Page;

public class PIMPage {

    private Page page;

    public PIMPage(Page page) {
        this.page = page;
    }

    // Locators
    private String pimMenu = "a[href='/web/index.php/pim/viewPimModule']";
    private String addEmployeeButton = "button:has-text('Add')";
    private String firstName = "input[name='firstName']";
    private String lastName = "input[name='lastName']";
    private String saveButton = "button[type='submit']";
    private String employeeDetailsHeader = "h6:has-text('Personal Details')";

    // Open PIM Module
    public void openPIM() {

        page.click(pimMenu);

        page.waitForLoadState();

    }

    // Click Add Employee
    public void clickAddEmployee() {

        page.click(addEmployeeButton);

        page.waitForLoadState();

    }

    // Add Employee
    public void addEmployee(String fname, String lname) {

        page.waitForSelector(firstName);

        page.fill(firstName, fname);

        page.fill(lastName, lname);

        System.out.println("Before Save : " + page.url());

        page.click(saveButton);

        page.waitForLoadState();

        System.out.println("After Save : " + page.url());

    }

    // Verify Employee Created
    public boolean isEmployeeCreated() {

        try {

            page.waitForURL("**viewPersonalDetails**");

            return page.url().contains("viewPersonalDetails");

        } catch (Exception e) {

            return false;

        }

    }

}