package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourseNotFoundException;
import com.app.dto.BasicPlayerDetailsDTO;
import com.app.dto.LoginDTO;
import com.app.entities.Player;
import com.app.repositories.PlayerRepository;

@Service
@Transactional
public class PlayerServiceImpl implements IPlayerService {
	// dependncy injection
	@Autowired
	private PlayerRepository playerRepo;

	// dep modelmapper
//	@Autowired
//	private ModelMapper mapper;

	@Override
	public List<Player> getAllPlayers() {
		return playerRepo.findAll();
	}

	@Override
	public Player insertPlayer(Player player) {
		return playerRepo.save(player);
	}

	@Override
	public String deletePlayer(Long pid) {
		System.out.println("id= " + pid);
		if (playerRepo.existsById(pid)) {
			playerRepo.deleteById(pid);
			return "Delete Succesfully!";
		}
		throw new ResourseNotFoundException("ID Invalid");
	}

	@Override
	public Player editPlayer(Player ply) {
		if (playerRepo.existsById(ply.getId())) {
			return playerRepo.save(ply);
		}
		throw new ResourseNotFoundException("Invalid ID!");
	}

	@Override
	public Player authenticatePlayer(LoginDTO dto) {
		return playerRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new ResourseNotFoundException("Wrong Credentials!!"));
	}

	@Override
	public List<BasicPlayerDetailsDTO> getAllPlayersBasicDetails(String firstname) {
		return playerRepo.fetchPlayersByFirstname(firstname)
				.map(p -> new BasicPlayerDetailsDTO(p.getFirstname(), p.getLastname(), p.getDob(), p.getRanking()))
				.collect(Collectors.toList());
	}

//	@Override
//	public List<EmployeeResponse> getEmpsByFirstName(String firstname) {
//
//		return empRepository.fetchEmpByFirstname(firstname).map(e -> mapper.map(e, EmployeeResponse.class))
//				.collect(Collectors.toList());
//	}

}
