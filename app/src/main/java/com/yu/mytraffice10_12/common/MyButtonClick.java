package com.yu.mytraffice10_12.common;

import android.view.View;

/**
 * Created by 小新 on 2017/10/16.
 */

public abstract class MyButtonClick implements View.OnClickListener{
    public abstract void MyOnClickLister(int postion,View v);
    @Override
    public void onClick(View v) {
        MyOnClickLister((Integer) v.getTag(),v);
    }
}
