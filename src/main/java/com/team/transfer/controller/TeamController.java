package com.team.transfer.controller;

import com.team.transfer.contract.TransferFormContract;
import com.team.transfer.domain.Team;
import com.team.transfer.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String transfer(@RequestBody TransferFormContract transferFormContract) {
        return "transfer occurred";
    }
}
