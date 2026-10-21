package com.ispan.eeit69.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.player_Games_library;

public interface PlayerGamesLibraryRepository extends JpaRepository<player_Games_library, Integer> {
	boolean existsByPlayerIDAndGameID(Integer playerID, Integer gameID);
}
