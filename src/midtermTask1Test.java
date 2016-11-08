
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



public class midtermTask1Test {

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
	public void Zootopia() {
		String url = "http://www.redbox.com/";
        String searchObject = "Zootopia";
        WebDriverWait wbWait = new WebDriverWait(driver, 5);
        
        By searchIcon = new By.ByCssSelector("a[id=searchdigital0_SearchIcon]");
        By searchBar = new By.ByCssSelector("input[id='searchdigital0_SearchBox']");
        By resultspage = new By.ByXPath("//*[@id='searchresultscore0_Widget']/div[3]/div[1]/div/span[1]");
        By runtime = new By.ByCssSelector("#titledetailscore0_Widget>div:nth-child(1)>div>div.digital-details-subhead.row>div:nth-child(6)");
        By videoResults = new By.ByCssSelector("img[err-src]");
        By movie = new By.ByCssSelector(".title-box-odopod.popover.img-loaded>a>img");
        
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
		
		System.out.println("Asserting the result page has one result");
		wbWait.until(ExpectedConditions.visibilityOfElementLocated(videoResults));
		List<WebElement> allResults = driver.findElements(By.cssSelector("div[class^='title-box-odopod popover']"));
		System.out.println("Results count is "+allResults.size());
		Assert.assertTrue("Results!=1", allResults.size()==1);
		
		System.out.println("Asserting the movie searched IS in the result page");
		WebElement weResultsPage = driver.findElement(resultspage);
		Assert.assertTrue("The movie is not on the result page", weResultsPage.getText().contains("Results for"));
		
		System.out.println("Clicking the movie");
		WebElement weMovie = driver.findElement(movie);
		weMovie.click();
		wbWait.until(ExpectedConditions.visibilityOfElementLocated(runtime));
		
		System.out.println("Validating of runtime");
		WebElement weRunTime = driver.findElement(runtime);
		System.out.println("Runtime: "+weRunTime.getText());
		Assert.assertTrue("Runtime is different than 1:48", weRunTime.getText().contains("1:48")); 
		
        System.out.println("Languages test");
		List<WebElement> allLanguages = driver.findElements(By.cssSelector("div[itemprop='inLanguage']"));
        System.out.println("Languages count is:" +allLanguages.size()+" and they are as follows:");
        for (WebElement link : allLanguages){
				System.out.println(link.getText());
			}
}
}