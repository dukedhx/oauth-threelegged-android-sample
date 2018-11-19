package com.example.administrator.myapplication.tools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Messenger {

    public static final String msgId = "msg";

    ;

    public static void sendString(Handler mHandler, String msg, int arg1, int arg2) {
        Message m = new Message();
        Bundle b = new Bundle();
        b.putString(msgId, msg);
        m.setData(b);
        m.arg1 = arg1;
        m.arg2 = arg2;
        mHandler.sendMessage(m);
    }

    public static void sendString(Handler mHandler, String msg) {
        sendString(mHandler, msg, signals.NOTHING.ordinal(), signals.NOTHING.ordinal());
    }

    public enum signals {NOTHING, SUCCESS, FAILURE}
}
