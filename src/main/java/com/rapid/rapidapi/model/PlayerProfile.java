package com.rapid.rapidapi.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@jakarta.persistence.Entity
public class PlayerProfile implements Comparable<PlayerProfile>{
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imageURL;

    public PlayerProfile() {}
    public PlayerProfile(String name, String imageURL){
        this.name = name;
        this.imageURL = imageURL;
    }
    public String getName(){return name;}
    public String getImageURL(){return imageURL;}
    public void setName(String name){this.name = name;}
    @Override
    public int compareTo(PlayerProfile player) {
        return this.name.compareTo(player.getName());

    }
}
