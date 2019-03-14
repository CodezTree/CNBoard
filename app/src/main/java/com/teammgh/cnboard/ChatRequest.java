package com.teammgh.cnboard;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ChatRequest extends StringRequest {

    final static private String URL = "http://10.1.111.142:8000/Meal/addComment/";
    private Map<String, String> parameters;

    public ChatRequest(String postID, String comment, String studentNum, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("postID",postID);
        parameters.put("comment",comment);
        parameters.put("studentNum",studentNum);

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
