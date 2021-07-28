package ru.alarh.seventech.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Account domain object
 */
@Data
@NoArgsConstructor
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    private String accountNumber;
    private BigDecimal balance;

    /**
     * Account change balance method
     *
     * @param amount changing amount
     * @return current balance after changing
     */
    public BigDecimal changeBalance(double amount) {
        balance = balance.add(BigDecimal.valueOf(amount));
        return balance;
    }

}