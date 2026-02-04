package com.devashish.dirtyhands.common;

import java.time.Instant;

public record ApiResponse<T>(boolean success, T data, ApiError error, ApiMeta meta) {
    public static <T> ApiResponse<T> ok(T data, String requestId) {
        return new ApiResponse<>(true, data, null, new ApiMeta(Instant.now(), requestId));
    }

    public static <T> ApiResponse<T> error(ApiError error, String requestId) {
        return new ApiResponse<>(false, null, error, new ApiMeta(Instant.now(), requestId));
    }
}
