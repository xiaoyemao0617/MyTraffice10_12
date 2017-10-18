package com.yu.mytraffice10_12.myClass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yu.mytraffice10_12.R;
import com.yu.mytraffice10_12.common.MyButtonClick;
import com.yu.mytraffice10_12.common.MyCheckLister;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 小新 on 2017/10/14.
 */

public class MyBaseAdpter extends BaseAdapter {
    LayoutInflater minflater;
    ArrayList<Map<String,Object>> arrayList;
    MyButtonClick btnClick;
    MyCheckLister checkLis;
    int yue_biaozhun=0;
    public MyBaseAdpter(Context context, ArrayList<Map<String,Object>> arrayList,int shu) {
        this.arrayList = arrayList;
        minflater = LayoutInflater.from(context);
        yue_biaozhun = shu;
    }
    public class viewHolder{
        private LinearLayout linearFrag1List;
        private TextView tvFrag1ListNumber;
        private ImageView imgFrag1ListLogo;
        private TextView tvFrag1ListChepai;
        private TextView tvFrag1ListChezhu;
        private TextView tvFrag1ListYue;
        private CheckBox checkFrag1List;
        private Button btnFrag1ListChongzhi;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder=null;
        if (holder==null){
            holder = new viewHolder();
            convertView = minflater.inflate(R.layout.frag1_list,null);
            holder.linearFrag1List = (LinearLayout) convertView.findViewById(R.id.linear_frag1_list);
            holder.tvFrag1ListNumber = (TextView) convertView.findViewById(R.id.tv_frag1_list_number);
            holder.imgFrag1ListLogo = (ImageView) convertView.findViewById(R.id.img_frag1_list_logo);
            holder.tvFrag1ListChepai = (TextView) convertView.findViewById(R.id.tv_frag1_list_chepai);
            holder.tvFrag1ListChezhu = (TextView) convertView.findViewById(R.id.tv_frag1_list_chezhu);
            holder.tvFrag1ListYue = (TextView) convertView.findViewById(R.id.tv_frag1_list_yue);
            holder.checkFrag1List = (CheckBox) convertView.findViewById(R.id.check_frag1_list);
            holder.btnFrag1ListChongzhi = (Button) convertView.findViewById(R.id.btn_frag1_list_chongzhi);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.tvFrag1ListNumber.setText(arrayList.get(position).get("number").toString());
        holder.imgFrag1ListLogo.setImageResource((Integer) arrayList.get(position).get("img"));
        holder.tvFrag1ListChepai.setText(arrayList.get(position).get("chepai").toString());
        holder.tvFrag1ListChezhu.setText("车主"+arrayList.get(position).get("chezhu").toString());
        holder.tvFrag1ListYue.setText("余额"+arrayList.get(position).get("yue").toString()+"元");
        holder.checkFrag1List.setTag(position);
        holder.btnFrag1ListChongzhi.setTag(position);
        int yue = (int) arrayList.get(position).get("yue");
        if (yue<=yue_biaozhun){
            holder.linearFrag1List.setBackgroundResource(R.color.colorAccent);
        }
        return convertView;
    }
}
