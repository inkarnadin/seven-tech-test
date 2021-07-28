package ru.alarh.seventech.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A current account balance data transfer object
 */
@Data
@RequiredArgsConstructor
public class CurrentBalanceDto {

    private final String accountNumber;
    private final Double currentBalance;

}