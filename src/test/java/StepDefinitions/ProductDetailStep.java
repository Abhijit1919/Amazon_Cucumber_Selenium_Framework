package StepDefinitions;

import PageObjects.ProductListPage;
import Utilities.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailStep {

    ProductListPage productListPage;
    private Map<String, String> data;
    TestContext testContext;

    private static String PRODUCT_NAME = "ProductName";
    private static String PRODUCT_PTICE = "ProductPrice";


    public ProductDetailStep(TestContext context) {
        data = new HashMap<>();
        testContext = context;
        productListPage = testContext.getPageObjectManager().getProductListPage();
    }

    @When("Get ProductDetails")
    public void get_product_details() throws IOException {
        productListPage.allProductOnPage();
    }

    @When("Select the “4th” product displayed in row one in the results page")
    public void select_the_4th_product_displayed_in_row_one_in_the_results_page() {
        String[] ProductName = productListPage.select4ThProduct();
        data.put(PRODUCT_NAME, ProductName[0]);
        data.put(PRODUCT_PTICE, ProductName[1]);
    }

    @Then("Verify the title of the product is same as the product title displayed on the results page")
    public void verify_the_title_of_the_product_is_same_as_the_product_title_displayed_on_the_results_page() {

        Assert.assertTrue(productListPage.navigate2ndPageToGetProNmae(data.get(PRODUCT_NAME)));
    }

    @Then("Verify the price of the product is same as the price of the product displayed on the results page")
    public void verify_the_price_of_the_product_is_same_as_the_price_of_the_product_displayed_on_the_results_page() {
        Assert.assertTrue(productListPage.navigate2ndPageToGetProPrice(data.get(PRODUCT_PTICE)));
    }

    @Then("Click on Add to Cart")
    public void click_on_add_to_cart() {
        Assert.assertTrue(productListPage.navigate3rdPageToGetProPrice(data.get(PRODUCT_PTICE)));
    }

    @Then("Click on Product to chevkout and Verify Login Page is Opend")
    public void click_on_product_to_chevkout_and_verify_login_page_is_opend() {
        Assert.assertTrue(productListPage.navigateToProceed());
    }
}
