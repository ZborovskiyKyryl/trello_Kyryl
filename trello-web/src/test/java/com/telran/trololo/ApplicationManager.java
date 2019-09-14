package com.telran.trololo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public String boardName;
    public int numberOfBoardsBefore;
    WebDriver driver;

    protected void init() {
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

    protected void stop() {
      driver.quit();
    }

    public boolean isUserLoggedIn() {
      return isElementPresent(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void waitForElementAndClick(By locator, int time){
      new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(locator));
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

    public void clickOnPlusButtonOnHeader()
    {

      waitForElementAndClick(By.cssSelector("[data-test-id='header-create-menu-button']"), 15);
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

    public void confirmBoardCreation() throws InterruptedException {
      Thread.sleep(10000);
      click(By.cssSelector("[data-test-id='header-create-board-submit-button']"));
    }

    protected String getTeamNameFromTeamPage() {
      new WebDriverWait(driver, 15)
              .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1")));
      return driver.findElement(By.cssSelector("h1")).getText();
    }

    public void returnToHomePage() throws InterruptedException {
     // if(isElementPresent(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))){
        //new WebDriverWait(driver, 15)
               // .until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))));
        Thread.sleep(5000);
        click(By.cssSelector("a[href='/']"));
        click(By.cssSelector("a[href='/']"));
     // } else
     //waitForElementAndClick(By.cssSelector("a[href='/']"), 15);
    }

    public int getTeamsCount()  {
      new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(
                      By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")));
      return driver.findElements(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li")).size();
    }

    public void clickXButton() {

    }

    public int getPersnalBoardsCount() throws InterruptedException {
      Thread.sleep(5000);
      return driver.findElements(By.xpath("//*[@class='icon-lg icon-member']/../../..//li")).size()-1;
    }

    public void createNewBoard(String name) throws InterruptedException {
        Thread.sleep(5000);
        numberOfBoardsBefore = driver.findElements(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/div/div/div[2]/div/div/div[1]/ul/li")).size()-1;
        click(By.cssSelector("[class='board-tile mod-add']"));
        type(By.cssSelector("[class='subtle-input']"), name);
        click(By.cssSelector("[class='primary']"));
      Thread.sleep(5000);
        returnToHomePage();
    }

    public boolean isNewBoardCreatedBySize() throws InterruptedException {
        Thread.sleep(5000);
        click(By.cssSelector("[href='/']"));
        click(By.cssSelector("[href='/']"));
        int numberOfBoardsAfter = driver.findElements(By.xpath("//*[@id='content']/div/div[2]/div/div/div/div/div[2]/div/div/div[2]/ul/li")).size()-1;
        return numberOfBoardsAfter > numberOfBoardsBefore;
    }

    public boolean isNewBoardCreatedByName() throws InterruptedException {
        Thread.sleep(10000);
        click(By.cssSelector("[href='/']"));
        click(By.cssSelector("[href='/']"));
        return driver.getPageSource().contains(boardName);
    }

    public boolean isTherePersonalBoards() {
      return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
    }

    public void refreshPage()
    {
      driver.navigate().refresh();
    }

    public void clickOnPlusButtonOnLeftNavMenu() {

      click(By.cssSelector(".icon-add.icon-sm"));
    }

    public void deleteTeam() {
      new WebDriverWait(driver, 10)
              .until(ExpectedConditions.elementToBeClickable(By.cssSelector(".quiet-button")));
      click(By.cssSelector(".quiet-button"));
      click(By.cssSelector(".js-confirm"));
    }

    public void openSettings() {
      click(By.cssSelector(".icon-gear.icon-sm.OiX3P2i2J92Xat"));
    }

    public void clickOnFirstTeam() {
      click(By.xpath("//*[@class='_mtkwfAlvk6O3f']/../../..//li"));
    }

    public void clickOnPermanentlyDeleteBoard() {
      click(By.cssSelector(".js-delete"));
      click(By.cssSelector("[class=\"js-confirm full negate\"]"));
    }

    public void clickOnCloseBoard() {
      click(By.cssSelector(".js-close-board"));
      click(By.cssSelector("[class=\"js-confirm full negate\"]"));

    }

    public void clickOnMoreButtonInBoardMenu() {
      WebElement menuButton = driver.findElement(By.cssSelector(".board-header-btn.mod-show-menu"));
      System.out.println(menuButton.getCssValue("visibility"));
      if(menuButton.getCssValue("visibility").equals("visible")){
        click(By.cssSelector(".mod-show-menu"));
        click(By.cssSelector(".js-open-more"));
      } else{
        click(By.cssSelector(".js-open-more"));
      }

    }

    public void clickOnFirstPrivateBoard() {
      click(By.xpath("//*[@class='icon-lg icon-member']/../../..//li"));
    }

    public void leave3BoardsOnly() throws InterruptedException {
      Thread.sleep(5000); // if the internet is poor((
      int valueOfPresentBoards = getPersnalBoardsCount();
      while (valueOfPresentBoards > 3) {
        clickOnFirstPrivateBoard();
        clickOnMoreButtonInBoardMenu();
        clickOnCloseBoard();
        clickOnPermanentlyDeleteBoard();
        returnToHomePage();
        valueOfPresentBoards = getPersnalBoardsCount();
      }


    }
}
