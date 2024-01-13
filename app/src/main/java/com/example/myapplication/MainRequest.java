package com.example.myapplication;

import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainRequest extends StringRequest {
    //서버 URL 설정
    final static private String url = "http://203.249.87.58/class_501/501_S3/php/html/db-android-user_transaction.php";
    private Map<String, String> map;

    public MainRequest(String userID, Response.Listener<String> listener) {
        super(Method.POST,url,listener,null);

        map = new HashMap<>();
        map.put("user_id",userID);
    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
