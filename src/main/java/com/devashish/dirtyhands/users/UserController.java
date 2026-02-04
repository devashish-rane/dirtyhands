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

import com.devashish.dirtyhands.common.ApiResponse;
import com.devashish.dirtyhands.common.RequestIdUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDetail>>> getUsers(HttpServletRequest request) {
        String requestId = RequestIdUtil.getRequestId(request);
        return ResponseEntity.ok(ApiResponse.ok(this.userService.getAllUsers(), requestId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDetail>> postUsers(
        @Valid @RequestBody UserRequest request,
        HttpServletRequest httpRequest
    ) {
        UserDetail user = new UserDetail();
        user.setName(request.name());
        user.setCollegeName(request.collegeName());

        UserDetail saved = this.userService.save(user);
        String requestId = RequestIdUtil.getRequestId(httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(saved, requestId));
    }
}
