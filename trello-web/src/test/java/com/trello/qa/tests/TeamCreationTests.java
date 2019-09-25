package com.trello.qa.tests;

import com.trello.qa.manadger.TeamData;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
public class TeamCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validTeams(){

    List<Object[]> list = new ArrayList<>();
    list.add(new Object[]{"name","discription"});
    list.add(new Object[]{"NAME","DESC"});
    list.add(new Object[]{"1234","45676"});
    list.add(new Object[]{"#^$&#$^","(&*(&*)"});
    list.add(new Object[]{"name"," "});

    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validTeamsfromcsv() throws IOException {
    List<Object[]> list = new ArrayList<>();
    BufferedReader reader =
            new BufferedReader(new FileReader
                    (new File("src/test/resources/Team.csv")));
    String line = reader.readLine();
    while (line!=null){
      String[] split = line.split(",");
      list.add(new Object[] {
              new TeamData()
                      .withTeamName(split[0])
                      .withDescription(split[1])});

      line = reader.readLine();
    }

    return  list.iterator();
  }

  @BeforeMethod
  public void ensurePreconditionsLogin() {
    if (!app.getSessionHelper().isUserLoggedIn()) {
      app.getSessionHelper().login("elena.telran@yahoo.com", "12345.com");
    }
  }



  @Test
  public void testTeamCreationFromLeftNavMenu() throws InterruptedException {
    int before = app.getTeamHelper().getTeamsCount();
    app.getTeamHelper().clickOnPlusButtonOnLeftNavMenu();
    app.getTeamHelper().fillTeamCreationForm(
            new TeamData()
                    .withTeamName("h")
                    .withDescription("g"));
    app.getTeamHelper().clickContinueButton();
    String createdTeamName = app.getTeamHelper().getTeamNameFromTeamPage();
    app.getTeamHelper().returnToHomePage();
    //  refreshPage();
    int after = app.getTeamHelper().getTeamsCount();

    Assert.assertEquals(after, before + 1);
    Assert.assertEquals(createdTeamName, "h");
  }

  @Test(enabled = false)
  public void testTeamCuncellCreationFromPlusButtonOnHeader() {
    app.getTeamHelper().clickOnPlusButtonOnHeader();
    app.getTeamHelper().selectCreateTeamFromDropDown();
    app.getTeamHelper().fillTeamCreationForm(
            new TeamData()
                    .withTeamName("h")
                    .withDescription("g"));
    app.getTeamHelper().clickXButton();
    //Assert


    Assert.assertTrue(app.getSessionHelper().isUserLoggedIn());
  }

  @Test(dataProvider = "validTeams")
  public void testTeamCreationFromPlusButtonOnHeaderWithDataProvider(String teamName, String discription) throws InterruptedException {
    TeamData team = new TeamData().withTeamName(teamName).withDescription(discription);
    int before = app.getTeamHelper().getTeamsCount();
    app.getTeamHelper().clickOnPlusButtonOnHeader();
    app.getTeamHelper().selectCreateTeamFromDropDown();
    //String teamName = "qa21-" + System.currentTimeMillis();
    app.getTeamHelper().fillTeamCreationForm(
            new TeamData()
                    .withTeamName("h")
                    .withDescription("g"));
    app.getTeamHelper().clickContinueButton();

    app.getTeamHelper().fillTeamCreationForm(team);
    //  String createdTeamName = getTeamNameFromTeamPage();
    app.getTeamHelper().returnToHomePage();
    int after = app.getTeamHelper().getTeamsCount();
    Assert.assertEquals(after, before + 1);
    // Assert.assertEquals(createdTeamName.toLowerCase(), teamName.toLowerCase());
  }

  @AfterClass
  public void postActions() throws InterruptedException {
    app.getTeamHelper().cleanTeams();
  }

}
