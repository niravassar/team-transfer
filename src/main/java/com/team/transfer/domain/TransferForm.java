package com.team.transfer.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferForm {
    private Team fromTeam;
    private Team toTeam;
    private Player player;
}
