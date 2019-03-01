package com.samsung.android.lxd.processor.handler;

import android.os.Message;

public class LxdMessage {
    Object callback;
    Object obj1;
    Object obj2;
    Object obj3;
    Object obj4;
    Object obj5;
    Object obj6;
    Object obj7;

    public static Message obtain(int i, Object obj) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.callback = obj;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }

    public static Message obtain(int i, Object obj, Object obj2) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.obj1 = obj;
        lxdMessage.callback = obj2;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }

    public static Message obtain(int i, Object obj, Object obj2, Object obj3) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.obj1 = obj;
        lxdMessage.obj2 = obj2;
        lxdMessage.callback = obj3;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }

    public static Message obtain(int i, Object obj, Object obj2, Object obj3, Object obj4) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.obj1 = obj;
        lxdMessage.obj2 = obj2;
        lxdMessage.obj3 = obj3;
        lxdMessage.callback = obj4;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }

    public static Message obtain(int i, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.obj1 = obj;
        lxdMessage.obj2 = obj2;
        lxdMessage.obj3 = obj3;
        lxdMessage.obj4 = obj4;
        lxdMessage.callback = obj5;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }

    public static Message obtain(int i, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.obj1 = obj;
        lxdMessage.obj2 = obj2;
        lxdMessage.obj3 = obj3;
        lxdMessage.obj4 = obj4;
        lxdMessage.obj5 = obj5;
        lxdMessage.callback = obj6;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }

    public static Message obtain(int i, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.obj1 = obj;
        lxdMessage.obj2 = obj2;
        lxdMessage.obj3 = obj3;
        lxdMessage.obj4 = obj4;
        lxdMessage.obj5 = obj5;
        lxdMessage.obj6 = obj6;
        lxdMessage.callback = obj7;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }

    public static Message obtain(int i, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        LxdMessage lxdMessage = new LxdMessage();
        lxdMessage.obj1 = obj;
        lxdMessage.obj2 = obj2;
        lxdMessage.obj3 = obj3;
        lxdMessage.obj4 = obj4;
        lxdMessage.obj5 = obj5;
        lxdMessage.obj6 = obj6;
        lxdMessage.obj7 = obj7;
        lxdMessage.callback = obj8;
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = lxdMessage;
        return obtain;
    }
}
