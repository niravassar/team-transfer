package com.team.transfer.controller;

import com.team.transfer.exception.ValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @GetMapping("/team")
    String getAllTeams() {
        throw new ValidationException("nirav threw it");
    }
}
