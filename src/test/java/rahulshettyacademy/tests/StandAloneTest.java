package rahulshettyacademy.tests;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client/");
//		LandingPage landingPage = new LandingPage(driver);
		// Logging into the website
		driver.findElement(By.id("userEmail")).sendKeys("don123@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Automation@123");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		// getting the list of products
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		// clicking on add to cart
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Explicit wait untill product is added to cart (untill the text added to cart
		// is visible on the page
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// wait condition untill the loading symbol is invisible
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// clicking on cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// Getting the list of products in the cart
		List<WebElement> cartList = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean flag = cartList.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(flag);

		// clicking on checkout
		driver.findElement(By.cssSelector(" .totalRow button")).click();

		// placing the order
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,500)");
		
		
//		a.moveToElement(driver.findElement(By.xpath("//*[text()='Place Order ']")));
		
		a.sendKeys(Keys.PAGE_DOWN).build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Place Order ']")));
		
		driver.findElement(By.xpath("//*[text()='Place Order ']")).click();
		String cnfrmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(cnfrmMsg.equalsIgnoreCase("Thankyou for the order."));

		driver.close();

	}

}
