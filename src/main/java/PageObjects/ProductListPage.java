package PageObjects;

import org.apache.log4j.Logger;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;


public class ProductListPage {
    private static Logger log = Logger.getLogger(ProductListPage.class.getName());
    private final WebDriver webDriver;


    public ProductListPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    @FindBy(xpath = "//div[contains(@data-asin,'B0')]")
    private List<WebElement> allProductList;

    @FindBy(xpath = "//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[5]/div/div/div/div/div[2]/span/a/div/img")
    private WebElement forthProduct;

    @FindBy(xpath = "//*[@id='search']/div[1]/div[1]/div/span[1]/div[1]/div[5]/div/div/div/div/div[3]/div[1]/h2/a/span")
    private WebElement forthProductName;

    @FindBy(xpath = "//div[contains(@data-asin,'B0')][4]//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/div/a/span/span/span[@class='a-price-whole']")
    private WebElement aPriceWhole;
    @FindBy(xpath = "//div[contains(@data-asin,'B0')][4]//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/div/a/span/span/span[@class='a-price-fraction']")
    private WebElement aPriceFraction;

    @FindBy(xpath = "//*[@id='corePrice_feature_div']/div/span[1]/span[2]/span[2]")
    private WebElement a2ndPagePriceWhole;

    @FindBy(xpath = "//*[@id='corePrice_feature_div']/div/span[1]/span[2]/span[3]")
    private WebElement a2ndPagePriceFraction;


    @FindBy(xpath = "(//*[@id='productTitle'])[1]")
    private WebElement nextPageProductName;

    @FindBy(xpath = "//*[@id='add-to-cart-button']")
    private WebElement btnAddToCart;

    @FindBy(xpath = "//*[@id='attach-accessory-cart-subtotal']")
    private WebElement checkoutPrice;


    @FindBy(xpath = "//*[@id='attach-sidesheet-checkout-button']/span/input")
    private WebElement procceedBtn;


    @FindBy(xpath = "//h1[contains(text(),'Sign in')]")
    private WebElement signIN;

    @FindBy(xpath = "//*[@id='attachDisplayAddBaseAlert']/div/h4")
    private WebElement addBaseAlert;


    public void allProductOnPage() {
        try {
            String [] row_header = {"Product Name", "Product Price", "Best Seller – Yes/No"};

           // FileInputStream fi = new FileInputStream(".\\datafiles\\ProductList.xlsx");
            FileOutputStream fos = new FileOutputStream( new File(".\\datafiles\\ProductList.xlsx"));
            XSSFWorkbook workBook = new XSSFWorkbook();
            XSSFSheet sheet = workBook.createSheet("Products");

            Row header = sheet.createRow(0);

            //Create header row
            for (int i=0; i<row_header.length; i++){
                Cell cell = header.createCell(i);
                cell.setCellValue(row_header[i]);
            }
            //set the data to excel
            for (int i = 1; i <= allProductList.size(); i++) {
                Row row = sheet.createRow(i);
                String productName = webDriver.findElement(By.xpath("//div[contains(@data-asin,'B0')][" + i + "]//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/h2")).getText();

                System.out.println(productName);
                Cell pNameCell = row.createCell(0);
                pNameCell.setCellValue(webDriver.findElement(By.xpath("//div[contains(@data-asin,'B0')][" + i + "]//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/h2")).getText());

                int productPrice = webDriver.findElements(By.xpath("//div[contains(@data-asin,'B0')][" + i + "]//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/div/a/span/span/span[@class='a-price-whole']")).size();
                if(productPrice == 0){
                    Cell pPriceCell = row.createCell(1);
                    pPriceCell.setCellValue("No Price");
                }else {
                    String ProductPriceWhole = webDriver.findElement(By.xpath("//div[contains(@data-asin,'B0')][" + i + "]//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/div/a/span/span/span[@class='a-price-whole']")).getText();
                    String productPriceInfraction = webDriver.findElement(By.xpath("//div[contains(@data-asin,'B0')][" + i + "]//div[@class='a-section a-spacing-small puis-padding-left-small puis-padding-right-small']/div/div/a/span/span/span[@class='a-price-fraction']")).getText();
                    String totalPrice = ProductPriceWhole + "." + productPriceInfraction;
                    Cell pPriceCell = row.createCell(1);
                    pPriceCell.setCellValue(totalPrice);
                }


                int bestSeller = webDriver.findElements(By.xpath("//div[contains(@data-asin,'B0')][" + i + "]/div/div/div/div/div/span/div/span/span/span/span[text()='Best Seller']")).size();
                Cell bSellerCell = row.createCell(2);
                if (bestSeller == 0) {
                    bSellerCell.setCellValue("NO");
                } else {
                    bSellerCell.setCellValue("YES");
                }
            }

            workBook.write(fos);
            workBook.close();
            fos.close();

        } catch (final Exception e) {
            log.info("Failed to capture data in excel");
        }

    }

