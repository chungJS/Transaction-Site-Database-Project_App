package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText et_l_id, et_l_pw;
    private Button btn_l_log, btn_l_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_l_id = findViewById(R.id.et_l_id);
        et_l_pw = findViewById(R.id.et_l_pw);
        btn_l_log = findViewById(R.id.btn_l_log);
        btn_l_reg = findViewById(R.id.btn_l_reg);
        btn_l_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_l_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = et_l_id.getText().toString();
                String userPW = et_l_pw.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                String user_id = jsonObject.getString("user_id");
                                String user_password = jsonObject.getString("user_password");
                                Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("user_id",user_id);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "JSON Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();  // 에러 로그를 출력
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();  // 다른 예외에 대한 에러 로그를 출력
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID,userPW,responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}