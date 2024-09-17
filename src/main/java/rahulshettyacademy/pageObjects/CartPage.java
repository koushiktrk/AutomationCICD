package rahulshettyacademy.pageObjects;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=" .totalRow button")
	WebElement checkOut;
	
	WebDriver driver;
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyProductName(String productName)
	{	
		boolean flag = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return flag;
	
	}
	
	public CheckOutPage goToCheckout()
	{
		checkOut.click();
		return new CheckOutPage(driver);
	}
}
