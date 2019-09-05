package com.telran.trololo;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BoardCreationTest extends TestBase {

    @Test
    public void mainTest(){
   //createNewBoard("massacre");
   Assert.assertEquals(driver.findElement(By.cssSelector("[aria-label=\"massacre\"]")).getText(), "massacre");
    }

    public void createNewBoard(String name){
        click(By.cssSelector("[class='board-tile mod-add']"));
        type(By.cssSelector("[class='subtle-input']"), name);
        click(By.cssSelector("[class='primary']"));
    }



    public boolean isNewBoardCreated(){
        return driver.findElements(By.cssSelector("[type='submit']")).size()>0;
    }

}
