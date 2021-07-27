package ru.alarh.seventech.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CurrentBalanceDto {

    private final String accountNumber;
    private final Double currentBalance;

}