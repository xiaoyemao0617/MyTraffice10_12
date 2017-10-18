package com.yu.mytraffice10_12.Fragment.frag3_lukou;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yu.mytraffice10_12.R;
import com.yu.mytraffice10_12.common.MyTextView;
import com.yu.mytraffice10_12.common.urlBeanHttp;
import com.yu.mytraffice10_12.common.utilSetGet;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 小新 on 2017/10/17.
 */

public class frag3_1 extends Fragment {
    View view;
    View listV;
    urlBeanHttp beanHttp;
    private LinearLayout linearFrag31;
    int TrafficLightID = 1;
    LayoutInflater inflater;
    String yellow,green,red;
    String HTTP1;
    String[] name = {"路口","红灯时长","黄灯时长","绿灯时长","操作项","设置"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3_1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        linearFrag31 = (LinearLayout) view.findViewById(R.id.linear_frag3_1_);
        beanHttp = utilSetGet.loadSetting(getActivity());
        initLayout();
    }
    public void initLayout(){
        inflater = LayoutInflater.from(getActivity());
        listV = inflater.inflate(R.layout.frag3_list,null);
        MyTextView myTextView = (MyTextView) listV.findViewById(R.id.list1_1);
        myTextView.setText(name[0]);
        myTextView =(MyTextView) listV.findViewById(R.id.list1_2);
        myTextView.setText(name[1]);
        myTextView = (MyTextView) listV.findViewById(R.id.list1_3);
        myTextView.setText(name[2]);
        myTextView = (MyTextView) listV.findViewById(R.id.list1_4);
        myTextView.setText(name[3]);
        myTextView = (MyTextView) listV.findViewById(R.id.list1_5);
        myTextView.setText(name[4]);
        myTextView = (MyTextView) listV.findViewById(R.id.list1_6);
        myTextView.setText(name[5]);
        CheckBox checkFrag31 = (CheckBox) listV.findViewById(R.id.check_frag3_1);
        checkFrag31.setVisibility(View.GONE);
        Button btnFrag31 = (Button) listV.findViewById(R.id.btn_frag3_1);
        btnFrag31.setVisibility(View.GONE);
        linearFrag31.addView(listV);
        HTTP1 = "http://"+beanHttp.getUrlHttp()+":"+beanHttp.getUrlPort()+"/transportservice/type/jason/action/GetTrafficLightConfigAction.do";
        JSONObject object1 = new JSONObject();
        try {
            object1.put("TrafficLightId",TrafficLightID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("http",HTTP1+object1);
        getAllCarValue(HTTP1,object1);
    }
    public void getAllCarValue(String http,JSONObject object){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, http, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String shu1 = jsonObject.toString();
                Log.i("shu1",shu1);
                getExcel(shu1);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"网络访问失败！",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(objectRequest);
    }
    public void getExcel(String shu){
        try {
            JSONObject object1 = new JSONObject(shu);
            String serverinfo = object1.optString("serverinfo");
            JSONObject object2 = new JSONObject(serverinfo);
            yellow =object2.optString("YellowTime");
            green =object2.optString("GreenTime");
            red =object2.optString("RedTime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        inflater = LayoutInflater.from(getActivity());
        listV = inflater.inflate(R.layout.frag3_list,null);
        MyTextView myTextView = (MyTextView) listV.findViewById(R.id.list1_1);
        myTextView.setText(String.valueOf(TrafficLightID));
        myTextView =(MyTextView) listV.findViewById(R.id.list1_2);
        myTextView.setText(red);
        myTextView = (MyTextView) listV.findViewById(R.id.list1_3);
        myTextView.setText(yellow);
        myTextView = (MyTextView) listV.findViewById(R.id.list1_4);
        myTextView.setText(green);
        CheckBox checkFrag31 = (CheckBox) listV.findViewById(R.id.check_frag3_1);
        final Button btnFrag31 = (Button) listV.findViewById(R.id.btn_frag3_1);
        btnFrag31.setText("设置");
//        btnFrag31.setTag(TrafficLightID);
        btnFrag31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //btnFrag31.getTag();
                //SettingDialog(TrafficLightID);
            }
        });
        linearFrag31.addView(listV);
        TrafficLightID++;
        if (TrafficLightID<=5){
            JSONObject object1 = new JSONObject();
            try {
                object1.put("TrafficLightId",TrafficLightID);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.i("http",HTTP1+object1);
            getAllCarValue(HTTP1,object1);
        }
    }
//    public void SettingDialog(int trafficLightID){
//        Dialog dialog = new Dialog(getActivity());
//        dialog.setTitle("红路灯设置");
//        dialog.getWindow().setContentView(R.layout.frag3_dialog_setting);
//        EditText etFrag31Dialog1 = (EditText) dialog.findViewById(R.id.et_frag3_1_dialog1);
//        EditText etFrag31Dialog2 = (EditText) dialog.findViewById(R.id.et_frag3_1_dialog2);
//        EditText etFrag31Dialog3 = (EditText) dialog.findViewById(R.id.et_frag3_1_dialog3);
//        Button btnFrag31Dialog1Save = (Button) dialog.findViewById(R.id.btn_frag3_1_dialog1_save);
//        Button btnFrag31Dialog1Cancel = (Button) dialog.findViewById(R.id.btn_frag3_1_dialog1_cancel);
//        dialog.show();
//    }

}
