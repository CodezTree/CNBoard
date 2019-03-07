package com.teammgh.cnboard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 2018-01-12.
 */

public class RefreshDetailedNotice extends StringRequest{

    final static private String URL = "http://45.32.49.247:8000/Service/showFilteredNotice/";
    private Map<String, String> parameters;

    public RefreshDetailedNotice(Response.Listener<String> listener, int notice_kind, int target_grade) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("notice_kind", Integer.toString(notice_kind));
        parameters.put("target_grade", Integer.toString(target_grade));
    }

    @Override
    public Map<String, String> getParams() { return parameters; }
}
