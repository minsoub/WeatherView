package com.hist.repository.util;

/**
 * HTTP 에러 처리
 * Author:JJW
 * Date: 2018.04.02
 * Remark:
 */

public class HttpError {
    private int statusCode;
    private String message;

    public HttpError() {
    }

    public HttpError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
}
