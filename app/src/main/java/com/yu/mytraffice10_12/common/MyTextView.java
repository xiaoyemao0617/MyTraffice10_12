package com.yu.mytraffice10_12.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
/**
 * Created by 小新 on 2017/10/17.
 */

public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    Paint paint = new Paint();
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0,0,getWidth()-1,0,paint);
        canvas.drawLine(0,0,0,getHeight()-1,paint);
        canvas.drawLine(getWidth()-1,0,getWidth()-1,getHeight()-1,paint);
        canvas.drawLine(0,getHeight()-1,getWidth()-1,getHeight()-1,paint);
    }
}
