package com.devashish.dirtyhands.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    // constructor injection
    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserDetail> getAllUsers() {
        return userRepo.findAll();
    }

    public UserDetail save(UserDetail userDetail) {
        return userRepo.save(userDetail);
    }
}
