package pl.jakubpiecuch.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jakubpiecuch.accounts.model.Account;

public interface AccountsRepository extends JpaRepository<Account, String> {
}
