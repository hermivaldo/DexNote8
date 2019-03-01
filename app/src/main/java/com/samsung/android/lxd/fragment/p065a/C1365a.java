package com.samsung.android.lxd.fragment.p065a;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.C0560a;
import android.support.v7.widget.RecyclerView.C0573j;
import android.support.v7.widget.RecyclerView.C0588w;
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
import com.samsung.android.lxd.fragment.CardViewFragment.C0900a;
import com.samsung.android.lxd.fragment.CardViewFragment.C0901b;
import com.samsung.android.lxd.fragment.CardViewFragment.C0902c;
import com.samsung.android.lxd.fragment.CardViewFragment.C0903d;
import com.samsung.android.lxd.p064a.C0869j;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.List;

/* compiled from: CardViewAdapter */
/* renamed from: com.samsung.android.lxd.fragment.a.a */
public class C1365a extends C0560a<C1364a> {
    /* renamed from: a */
    private static final String f4489a = "a";
    /* renamed from: b */
    private static C1364a f4490b;
    /* renamed from: c */
    private final C0900a f4491c;
    /* renamed from: d */
    private final int f4492d;
    /* renamed from: e */
    private final C0901b f4493e;
    /* renamed from: f */
    private final List<String> f4494f;
    /* renamed from: g */
    private final Context f4495g;
    /* renamed from: h */
    private final List<String> f4496h;
    /* renamed from: i */
    private final Activity f4497i;
    /* renamed from: j */
    private final C0903d f4498j;
    /* renamed from: k */
    private final int f4499k;
    /* renamed from: l */
    private final List<String> f4500l;
    /* renamed from: m */
    private boolean f4501m = null;
    /* renamed from: n */
    private C0902c f4502n;

    /* compiled from: CardViewAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.a$2 */
    class C09252 implements OnCheckedChangeListener {
        /* renamed from: a */
        final /* synthetic */ C1365a f2920a;

        C09252(C1365a c1365a) {
            this.f2920a = c1365a;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f2920a.f4501m = z;
            if (z) {
                C1365a.f4490b.f4481s.setText("ON");
            } else {
                C1365a.f4490b.f4481s.setText("OFF");
            }
        }
    }

    /* compiled from: CardViewAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.a$7 */
    class C13627 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C1365a f4471a;

        C13627(C1365a c1365a) {
            this.f4471a = c1365a;
        }

