package com.samsung.android.lxd.a;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: OnClickListenerHelper */
public class j implements OnClickListener {
    private static final String a = "j";

    public void onClick(final View view) {
        view.setEnabled(false);
        new Timer().schedule(new TimerTask() {
            public void run() {
                o.a(new Runnable() {
                    public void run() {
                        view.setEnabled(true);
                    }
                });
            }
        }, 500);
    }
}
