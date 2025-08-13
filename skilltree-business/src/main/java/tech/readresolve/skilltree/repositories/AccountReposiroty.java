package tech.readresolve.skilltree.repositories;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tech.readresolve.skilltree.dtos.out.AccountView;
import tech.readresolve.skilltree.entities.Account;

@Repository
public interface AccountReposiroty extends BaseRepository<Account> {

    boolean existsByUsernameIgnoreCase(String value);

    Optional<Account> findByUsernameIgnoreCase(String username);

    @Query("""
            select a.id as id, a.username as username, a.firstname as firstname,
                a.lastname as lastname, a.role.code as roleCode
            from Account a where a.username != :username
            order by a.lastname, a.firstname
            """)
    Collection<AccountView> findAllProjectedBy(String username);

}
