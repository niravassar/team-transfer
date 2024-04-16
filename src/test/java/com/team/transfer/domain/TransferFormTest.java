package com.team.transfer.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.team.transfer.domain.FormatType.ELEVEN_V_ELEVEN;
import static com.team.transfer.domain.FormatType.NINE_V_NINE;
import static com.team.transfer.utils.DataGenerator.getPlayers;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransferFormTest {

    @Test
    void isTransferValidDifferentFormatType() {
        Team liverpool = getTeam(13);
        Team manU = getTeam(13);
        manU.setTeamFormat(getTeamFormat(NINE_V_NINE));
        TransferForm transferForm = TransferForm.builder().fromTeam(liverpool).toTeam(manU).teamFormat(getTeamFormat(ELEVEN_V_ELEVEN)).build();

        assertFalse(transferForm.isTransferValid());
    }

    @Test
    void isTransferValidTeamsHaveEnoughPlayers() {
        Team liverpool = getTeam(12);
        Team manU = getTeam(15);
        TransferForm transferForm = TransferForm.builder().fromTeam(liverpool).toTeam(manU).player(liverpool.getPlayers().get(0)).teamFormat(getTeamFormat(ELEVEN_V_ELEVEN)).build();

        assertTrue(transferForm.isTransferValid());
    }

    @Test
    void isTransferValidFromTeamHasNotEnoughPlayers() {
        Team liverpool = getTeam(11);
        Team manU = getTeam(15);
        TransferForm transferForm = TransferForm.builder().fromTeam(liverpool).toTeam(manU).player(liverpool.getPlayers().get(0)).teamFormat(getTeamFormat(ELEVEN_V_ELEVEN)).build();

        assertFalse(transferForm.isTransferValid());
    }

    @Test
    void isTransferValidToTeamHasTooManyPlayers() {
        Team liverpool = getTeam(12);
        Team manU = getTeam(16);
        TransferForm transferForm = TransferForm.builder().fromTeam(liverpool).toTeam(manU).player(liverpool.getPlayers().get(0)).teamFormat(getTeamFormat(ELEVEN_V_ELEVEN)).build();

        assertFalse(transferForm.isTransferValid());
    }

    private Team getTeam(int numberOfPlayers) {
        return Team.builder().teamFormat(getTeamFormat(ELEVEN_V_ELEVEN)).players(getPlayers(numberOfPlayers)).build();
    }

    private TeamFormat getTeamFormat(FormatType formatType) {
        return TeamFormat.builder()
                .formatType(formatType)
                .maxNumberOfPlayers(16)
                .minNumberOfPlayers(11)
                .build();
    }
}