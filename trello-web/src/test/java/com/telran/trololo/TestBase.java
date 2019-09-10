package com.telran.trololo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


public class TestBase {
  public String boardName;
  public int numberOfBoardsBefore;
  WebDriver driver;
  @BeforeClass
  public void setUp(){
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    driver.manage().window().maximize();

    openSite("https://trello.com");
    login("speak.zborovskiy@gmail.com", "154324qq");
  }

  public void login(String email, String password) {
    click(By.cssSelector("[href='/login']"));
    type(By.cssSelector("[type=email]"), email);
    type(By.cssSelector("[type=password]"),password);
    click(By.id("login"));
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void type(By locator, String text){
    driver.findElement(locator).click();
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
  }

  public void openSite(String url) {
    driver.get(url);
  }

  @AfterClass
  public void tearDown(){
driver.quit();
  }

  public boolean isUserLoggedIn() {
    return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
  }

  public boolean isElementPresent(By locator) {
    return driver.findElements(locator).size()>0;
  }

  public void clickContinueButton() {
    click(By.cssSelector("[type=submit]"));
  }

  public void fillTeamCreationForm(String teamName, String description) {
    type(By.cssSelector("[data-test-id='header-create-team-name-input']"), teamName);
    type(By.cssSelector("textarea"), description);
  }

  public void selectCreateTeamFromDropDown() {
    click(By.cssSelector("[data-test-id='header-create-team-button']"));
  }

  public void clickOnPlusButtonOnHeader() {
    click(By.cssSelector("[data-test-id='header-create-menu-button']"));
      }

  public void fillBoardCreationForm(String boardName, String s) {
    type(By.cssSelector("[data-test-id='header-create-board-title-input']"), boardName);

    if(isElementPresent(By.cssSelector(".W6rMLOx8U0MrPx"))){
    click(By.cssSelector(".W6rMLOx8U0MrPx"));
    click(By.xpath("//nav[@class='SdlcRrTVPA8Y3K']//li[1]"));//no team
    }

  }

  public void selectCreateBoardFromDropDown() {
    click(By.cssSelector("[data-test-id='header-create-board-button']"));
  }

  public void confirmBoardCreation() {
    click(By.cssSelector("[data-test-id='header-create-board-submit-button']"));
  }

  protected String getTeamNameFromTeamPage() {
    new WebDriverWait(driver, 15)
            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
    return driver.findElement(By.cssSelector("h1")).getText();
  }

  public void returnToHomePage() {
    if(isElementPresent(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))){
      new WebDriverWait(driver, 15)
              .until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))));
      click(By.cssSelector("a[href='/']"));
      click(By.cssSelector("a[href='/']"));
    } else
    click(By.cssSelector("a[href='/']"));
  }

  public int getTeamsCount()  {
    new WebDriverWait(driver, 5)
            .until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
    return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
  }

  public void clickXButton() {

  }

  public int getPersnalBoardsCount() {
    return driver.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size()-1;
  }

  public void createNewBoard(String name) throws InterruptedException {
      numberOfBoardsBefore = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/div/div/div[2]/div/div/div[1]/ul/li")).size();
      click(By.cssSelector("[class='board-tile mod-add']"));
      type(By.cssSelector("[class='subtle-input']"), name);
      click(By.cssSelector("[class='primary']"));
  }

  public boolean isNewBoardCreatedBySize() throws InterruptedException {
      Thread.sleep(5000);
      click(By.cssSelector("[href='/']"));
      click(By.cssSelector("[href='/']"));
      int numberOfBoardsAfter = driver.findElements(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/ul/li")).size();
      return numberOfBoardsAfter > numberOfBoardsBefore;
  }

  public boolean isNewBoardCreatedByName() throws InterruptedException {
      Thread.sleep(10000);
      click(By.cssSelector("[href='/']"));
      click(By.cssSelector("[href='/']"));
      return driver.getPageSource().contains(boardName);
  }
}
