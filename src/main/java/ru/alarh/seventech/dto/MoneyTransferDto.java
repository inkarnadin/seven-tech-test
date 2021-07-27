package ru.alarh.seventech.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MoneyTransferDto {

    private final String senderAccountNumber;
    private final String recipientAccountNumber;
    private final Double amount;

}