package com.rapid.rapidapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@jakarta.persistence.Entity
public class Match {
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String team1;
    private String team2;
    private Integer homeGoals;
    private Integer awayGoals;
    private boolean home;
    private LocalDate date;
    private String team1URL;
    private String team2URL;
    private String matchType;
    public Match(){}
    public Match(String team1, String team2, Integer homeGoals, Integer awayGoals, boolean home, LocalDate date, String team1URL, String team2URL, String matchType){
        this.team1 = team1;
        this.team2 = team2;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        this.home = home;
        this.date = date;
        this.team1URL = team1URL;
        this.team2URL = team2URL;
        this.matchType = matchType;
    }
    public String getTeam1(){return team1;}
    public String getTeam2(){return team2;}
    public Integer getHomeGoals(){return homeGoals;}
    public Integer getAwayGoals(){return awayGoals;}
    public boolean getHome(){return home;}
    public LocalDate getDate(){return date;}
    public String getTeam1URL(){return team1URL;}
    public String getTeam2URL(){return team2URL;}
    public String getMatchType(){return matchType;}
    public void setTeam1(String oponent){this.team1 = oponent;}
    public void setTeam2(String oponent){this.team2 = oponent;}
    public void setHomeGoals(Integer homeGoals){this.homeGoals = homeGoals;}
    public void setAwayGoals(Integer awayGoals){this.awayGoals = awayGoals;}
    public void setHome(boolean home){this.home = home;}
    public void setDate(LocalDate date){this.date = date;}
    public void setTeam1URL(String team1URL){this.team1URL = team1URL;}
    public void setTeam2URL(String team2URL){this.team2URL = team2URL;}


}
