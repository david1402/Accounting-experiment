package com.school.project.repository;

import com.school.project.model.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Transactional
    void deleteUserAccountByUserId(Long id);

    Optional<UserAccount> getUserAccountByUserId (Long id);

}
