package com.rapid.rapidapi.controller;


import com.rapid.rapidapi.model.Match;
import com.rapid.rapidapi.model.PlayerProfile;
import com.rapid.rapidapi.service.RapidService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RapidController {
    private final RapidService rapidService;
    public RapidController(RapidService rapidService){
        this.rapidService = rapidService;
    }
    @GetMapping("/players")
    public List<PlayerProfile> getPlayers(){
        return rapidService.getPlayers();
    }

    @GetMapping("/next-match")
    public Match getNextMatch(){
        return rapidService.getNextMatch();
    }

    @GetMapping("/last-matches")
    public List<Match> getLastMatches(){
        return rapidService.getLastMatches();
    }
}
