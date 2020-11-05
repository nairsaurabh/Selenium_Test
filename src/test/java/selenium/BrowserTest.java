package selenium;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class BrowserTest {

	@Test
	public void testtts() throws InterruptedException {
		// TODO Auto-generated method stub
		
		//System.setProperty("webdriver.chrome.driver", "/Users/snair108/ATDD/selenium/Drivers/chromedriver_2");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://demoblaze.com/index.html");
		
		driver.manage().window().maximize();
		
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		
		//Fluent Wait
		
		Wait<WebDriver> wait2= new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		
		
				
	/*	String title = driver.getTitle();
		System.out.println(title.length());*/
		waitForPageToLoad();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Home')]")));
		
		
		driver.findElement(By.xpath("//body/div[@id='contcont']//a[text()='Laptops']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sony vaio i5')]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
		
		Thread.sleep(3000);
		
		driver.switchTo().alert().accept();
		
		//Click Home
		driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
		
		//Select 2nd Laptop
		driver.findElement(By.xpath("//body/div[@id='contcont']//a[text()='Laptops']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Dell i7 8gb')]")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']"))).click();
		
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		
		driver.findElement(By.xpath("//a[@id='cartur']")).click();
		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='page-wrapper']//button[text()='Place Order']")));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[1]/td[4]/a[1]"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//body/div[@id='page-wrapper']//button[text()='Place Order']"))).click();
		
		
		Thread.sleep(3000);
		WebElement ele=driver.findElement(By.xpath("//input[@id='name']"));
		if (!ele.isDisplayed()){
			driver.findElement(By.xpath("//body/div[@id='page-wrapper']//button[text()='Place Order']")).click();
		}
		wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='name']"))));
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Saurabh");
		driver.findElement(By.xpath("//input[@id='country']")).sendKeys("India");
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Noida");
		driver.findElement(By.xpath("//input[@id='card']")).sendKeys("1234");
		driver.findElement(By.xpath("//input[@id='month']")).sendKeys("Nov");
		driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2020");
		
		driver.findElement(By.xpath("//body/div[@id='orderModal']//button[text()='Purchase']")).click();
		
		
		String data =driver.findElement(By.xpath("//p[contains(@class,'lead')]")).getText();
		
		String[] details=data.split("\\r?\\n");
		String[] Id=details[0].split(":");
		String[] cost=details[1].split(":");
		
		System.out.println("Id is :"+Id[1]);
		System.out.println("Cost is :"+cost[1]);
		
		//Assert.assertEquals("790", cost[1]);
		Assert.assertTrue(cost[1].contains("790"));
	}
	
	
	public static void waitForPageToLoad() 
    {
       try
       {
       Thread.sleep(1000);
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
       }
       catch(Exception e)
       {
              System.out.println("Error Occured in Wait for Page Load"+e.getMessage());
       }

    }

}
