package rahulshettyacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckOutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage cPage;
	public CartPage cp;
	public CheckOutPage chckPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {

		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {

		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {

		productCatalogue.addToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		cp = productCatalogue.goToCart();
		boolean flag = cp.verifyProductName(productName);
		Assert.assertTrue(flag);
		chckPage = cp.goToCheckout();
		chckPage.selectCountry("india");
		cPage = chckPage.submitOrder();
	}

	@Then("{string} message is displayed on confirmationPage")
	public void message_is_displayed_on_confirmationPage(String message) {
		String cnfrmMsg = cPage.getConfMessage();
		Assert.assertTrue(cnfrmMsg.equalsIgnoreCase(message));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String message) {
		
		Assert.assertEquals(message, landingPage.getErrorMessage());
		driver.close();

	}
}
