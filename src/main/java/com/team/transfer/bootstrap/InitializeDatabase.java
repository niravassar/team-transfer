package com.team.transfer.bootstrap;

import com.team.transfer.domain.Player;
import com.team.transfer.domain.Team;
import com.team.transfer.domain.TeamFormat;
import com.team.transfer.repository.PlayerRepository;
import com.team.transfer.repository.TeamFormatRepository;
import com.team.transfer.repository.TeamRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

import static com.team.transfer.domain.FormatType.ELEVEN_V_ELEVEN;
import static com.team.transfer.domain.FormatType.NINE_V_NINE;

@Component
public class InitializeDatabase implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final TeamFormatRepository teamFormatRepository;
    private final PlayerRepository playerRepository;

    public InitializeDatabase(TeamRepository teamRepository, TeamFormatRepository teamFormatRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.teamFormatRepository = teamFormatRepository;
        this.playerRepository = playerRepository;
    }

    public void run(String... args) throws Exception {
        TeamFormat nineVNine = TeamFormat.builder().formatType(NINE_V_NINE).minNumberOfPlayers(9).maxNumberOfPlayers(12).build();
        TeamFormat elevenVEleven = TeamFormat.builder().formatType(ELEVEN_V_ELEVEN).minNumberOfPlayers(11).maxNumberOfPlayers(16).build();
        this.teamFormatRepository.saveAll(List.of(nineVNine, elevenVEleven));

        List<Player> liverpoolPlayers = getPlayersForTeam(15);
        List<Player> manUPlayers =  getPlayersForTeam(14);
        this.playerRepository.saveAll(liverpoolPlayers);
        this.playerRepository.saveAll(manUPlayers);

        Team lvp = Team.builder().name("Liverpool").teamFormat(elevenVEleven).players(liverpoolPlayers).build();
        Team manU = Team.builder().name("ManU").teamFormat(elevenVEleven).players(manUPlayers).build();
        this.teamRepository.saveAll(List.of(lvp, manU));
    }

    private List<Player> getPlayersForTeam(int count) {
        return IntStream.range(0, count).mapToObj(i -> getPlayer()).toList();
    }

    private Player getPlayer() {
        return Player.builder().name(RandomStringUtils.randomAlphabetic(5)).build();
    }
}