        public void onClick(View view) {
            super.onClick(view);
            this.f4471a.f4498j.mo696b();
        }
    }

    /* compiled from: CardViewAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.a$8 */
    class C13638 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C1365a f4472a;

        C13638(C1365a c1365a) {
            this.f4472a = c1365a;
        }

        public void onClick(View view) {
            super.onClick(view);
            this.f4472a.f4498j.mo695a();
        }
    }

    /* compiled from: CardViewAdapter */
    /* renamed from: com.samsung.android.lxd.fragment.a.a$a */
    class C1364a extends C0588w {
        /* renamed from: A */
        private TextView f4473A;
        /* renamed from: B */
        private TextView f4474B;
        /* renamed from: C */
        private Button f4475C;
        /* renamed from: D */
        private TextView f4476D;
        /* renamed from: E */
        private LinearLayout f4477E;
        /* renamed from: F */
        private View f4478F;
        /* renamed from: q */
        final /* synthetic */ C1365a f4479q;
        /* renamed from: r */
        private LinearLayout f4480r;
        /* renamed from: s */
        private Switch f4481s;
        /* renamed from: t */
        private TextView f4482t;
        /* renamed from: u */
        private TextView f4483u;
        /* renamed from: v */
        private LinearLayout f4484v;
        /* renamed from: w */
        private TextView f4485w;
        /* renamed from: x */
        private TextView f4486x;
        /* renamed from: y */
        private ProgressBar f4487y;
        /* renamed from: z */
        private LinearLayout f4488z;

        C1364a(C1365a c1365a, View view) {
            this.f4479q = c1365a;
            super(view);
            this.f4480r = (LinearLayout) view.findViewById(R.id.adbWiFiLayout);
            this.f4481s = (Switch) view.findViewById(R.id.adbWiFiSwitch);
            this.f4482t = (TextView) view.findViewById(R.id.cancelButton);
            this.f4483u = (TextView) view.findViewById(R.id.imagePath);
            this.f4484v = (LinearLayout) view.findViewById(R.id.fileImage);
            this.f4485w = (TextView) view.findViewById(R.id.notifyDesc);
            this.f4486x = (TextView) view.findViewById(R.id.positiveButton);
            this.f4487y = (ProgressBar) view.findViewById(R.id.progressBar);
            this.f4488z = (LinearLayout) view.findViewById(R.id.progressBarLayout);
            this.f4473A = (TextView) view.findViewById(R.id.progressPercentage);
            this.f4474B = (TextView) view.findViewById(R.id.progressSize);
            this.f4475C = (Button) view.findViewById(R.id.button);
            this.f4476D = (TextView) view.findViewById(R.id.title);
            this.f4477E = (LinearLayout) view.findViewById(R.id.unmountImage);
            this.f4478F = view;
        }
    }

    /* renamed from: b */
    public /* synthetic */ C0588w mo104b(ViewGroup viewGroup, int i) {
        return m6269a(viewGroup, i);
    }

    public C1365a(Context context, Activity activity, List<String> list, List<String> list2, C0901b c0901b, C0900a c0900a, int i, C0902c c0902c, List<String> list3, int i2, C0903d c0903d) {
        this.f4495g = context;
        this.f4497i = activity;
        this.f4500l = list2;
        this.f4493e = c0901b;
        this.f4491c = c0900a;
        this.f4494f = list;
        this.f4492d = i;
        f4490b = null;
        this.f4502n = c0902c;
        this.f4496h = list3;
        this.f4499k = i2;
        this.f4498j = c0903d;
    }

    /* renamed from: a */
    public C1364a m6269a(ViewGroup viewGroup, int i) {
        viewGroup = LayoutInflater.from(this.f4495g).inflate(R.layout.item_card_view, null, false);
        viewGroup.setLayoutParams(new C0573j(-1, -2));
        return new C1364a(this, viewGroup);
    }

    /* renamed from: a */
    public void m6271a(C1364a c1364a, int i) {
        f4490b = c1364a;
        m6266d(i);
        m6263c(i);
    }

    /* renamed from: c */
    private void m6263c(final int i) {
        switch (this.f4493e) {
            case WITH_UNMOUNT_IMAGE:
                f4490b.f4475C.setVisibility(8);
                f4490b.f4477E.setVisibility(0);
                f4490b.f4477E.setOnClickListener(new C0869j(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1365a f4462b;

                    public void onClick(View view) {
                        super.onClick(view);
                        this.f4462b.f4491c.mo659a(i);
                    }
                });
                return;
            case TEXT_ONLY:
                f4490b.f4475C.setVisibility(8);
                return;
            case WITH_TEXT_BUTTON:
                if (C0877o.m3534s()) {
                    this.f4501m = SystemConfigHelper.isConfigOptionOn(SystemConfigManager.load(Long.parseLong((String) this.f4494f.get(i))), SystemConfigType.ADB_WiFi);
                    f4490b.f4480r.setVisibility(0);
                    f4490b.f4481s.setChecked(this.f4501m);
                    f4490b.f4481s.setText(this.f4501m ? "ON" : "OFF");
                    f4490b.f4481s.setOnCheckedChangeListener(new C09252(this));
                    break;
                }
                break;
            case WITH_TEXT_BUTTON_PHONE:
                break;
            case NOTIFY_WITH_PENDING:
            case NOTIFY_WITH_PROGRESS:
                f4490b.f4488z.setVisibility(0);
                f4490b.f4486x.setVisibility(8);
                f4490b.f4487y.setProgress(this.f4502n.f2813c);
                f4490b.f4474B.setText(this.f4502n.f2812b);
                TextView i2 = f4490b.f4473A;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(this.f4502n.f2811a);
                stringBuilder.append("%");
                i2.setText(stringBuilder.toString());
                if (!C0877o.m3504e(this.f4495g) && C0877o.m3525l(this.f4495g)) {
                    LayoutParams layoutParams = new RelativeLayout.LayoutParams((C0877o.m3526m(this.f4495g).x * 92) / 100, -2);
                    layoutParams.addRule(3, R.id.textLayout);
                    layoutParams.setMargins((int) C0877o.m3453a(this.f4495g, 30.0f), (int) C0877o.m3453a(this.f4495g, 40.0f), 0, 0);
                    f4490b.f4488z.setLayoutParams(layoutParams);
                }
                f4490b.f4483u.setVisibility(8);
                f4490b.f4475C.setVisibility(8);
                f4490b.f4482t.setOnClickListener(new C0869j(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1365a f4466b;

                    public void onClick(View view) {
                        super.onClick(view);
                        this.f4466b.f4491c.mo659a(i);
                    }
                });
                f4490b.f4476D.setText((CharSequence) this.f4500l.get(i));
                f4490b.f4485w.setVisibility(0);
                f4490b.f4476D.setSingleLine(false);
                f4490b.f4476D.setTextAppearance(this.f4495g, R.style.notifyTitle);
                if (this.f4493e == C0901b.NOTIFY_WITH_PROGRESS) {
                    f4490b.f4485w.setText((CharSequence) this.f4496h.get(i));
                    return;
                } else {
                    f4490b.f4485w.setVisibility(8);
                    return;
                }
            case NOTIFY_WITH_RECT_BUTTON:
                f4490b.f4488z.setVisibility(4);
                f4490b.f4483u.setVisibility(8);
                f4490b.f4475C.setVisibility(0);
                f4490b.f4475C.setText(this.f4492d);
                f4490b.f4475C.setTextColor(this.f4497i.getResources().getColor(R.color.LightTheme.white, null));
                f4490b.f4475C.setBackground(this.f4495g.getDrawable(R.drawable.custom_unzip_button_bg));
                f4490b.f4475C.setOnClickListener(new C0869j(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1365a f4468b;

                    public void onClick(View view) {
                        super.onClick(view);
                        this.f4468b.f4491c.mo659a(i);
                    }
                });
                f4490b.f4476D.setTextAppearance(this.f4495g, R.style.notifyTitle);
                f4490b.f4476D.setSingleLine(false);
                f4490b.f4476D.setText((CharSequence) this.f4500l.get(i));
                if (this.f4496h == null || this.f4496h.isEmpty()) {
                    f4490b.f4485w.setVisibility(8);
                    return;
                }
                f4490b.f4485w.setVisibility(0);
                f4490b.f4485w.setText((CharSequence) this.f4496h.get(i));
                return;
            case NOTIFY_WITH_ONE_BUTTON:
                f4490b.f4475C.setVisibility(8);
                f4490b.f4488z.setVisibility(0);
                f4490b.f4487y.setVisibility(4);
                f4490b.f4473A.setVisibility(4);
                f4490b.f4474B.setVisibility(4);
                f4490b.f4486x.setVisibility(8);
                f4490b.f4482t.setVisibility(0);
                f4490b.f4482t.setOnClickListener(new C0869j(this) {
                    /* renamed from: b */
                    final /* synthetic */ C1365a f4470b;

                    public void onClick(View view) {
                        super.onClick(view);
                        this.f4470b.f4491c.mo659a(i);
                    }
                });
                f4490b.f4476D.setTextAppearance(this.f4495g, R.style.notifyTitle);
                f4490b.f4476D.setSingleLine(false);
                f4490b.f4476D.setText((CharSequence) this.f4500l.get(i));
                f4490b.f4485w.setText((CharSequence) this.f4496h.get(i));
                f4490b.f4485w.setVisibility(0);
                return;
            case NOTIFY_WITH_TWO_BUTTON:
            case NOTIFY_WITH_TWO_BUTTON_DESC:
                f4490b.f4475C.setVisibility(8);
                f4490b.f4488z.setVisibility(0);
                f4490b.f4487y.setVisibility(4);
                f4490b.f4473A.setVisibility(4);
                f4490b.f4474B.setVisibility(4);
                f4490b.f4486x.setVisibility(0);
                f4490b.f4482t.setVisibility(0);
                f4490b.f4486x.setText(this.f4499k);
                f4490b.f4482t.setOnClickListener(new C13627(this));
                f4490b.f4486x.setOnClickListener(new C13638(this));
                f4490b.f4476D.setTextAppearance(this.f4495g, R.style.notifyTitle);
                f4490b.f4476D.setSingleLine(false);
                f4490b.f4476D.setText((CharSequence) this.f4500l.get(i));
                if (this.f4496h != null && !this.f4496h.isEmpty()) {
                    f4490b.f4485w.setText((CharSequence) this.f4496h.get(i));
                    if (this.f4493e == C0901b.NOTIFY_WITH_TWO_BUTTON) {
                        f4490b.f4485w.setVisibility(4);
                        return;
                    } else {
                        f4490b.f4485w.setVisibility(0);
                        return;
                    }
                }
                return;
            default:
                Log.m3855e(f4489a, "CardViewType Error!");
                return;
        }
        f4490b.f4475C.setText(this.f4492d);
        f4490b.f4475C.setVisibility(0);
        f4490b.f4475C.setOnClickListener(new C0869j(this) {
            /* renamed from: b */
            final /* synthetic */ C1365a f4464b;

            public void onClick(View view) {
                super.onClick(view);
                view = new SystemConfig();
                view.set(SystemConfigType.ADB_WiFi, this.f4464b.f4501m ? SystemConfigHelper.CONFIG_OPTION_ON : SystemConfigHelper.CONFIG_OPTION_OFF);
                SystemConfigManager.update(Long.parseLong((String) this.f4464b.f4494f.get(i)), view);
                this.f4464b.f4491c.mo659a(i);
            }
        });
    }

    /* renamed from: d */
    private void m6266d(int i) {
        if (m6264c()) {
            f4490b.f4478F.setBackground(this.f4495g.getResources().getDrawable(R.drawable.custom_notify_card_bg, null));
        } else if (m6267e(i)) {
            f4490b.f4478F.setBackground(this.f4495g.getResources().getDrawable(R.drawable.custom_error_card_view_border, null));
            f4490b.f4483u.setText(R.string.fault_image_title);
            f4490b.f4483u.setTextColor(this.f4495g.getResources().getColor(R.color.LightTheme.error, null));
            f4490b.f4476D.setText(C0877o.m3487b((String) this.f4500l.get(i)));
        } else {
            f4490b.f4478F.setBackground(this.f4495g.getResources().getDrawable(R.drawable.custom_card_view_border, null));
            String str = (String) this.f4500l.get(i);
            if (str.length() == 0) {
                f4490b.f4483u.setText(R.string.default_path_name);
                f4490b.f4476D.setText(R.string.default_file_name);
                f4490b.f4484v.setVisibility(0);
                return;
            }
            f4490b.f4483u.setText(str);
            f4490b.f4476D.setText(C0877o.m3487b(str));
        }
    }

    /* renamed from: c */
    private boolean m6264c() {
        if (!(this.f4493e == C0901b.NOTIFY_WITH_PENDING || this.f4493e == C0901b.NOTIFY_WITH_PROGRESS || this.f4493e == C0901b.NOTIFY_WITH_RECT_BUTTON || this.f4493e == C0901b.NOTIFY_WITH_ONE_BUTTON || this.f4493e == C0901b.NOTIFY_WITH_TWO_BUTTON)) {
            if (this.f4493e != C0901b.NOTIFY_WITH_TWO_BUTTON_DESC) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    private boolean m6267e(int i) {
        return (this.f4494f == null || this.f4494f.isEmpty() || !SystemConfigManager.contains(Long.parseLong((String) this.f4494f.get(i)))) ? false : new File(SystemConfigManager.load(Long.parseLong((String) this.f4494f.get(i))).get(SystemConfigType.IMAGE_PATH)).isFile() ^ 1;
    }

    /* renamed from: a */
    public int mo99a() {
        return this.f4500l.size();
    }
}
