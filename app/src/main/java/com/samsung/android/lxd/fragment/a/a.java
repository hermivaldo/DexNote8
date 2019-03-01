package com.samsung.android.lxd.fragment.a;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.j;
import android.support.v7.widget.RecyclerView.w;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.CardViewFragment.b;
import com.samsung.android.lxd.fragment.CardViewFragment.c;
import com.samsung.android.lxd.fragment.CardViewFragment.d;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.List;

/* compiled from: CardViewAdapter */
public class a extends android.support.v7.widget.RecyclerView.a<a> {
    private static final String a = "a";
    private static a b;
    private final com.samsung.android.lxd.fragment.CardViewFragment.a c;
    private final int d;
    private final b e;
    private final List<String> f;
    private final Context g;
    private final List<String> h;
    private final Activity i;
    private final d j;
    private final int k;
    private final List<String> l;
    private boolean m = false;
    private c n;

    /* compiled from: CardViewAdapter */
    class a extends w {
        private TextView A;
        private TextView B;
        private Button C;
        private TextView D;
        private LinearLayout E;
        private View F;
        private LinearLayout r;
        private Switch s;
        private TextView t;
        private TextView u;
        private LinearLayout v;
        private TextView w;
        private TextView x;
        private ProgressBar y;
        private LinearLayout z;

        a(View view) {
            super(view);
            this.r = (LinearLayout) view.findViewById(R.id.adbWiFiLayout);
            this.s = (Switch) view.findViewById(R.id.adbWiFiSwitch);
            this.t = (TextView) view.findViewById(R.id.cancelButton);
            this.u = (TextView) view.findViewById(R.id.imagePath);
            this.v = (LinearLayout) view.findViewById(R.id.fileImage);
            this.w = (TextView) view.findViewById(R.id.notifyDesc);
            this.x = (TextView) view.findViewById(R.id.positiveButton);
            this.y = (ProgressBar) view.findViewById(R.id.progressBar);
            this.z = (LinearLayout) view.findViewById(R.id.progressBarLayout);
            this.A = (TextView) view.findViewById(R.id.progressPercentage);
            this.B = (TextView) view.findViewById(R.id.progressSize);
            this.C = (Button) view.findViewById(R.id.button);
            this.D = (TextView) view.findViewById(R.id.title);
            this.E = (LinearLayout) view.findViewById(R.id.unmountImage);
            this.F = view;
        }
    }

    public a(Context context, Activity activity, List<String> list, List<String> list2, b bVar, com.samsung.android.lxd.fragment.CardViewFragment.a aVar, int i, c cVar, List<String> list3, int i2, d dVar) {
        this.g = context;
        this.i = activity;
        this.l = list2;
        this.e = bVar;
        this.c = aVar;
        this.f = list;
        this.d = i;
        b = null;
        this.n = cVar;
        this.h = list3;
        this.k = i2;
        this.j = dVar;
    }

    /* renamed from: a */
    public a b(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(this.g).inflate(R.layout.item_card_view, null, false);
        inflate.setLayoutParams(new j(-1, -2));
        return new a(inflate);
    }

    public void a(a aVar, int i) {
        b = aVar;
        d(i);
        c(i);
    }

