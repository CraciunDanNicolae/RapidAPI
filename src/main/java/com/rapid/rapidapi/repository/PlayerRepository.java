package com.rapid.rapidapi.repository;

import com.rapid.rapidapi.model.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends  JpaRepository<PlayerProfile, Long>{
}
