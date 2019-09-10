package com.telran.trololo;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamCreationTests extends  TestBase{
  @BeforeClass
  public void ensurePreconditionsLogin(){
    if(!isUserLoggedIn()){
      login("elena.telran@yahoo.com", "12345.com");
    }
  }

  @BeforeMethod
  public void isOnHomePage(){
    if(!isTherePersonalBoards()){
      returnToHomePage();
    }
  }

  public boolean isTherePersonalBoards() {
    return isElementPresent(By.xpath("//*[@class='icon-lg icon-member']/../../.."));
  }

  @Test
  public void testTeamCreationFromPlusButtonOnHeader() throws InterruptedException {
    int before = getTeamsCount();
    clickOnPlusButtonOnHeader();
    selectCreateTeamFromDropDown();
    String teamName = "qa21-"+ System.currentTimeMillis();
    fillTeamCreationForm(teamName, "descr qa 21");
    clickContinueButton();
  //  String createdTeamName = getTeamNameFromTeamPage();
    returnToHomePage();
    int after = getTeamsCount();
    Assert.assertEquals(after, before+1);
  //  Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
  }

  @Test
  public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
    int before = getTeamsCount();
    clickOnPlusButtonOnLeftNavMenu();
    fillTeamCreationForm("h", "g");
    clickContinueButton();
    String createdTeamName = getTeamNameFromTeamPage();
    returnToHomePage();
  //  refreshPage();
    int after = getTeamsCount();

    Assert.assertEquals(after, before+1);
    Assert.assertEquals(createdTeamName, "h");
  }

  public void refreshPage() {
    driver.navigate().refresh();
  }

  public void clickOnPlusButtonOnLeftNavMenu() {
    click(By.cssSelector(".icon-add.icon-sm"));
  }

  @Test(enabled=false)
  public void testTeamCuncellCreationFromPlusButtonOnHeader(){
    clickOnPlusButtonOnHeader();
    selectCreateTeamFromDropDown();
    fillTeamCreationForm("qa21", "descr qa 21");
    clickXButton();
    //Assert


    Assert.assertTrue(isUserLoggedIn());
  }


}
