package com.example.tienthanh.firstapp.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyButton(Context context) {
        super(context);
        init();
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/nasalization-rg.ttf");
            setTypeface(tf);
        }
    }
}
