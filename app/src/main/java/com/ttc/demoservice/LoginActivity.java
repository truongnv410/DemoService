package com.ttc.demoservice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFunction();
            }
        });
    }

    private void loginFunction() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Bạn nhập thiếu thông tin", Toast.LENGTH_SHORT).show();
        }else{
            callAPILogin(username, password);
        }
    }

    private void callAPILogin(String username, String password){
        DemoService service = NetworkController.getApiInfo();
        Call<BaseResponse<User>> call = service.login(new LoginRequest(username, password));
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                if(response.isSuccessful()){
                    SharedPreferences preferences = getSharedPreferences("login_data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", response.body().getData().getToken());
                    editor.apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "Đã có lỗi xảy ra, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Lỗi không xác định xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }
}