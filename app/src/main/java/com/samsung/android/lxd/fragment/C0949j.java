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
/* renamed from: com.samsung.android.lxd.fragment.j */
public class C0949j extends Fragment {
    /* renamed from: a */
    private static final String f2992a = "j";
    /* renamed from: b */
    private final HashMap<String, C0948a> f2993b = new HashMap();
    /* renamed from: c */
    private int f2994c = 0;

    /* compiled from: VirtualKeyFragment */
    /* renamed from: com.samsung.android.lxd.fragment.j$1 */
    class C09451 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C0949j f2984a;

        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }

        C09451(C0949j c0949j) {
            this.f2984a = c0949j;
        }
    }

    /* compiled from: VirtualKeyFragment */
    /* renamed from: com.samsung.android.lxd.fragment.j$2 */
    class C09462 implements OnGenericMotionListener {
        /* renamed from: a */
        final /* synthetic */ C0949j f2985a;

        public boolean onGenericMotion(View view, MotionEvent motionEvent) {
            return true;
        }

        C09462(C0949j c0949j) {
            this.f2985a = c0949j;
        }
    }

    /* compiled from: VirtualKeyFragment */
    /* renamed from: com.samsung.android.lxd.fragment.j$a */
    private class C0948a {
        /* renamed from: a */
        final /* synthetic */ C0949j f2988a;
        /* renamed from: b */
        private final Button f2989b;
        /* renamed from: c */
        private final int f2990c;
        /* renamed from: d */
        private boolean f2991d;

        private C0948a(final C0949j c0949j, Button button, int i, boolean z) {
            this.f2988a = c0949j;
            this.f2989b = button;
            this.f2990c = i;
            this.f2991d = z;
            this.f2989b.setSelected(0);
            this.f2989b.setOnClickListener(new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ C0948a f2987b;

                public void onClick(View view) {
                    if (this.f2987b.f2991d != null) {
                        this.f2987b.m3826e();
                        return;
                    }
                    this.f2987b.m3822c();
                    this.f2987b.m3824d();
                }
            });
        }

        /* renamed from: a */
        private Bundle m3813a() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("on", m3821b());
            return bundle;
        }

        /* renamed from: a */
        private C0948a m3815a(Bundle bundle) {
            boolean z = (bundle == null || bundle.getBoolean("on") == null) ? null : true;
            m3819a(z);
            return this;
        }

        /* renamed from: b */
        private boolean m3821b() {
            return this.f2989b.isSelected();
        }

        /* renamed from: a */
        private void m3819a(boolean z) {
            this.f2989b.setSelected(z);
        }

        /* renamed from: c */
        private void m3822c() {
            if (this.f2988a.getActivity() != null) {
                this.f2988a.getActivity().dispatchKeyEvent(new KeyEvent(0, this.f2990c));
                m3819a(true);
            }
            this.f2988a.m3834d();
        }

        /* renamed from: d */
        private void m3824d() {
            if (this.f2988a.getActivity() != null) {
                this.f2988a.getActivity().dispatchKeyEvent(new KeyEvent(1, this.f2990c));
                m3819a(false);
            }
            this.f2988a.m3834d();
        }

        /* renamed from: e */
        private void m3826e() {
            if (m3821b()) {
                m3824d();
            } else {
                m3822c();
            }
        }

        /* renamed from: f */
        private void m3828f() {
            if (m3821b()) {
                m3824d();
            }
        }

        /* renamed from: a */
        private String m3818a(String str) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" : ");
            stringBuilder.append(m3821b());
            stringBuilder.append(" ");
            return stringBuilder.toString();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = layoutInflater.inflate(R.layout.fragment_virtual_key, viewGroup, false);
        layoutInflater.setOnTouchListener(new C09451(this));
        layoutInflater.setOnGenericMotionListener(new C09462(this));
        return layoutInflater;
    }

    /* renamed from: a */
    public static C0949j m3831a() {
        return new C0949j();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        m3835a(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        for (String str : this.f2993b.keySet()) {
            bundle.putBundle(str, ((C0948a) this.f2993b.get(str)).m3813a());
        }
    }

    /* renamed from: a */
    private Button m3830a(int i) {
        return getView() != null ? (Button) getView().findViewById(i) : null;
    }

    /* renamed from: c */
    private void m3833c() {
        if (!this.f2993b.containsKey("btnEsc")) {
            this.f2993b.put("btnEsc", new C0948a(m3830a((int) R.id.esc_button), 111, false));
        }
        if (!this.f2993b.containsKey("btnTab")) {
            this.f2993b.put("btnTab", new C0948a(m3830a((int) R.id.tab_button), 61, false));
        }
        if (!this.f2993b.containsKey("btnCtrl")) {
            this.f2993b.put("btnCtrl", new C0948a(m3830a((int) R.id.ctrl_button), 113, true));
        }
        if (!this.f2993b.containsKey("btnFn")) {
            this.f2993b.put("btnFn", new C0948a(m3830a((int) R.id.fn_button), 119, true));
        }
        if (!this.f2993b.containsKey("btnLeft")) {
            this.f2993b.put("btnLeft", new C0948a(m3830a((int) R.id.left_button), 21, false));
        }
        if (!this.f2993b.containsKey("btnDown")) {
            this.f2993b.put("btnDown", new C0948a(m3830a((int) R.id.down_button), 20, false));
        }
        if (!this.f2993b.containsKey("btnUp")) {
            this.f2993b.put("btnUp", new C0948a(m3830a((int) R.id.up_button), 19, false));
        }
        if (!this.f2993b.containsKey("btnRight")) {
            this.f2993b.put("btnRight", new C0948a(m3830a((int) R.id.right_button), 22, false));
        }
    }

    /* renamed from: b */
    public C0949j m3836b() {
        return m3835a(null);
    }

    /* renamed from: a */
    public C0949j m3835a(Bundle bundle) {
        if (getView() == null) {
            Log.m3855e(f2992a, "update : View had not been created yet!");
            return this;
        }
        getView().setVisibility(this.f2994c);
        m3833c();
        for (String str : this.f2993b.keySet()) {
            ((C0948a) this.f2993b.get(str)).m3815a(bundle != null ? bundle.getBundle(str) : ((C0948a) this.f2993b.get(str)).m3813a()).m3828f();
        }
        return this;
    }

    /* renamed from: d */
    private void m3834d() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.f2993b.keySet()) {
            stringBuilder.append(((C0948a) this.f2993b.get(str)).m3818a(str));
        }
        Log.m3853d(f2992a, stringBuilder.toString());
    }
}
