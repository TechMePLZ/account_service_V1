package ru.slot.account_service_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.slot.account_service_v1.entity.Account;
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {


}
