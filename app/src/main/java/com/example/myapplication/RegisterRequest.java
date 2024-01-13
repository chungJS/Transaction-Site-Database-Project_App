package com.example.myapplication;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    //서버 URL 설정

    final static private String url = "http://203.249.87.58/class_501/501_S3/php/html/db-android-register.php";
    private Map<String, String> map;

    public RegisterRequest(String userID, String userName, String userPhone, String userEmail,
                           String userCity, String userPW, String userCPW, Response.Listener<String> listener) {
        super(Method.POST,url,listener,null);

        map = new HashMap<>();
        map.put("user_id",userID);
        map.put("user_name",userName);
        map.put("user_phone",userPhone);
        map.put("user_email",userEmail);
        map.put("user_city",userCity);
        map.put("user_password",userPW);
        map.put("user_passwordcheck",userCPW);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
