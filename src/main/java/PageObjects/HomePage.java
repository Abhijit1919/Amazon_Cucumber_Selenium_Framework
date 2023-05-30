package PageObjects;

import Utilities.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver webDriver;
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(new AjaxElementLocatorFactory(webDriver, 15), this);
    }
    Wait wait;
    @FindBy(xpath = "//*[@id='nav-logo-sprites']")
    private WebElement amazonLogo;
    @FindBy(xpath = "//*[@id='nav-hamburger-menu']/span")
    private WebElement btnAll;

    @FindBy(xpath = "//*[@id='hmenu-content']/ul[1]/li[7]/a/div[text()='Electronics']")
    private WebElement btnElectronics;

    @FindBy(xpath = "//*[@id='hmenu-content']/ul[5]/li[6]/a[text()='Cell Phones & Accessories']")
    private WebElement btnCellPhonesAccessories;

    @FindBy(xpath = "//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[1]/div/span/div/div/span[text()='Results']")
    private WebElement btnResults;

    @FindBy(xpath = "//*[@id='h']/div/a/img")
    private WebElement errorPage;


    public boolean defaultHomePageIsDisplayed() {
        amazonLogo.isDisplayed();
        return true;
    }

    public void clickAll() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOf(btnAll));
        btnAll.isDisplayed();
        btnAll.click();
        //checkErrorPage();
    }
    public void clickbtnElectronics(){
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        try {
            wait.until(ExpectedConditions.visibilityOf(btnElectronics));
            btnElectronics.isDisplayed();
            btnElectronics.click();
            //checkErrorPage();
            Wait.untilAjaxCallIsDone(webDriver, 10L);
        } catch (final Exception e) {
            e.getMessage();
        }

    }

    public void clickbtnCellPhonesAccessories(){
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        try{
            wait.until(ExpectedConditions.visibilityOf(btnCellPhonesAccessories));
            btnCellPhonesAccessories.isDisplayed();
            btnCellPhonesAccessories.click();
            checkErrorPage();
        } catch (final Exception e){
            e.getMessage();
        }
    }
    public boolean cellPhonesAccessoriesDisplayed() {
        Wait.untilElementIsVisible(webDriver, btnResults, 8L);
        btnResults.isDisplayed();
        return true;
    }
    public void checkErrorPage(){
        try{
            if (errorPage.isDisplayed()){
                webDriver.navigate().back();
                clickAll();
                clickbtnElectronics();
                btnCellPhonesAccessories.click();
            }
        } catch (final Exception e){
            e.getMessage();
        }
    }

}


