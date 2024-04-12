package com.team.transfer.bootstrap;

import com.team.transfer.domain.FormatType;
import com.team.transfer.domain.Player;
import com.team.transfer.domain.Team;
import com.team.transfer.domain.TeamFormat;
import com.team.transfer.repository.PlayerRepository;
import com.team.transfer.repository.TeamFormatRepository;
import com.team.transfer.repository.TeamRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jeasy.random.EasyRandom;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

import static com.team.transfer.domain.FormatType.*;

@Component
public class InitializeDatabase implements CommandLineRunner {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

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

        List<Player> liverpoolPlayers = IntStream.range(0,15).mapToObj(i -> {
            Player player = EASY_RANDOM.nextObject(Player.class);
            player.setId(null);
            return player;
        }).toList();

        List<Player> manUPlayers =  IntStream.range(0,14).mapToObj(i -> {
            Player player = EASY_RANDOM.nextObject(Player.class);
            player.setId(null);
            return player;
        }).toList();
        this.playerRepository.saveAll(liverpoolPlayers);
        this.playerRepository.saveAll(manUPlayers);

        Team lvp = Team.builder().name("Liverpool").teamFormat(elevenVEleven).build();
        Team manU = Team.builder().name("ManU").teamFormat(elevenVEleven).build();
        this.teamRepository.saveAll(List.of(lvp, manU));
    }
}
