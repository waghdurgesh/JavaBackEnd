package com.app.repositories;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	Optional<Player> findByEmailAndPassword(String email, String password);

	// basic details by name
	@Query(value = "select p from Player p where p.firstname = ?1")
	Stream<Player> fetchPlayersByFirstname(String firstname);

}
