package com.example.linkly.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class LoginException extends BaseException {
  public LoginException(String message, HttpStatus status) {
    super(message,status);
  }
  public LoginException(String message, HttpStatus status, List<String> errorField) {
    super(message,status,errorField);
  }
}
