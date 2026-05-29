package com.rapid.rapidapi.scheduler;


import com.rapid.rapidapi.service.RapidService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataRefreshScheduler {
    private final RapidService rapidService;
    DataRefreshScheduler(RapidService rapidService){this.rapidService = rapidService;}

    @Scheduled(fixedRate = 86400000)
    public void refreshPlayers(){
        rapidService.refreshPlayers();
    }

    @Scheduled(fixedRate = 43200000)
    public void refreshData(){
        rapidService.refreshResults();
        rapidService.refreshNextMatch();
    }
}
