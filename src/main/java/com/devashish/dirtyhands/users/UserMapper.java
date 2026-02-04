package com.devashish.dirtyhands.users;

public final class UserMapper {
    private UserMapper() {}

    public static UserDetail toEntity(UserRequest request) {
        UserDetail user = new UserDetail();
        user.setName(request.name());
        user.setCollegeName(request.collegeName());
        return user;
    }

    public static UserResponse toResponse(UserDetail user) {
        return new UserResponse(user.getId(), user.getName(), user.getCollegeName());
    }
}
