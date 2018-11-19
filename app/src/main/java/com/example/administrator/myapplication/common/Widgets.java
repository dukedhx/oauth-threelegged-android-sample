package com.example.administrator.myapplication.common;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class Widgets {
    public static void ShowToast(Context context, String text) {
        Toast t = Toast.makeText(context, text, Toast.LENGTH_LONG);

        t.setGravity(Gravity.CENTER, 0, 0);
        t.show();
    }
}
