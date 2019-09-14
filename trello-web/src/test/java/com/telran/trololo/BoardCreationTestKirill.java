package com.telran.trololo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class BoardCreationTestKirill extends TestBase {

    @Test
    public void mainTest() throws InterruptedException {
        isOnHomePage();

        app.boardName = "newBoard5.12";

            app.createNewBoard(app.boardName);

        Assert.assertTrue(app.isNewBoardCreatedBySize());
        //Assert.assertTrue(isNewBoardCreatedByName());
    }


}
