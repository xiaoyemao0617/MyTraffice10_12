package com.yu.mytraffice10_12.common;

import android.widget.CompoundButton;

/**
 * Created by 小新 on 2017/10/16.
 */

public abstract class MyCheckLister implements CompoundButton.OnCheckedChangeListener {
    public abstract void MyCheckOnClick(int postion,boolean b);
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        MyCheckOnClick((Integer) buttonView.getTag(),false);
    }
}
