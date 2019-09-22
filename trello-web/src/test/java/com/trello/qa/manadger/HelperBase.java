package com.trello.qa.manadger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

public class HelperBase {
  WebDriver driver;

  public HelperBase(WebDriver driver) {
    this.driver = driver;
  }


  public void waitForElementAndClick(By locator, int time) {
    new WebDriverWait(driver, time)
            .until(ExpectedConditions.elementToBeClickable(locator)).click();
  }

  public void click(By locator) {
    driver.findElement(locator).click();
  }

  public void type(By locator, String text) {
    driver.findElement(locator).click();
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
  }

  public void Onlytype(By locator, String text) {
    driver.findElement(locator).sendKeys(text);
  }

  public boolean isElementPresent(By locator) {
    return driver.findElements(locator).size() > 0;
  }

  public void clickOnPlusButtonOnHeader() {
    waitForElementAndClick(By.
            cssSelector("[data-test-id='header-create-menu-button']"), 15);
  }

  public void returnToHomePage() {
    if (isElementPresent(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))) {
      new WebDriverWait(driver, 20)
              .until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("._3gUubwRZDWaOF0._2WhIqhRFBTG7Ry._2NubQcQM83YCVV"))));
      click(By.cssSelector("a[href='/']"));
      click(By.cssSelector("a[href='/']"));
    } else
      waitForElementAndClick(By.cssSelector("a[href='/']"), 15);
  }

  @BeforeMethod
  public void isOnHomePage() {
    if (!isTherePersonalBoards()) {
      returnToHomePage();
    }
  }

  public boolean isTherePersonalBoards() {
    return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
  }

  public void refreshPage() {
    driver.navigate().refresh();
  }

  public void clickOnPlusButtonOnLeftNavMenu() {
    click(By.cssSelector(".icon-add.icon-sm"));
  }

  public void cleaner(String xPath) {
    driver.findElement(By.xpath(xPath)).click();
    driver.findElement(By.xpath(xPath)).click();
    driver.findElement(By.xpath(xPath)).clear();
  }
}
