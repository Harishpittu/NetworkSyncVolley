package com.sevencstudio.harish.networksyncvolley;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by harish on 16/03/16.
 */
public class VolleyWrapper {

    private static String AuthToken = null;
    private static VolleyWrapperListener volleyWrapperListener;
    private static String NextPage = "";
    private boolean _pagination = false;
    private Object PaginationRealmObject;
    public static String JSONOBJECT = "jsonobject";
    public static String JSONARRAY = "jsonarray";
    private boolean _store = false;
    public static String AUTHTOKEN = "auth_token";

    public VolleyWrapper(VolleyWrapperListener volleyWrapperListener) {
        this.volleyWrapperListener = volleyWrapperListener;
    }


    public void Get(Object object, String Url, String EndPoint, String type, boolean Pagination, boolean store, String TAG) {


        if (type.matches(VolleyWrapper.JSONOBJECT)) {

            if(store)
            GetStoredObjects(object);
            _pagination=false;
            VolleyJsonObjectRequest(object, Request.Method.GET, null, Url + "/" + EndPoint, null, TAG, _pagination, store);

            _pagination = Pagination;
            _store = store;


        } else if (type.matches(VolleyWrapper.JSONARRAY)) {

            VolleyJsonArrayRequest(object, store, Url + "/" + EndPoint, TAG);

        }


    }


    public void Delete(String Url, String EndPoint, String TAG) {
        VolleyJsonObjectRequest(null, Request.Method.DELETE, null, Url + "/" + EndPoint, null, TAG, false, false);
    }

    public void Put(Object object, String Url, String EndPoint, String TAG) {
        VolleyJsonObjectRequest(null, Request.Method.POST, "PUT", GetRequestUrl(Url, object, EndPoint), GetJsonobject(object), TAG, false, false);

    }

    public void Patch(Object object, String Url, String EndPoint, String TAG) {
        VolleyJsonObjectRequest(null, Request.Method.POST, "PATCH", GetRequestUrl(Url, object, EndPoint), GetJsonobject(object), TAG, false, false);

    }

    public void Post(Object object, String Url, String EndPoint, String TAG) {

        VolleyJsonObjectRequest(null, Request.Method.POST, null, GetRequestUrl(Url, object, EndPoint), GetJsonobject(object), TAG, false, false);

    }

    public void PostBind(Activity activity, int layoutid, Object object, String Url, String EndPoint, String TAG) {

        ViewModelBinding.Bind(activity, layoutid, object);

        VolleyJsonObjectRequest(null, Request.Method.POST, null, GetRequestUrl(Url, object, EndPoint), GetJsonobject(object), TAG, false, false);


    }

    public void PutBind(Activity activity, int id, Object object, String Url, String EndPoint, String TAG) {

        ViewModelBinding.Bind(activity, id, object);
        VolleyJsonObjectRequest(null, Request.Method.POST, "PUT", GetRequestUrl(Url, object, EndPoint), GetJsonobject(object), TAG, false, false);

    }

    public void CancelRequest(String Tag) {
        AppController.getInstance().cancelPendingRequests(Tag);
    }

    private void StoreInRealm(Object object, JSONObject jsonObject, boolean Pagination) {
        Realm realm = Realm.getInstance(AppController.getInstance().getApplicationContext());
        PaginationRealmObject = object;
        Class cls = object.getClass();
        realm.beginTransaction();
        if (!Pagination)
            realm.clear(cls);
        realm.createObjectFromJson(cls, jsonObject);

        realm.commitTransaction();
        RealmResults<RealmObject> realmResults = realm.where(cls).findAll();
        Object[] objects = realmResults.toArray();
        volleyWrapperListener.VolleyWrapperResponse(objects);

    }

