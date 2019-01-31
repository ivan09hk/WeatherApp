package com.ivanleung.hksgweatherapp.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ivanleung.hksgweatherapp.data.model.WeatherModel;

import org.json.JSONException;
import org.json.JSONObject;

public class ApiModule {

    public static final String HKID = "1819730";
    public static final String SGID = "1880251";
    private final String APPID = "37959822167eb7e5f6fe73c48db44371";

    public static final int API_RESULT_SUCCESS = 1;
    public static final int API_RESULT_FAIL = 0;

    private static ApiModule sInstance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    public ApiModule(Context mContext){
        this.mContext = mContext;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized ApiModule getInstance(Context mContext){
        if (sInstance == null) {
            sInstance = new ApiModule(mContext);
        }
        return sInstance;
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    public void getWeather(ApiCallBack mApiCallBack){
        int ApiTag = 1;
        String mUrl = "http://api.openweathermap.org/data/2.5/group?id="+HKID+","+SGID+"&appid="+APPID;
        getApi(mUrl, ApiTag, (mApiTag, mResult, mResponse) -> {
            if(mApiCallBack != null){
                WeatherModel weatherModel = new WeatherModel();
                weatherModel.InsertWeatherToDB(mContext, mResponse);
                mApiCallBack.OnApiCallBack(mApiTag, mResult, mResponse);
            }else{
                mApiCallBack.OnApiCallBack(mApiTag, API_RESULT_FAIL, "Error");
            }
        });
    }

    private void  getApi(String mUrl, int mApiTag, ApiCallBack mOnApiCallBack){
        MyStringRequest myStringRequest = new MyStringRequest(Request.Method.GET, mUrl, response -> {
            try {
                Log.i("APImodule", "API result : " + new JSONObject(response).toString(5));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mOnApiCallBack.OnApiCallBack(mApiTag, API_RESULT_SUCCESS, response);
        }, error -> {
            //TODO Error handle
            mOnApiCallBack.OnApiCallBack(mApiTag, API_RESULT_FAIL, "Error");
        });
        this.addToRequestQueue(myStringRequest);
    }

    private <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
