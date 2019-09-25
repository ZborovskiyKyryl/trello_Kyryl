package com.trello.qa.tests;

import com.trello.qa.manadger.BoardData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validBoards(){

    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{"name"});
    list.add(new Object[]{"NAME"});
    list.add(new Object[]{"1234"});
    list.add(new Object[]{"#^$&#$^"});
    list.add(new Object[]{"YOOOOOOOOOOOOOOOO"});

    return list.iterator();
  }

  @Test
  public void testBoardCreation() throws InterruptedException {

    int beforeCreation = app.getBoardHelper().getPersnalBoardsCount();
    app.getBoardHelper().createBoard();
    int afterCreation = app.getBoardHelper().getPersnalBoardsCount();

    Assert.assertEquals(afterCreation, beforeCreation + 1);
  }

  @Test(dataProvider = "validBoards")
  public void testBoardCreationWithDataProvider(String teamName){
    BoardData board = new BoardData().withBoardName(teamName);

    app.getBoardHelper().clickOnPlusButtonOnHeader();
    app.getBoardHelper().selectCreateBoardFromDropDown();
    app.getBoardHelper().fillBoardCreationForm(board);
    app.getBoardHelper().confirmBoardCreation();
    app.getBoardHelper().returnToHomePage();



  }




}
