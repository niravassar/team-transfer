package com.team.transfer.service;

import com.team.transfer.contract.TransferFormContract;
import com.team.transfer.domain.FormatType;
import com.team.transfer.domain.Player;
import com.team.transfer.domain.Team;
import com.team.transfer.domain.TeamFormat;
import com.team.transfer.repository.PlayerRepository;
import com.team.transfer.repository.TeamFormatRepository;
import com.team.transfer.repository.TeamRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

import static com.team.transfer.domain.FormatType.ELEVEN_V_ELEVEN;
import static com.team.transfer.utils.DataGenerator.getPlayers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {

    @Mock
    TeamRepository teamRepository;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    TeamFormatRepository teamFormatRepository;

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
        final int originalTeamSize = 13;
        Team lvp = getTeam(originalTeamSize);
        Team manU = getTeam(originalTeamSize);
        Player tradedPlayer = lvp.getPlayers().get(0);
        TeamFormat teamFormat = getTeamFormat(ELEVEN_V_ELEVEN);
        TransferFormContract transferFormContract = TransferFormContract.builder()
                .formatType(ELEVEN_V_ELEVEN)
                .fromTeamId(lvp.getId())
                .toTeamId(manU.getId())
                .playerId(tradedPlayer.getId())
                .build();

        when(this.teamRepository.findById(lvp.getId())).thenReturn(Optional.of(lvp));
        when(this.teamRepository.findById(manU.getId())).thenReturn(Optional.of(manU));
        when(this.playerRepository.findById(tradedPlayer.getId())).thenReturn(Optional.of(tradedPlayer));
        when(this.teamFormatRepository.findAll()).thenReturn(List.of(teamFormat));

        this.teamService.transferPlayer(transferFormContract);

        verify(this.teamRepository, times(1)).findById(lvp.getId());
        verify(this.teamRepository, times(1)).findById(manU.getId());
        verify(this.playerRepository, times(1)).findById(tradedPlayer.getId());
        verify(this.teamFormatRepository, times(1)).findById(teamFormat.getId());
        assertThat(lvp.getNumberOfPlayers()).isEqualTo(originalTeamSize-1);
        assertThat(manU.getNumberOfPlayers()).isEqualTo(originalTeamSize-1);
        assertThat(lvp.getPlayers()).doesNotContain(tradedPlayer);
        assertThat(manU.getPlayers()).contains(tradedPlayer);
    }

    private Team getTeam(int numberOfPlayers) {
        return Team.builder()
                .id(new Random().nextLong())
                .teamFormat(getTeamFormat(ELEVEN_V_ELEVEN)).players(getPlayers(numberOfPlayers)).build();
    }

    private TeamFormat getTeamFormat(FormatType formatType) {
        return TeamFormat.builder()
                .formatType(formatType)
                .maxNumberOfPlayers(16)
                .minNumberOfPlayers(11)
                .build();
    }

}