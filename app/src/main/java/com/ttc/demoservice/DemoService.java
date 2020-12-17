package com.ttc.demoservice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DemoService {
    @POST("home/login")
    Call<BaseResponse<User>> login(@Body LoginRequest request);
}
