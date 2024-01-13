package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_r_id, et_r_name, et_r_phone,et_r_email, et_r_city, et_r_pw, et_r_cpw;
    private Button btn_r_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 엑티비티 실행 시 처음으로 실행되는 코드
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_r_id = findViewById(R.id.et_r_id);
        et_r_name = findViewById(R.id.et_r_name);
        et_r_phone = findViewById(R.id.et_r_phone);
        et_r_email = findViewById(R.id.et_r_email);
        et_r_city = findViewById(R.id.et_r_city);
        et_r_pw = findViewById(R.id.et_r_pw);
        et_r_cpw = findViewById(R.id.et_r_cpw);

        // 회원가입 버튼
        btn_r_reg = findViewById(R.id.btn_r_reg);

        btn_r_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String userID = et_r_id.getText().toString();
                String userName = et_r_name.getText().toString();
                String userPhone = et_r_phone.getText().toString();
                String userEmail = et_r_email.getText().toString();
                String userCity = et_r_city.getText().toString();
                String userPW = et_r_pw.getText().toString();
                String userCPW = et_r_cpw.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if(success){
                                Toast.makeText(getApplicationContext(),"Register Success",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Register Failed",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(userID,userName,userPhone,userEmail,userCity,userPW,userCPW,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}