package Managers;

import PageObjects.HomePage;
import PageObjects.ProductListPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private final WebDriver webDriver;
    private HomePage homePage;
    private ProductListPage productListPage;

    public PageObjectManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Short Hand If...Else
    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(webDriver) : homePage;
    }

    //General If...Else
//    public LoginPage getLoginPage() {
//
//        if (loginPage == null) {
//            loginPage = new LoginPage(webDriver);
//        }
//        return loginPage;
//    }
//
    public ProductListPage getProductListPage() {
        return (productListPage == null) ? productListPage = new ProductListPage(webDriver) : productListPage;
    }
//
//    public ProductDetailPage getProductDetailPage() {
//        if (productDetailPage == null) productDetailPage = new ProductDetailPage(webDriver);
//        return productDetailPage;
//    }
}
