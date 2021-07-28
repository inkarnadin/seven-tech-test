package ru.alarh.seventech.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * A money transfer object between two accounts
 */
@Data
@RequiredArgsConstructor
public class MoneyTransferDto {

    private final String senderAccountNumber;
    private final String recipientAccountNumber;
    private final Double amount;

}