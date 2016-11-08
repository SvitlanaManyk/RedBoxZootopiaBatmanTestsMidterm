
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class midtermTask2Test {

	WebDriver driver = null;

	@Before
	public void setup() {
		System.out.println("Starting driver");
		System.out.println("________________________________________");
		driver = new FirefoxDriver();
	}

	@After
	public void teardown() {
		System.out.println("________________________________________");
		System.out.println("Closing driver");
		driver.close();
		driver = null;
	}
	
	@Test
	public void Batman() {
		String url = "http://www.redbox.com/";
        String searchObject = "Batman";
        WebDriverWait wbWait = new WebDriverWait(driver, 5);
        
        By searchIcon = new By.ByCssSelector("a[id=searchdigital0_SearchIcon]");
        By searchBar = new By.ByCssSelector("input[id='searchdigital0_SearchBox']");
        By runtime = new By.ByCssSelector("#titledetailscore0_Widget>div:nth-child(1)>div>div.digital-details-subhead.row>div:nth-child(6)");
        By videoResults = new By.ByCssSelector("img[err-src]");
        By movie = new By.ByCssSelector("a[href='http://www.redbox.com/games/batman-arkham-knight-ps4']>img");
        By articleName = new By.ByCssSelector("h1.digital-details-title");
        
        System.out.println("Opening page:" + url);
	    driver.manage().window().maximize();
		driver.get(url);
		
		System.out.println("Clicking Search button");
		WebElement weSearchIcon = driver.findElement(searchIcon);
		weSearchIcon.click();
		wbWait.until(ExpectedConditions.visibilityOfElementLocated(searchBar));
		
		System.out.println("Locating search bar");
		WebElement weSearchBar = driver.findElement(searchBar);
		weSearchBar.sendKeys(searchObject + Keys.ENTER);
		
		System.out.println("Asserting the result page has 12 results");
		wbWait.until(ExpectedConditions.visibilityOfElementLocated(videoResults));
		List<WebElement> allResults = driver.findElements(By.cssSelector("div[class^='title-box-odopod popover']"));
		System.out.println("Results count is "+allResults.size());
        Assert.assertTrue("Results!=12", allResults.size()==12);

        System.out.println("Clicking the movie");
		WebElement weMovie = driver.findElement(movie);
		weMovie.click();
		wbWait.until(ExpectedConditions.visibilityOfElementLocated(runtime));
		
		System.out.println("Validating of Article Name");
		WebElement weArticleName = driver.findElement(articleName);
		System.out.println("Article Name is: "+weArticleName.getText());
		Assert.assertTrue("Article Name does not contain 'Batman'!", weArticleName.getText().contains("Batman")); 
			}
}
