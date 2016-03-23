package com.sevencstudio.harish.networksyncframework.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.sevencstudio.harish.networksyncframework.R;
import com.sevencstudio.harish.networksyncframework.User_Profiles;
import com.sevencstudio.harish.networksyncvolley.VolleyWrapper;
import com.sevencstudio.harish.networksyncvolley.VolleyWrapperListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class PostExample extends Activity implements VolleyWrapperListener {

    VolleyWrapper volleyWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_example);
        volleyWrapper = new VolleyWrapper(this);
    }

    public void Post(View v) {
        volleyWrapper.PostBind(this, R.layout.activity_post_example, new User_Profiles(), "http://52.34.24.109/api", null, "Post");
    }

    public void MVExample(View v) {
        startActivity(new Intent(PostExample.this, ModelViewBindingExample.class));
    }

    @Override
    public void VolleyWrapperError(VolleyError volleyError, int statuscode, String data) {
        VolleyWrapper.TOAST(statuscode + "###" + data);

    }

    @Override
    public void VolleyWrapperResponse(JSONArray jsonArray) {
        VolleyWrapper.TOAST(jsonArray.toString());

    }

    @Override
    public void VolleyWrapperResponse(JSONObject jsonObject) {
        VolleyWrapper.TOAST(jsonObject.toString());
        volleyWrapper.GetPagination();

    }

    @Override
    public void VolleyWrapperResponse(Object[] objects) {
        VolleyWrapper.TOAST("Objects Length" + objects.length);

        volleyWrapper.GetPagination();

    }

    @Override
    public void VolleyWrapperNoNetwork() {
        VolleyWrapper.TOAST("No Internet");
    }
}
