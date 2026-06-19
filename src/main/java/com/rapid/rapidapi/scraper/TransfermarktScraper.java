package com.rapid.rapidapi.scraper;


import com.rapid.rapidapi.model.Match;
import com.rapid.rapidapi.model.PlayerProfile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class TransfermarktScraper {

    public List<PlayerProfile> scraperPlayers(){
        List<PlayerProfile> players = new ArrayList<PlayerProfile>();
        try{
            Document doc = Jsoup.connect("https://www.fcrapid.ro/prima-echipa/")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .get();

            Elements rows = (Elements) doc.select("div.jucator.col-6.col-lg-3");
            for(Element row : rows){
                String name = row.select("h2.nume-jucator").text();
                String imageURL = row.select("img").attr("src");
                players.add(new PlayerProfile(name,imageURL));
            }
        }
        catch(IOException e){
            System.out.println("Problema la obtinerea doc");
        }


        return players;
    }

    public Match getNextMatch(){
        try {
            Document doc = Jsoup.connect("https://lpf.ro/cluburi/fc-rapid/30/calendar")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml")
                    .header("Accept-Language", "ro-RO,ro;q=0.9,en;q=0.8")
                    .get();

        Elements rows = (Elements) doc.select("div.meci-card.white-shadow");
        if(rows.isEmpty()){
            return null;
        }
            Match match = new Match("", "", null, null, false, LocalDate.now(), "", "", "UPCOMING");

            for(Element row : rows){
            String team1 = row.select("div.meci-details-center div.echipa1-text").text();
            String team2 = row.select("div.meci-details-center div.echipa2-text").text();
            String team1URL = row.select("div.meci-details-center div.echipa1-logo img").attr("src");
            String team2URL = row.select("div.meci-details-center div.echipa2-logo img").attr("src");
            String date = row.select("div.meci-details-top div.meci-date").text();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ro"));
            LocalDate matchDate = LocalDate.parse(date, formatter);
            match.setTeam1(team1);
            match.setTeam1URL(team1URL);
            match.setTeam2(team2);
            match.setTeam2URL(team2URL);
            match.setDate(matchDate);
        }

            return match;
        }
        catch(IOException e){
            System.out.println("Problema la obtinerea doc next match");
            return null;
        }

    }

    public List<Match> getResults(){
        List<Match> matches = new ArrayList<Match>();
        try{
            Document doc =  Jsoup.connect("https://lpf.ro/cluburi/fc-rapid/30/rezultate")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Accept", "text/html,application/xhtml+xml")
                    .header("Accept-Language", "ro-RO,ro;q=0.9,en;q=0.8")
                    .get();
            Elements rows = (Elements) doc.select("div.meci-card.white-shadow.meci-card-rezultate");
            Elements teams1 = rows.select("div.meci-details-center div.echipa1-text");
            Elements teams2 = rows.select("div.meci-details-center div.echipa2-text");
            int index = 0;
            if(rows.isEmpty())
                return matches;
            for(Element row : rows){
                String team1 = teams1.get(index).text();
                String team2 = teams2.get(index).text();
                Elements goals = row.select("span.scor-goluri");
                Integer team1Goals = Integer.parseInt(goals.get(0).text());
                Integer team2Goals = Integer.parseInt(goals.get(1).text());
                String team1URL = row.select("div.echipa1-logo img").attr("abs:src");
                String team2URL = row.select("div.echipa2-logo img").attr("abs:src");
                String deplasare = row.select("div.meci-locatie span").text();
                boolean home = true;
                if(deplasare.equals("Deplasare"))
                    home = false;
                String date = row.select("div.meci-date span").text();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ro"));
                LocalDate matchDate = LocalDate.parse(date, formatter);
                matches.add(new Match(team1, team2, team1Goals, team2Goals, home, matchDate, team1URL, team2URL, "RESULT"));
                index = index +1;
                }
        }
        catch (IOException e){
            System.out.println("Problema la conectarea cu pagina lpf(rezultate)");

        }
        return matches;
    }
}
