package com.telran.trololo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardDeletionTest extends  TestBase{
  @Test
  public void deletionBoardTest() throws InterruptedException {

    leave3BoardsOnly();
    Assert.assertTrue(getPersnalBoardsCount() == 3);
  }


}
