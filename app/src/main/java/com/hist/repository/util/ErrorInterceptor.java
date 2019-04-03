package com.hist.repository.util;

import android.util.Log;
import retrofit2.Response;

/**
 * HTTP 에러 인터셉터
 * Author:JJW
 * Date: 2018.04.02
 * Remark:
 */


public class ErrorInterceptor {
    //private Logger logger = null;
    private String className, methodName;
    private Response errorResponse;
    private Class errorClass;

    public ErrorInterceptor(Class errorClass) {
        this.errorClass = errorClass;
    }

    public HttpError parseError(Response response) {
        errorResponse = response;

        String errorMessage = null;
        switch (response.code()) {
            case 200:   // success
                break;
            case 401:   // Unauthorized : access_token 오류
            case 403:   // Forbidden
                errorMessage = "서비스에 대한 호출 권한이 없습니다.";
                break;
            case 400:   // Bad Request : 쿼리 이상
            case 404:   // Not Found
            case 405:   // Method Not Allowed
            case 406:   // Not Acceptable : 요청한 페이지가 요청한 콘텐츠 특성으로 응답할 수 없음
            case 444:   // nginx 응답없음
                errorMessage = "잘못된 접근입니다.";
                break;
            case 408:
                errorMessage = "요청 시간이 초과되었습니다.";
                break;
            case 500:   // Internal Server Error
            case 502:   // Bad Gateway : nginx가 잘못된 서버로 요청함
            case 503:   // Service Unavailable : Server overload or down
            case 504:   // Gateway Time Out
                errorMessage = "일시적인 서버 장애입니다. 다시 시도해주세요.";
                break;
            default:
                errorMessage = "(" + response.code() + ") 일시적인 서버 장애입니다. 다시 시도해주세요.";
                break;
        }

        Log.e("RESPONSE_CODE", "(" + response.code() + ")  " + response.message());
        return new HttpError(response.code(), errorMessage);
    }
}
