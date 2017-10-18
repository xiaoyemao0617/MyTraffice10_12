package com.yu.mytraffice10_12.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.yu.mytraffice10_12.Fragment.frag3_lukou.frag3_1;
import com.yu.mytraffice10_12.Fragment.frag3_lukou.frag3_2;
import com.yu.mytraffice10_12.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 小新 on 2017/10/16.
 */

public class fragment3 extends Fragment {
    private Spinner spinnerFrag3;
    private Button btnFrag3Chaxun;
    private Button btnFrag3Shezhi;
    List<String> list = new ArrayList<>();
    ArrayAdapter adapter ;
    View view;
    String[] spinner = {"路口升序","路口降序","红灯升序","红灯降序","路口升序","路口升序"};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        spinnerFrag3 = (Spinner) view.findViewById(R.id.spinner_frag3);
        btnFrag3Chaxun = (Button) view.findViewById(R.id.btn_frag3_chaxun);
        btnFrag3Shezhi = (Button) view.findViewById(R.id.btn_frag3_shezhi);
        for (int i=0;i<=5;i++){
            list.add(spinner[i]);
        }
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,list);
        spinnerFrag3.setAdapter(adapter);
        spinnerFrag3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getFragmentManager().beginTransaction().replace(R.id.linear_frag3_1,new frag3_1()).commit();
                        break;
                    case 1:
                        getFragmentManager().beginTransaction().replace(R.id.linear_frag3_1,new frag3_2()).commit();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}
