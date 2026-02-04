package com.devashish.dirtyhands.users;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
    @NotBlank String name,
    @NotBlank String collegeName
) {}