    private void c(final int i) {
        switch (this.e) {
            case WITH_UNMOUNT_IMAGE:
                b.C.setVisibility(8);
                b.E.setVisibility(0);
                b.E.setOnClickListener(new com.samsung.android.lxd.a.j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        a.this.c.a(i);
                    }
                });
                return;
            case TEXT_ONLY:
                b.C.setVisibility(8);
                return;
            case WITH_TEXT_BUTTON:
                if (o.s()) {
                    this.m = SystemConfigHelper.isConfigOptionOn(SystemConfigManager.load(Long.parseLong((String) this.f.get(i))), SystemConfigType.ADB_WiFi);
                    b.r.setVisibility(0);
                    b.s.setChecked(this.m);
                    b.s.setText(this.m ? "ON" : "OFF");
                    b.s.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                            a.this.m = z;
                            if (z) {
                                a.b.s.setText("ON");
                            } else {
                                a.b.s.setText("OFF");
                            }
                        }
                    });
                    break;
                }
                break;
            case WITH_TEXT_BUTTON_PHONE:
                break;
            case NOTIFY_WITH_PENDING:
            case NOTIFY_WITH_PROGRESS:
                b.z.setVisibility(0);
                b.x.setVisibility(8);
                b.y.setProgress(this.n.c);
                b.B.setText(this.n.b);
                TextView i2 = b.A;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.n.a);
                stringBuilder.append("%");
                i2.setText(stringBuilder.toString());
                if (!o.e(this.g) && o.l(this.g)) {
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams((o.m(this.g).x * 92) / 100, -2);
                    layoutParams.addRule(3, R.id.textLayout);
                    layoutParams.setMargins((int) o.a(this.g, 30.0f), (int) o.a(this.g, 40.0f), 0, 0);
                    b.z.setLayoutParams(layoutParams);
                }
                b.u.setVisibility(8);
                b.C.setVisibility(8);
                b.t.setOnClickListener(new com.samsung.android.lxd.a.j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        a.this.c.a(i);
                    }
                });
                b.D.setText((CharSequence) this.l.get(i));
                b.w.setVisibility(0);
                b.D.setSingleLine(false);
                b.D.setTextAppearance(this.g, R.style.notifyTitle);
                if (this.e == b.NOTIFY_WITH_PROGRESS) {
                    b.w.setText((CharSequence) this.h.get(i));
                    return;
                } else {
                    b.w.setVisibility(8);
                    return;
                }
            case NOTIFY_WITH_RECT_BUTTON:
                b.z.setVisibility(4);
                b.u.setVisibility(8);
                b.C.setVisibility(0);
                b.C.setText(this.d);
                b.C.setTextColor(this.i.getResources().getColor(R.color.LightTheme.white, null));
                b.C.setBackground(this.g.getDrawable(R.drawable.custom_unzip_button_bg));
                b.C.setOnClickListener(new com.samsung.android.lxd.a.j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        a.this.c.a(i);
                    }
                });
                b.D.setTextAppearance(this.g, R.style.notifyTitle);
                b.D.setSingleLine(false);
                b.D.setText((CharSequence) this.l.get(i));
                if (this.h == null || this.h.isEmpty()) {
                    b.w.setVisibility(8);
                    return;
                }
                b.w.setVisibility(0);
                b.w.setText((CharSequence) this.h.get(i));
                return;
            case NOTIFY_WITH_ONE_BUTTON:
                b.C.setVisibility(8);
                b.z.setVisibility(0);
                b.y.setVisibility(4);
                b.A.setVisibility(4);
                b.B.setVisibility(4);
                b.x.setVisibility(8);
                b.t.setVisibility(0);
                b.t.setOnClickListener(new com.samsung.android.lxd.a.j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        a.this.c.a(i);
                    }
                });
                b.D.setTextAppearance(this.g, R.style.notifyTitle);
                b.D.setSingleLine(false);
                b.D.setText((CharSequence) this.l.get(i));
                b.w.setText((CharSequence) this.h.get(i));
                b.w.setVisibility(0);
                return;
            case NOTIFY_WITH_TWO_BUTTON:
            case NOTIFY_WITH_TWO_BUTTON_DESC:
                b.C.setVisibility(8);
                b.z.setVisibility(0);
                b.y.setVisibility(4);
                b.A.setVisibility(4);
                b.B.setVisibility(4);
                b.x.setVisibility(0);
                b.t.setVisibility(0);
                b.x.setText(this.k);
                b.t.setOnClickListener(new com.samsung.android.lxd.a.j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        a.this.j.b();
                    }
                });
                b.x.setOnClickListener(new com.samsung.android.lxd.a.j() {
                    public void onClick(View view) {
                        super.onClick(view);
                        a.this.j.a();
                    }
                });
                b.D.setTextAppearance(this.g, R.style.notifyTitle);
                b.D.setSingleLine(false);
                b.D.setText((CharSequence) this.l.get(i));
                if (this.h != null && !this.h.isEmpty()) {
                    b.w.setText((CharSequence) this.h.get(i));
                    if (this.e == b.NOTIFY_WITH_TWO_BUTTON) {
                        b.w.setVisibility(4);
                        return;
                    } else {
                        b.w.setVisibility(0);
                        return;
                    }
                }
                return;
            default:
                Log.e(a, "CardViewType Error!");
                return;
        }
        b.C.setText(this.d);
        b.C.setVisibility(0);
        b.C.setOnClickListener(new com.samsung.android.lxd.a.j() {
            public void onClick(View view) {
                super.onClick(view);
                SystemConfig systemConfig = new SystemConfig();
                systemConfig.set(SystemConfigType.ADB_WiFi, a.this.m ? SystemConfigHelper.CONFIG_OPTION_ON : SystemConfigHelper.CONFIG_OPTION_OFF);
                SystemConfigManager.update(Long.parseLong((String) a.this.f.get(i)), systemConfig);
                a.this.c.a(i);
            }
        });
    }

    private void d(int i) {
        if (c()) {
            b.F.setBackground(this.g.getResources().getDrawable(R.drawable.custom_notify_card_bg, null));
        } else if (e(i)) {
            b.F.setBackground(this.g.getResources().getDrawable(R.drawable.custom_error_card_view_border, null));
            b.u.setText(R.string.fault_image_title);
            b.u.setTextColor(this.g.getResources().getColor(R.color.LightTheme.error, null));
            b.D.setText(o.b((String) this.l.get(i)));
        } else {
            b.F.setBackground(this.g.getResources().getDrawable(R.drawable.custom_card_view_border, null));
            String str = (String) this.l.get(i);
            if (str.length() == 0) {
                b.u.setText(R.string.default_path_name);
                b.D.setText(R.string.default_file_name);
                b.v.setVisibility(0);
                return;
            }
            b.u.setText(str);
            b.D.setText(o.b(str));
        }
    }

    private boolean c() {
        return this.e == b.NOTIFY_WITH_PENDING || this.e == b.NOTIFY_WITH_PROGRESS || this.e == b.NOTIFY_WITH_RECT_BUTTON || this.e == b.NOTIFY_WITH_ONE_BUTTON || this.e == b.NOTIFY_WITH_TWO_BUTTON || this.e == b.NOTIFY_WITH_TWO_BUTTON_DESC;
    }

    private boolean e(int i) {
        return (this.f == null || this.f.isEmpty() || !SystemConfigManager.contains(Long.parseLong((String) this.f.get(i)))) ? false : new File(SystemConfigManager.load(Long.parseLong((String) this.f.get(i))).get(SystemConfigType.IMAGE_PATH)).isFile() ^ 1;
    }

    public int a() {
        return this.l.size();
    }
}
