package com.trello.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TeamModificationTest extends TestBase {



    @Test
    public void testRenameTeam() throws InterruptedException {
        app.getTeamHelper().clickOnFirstTeam();
        app.getTeamHelper().openSettings();
        app.getTeamHelper().initEditTeamProfile();
        app.getTeamHelper().changeTeamProfile("hh2223333","hha");
        app.getTeamHelper().confitmEditTeam();

        Thread.sleep(5000);

        Assert.assertEquals(app.getTeamHelper().getTeamNameFromTeamPage(), "hh2223333");
    }

}