    public String[] select4ThProduct() {
        try {
            Thread.sleep(10);
            final String[] strArr = new String[2];
            Thread.sleep(6);
            String forthProName = forthProductName.getText();
            Thread.sleep(6);
            strArr[0] = forthProName.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").toUpperCase();
            Thread.sleep(6);
            String wholeName = aPriceWhole.getText();
            Thread.sleep(6);
            String fractionName = aPriceFraction.getText();
            strArr[1] = wholeName + fractionName;
            return strArr;
        } catch (final Exception e) {

        }
        return null;
    }

    public boolean navigate2ndPageToGetProNmae(final String name) {
        try {
            forthProduct.click();
            return checkProductName(name, nextPageProductName);
        } catch (final Exception e) {
            log.info("Failed to Navigate navigate2ndPageToGetProNmae");
        }
        return false;
    }

    public boolean navigate2ndPageToGetProPrice(final String price) {
        try {
            return checkProductPrice(price, a2ndPagePriceWhole, a2ndPagePriceFraction);
        } catch (final Exception e) {
            log.info("Failed to Navigate navigate2ndPageToGetProPrice");
        }
        return false;
    }

    public boolean navigate3rdPageToGetProPrice(final String price) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        try {
            Thread.sleep(3);
            if (btnAddToCart.isDisplayed()) {
                btnAddToCart.click();
                wait.until(ExpectedConditions.visibilityOf(addBaseAlert));
                Thread.sleep(15);
                final String thirddPagePrice = checkoutPrice.getText();
                final String finalPrice = thirddPagePrice.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "");
                if (price.contentEquals(finalPrice)) {
                    return true;
                }
            }
        } catch (final Exception e) {
            log.info("Failed to Navigate navigate3rdPageToGetProPrice");
        }
        return false;
    }

    public boolean navigateToProceed() {
        try {
            Thread.sleep(3);
            procceedBtn.click();
            Thread.sleep(3);
            if (signIN.isDisplayed()) {
                return true;
            }
        } catch (final Exception e) {
            log.info("Failed to Navigate navigateToProceed");
        }
        return false;
    }

    public boolean checkProductPrice(final String price, WebElement elementWhole, WebElement elementFreac) {
        try {
            String nextPageWholePrice = elementWhole.getText();
            String nextPageFractionPrice = elementFreac.getText();
            if ((nextPageWholePrice + nextPageFractionPrice).contentEquals(price)) {
                return true;
            } else {
                return false;
            }
        } catch (final Exception e) {
            log.info("Failed to Navigate checkProductPrice");
        }
        return false;
    }

    public boolean checkProductName(final String name, WebElement element) {
        try {
            String nextProName = element.getText();
            final String replaceString = nextProName.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "").toUpperCase();
            if (name.contentEquals(replaceString)) {
                return true;
            } else {
                return false;
            }
        } catch (final Exception e) {
            log.info("Failed to Navigate checkProductName");
        }
        return false;
    }
}
