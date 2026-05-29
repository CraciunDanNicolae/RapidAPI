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
    private String position;
    private Integer age;
    private String nationality;
    private LocalDate dateOfBirth;
    private Integer number;
    private String imageURL;

    public PlayerProfile() {}
    public PlayerProfile(String name, String position, Integer age, String nationality, LocalDate date_of_birth, Integer number, String imageURL){
        this.name = name;
        this.position = position;
        this.age = age;
        this.nationality = nationality;
        this.dateOfBirth = date_of_birth;
        this.number = number;
        this.imageURL = imageURL;
    }
    public String getName(){return name;}
    public String getPosition(){return position;}
    public  Integer getAge(){return age;}
    public String getNationality(){return nationality;}
    public LocalDate getDate_of_birth(){return dateOfBirth;}
    public Integer getNumber(){return number;}
    public String getImageURL(){return imageURL;}
    public void setName(String name){this.name = name;}
    public void setPosition(String position){this.position = position;}
    public  void setAge(Integer age){this.age = age;}
    public void setNationality(String nationality){this.nationality = nationality;}
    public void setDate_of_birth(LocalDate date_of_birth){this.dateOfBirth = date_of_birth;}
    @Override
    public int compareTo(PlayerProfile player) {
        return this.name.compareTo(player.getName());

    }
}
