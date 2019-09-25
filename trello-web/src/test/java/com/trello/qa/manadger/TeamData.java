package com.trello.qa.manadger;

public class TeamData {
    private String teamName;
    private String description;


    public TeamData withTeamName(String teamName) { //setter change to with
        this.teamName = teamName;
        return this;
    }

    public TeamData withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDescription() {
        return description;
    }
}