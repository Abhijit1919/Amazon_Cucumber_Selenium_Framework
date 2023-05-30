package StepDefinitions;

import PageObjects.HomePage;
import Utilities.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class HomeSteps {

    HomePage homePage;
    TestContext testContext;

    public HomeSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    @Given("the user navigate to www.amazon.com")
    public void the_user_navigate_to_www_amazon_com() {
        Assert.assertTrue(homePage.defaultHomePageIsDisplayed());
    }

    @When("navigate to page Cell phone and Accessories")
    public void navigate_to_page_cell_phone_and_accessories() {
        homePage.clickAll();
        homePage.clickbtnElectronics();
        homePage.clickbtnCellPhonesAccessories();
        Assert.assertTrue(homePage.cellPhonesAccessoriesDisplayed());
    }


}
