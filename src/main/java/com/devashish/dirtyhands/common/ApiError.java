package com.devashish.dirtyhands.common;

import java.util.Map;

public record ApiError(String code, String message, Map<String, String> details) {}
