package com.teammgh.cnboard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by USER on 2018-01-12.
 */

public class VersionRequest extends StringRequest{

    final static private String URL = "http://10.1.111.142:8000/Service/showAppVersion/";
    private Map<String, String> parameters;

    public VersionRequest(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
    }

    @Override
    public Map<String, String> getParams() { return parameters; }
}
