package com.yu.mytraffice10_12.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.yu.mytraffice10_12.R;
import com.yu.mytraffice10_12.myClass.MyBaseAdpter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 小新 on 2017/10/14.
 */

public class fragment1 extends Fragment {
    View view;
    private Button btn_F1Piliang;
    private Button btn_F1Chongzhi;
    private ListView lv_Frag1;
    int carNumber = 4;
    String http;
    int carCount = 1;
    int MyNet = 0;
    private ArrayList<Map<String,Object>>  marrayList = new ArrayList<Map<String, Object>>();
    int shu = 1;
    private int[] img = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,};
    String[] name = {"张三","李四","王五","赵六"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_F1Piliang = (Button) view.findViewById(R.id.btn_f1_piliang);
        btn_F1Chongzhi = (Button) view.findViewById(R.id.btn_f1_chongzhi);
        lv_Frag1 = (ListView) view.findViewById(R.id.lv_frag1);
        initList();
    }
    public void initList(){
//        http = "";
//        JSONObject carId = new JSONObject();
//        try {
//            carId.put("CarId",1);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        json0();
       // getAllCarValue(http,carId);
    }
//    public void getAllCarValue(String http,JSONObject object){
//        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, http, object, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject object) {
//                String str = object.toString();
//                if (MyNet==0){
//                    json0(str);
//                }else if (MyNet==1){
//
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        });
//
//    }
    private void json0(){
//            JSONObject jsonObject = new JSONObject(str);
//            String shu1 = jsonObject.optString("serverinfo");
//            JSONObject  jsonObject1 = new JSONObject(shu1);
//            int shu2 = Integer.parseInt(jsonObject1.optString("Balance"));
            
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("number",carCount);
            map.put("img",img[carCount-1]);
            map.put("chepai","鲁Q12345"+carCount);
            map.put("chezhu",name[carCount-1]);
            map.put("yue",6);
            map.put("chongzhi",false);
            marrayList.add(map);
            carCount = carCount+1;
            if (carCount<=carNumber){
                JSONObject carId = new JSONObject();
                try {
                    carId.put("CarId",carCount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MyNet=0;
                json0();
            }else{
                MyBaseAdpter base = new MyBaseAdpter(getActivity(),marrayList,shu);
                lv_Frag1.setAdapter(base);
            }

    }
}
