@test
Feature: Check Login functionality with OrangeHRM

  @tag1
  Scenario: Verify Successful login to Orange HRM
    Given I visit the bestbuy url "https://www.bestbuy.com"
    And I Search for the Product "Pillow" on HomePage
    And I filter out the Products from ProductList page using Price Filter
    And I select the first available product from List
    And I select AddToCart option for the product on Product Description Page
    And I move to View Cart Page
    Then I update Shipping Location to "07645"
    And I click on checkout Option
    And I select "ContinueAsGuest" option
    Then I Enter Following Details
      | FirstName | LastName | Address2       | zipCode | EmailAddress   | PhoneNumber |
      | Abhijeet  | Gupta    | Walking Street |   07645 | abc2@gmail.com |  2123456172 |
    Then I Continue to payment Information
    And I Enter basic Payment Details
      | cardNumber       | CVV | monthExp | yearExp |
      | 4111111111111111 | 123 |       03 |    2022 |
