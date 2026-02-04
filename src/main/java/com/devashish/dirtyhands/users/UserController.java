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
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers(HttpServletRequest request) {
        String requestId = RequestIdUtil.getRequestId(request);
        List<UserResponse> users = this.userService.getAllUsers().stream()
            .map(UserMapper::toResponse)
            .toList();
        return ResponseEntity.ok(ApiResponse.ok(users, requestId));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> postUsers(
        @Valid @RequestBody UserRequest request,
        HttpServletRequest httpRequest
    ) {
        UserDetail user = UserMapper.toEntity(request);

        UserDetail saved = this.userService.save(user);
        String requestId = RequestIdUtil.getRequestId(httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok(UserMapper.toResponse(saved), requestId));
    }
}
