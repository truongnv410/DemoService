package com.ttc.demoservice;

public class BaseResponse<T> {
  private int status;
  private String message;
  private String access_token;
  private T data;

  public BaseResponse() {
  }

  public BaseResponse(int status, String message, String access_token, T data) {
    this.status = status;
    this.message = message;
    this.access_token = access_token;
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getAccess_token() {
    return access_token;
  }

  public void setAccess_token(String access_token) {
    this.access_token = access_token;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
