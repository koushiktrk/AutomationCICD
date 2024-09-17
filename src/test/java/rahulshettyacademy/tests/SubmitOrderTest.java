package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckOutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		ProductCatalogue pc = landingPage.loginApplication(input.get("email"), input.get("password"));
		pc.addToCart(input.get("product"));
		CartPage cp = pc.goToCart();
		boolean flag = cp.verifyProductName(input.get("product"));
		Assert.assertTrue(flag);
		CheckOutPage chckPage = cp.goToCheckout();
		chckPage.selectCountry("india");
		ConfirmationPage cPage = chckPage.submitOrder();
		String cnfrmMsg = cPage.getConfMessage();
		Assert.assertTrue(cnfrmMsg.equalsIgnoreCase("Thankyou for the order."));
	}

	// To verify if the product is displaying in the orders page
	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalogue pc = landingPage.loginApplication("don123@gmail.com", "Automation@123");
		OrderPage op = pc.goToOrders();
		Assert.assertTrue(op.verifyProductName(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				"D:\\Selenium\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}

//@DataProvider
//public Object[][] getD() {
//	
//	return new Object[][] {{"don123@gmail.com","Automation@123","ZARA COAT 3"}, {"don1@gmail.com","Automation@123","ADIDAS ORIGINAL"}};
//}
//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "don123@gmail.com");
//map.put("password", "Automation@123");
//map.put("product", "ZARA COAT 3");
//
//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "don1@gmail.com");
//map1.put("password", "Automation@123");
//map1.put("product", "ADIDAS ORIGINAL");