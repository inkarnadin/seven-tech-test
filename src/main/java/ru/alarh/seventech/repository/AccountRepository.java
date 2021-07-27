package ru.alarh.seventech.repository;

import org.springframework.data.repository.CrudRepository;
import ru.alarh.seventech.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);

}