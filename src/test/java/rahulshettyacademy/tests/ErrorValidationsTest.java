package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = rahulshettyacademy.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException {
		landingPage.loginApplication("don123@gmail.com", "Autoion@123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue pc = landingPage.loginApplication("don123@gmail.com", "Automation@123");	
		// Demo purpose
		pc.addToCart(productName);
		CartPage cp = pc.goToCart();
		boolean flag = cp.verifyProductName("ZARA COAT 33");
		Assert.assertFalse(flag);
	}
}
