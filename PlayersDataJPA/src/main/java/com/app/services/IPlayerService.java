package com.app.services;

import java.util.List;

import com.app.dto.BasicPlayerDetailsDTO;
import com.app.dto.LoginDTO;
import com.app.entities.Player;

public interface IPlayerService {
	// get
	List<Player> getAllPlayers();

	// insert
	Player insertPlayer(Player player);

	// delete
	String deletePlayer(Long id);

	// edit
	Player editPlayer(Player player);

	// authenticate
	Player authenticatePlayer(LoginDTO dto);

	// show basic details --> dto
	List<BasicPlayerDetailsDTO> getAllPlayersBasicDetails(String name);
}
