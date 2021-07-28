package ru.alarh.seventech.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.alarh.seventech.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByAccountNumber(String accountNumber);

}