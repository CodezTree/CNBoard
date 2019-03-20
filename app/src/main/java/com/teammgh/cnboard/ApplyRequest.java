package com.teammgh.cnboard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by USER on 2018-01-12.
 */

public class ApplyRequest extends StringRequest{

    final static private String URL = "http://10.1.111.142:8000/Service/applyCnboard/";
    private Map<String, String> parameters;

    public ApplyRequest(Response.Listener<String> listener, int student_number, String apply_target, String apply_content) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("student_number", Integer.toString(student_number));
        parameters.put("apply_target", apply_target);
        parameters.put("apply_content", apply_content);
    }

    @Override
    public Map<String, String> getParams() { return parameters; }
}