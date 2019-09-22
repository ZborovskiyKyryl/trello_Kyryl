package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTests extends TestBase {
  @Test
  public void testBoardCreation() throws InterruptedException {

    int beforeCreation = app.getBoardHelper().getPersnalBoardsCount();
    app.getBoardHelper().createBoard();
    int afterCreation = app.getBoardHelper().getPersnalBoardsCount();

    Assert.assertEquals(afterCreation, beforeCreation + 1);
  }




}
