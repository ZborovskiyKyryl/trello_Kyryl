package com.trello.qa.tests;

import com.trello.qa.manadger.BoardData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.*;

public class BoardCreationTests extends TestBase {

  List<Object[]> list = new ArrayList<>();

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

  @DataProvider
  public Iterator<Object[]> validBoardsCvs() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Team.csv")));
    String line = reader.readLine();
    while (line!=null){
      list.add(new Object[] {new BoardData().withBoardName(line)});
      line = reader.readLine();
    }
    return  list.iterator();
    }

  @Test
  public void testBoardCreation() throws InterruptedException {

    int beforeCreation = app.getBoardHelper().getPersnalBoardsCount();
    app.getBoardHelper().createBoard();
    int afterCreation = app.getBoardHelper().getPersnalBoardsCount();

    Assert.assertEquals(afterCreation, beforeCreation + 1);
  }

  @Test(dataProvider = "validBoardsCvs")
  public void testBoardCreationWithDataProvider(BoardData board){
    //BoardData board = new BoardData().withBoardName(teamName);

    app.getBoardHelper().clickOnPlusButtonOnHeader();
    app.getBoardHelper().selectCreateBoardFromDropDown();
    app.getBoardHelper().fillBoardCreationForm(board);
    app.getBoardHelper().confirmBoardCreation();
    app.getBoardHelper().returnToHomePage();
  }






}
