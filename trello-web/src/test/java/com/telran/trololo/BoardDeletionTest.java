package com.telran.trololo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardDeletionTest extends  TestBase{
  @Test
  public void deletionBoardTest() throws InterruptedException {

    app.leave3BoardsOnly();

    Assert.assertTrue(app.getPersnalBoardsCount() == 3);
  }


}
