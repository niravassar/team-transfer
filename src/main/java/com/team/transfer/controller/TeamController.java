package com.team.transfer.controller;

import com.team.transfer.contract.TransferFormContract;
import com.team.transfer.exception.ValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @GetMapping("/team")
    public String getAllTeams() {
        throw new ValidationException("nirav threw it");
    }

    @PostMapping("/team/transfer")
    public String transfer(@RequestBody TransferFormContract transferFormContract) {
        return "transfer occurred";
    }
}
