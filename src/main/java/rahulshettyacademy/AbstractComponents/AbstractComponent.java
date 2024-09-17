package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartLink;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersPage;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void waitForElement(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElement(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitToDisappear(WebElement ele) throws InterruptedException
	{
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	public CartPage goToCart()
	{
		cartLink.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrders()
	{
		ordersPage.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}
	
	
}
