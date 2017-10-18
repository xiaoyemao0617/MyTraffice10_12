package com.yu.mytraffice10_12;

import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yu.mytraffice10_12.Fragment.fragment1;
import com.yu.mytraffice10_12.Fragment.fragment2;
import com.yu.mytraffice10_12.Fragment.fragment3;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private SlidingPaneLayout Sliding;
    private ListView lvSlidingMain;
    private ImageView ivSlidingContext;
    private TextView tvSlidingContext;
    private LinearLayout linearSlidingContext;
    String[] mokuai = {"账户管理","公交查询","红路灯管理","车辆违章","路况查询","生活助手","数据分析","个人中心"};
    List<String> list= new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Sliding = (SlidingPaneLayout) findViewById(R.id.Sliding);
        lvSlidingMain = (ListView) findViewById(R.id.lv_sliding_list);
        ivSlidingContext = (ImageView) findViewById(R.id.iv_sliding_context);
        ivSlidingContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sliding.openPane()){
                    Sliding.closePane();
                }else{
                    Sliding.openPane();
                }
            }
        });
        tvSlidingContext = (TextView) findViewById(R.id.tv_sliding_context);
        linearSlidingContext = (LinearLayout) findViewById(R.id.linear_sliding_context);
        setListView();
    }
    public void setListView(){

            for (int i = 0; i<=7;i++){
                  list.add(mokuai[i]);
              }
           ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                   R.layout.simple_item,list);
           lvSlidingMain.setAdapter(adapter);

        lvSlidingMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear_sliding_context,new fragment1()).commit();
                        tvSlidingContext.setText(mokuai[0]);
                        break;
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear_sliding_context,new fragment2()).commit();
                        tvSlidingContext.setText(mokuai[1]);
                        break;
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.linear_sliding_context,new fragment3()).commit();
                        tvSlidingContext.setText(mokuai[2]);
                        break;
                    case 3:
                        tvSlidingContext.setText(mokuai[3]);
                        break;
                    case 4:
                        tvSlidingContext.setText(mokuai[4]);
                        break;
                    case 5:
                        tvSlidingContext.setText(mokuai[5]);
                        break;
                    case 6:
                        tvSlidingContext.setText(mokuai[6]);
                        break;
                    case 7:
                        tvSlidingContext.setText(mokuai[7]);
                        break;
                }
            }
        });
    }
}
