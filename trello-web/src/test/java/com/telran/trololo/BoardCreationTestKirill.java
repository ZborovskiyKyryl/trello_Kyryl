package com.telran.trololo;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;

public class BoardCreationTestKirill extends TestBase {

    @Test
    public void mainTest() throws InterruptedException {

        boardName = "newBoard5.12";

            createNewBoard(boardName);

        Assert.assertTrue(isNewBoardCreatedBySize());
        //Assert.assertTrue(isNewBoardCreatedByName());
    }


}
