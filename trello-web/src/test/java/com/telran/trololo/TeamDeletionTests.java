package com.telran.trololo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TeamDeletionTests extends TestBase {





  @Test
  public void deleteTeamFromLeftNavMenu() throws InterruptedException {
    isOnHomePage();
    int before = app.getTeamsCount();
    app.clickOnFirstTeam();
    app.openSettings();
    app.deleteTeam();

    app.returnToHomePage();
    int after = app.getTeamsCount();
    Assert.assertEquals(after, before - 1);
  }

}
