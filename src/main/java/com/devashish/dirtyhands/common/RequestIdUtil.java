package com.devashish.dirtyhands.common;

import jakarta.servlet.http.HttpServletRequest;

public final class RequestIdUtil {
    private RequestIdUtil() {}

    public static String getRequestId(HttpServletRequest request) {
        Object attr = request.getAttribute(RequestIdFilter.REQUEST_ID_ATTR);
        if (attr instanceof String id && !id.isBlank()) {
            return id;
        }
        String header = request.getHeader(RequestIdFilter.REQUEST_ID_HEADER);
        return header == null ? "" : header;
    }
}
