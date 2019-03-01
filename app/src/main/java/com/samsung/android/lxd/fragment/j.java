package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.HashMap;

/* compiled from: VirtualKeyFragment */
public class j extends Fragment {
    private static final String a = "j";
    private final HashMap<String, a> b = new HashMap();
    private int c = 0;

    /* compiled from: VirtualKeyFragment */
    private class a {
        private final Button b;
        private final int c;
        private boolean d;

        /* synthetic */ a(j jVar, Button button, int i, boolean z, AnonymousClass1 anonymousClass1) {
            this(button, i, z);
        }

        private a(Button button, int i, boolean z) {
            this.b = button;
            this.c = i;
            this.d = z;
            this.b.setSelected(false);
            this.b.setOnClickListener(new OnClickListener(j.this) {
                public void onClick(View view) {
                    if (a.this.d) {
                        a.this.e();
                        return;
                    }
                    a.this.c();
                    a.this.d();
                }
            });
        }

        private Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("on", b());
            return bundle;
        }

        private a a(Bundle bundle) {
            boolean z = bundle != null && bundle.getBoolean("on");
            a(z);
            return this;
        }

        private boolean b() {
            return this.b.isSelected();
        }

        private void a(boolean z) {
            this.b.setSelected(z);
        }

        private void c() {
            if (j.this.getActivity() != null) {
                j.this.getActivity().dispatchKeyEvent(new KeyEvent(0, this.c));
                a(true);
            }
            j.this.d();
        }

        private void d() {
            if (j.this.getActivity() != null) {
                j.this.getActivity().dispatchKeyEvent(new KeyEvent(1, this.c));
                a(false);
            }
            j.this.d();
        }

        private void e() {
            if (b()) {
                d();
            } else {
                c();
            }
        }

        private void f() {
            if (b()) {
                d();
            }
        }

        private String a(String str) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" : ");
            stringBuilder.append(b());
            stringBuilder.append(" ");
            return stringBuilder.toString();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_virtual_key, viewGroup, false);
        inflate.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        inflate.setOnGenericMotionListener(new OnGenericMotionListener() {
            public boolean onGenericMotion(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        return inflate;
    }

    public static j a() {
        return new j();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        for (String str : this.b.keySet()) {
            bundle.putBundle(str, ((a) this.b.get(str)).a());
        }
    }

    private Button a(int i) {
        return getView() != null ? (Button) getView().findViewById(i) : null;
    }

    private void c() {
        if (!this.b.containsKey("btnEsc")) {
            this.b.put("btnEsc", new a(this, a((int) R.id.esc_button), 111, false, null));
        }
        if (!this.b.containsKey("btnTab")) {
            this.b.put("btnTab", new a(this, a((int) R.id.tab_button), 61, false, null));
        }
        if (!this.b.containsKey("btnCtrl")) {
            this.b.put("btnCtrl", new a(this, a((int) R.id.ctrl_button), 113, true, null));
        }
        if (!this.b.containsKey("btnFn")) {
            this.b.put("btnFn", new a(this, a((int) R.id.fn_button), 119, true, null));
        }
        if (!this.b.containsKey("btnLeft")) {
            this.b.put("btnLeft", new a(this, a((int) R.id.left_button), 21, false, null));
        }
        if (!this.b.containsKey("btnDown")) {
            this.b.put("btnDown", new a(this, a((int) R.id.down_button), 20, false, null));
        }
        if (!this.b.containsKey("btnUp")) {
            this.b.put("btnUp", new a(this, a((int) R.id.up_button), 19, false, null));
        }
        if (!this.b.containsKey("btnRight")) {
            this.b.put("btnRight", new a(this, a((int) R.id.right_button), 22, false, null));
        }
    }

    public j b() {
        return a(null);
    }

    public j a(Bundle bundle) {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        getView().setVisibility(this.c);
        c();
        for (String str : this.b.keySet()) {
            ((a) this.b.get(str)).a(bundle != null ? bundle.getBundle(str) : ((a) this.b.get(str)).a()).f();
        }
        return this;
    }

    private void d() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.b.keySet()) {
            stringBuilder.append(((a) this.b.get(str)).a(str));
        }
        Log.d(a, stringBuilder.toString());
    }
}
