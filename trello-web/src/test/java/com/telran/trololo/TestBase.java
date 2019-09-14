package com.telran.trololo;

import org.testng.annotations.*;


public class TestBase {


  protected static ApplicationManager app = new ApplicationManager();

  @BeforeSuite
  public void setUp() {
    app.init();
  }

  @AfterSuite
  public void tearDown(){
    app.stop();
  }


    public void isOnHomePage() throws InterruptedException {
        if(!app.isTherePersonalBoards()){
            app.returnToHomePage();
        }
    }
}
