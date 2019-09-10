package com.telran.trololo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BoardDeletionTest extends  TestBase{
  @Test
  public void deletionBoardTest() throws InterruptedException {
    int before = getPersnalBoardsCount();
    clickOnFirstPrivateBoard();
    Thread.sleep(10000);
    clickOnMoreButtonInBoardMenu();
  //  initCloseBoard();
    //..


    int after = getPersnalBoardsCount();
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


}
