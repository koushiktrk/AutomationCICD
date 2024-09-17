
@tag
Feature: Purchase the order from the ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page
  
  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And  Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on confirmationPage

    Examples: 
      | username         | password 			|	productName	|	
      | don123@gmail.com | Automation@123	|	ZARA COAT 3	| 
      
