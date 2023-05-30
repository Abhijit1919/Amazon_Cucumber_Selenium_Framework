Feature: Open Amazon and finaliaz order

  Background:
    Given the user navigate to www.amazon.com
    When navigate to page Cell phone and Accessories

  @Regression
  @AddAllProductInExcel
  Scenario: Add to cart prodcut
    And Get ProductDetails

#  @Regression
#  @Select4thProduct
#  Scenario: Select 4th Product and check price till cart
#    And Select the “4th” product displayed in row one in the results page
#    Then Verify the title of the product is same as the product title displayed on the results page
#    Then Verify the price of the product is same as the price of the product displayed on the results page
#    And Click on Add to Cart
#    Then Click on Product to chevkout and Verify Login Page is Opend







