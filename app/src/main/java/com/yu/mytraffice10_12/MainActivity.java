package com.yu.mytraffice10_12;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.yu.mytraffice10_12.common.urlBeanHttp;
import com.yu.mytraffice10_12.common.utilSetGet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnMainWifi;
    private EditText etMainName;
    private EditText etMainPassword;
    private CheckBox checkMain1;
    private CheckBox checkMain2;
    private Button btnMainEnter;
    private Button btnMainSign;
    String admin;
    String urlhttp,urlport;
    urlBeanHttp beanHttp;
    SharedPreferences DBprefer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMainWifi = (Button) findViewById(R.id.btn_main_wifi);
        etMainName = (EditText) findViewById(R.id.et_main_name);
        etMainPassword = (EditText) findViewById(R.id.et_main_password);
        checkMain1 = (CheckBox) findViewById(R.id.check_main_1);
        checkMain2 = (CheckBox) findViewById(R.id.check_main_2);
        btnMainEnter = (Button) findViewById(R.id.btn_main_enter);
        btnMainSign = (Button) findViewById(R.id.btn_main_sign);
        btnMainEnter.setOnClickListener(this);
        btnMainSign.setOnClickListener(this);
        btnMainWifi.setOnClickListener(this);
        beanHttp = utilSetGet.loadSetting(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main_enter:
                String uname = etMainName.getText().toString();
                String upass = etMainPassword.getText().toString();
                if(uname.trim().equals("")||upass.trim().equals("")){
                    Toast.makeText(MainActivity.this,"输入内容不能为空！",Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this,"输入内容不能为空！",Toast.LENGTH_LONG).show();
                    return;
                }
                Boolean isCheck = checkMain1.isChecked();
                if (isCheck){
                    DBprefer = this.getSharedPreferences("main",MODE_PRIVATE);
                    SharedPreferences.Editor editor = DBprefer.edit();
                    editor.putString("name",uname);
                    editor.putString("pass",upass);
                    editor.commit();
                }else {
                    DBprefer = this.getSharedPreferences("main",MODE_PRIVATE);
                    SharedPreferences.Editor editor = DBprefer.edit();
                    editor.putString("name",null);
                    editor.putString("pass",null);
                    editor.commit();
                }
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_main_sign:
                break;
            case R.id.btn_main_wifi:
                wifiDialog();
                break;

        }
    }
    public void wifiDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Setting");
        dialog.getWindow().setContentView(R.layout.wifidialog);
        final EditText etSettingIP = (EditText) dialog.getWindow().findViewById(R.id.et_setting_IP);
        final EditText etSettingPort = (EditText) dialog.getWindow().findViewById(R.id.et_setting_port);
        Button btnSettingSave = (Button) dialog.getWindow().findViewById(R.id.btn_setting_save);
        Button btnSettingCancel = (Button) dialog.getWindow().findViewById(R.id.btn_setting_cancel);
        etSettingIP.setText(beanHttp.getUrlHttp());
        etSettingPort.setText(beanHttp.getUrlPort());
        btnSettingSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG).show();
                urlhttp = etSettingIP.getText().toString();
                urlport = etSettingPort.getText().toString();
                if (urlhttp==null||urlhttp.equals("")){
                    Toast.makeText(MainActivity.this,"输入内容不能为空",Toast.LENGTH_LONG).show();
                }else if (urlport==null||urlport.equals("")){
                    Toast.makeText(MainActivity.this,"输入内容不能为空",Toast.LENGTH_LONG).show();
                }else {
                    utilSetGet.saveSettig(MainActivity.this,urlhttp,urlport);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}
