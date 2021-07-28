package ru.alarh.seventech.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;

/**
 * A change account balance data transfer object
 */
@Data
@RequiredArgsConstructor
public class ChangeBalanceDto {

    private final String accountNumber;
    @Min(value = 0, message = "The amount of change must be a positive number")
    private final Double amount;

}