package com.teammgh.cnboard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by USER on 2018-01-12.
 */

public class RefreshNotice extends StringRequest{

    final static private String URL = "http://45.32.49.247/notice/noticeList";
    private Map<String, String> parameters;

    public RefreshNotice(Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);
    }

    @Override
    public Map<String, String> getParams() { return parameters; }
}
