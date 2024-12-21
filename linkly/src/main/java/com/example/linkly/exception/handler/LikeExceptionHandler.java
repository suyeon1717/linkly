package com.example.linkly.exception.handler;

import com.example.linkly.common.exception.ExceptionUtil;
import com.example.linkly.exception.LikeException;
import com.example.linkly.exception.util.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class LikeExceptionHandler extends BaseExceptionHandler{
    @ExceptionHandler(LikeException.class)
    public ResponseEntity<ErrorResponse> handleLoginException(LikeException e) {
        List<String> errorField = e.getErrorField();
        if (errorField != null && !errorField.isEmpty()) {
            return super.handleException(e, errorField);  // 검증 예외 처리
        }
        // 로그인 오류에 대한 특화된 처리

        return ExceptionUtil.GenerateResponseEntity(e.getStatus().value(), e.getMessage(), e.getStatus());
    }
}
