package com.rapid.rapidapi.service;
import com.rapid.rapidapi.model.Match;
import com.rapid.rapidapi.model.PlayerProfile;
import com.rapid.rapidapi.repository.MatchRepository;
import com.rapid.rapidapi.repository.PlayerRepository;
import com.rapid.rapidapi.scraper.TransfermarktScraper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RapidService {
    private final TransfermarktScraper scraper;
    private final PlayerRepository playerRepository;
    private final MatchRepository matchRepository;


    public RapidService(TransfermarktScraper scraper, PlayerRepository playerRepository, MatchRepository matchRepository){
        this.scraper = scraper;
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    @jakarta.annotation.PostConstruct
    public void init(){
            System.out.println("Se initializeaza RapidService");
            refreshPlayers();
            refreshNextMatch();
            refreshResults();
            System.out.println("S-a terminat initializarea RapidService");
    }
    public List<PlayerProfile> getPlayers(){
        List<PlayerProfile> players = playerRepository.findAll();
        if(players.isEmpty())
            return null;
        return players;}
    public List<Match> getLastMatches(){
        List<Match> results = matchRepository.findByMatchType("RESULT");
        if(results.isEmpty())
            return null;
        return results;}
    public Match getNextMatch(){
        List<Match> nextMatch = matchRepository.findByMatchType("UPCOMING");
        if(nextMatch.isEmpty())
            return null;
        return nextMatch.get(0);
    }

    public void refreshPlayers(){
        playerRepository.deleteAll();
        List<PlayerProfile> players = scraper.scraperPlayers();
        playerRepository.saveAll(players);
    }

    @org.springframework.transaction.annotation.Transactional
    public void refreshResults(){
        List<Match> results = scraper.getResults();
        for(Match match : results){
            if(!matchRepository.existsByDate(match.getDate()))
                matchRepository.save(match);
        }
      }

    @org.springframework.transaction.annotation.Transactional
      public void refreshNextMatch(){
        matchRepository.deleteByMatchType("UPCOMING");
        Match upcoming = scraper.getNextMatch();
        if(upcoming != null)
            matchRepository.save(upcoming);
      }

}
