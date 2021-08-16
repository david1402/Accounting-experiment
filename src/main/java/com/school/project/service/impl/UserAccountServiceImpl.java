package com.school.project.service.impl;

import com.school.project.exception.UserAccountNotFoundException;
import com.school.project.model.entities.User;
import com.school.project.model.entities.UserAccount;
import com.school.project.model.types.UserAccountType;
import com.school.project.repository.UserAccountRepository;
import com.school.project.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserAccount createUserAccount(User user, UserAccountType userAccountType) {
        UserAccount userAccount = UserAccount.builder()
                .user(user)
                .accountRole(userAccountType)
                .build();
        userAccountRepository.save(userAccount);
        return userAccount;
    }

    @Override
    public UserAccount getUserAccountByUserId(Long id) {
        Optional<UserAccount> account = userAccountRepository.getUserAccountByUserId(id);
        if (!account.isPresent()) throw new UserAccountNotFoundException("User account with userId : " + id + " is not found");
        return account.get();
    }

    @Override
    public void updateAccountRole(Long userId, Integer roleId){
        UserAccount account = getUserAccountByUserId(userId);
        account.setAccountRole(UserAccountType.values()[roleId-1]);
        userAccountRepository.saveAndFlush(account);
    }
}
