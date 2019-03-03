package com.teammgh.cnboard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by USER on 2018-01-12.
 */

public class RefreshExam extends StringRequest{

    final static private String URL = "http://45.32.49.247:8000/Service/showExam";
    private Map<String, String> parameter;

    public RefreshExam(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
    }

    @Override
    public Map<String, String> getParams() { return parameter; }
}