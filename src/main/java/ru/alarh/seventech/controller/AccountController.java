package ru.alarh.seventech.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alarh.seventech.dto.ChangeBalanceDto;
import ru.alarh.seventech.dto.CurrentBalanceDto;
import ru.alarh.seventech.dto.MoneyTransferDto;
import ru.alarh.seventech.service.AccountService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @SneakyThrows
    @PostMapping(path = "/replenish")
    public ResponseEntity<?> replenish(@Valid @RequestBody ChangeBalanceDto changeBalance) {
        log.info("Action: replenish, request path: {}, params: {}", "api/v1/account/replenish", changeBalance.toString());

        double currentBalance = accountService.changeMoneyBalance(changeBalance.getAccountNumber(), changeBalance.getAmount());
        return ResponseEntity.ok(new CurrentBalanceDto(changeBalance.getAccountNumber(), currentBalance));
    }

    @SneakyThrows
    @PostMapping(path = "/withdraw")
    public ResponseEntity<?> withdraw(@Valid @RequestBody ChangeBalanceDto changeBalance) {
        log.info("Action: withdraw, request path: {}, params: {}", "api/v1/account/replenish", changeBalance.toString());

        double currentBalance = accountService.changeMoneyBalance(changeBalance.getAccountNumber(), -1 * changeBalance.getAmount());
        return ResponseEntity.ok(new CurrentBalanceDto(changeBalance.getAccountNumber(), currentBalance));
    }

    @SneakyThrows
    @PostMapping(path = "/transfer")
    public ResponseEntity<?> transfer(@Valid @RequestBody MoneyTransferDto moneyTransfer) {
        log.info("Action: transfer, request path: {}, params: {}", "api/v1/account/replenish", moneyTransfer.toString());

        accountService.transferMoneyBetweenAccounts(
                moneyTransfer.getSenderAccountNumber(),
                moneyTransfer.getRecipientAccountNumber(),
                moneyTransfer.getAmount()
        );
        return ResponseEntity.ok("");
    }

}