package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.CookieManager;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText et_id,et_pw;
    String txt_id,txt_pw,strRet;
    TextView txt_loginFail;
    CheckBox chk_loginSave;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        btn_login = findViewById(R.id.btn_login);
        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        txt_loginFail = findViewById(R.id.txt_loginFail);
        chk_loginSave = findViewById(R.id.checkbox);
        linearLayout = findViewById(R.id.linearlayout);

        linearLayout.setVisibility(View.GONE);

        SharedPreferences pref=getSharedPreferences("pref", Activity.MODE_PRIVATE);
        String id=pref.getString("id_save", "");
        String pwd=pref.getString("pwd_save", "");
        Boolean chk1=pref.getBoolean("chk1", false);

        if(chk1){
            et_id.setText(id);
            et_pw.setText(pwd);
            chk_loginSave.setChecked(chk1);
        }else{
            et_id.setText("");
            et_pw.setText("");
            chk_loginSave.setChecked(false);
        }


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_login.setPressed(true);

                txt_id = et_id.getText().toString();
                txt_pw = et_pw.getText().toString();

                Login();

                if (txt_id.equals("") || txt_pw.equals("")) {
                    linearLayout.setVisibility(View.VISIBLE);
                    txt_loginFail.setText("아이디와 비밀번호를 입력해주세요.");
                } else {
                    if (strRet.contains("입력한 사용자 ID 또는 비밀번호가 잘못되었습니다")) {
                        linearLayout.setVisibility(View.VISIBLE);
                        txt_loginFail.setText("아이디 혹은 비밀번호가 잘못되었습니다.");
                        et_pw.setText("");
                    } else {
                        saveData();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"환영합니다",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
        }


    public void Login(){
        CookieManager cm = new CookieManager();
        CookieManager.setDefault(cm);

        HashMap<String, String> postMap = new HashMap<String, String>();

        postMap.put("pwMenuId", "");
        postMap.put("pwMenuUrl", "");
        postMap.put("trmnlIdntNo", "");
        postMap.put("gcmRegId", "");
        postMap.put("osKnd", "");
        postMap.put("osVer", "");
        postMap.put("dpi", "");
        postMap.put("rsotnHrzn", "");
        postMap.put("rsotnVrtc", "");
        postMap.put("modelNo", "");
        postMap.put("ipInfo", "");
        postMap.put("loginId", txt_id);
        postMap.put("loginPw", txt_pw);


        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36";
        String referer = "https://student.cnsa.hs.kr/login/userLogin";

        try {
            Connection.Response res = Jsoup.connect("http://10.1.100.32/login/userLogin")
                    .data(postMap)
                    .userAgent(userAgent)
                    .referrer(referer)
                    .method(Connection.Method.POST)
                    .execute();

            Document doc = res.parse();
            strRet = String.valueOf(doc);
            Log.d("debug doc",String.valueOf(doc));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("id_save", txt_id);
        editor.putString("pwd_save", txt_pw);
        editor.putBoolean("chk1", chk_loginSave.isChecked());
        editor.apply();
    }


}

