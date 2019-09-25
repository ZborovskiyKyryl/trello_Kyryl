package com.trello.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardModificationTest extends TestBase {

    @Test
    public void boardModification() throws InterruptedException {

        String boardName = "chachacha";
        app.getBoardHelper().changeBoard(boardName);

        app.getBoardHelper().clickOnFirstPrivateBoard();
        String actualName = app.getBoardHelper().getBoardNameFromBoardPage();

        Assert.assertEquals(actualName, boardName);


    }




}
