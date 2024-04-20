package com.team.transfer.controller;

import com.team.transfer.contract.TransferFormContract;
import com.team.transfer.domain.Team;
import com.team.transfer.service.TeamService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/team")
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping("/team/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public void transfer(@RequestBody @Valid TransferFormContract transferFormContract) {
        teamService.transferPlayer(transferFormContract);
    }
}
