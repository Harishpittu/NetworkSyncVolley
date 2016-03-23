package com.sevencstudio.harish.networksyncframework.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.sevencstudio.harish.networksyncframework.R;
import com.sevencstudio.harish.networksyncframework.models.Games;
import com.sevencstudio.harish.networksyncvolley.VolleyWrapper;
import com.sevencstudio.harish.networksyncvolley.VolleyWrapperListener;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetMethodsExamples extends Activity implements VolleyWrapperListener {

    VolleyWrapper volleyWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_methods_example);
        volleyWrapper = new VolleyWrapper(this);
    }


    public void Get(View v) {
        volleyWrapper.Get(null, "http://52.34.24.109/api", "games/?archived=TRUE", VolleyWrapper.JSONOBJECT, false, false, "Get");

    }

    public void GetPaginate(View v) {
        volleyWrapper.Get(null, "http://52.34.24.109/api", "games/?archived=TRUE", VolleyWrapper.JSONOBJECT, true, false, "GetPaginate");

    }

    public void GetStore(View v) {
        volleyWrapper.Get(new Games(), "http://52.34.24.109/api", "games/?archived=TRUE", VolleyWrapper.JSONOBJECT, false, true, "GetStore");

    }

    public void GetStorePaginate(View v) {
        volleyWrapper.Get(new Games(), "http://52.34.24.109/api", "games/?archived=TRUE", VolleyWrapper.JSONOBJECT, true, true, "GetStorePaginate");

    }

    public void GetStoredObjects(View v) {
        volleyWrapper.GetStoredObjects(new Games());
    }

    public void PostExample(View v) {

        startActivity(new Intent(GetMethodsExamples.this, PostExample.class));
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
