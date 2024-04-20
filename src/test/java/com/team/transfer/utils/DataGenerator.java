package com.team.transfer.utils;

import com.team.transfer.domain.Player;
import com.team.transfer.domain.Team;
import com.team.transfer.domain.TeamFormat;
import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.EasyRandom;

import java.util.List;
import java.util.stream.IntStream;

public class DataGenerator {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    public static List<Player> getPlayers(int count) {
        return IntStream.range(0, count).mapToObj(i ->  Player.builder().name(RandomStringUtils.randomAlphabetic(5)).build()).toList();
    }

    public static Team getTeam() {
        Team team = EASY_RANDOM.nextObject(Team.class);
        team.setId(null);
        return team;
    }

    public static TeamFormat getTeamFormat() {
        TeamFormat teamFormat = EASY_RANDOM.nextObject(TeamFormat.class);
        teamFormat.setId(null);
        return teamFormat;
    }
}
