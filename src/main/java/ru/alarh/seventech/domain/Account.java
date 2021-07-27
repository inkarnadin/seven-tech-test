package ru.alarh.seventech.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String accountNumber;
    private BigDecimal balance;

    public BigDecimal changeBalance(double amount) {
        balance = balance.add(BigDecimal.valueOf(amount));
        return balance;
    }

}