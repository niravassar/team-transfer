package com.team.transfer.repository;

import com.team.transfer.domain.Team;
import com.team.transfer.domain.TeamFormat;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TeamRepositoryTest {

    protected static final EasyRandom EASY_RANDOM = new EasyRandom();

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TeamFormatRepository teamFormatRepository;

    @Test
    void findAllTeams() {
        TeamFormat teamFormat = getTeamFormat();
        this.teamFormatRepository.save(teamFormat);
        List<Team> teamList = IntStream.range(0, 3).mapToObj(i -> {
            Team team = getTeam();
            team.setTeamFormat(teamFormat);
            return team;
        }).toList();
        this.teamRepository.saveAll(teamList);

        List<Team> actual = this.teamRepository.findAll();
        assertThat(actual).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(teamList);
    }

    private Team getTeam() {
        Team team = EASY_RANDOM.nextObject(Team.class);
        team.setId(null);
        return team;
    }

    private TeamFormat getTeamFormat() {
        TeamFormat teamFormat = EASY_RANDOM.nextObject(TeamFormat.class);
        teamFormat.setId(null);
        return teamFormat;
    }
}