package Day1;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


public class Myntra {

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.myntra.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		//Move to element WOMEN and click JACKETS & COATS
		WebElement women = driver.findElement(By.xpath("//a[text()='Women' and @class='desktop-main']"));
		WebElement jktCoat = driver.findElement(By.xpath("//a[text()='Jackets & Coats']"));
		Actions action = new Actions(driver);
		action.moveToElement(women).perform();
		Thread.sleep(5000);
		action.moveToElement(jktCoat).click().perform();
		
		String count = driver.findElement(By.className("title-count")).getText();
		String rplcText = count.replaceAll("\\D", "");
		int totalCount = Integer.parseInt(rplcText);
		System.out.println(totalCount);

		String jackets = driver.findElement(By.xpath("//input[@value='Jackets']//parent::label/span")).getText();
		String rplJackets = jackets.replaceAll("\\D", "");
		int jktCount = Integer.parseInt(rplJackets);
		
		String coats = driver.findElement(By.xpath("//input[@value='Coats']//parent::label/span")).getText();
		String rplCoats = coats.replaceAll("\\D", "");
		int coatCount = Integer.parseInt(rplCoats);		
		
		int totalCount2 = jktCount+coatCount;
		System.out.println(totalCount2);
		
		if (totalCount == totalCount2) {
			System.out.println("Both the values are same");
		} else {
			System.out.println("Values differ from each other");
		}
		
		
		driver.findElement(By.xpath("//input[@value='Coats']//parent::label")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("brand-more")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Mango",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-list']/li/label")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-indices']/following-sibling::span")).click();
		
		
		//Find number of MANGO Product
		WebElement dressTable = driver.findElement(By.className("results-base"));
		
		List<WebElement> dresses = dressTable.findElements(By.tagName("li"));
		for (int i = 0; i < dresses.size(); i++) {
			List<WebElement> brandName = dresses.get(i).findElements(By.tagName("h3"));
			for (WebElement eleBrand : brandName) {
				String brand = eleBrand.getText();
				if (brand.equalsIgnoreCase("MANGO")) {
					System.out.println("Brand Name matches");
				} else {
					System.out.println("Brand Mismatch");
				}
			}
		}
		
		//Mouse Hover and sort product based on Discount
		Actions builder = new Actions(driver);
		WebElement sortBy = driver.findElement(By.className("sort-sortBy"));
		builder.moveToElement(sortBy).perform();
		
		WebElement discount = driver.findElement(By.xpath("//label[text()='Better Discount']"));
		Thread.sleep(3000);
		builder.moveToElement(discount).click().perform();
		Thread.sleep(3000);
		
		
		//to get the Price and convert String into INTEGER
		WebElement priceTable = driver.findElement(By.className("results-base"));
		
		List<WebElement> price = priceTable.findElements(By.className("product-base"));
		for (int i = 0; i < 1 ; i++) {
			List<WebElement> priceList = price.get(i).findElements(By.xpath("//ul[@class='results-base']/li/a/div[2]/div/span/span[1]"));
			for (WebElement elePrice : priceList) {
				String dressPrice = elePrice.getText();
				String rplcPrice = dressPrice.replaceAll("\\D", "");
				int finalPrice = Integer.parseInt(rplcPrice);
				System.out.println(finalPrice);
			}
		}
		
		//Mouse Hover on Product to enable Wishlist
		WebElement product = driver.findElement(By.className("product-base"));
		builder.moveToElement(product).perform();
		Thread.sleep(3000);
		
		WebElement sizeOfPrd = driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/a/div/h4/span[4]"));
		builder.moveToElement(sizeOfPrd).perform();
		
		//Click WishList
		driver.findElement(By.xpath("//ul[@class='results-base']/li[1]/div[3]/span")).click();
		
		driver.close();
	}
}
