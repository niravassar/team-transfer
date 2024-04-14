package com.team.transfer.service;

import com.team.transfer.domain.Team;
import com.team.transfer.repository.TeamRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TeamService teamService;

    @Test
    void getAllTeams() {
        List<Team> teams = IntStream.range(0,2).mapToObj(i -> Team.builder().name(RandomStringUtils.randomAlphabetic(5)).build()).toList();
        when(this.teamRepository.findAll()).thenReturn(teams);
        List<Team> result = this.teamService.getAllTeams();
        assertThat(result).isEqualTo(teams);
    }

    @Test
    void transferPlayerSuccess() {

    }

}