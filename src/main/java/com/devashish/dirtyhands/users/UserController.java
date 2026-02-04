package com.devashish.dirtyhands.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<UserDetail>> getUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDetail> postUsers(@Valid @RequestBody UserRequest request) {
        UserDetail user = new UserDetail();
        user.setName(request.name());
        user.setCollegeName(request.collegeName());

        UserDetail saved = this.userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
