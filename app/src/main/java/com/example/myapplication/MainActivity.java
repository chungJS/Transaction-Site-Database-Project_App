package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView tv_name,trans1,trans2,trans3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_name = findViewById(R.id.tv_name);
        trans1 = findViewById(R.id.trans1);
        trans2 = findViewById(R.id.trans2);
        trans3 = findViewById(R.id.trans3);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("user_id");

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if(success){
                        String user_id = jsonObject.getString("user_id");
                        String user_name = jsonObject.getString("user_name");
                        String shop_name = jsonObject.getString("shop_name");
                        String product_name = jsonObject.getString("product_name");
                        String purchase_date = jsonObject.getString("purchase_date");
                        String purchase_price = jsonObject.getString("purchase_price");

                        String str = 


                        Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_SHORT).show();
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

        MainRequest mainRequest = new MainRequest(userID,responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(mainRequest);


    }
}