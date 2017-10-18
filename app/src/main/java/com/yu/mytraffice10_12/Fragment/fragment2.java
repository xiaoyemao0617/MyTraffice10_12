package com.yu.mytraffice10_12.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.yu.mytraffice10_12.R;
import com.yu.mytraffice10_12.common.urlBeanHttp;
import com.yu.mytraffice10_12.common.utilSetGet;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 小新 on 2017/10/16.
 */

public class fragment2 extends Fragment {
    private Button btnFrag2Xiangqing;
    private ExpandableListView exListView;
    View view;
    urlBeanHttp beanHttp;
    List<Map<String,Object>> groupData = new ArrayList<>();
    List<List<Map<String,Object>>> childData = new ArrayList<>();
    MyBaseListView exListAdpter = new MyBaseListView(getActivity());
    Context context1;
    int BusStationID = 1;
    int i;
    private static final String GROUP_TEXT = "group_text";//大组成员Map的key
    private static final String CHILD_TEXT1 = "child_text1";//小组成员Map的第一个key
    private static final String CHILD_TEXT2 = "child_text2";
    private static final String CHILD_TEXT3 = "child_text3";//小组成员Map的第二个key
    String[] names = {"中医院站","联想大厦站"};
    private String[] tree1_1;
    private String[] tree2_1;
    private String[] tree3_1;
    private String[] tree1_2;
    private String[] tree2_2;
    private String[] tree3_2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnFrag2Xiangqing = (Button) view.findViewById(R.id.btn_frag2_xiangqing);
        exListView = (ExpandableListView) view.findViewById(R.id.exListView);
        tree1_1 = new String[]{"1号（101人）","2号（101人）"};
        tree2_1 = new String[]{"5分钟到达","6分钟到达"};
        tree3_1 = new String[]{"距离站台100米","距离站台1000米"};

        tree1_2 = new String[]{"1号（101人）","2号（101人）"};
        tree2_2 = new String[]{"5分钟到达","7分钟到达"};
        tree3_2 = new String[]{"距离站台300米","距离站台1200米"};
        for (int i = 0; i <2; i++) {
            Map<String, Object> curGroupMap = new HashMap<>();
            groupData.add(curGroupMap);
            curGroupMap.put(GROUP_TEXT,names[i]);
            List<Map<String, Object>> children1 = new ArrayList<>();
            if (i==0){
                for (int j = 0; j <2; j++) {
                    Map<String, Object> curChildMap = new HashMap<>();
                    children1.add(curChildMap);
                    curChildMap.put(CHILD_TEXT1,tree1_1[j]);
                    curChildMap.put(CHILD_TEXT2,tree2_1[j]);
                    curChildMap.put(CHILD_TEXT3,tree3_1[j]);
                }
                childData.add(children1);
            }else{
                for (int j = 0; j <2; j++) {
                    Map<String, Object> curChildMap1 = new HashMap<>();
                    children1.add(curChildMap1);
                    curChildMap1.put(CHILD_TEXT1,tree1_2[j]);
                    curChildMap1.put(CHILD_TEXT2,tree2_2[j]);
                    curChildMap1.put(CHILD_TEXT3,tree3_2[j]);
                }
                childData.add(children1);
            }
        }
        exListView.setAdapter(exListAdpter);
        beanHttp = utilSetGet.loadSetting(getActivity());
        String HTTP1 = "http://"+beanHttp.getUrlHttp()+":"+beanHttp.getUrlPort()+"/transportservice/type/jason/action/GetBusStationInfo.do";
        JSONObject object1 = new JSONObject();
        try {
            object1.put("BusStationId",BusStationID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("sdasdasdsad",HTTP1+object1);
        getAllCarValue(HTTP1,object1);
    }

    public void getAllCarValue(String http,JSONObject jsonObject){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, http, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.i("JSON", String.valueOf(jsonObject));
                String shu = jsonObject.toString();
                Log.i("chezhan",shu);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("shuhsushu","失败");
                Toast.makeText(getActivity(),"网络访问失败!",Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request);
    }
    class MyBaseListView extends BaseExpandableListAdapter{

    public MyBaseListView(Context context) {
            super();
            context1 = context;
    }

    @Override
    public int getGroupCount() {
        return groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        convertView =inflater.inflate(R.layout.frag2_group,null);
        TextView tvFrag2GroupZhanpai = (TextView) convertView.findViewById(R.id.tv_frag2_group_zhanpai);
        tvFrag2GroupZhanpai.setText(groupData.get(groupPosition).get(GROUP_TEXT).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        convertView = inflater.inflate(R.layout.frag2_child,null);
        childHolder holder = new childHolder();
        holder.tvFrag21 = (TextView) convertView.findViewById(R.id.tv_frag2_1);
        holder.tvFrag2Fenzhong = (TextView) convertView.findViewById(R.id.tv_frag2_2);
        holder.tvFrag2Juli = (TextView) convertView.findViewById(R.id.tv_frag2_3);
        holder.tvFrag21.setText(childData.get(groupPosition).get(childPosition).get(CHILD_TEXT1).toString());
        holder.tvFrag2Fenzhong.setText(childData.get(groupPosition).get(childPosition).get(CHILD_TEXT2).toString());
        holder.tvFrag2Juli.setText(childData.get(groupPosition).get(childPosition).get(CHILD_TEXT3).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
  }
    class childHolder{
        private TextView tvFrag21;
        private TextView tvFrag2Fenzhong;
        private TextView tvFrag2Juli;
    }
}
