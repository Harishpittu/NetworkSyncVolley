package com.sevencstudio.harish.networksyncvolley;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by harish on 16/03/16.
 */
public interface VolleyWrapperListener {

    void VolleyWrapperError(VolleyError volleyError, int statuscode, String data);
    void VolleyWrapperResponse(JSONArray jsonArray);
    void VolleyWrapperResponse(JSONObject jsonObject);
    void VolleyWrapperResponse(Object[] objects);
    void VolleyWrapperNoNetwork();
}
