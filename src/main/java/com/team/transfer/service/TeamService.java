package com.team.transfer.service;

import com.team.transfer.domain.Team;
import com.team.transfer.domain.TransferForm;
import com.team.transfer.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return this.teamRepository.findAll();
    }

    public void transferPlayer(TransferForm transferForm) {

    }
}