    private void StoreInRealm(Object object, JSONArray jsonArray) {
        Realm realm = Realm.getInstance(AppController.getInstance().getApplicationContext());
        PaginationRealmObject = object;
        Class cls = object.getClass();

        for (int i = 0; i < jsonArray.length(); i++) {
            realm.beginTransaction();

            realm.clear(cls);

            try {
                realm.createObjectFromJson(cls, jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            realm.commitTransaction();
        }
        RealmResults<RealmObject> realmResults = realm.where(cls).findAll();
        Object[] objects = realmResults.toArray();
        volleyWrapperListener.VolleyWrapperResponse(objects);

    }

    public void GetStoredObjects(Object object) {
        Realm realm = Realm.getInstance(AppController.getInstance().getApplicationContext());

        Class cls = object.getClass();
        RealmResults<RealmObject> realmResults = realm.where(cls).findAll();
        Object[] objects = realmResults.toArray();
        volleyWrapperListener.VolleyWrapperResponse(objects);
    }

    private static JSONObject GetJsonobject(Object o) {


        JSONObject jsonObject = null;
        if (o != null) {
            Gson gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    })
                    .create();

            try {
                jsonObject = new JSONObject(gson.toJson(o));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.d("jsonobject", jsonObject.toString());

        return jsonObject;
    }

    private static String GetRequestUrl(String Url, Object o, String endpoint) {
        String requesturl = null;
        if (endpoint != null)
            requesturl = Url + "/" + o.getClass().getSimpleName().toLowerCase() + endpoint;
        else
            requesturl = Url + "/" + o.getClass().getSimpleName().toLowerCase() + "/";

        return requesturl;
    }

    public void VolleyJsonObjectRequest(final Object object, int volley_request_method, final String override_request_method, String Url,
                                        JSONObject jsonObject, String tag, final boolean pagination, final boolean store)

    {
        JsonObjectRequest req = new JsonObjectRequest(volley_request_method, Url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            NextPage = response.getString("next");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        if (store)
                            StoreInRealm(object, response, pagination);
                        else
                            volleyWrapperListener.VolleyWrapperResponse(response);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NetworkError) {
                    volleyWrapperListener.VolleyWrapperNoNetwork();
                } else {
                    NetworkResponse response = error.networkResponse;
                    if (response != null)

                        volleyWrapperListener.VolleyWrapperError(error, response.statusCode, new String(response.data));
                }

            }

        }) {

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");

                SharedPreferenceStorage.SetContext(AppController.getInstance().getApplicationContext());
                AuthToken = SharedPreferenceStorage.GetString(VolleyWrapper.AUTHTOKEN);


                if (AuthToken != null) {
                    headers.put("Authorization", AuthToken);
                    Log.d("AUTH_TOKEN:", AuthToken);
                }
                if (override_request_method != null) {
                    headers.put("X-HTTP-Method-Override", override_request_method);
                    Log.d("REQUEST_METHOD:", override_request_method);

                }

                Log.d("Headers:", headers.toString());
                return headers;

            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(req, tag);
    }


    public void GetPagination() {
        Log.d("json next", NextPage);
        if (NextPage != null && !NextPage.matches("null")) {


            if (_pagination) {
                if (_store) {
                    VolleyJsonObjectRequest(PaginationRealmObject, Request.Method.GET, null, NextPage, null, "Store Pagination", _pagination, true);
                } else {
                    VolleyJsonObjectRequest(null, Request.Method.GET, null, NextPage, null, "Pagination", _pagination, false);

                }
            }
        }
    }


    public void VolleyJsonArrayRequest(final Object object, final boolean store, String Url, String tag)

    {
        JsonArrayRequest req = new JsonArrayRequest(Url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        if (store)
                            StoreInRealm(object, response);
                        else

                            volleyWrapperListener.VolleyWrapperResponse(response);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    volleyWrapperListener.VolleyWrapperNoNetwork();
                } else {
                    NetworkResponse response = error.networkResponse;
                    if (response != null)
                        volleyWrapperListener.VolleyWrapperError(error, response.statusCode, new String(response.data));
                }

            }

        }) {

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");

                SharedPreferenceStorage.SetContext(AppController.getInstance().getApplicationContext());
                AuthToken = SharedPreferenceStorage.GetString(VolleyWrapper.AUTHTOKEN);
                if (AuthToken != null) {
                    headers.put("Authorization", AuthToken);
                    Log.d("AUTH_TOKEN:", AuthToken);
                }


                Log.d("Headers:", headers.toString());
                return headers;

            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(req, tag);


    }


    private static String Image_Url;
    private static int error_resource_id;
    private static int placeholder_resource_id;

    static VolleyWrapper volleyWrapper;

    public static VolleyWrapper load(String url) {


        volleyWrapper.Image_Url = url;


        return volleyWrapper;
    }

    public static VolleyWrapper errorplaceholder(int resource) {

        volleyWrapper.error_resource_id = resource;

        return volleyWrapper;
    }

    public static VolleyWrapper placeholder(int resource) {

        volleyWrapper.placeholder_resource_id = resource;

        return volleyWrapper;
    }

    public static VolleyWrapper into(final ImageView imageView) {
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        imageView.setImageResource(volleyWrapper.placeholder_resource_id);
        imageLoader.get(volleyWrapper.Image_Url, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", "Image Load Error: " + error.getMessage());
                imageView.setImageResource(volleyWrapper.error_resource_id);
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    imageView.setImageBitmap(response.getBitmap());
                }
            }
        });
        return volleyWrapper;
    }

    public void ClearRealm(Object object) {
        Realm realm = Realm.getInstance(AppController.getInstance().getApplicationContext());
        Class cls = object.getClass();
        realm.beginTransaction();
        realm.clear(cls);
        realm.commitTransaction();
    }

    public static void DEBUG(String text, String TAG) {
        Log.d("#Volley Wrapper - " + TAG, text);
    }
    public static void TOAST(String text) {
        Toast.makeText(AppController.getInstance().getApplicationContext(),  text, Toast.LENGTH_SHORT).show();
    }

}
