package com.samsung.p047a.p048a.p049a.p050a;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.samsung.p047a.p048a.p049a.C0790b;
import com.samsung.p047a.p048a.p049a.C0791c;
import com.samsung.p047a.p048a.p049a.C0796f;
import com.samsung.p047a.p048a.p049a.p050a.p051a.C0751b;
import com.samsung.p047a.p048a.p049a.p050a.p051a.C0752c;
import com.samsung.p047a.p048a.p049a.p050a.p052b.C0756a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C0758a;
import com.samsung.p047a.p048a.p049a.p050a.p053c.C1290d;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0763b;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0764c;
import com.samsung.p047a.p048a.p049a.p050a.p054d.C0766d;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0781d;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0781d.C0777a;
import com.samsung.p047a.p048a.p049a.p050a.p056f.p059d.C0779a;
import com.samsung.p047a.p048a.p049a.p050a.p062g.C1300a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0784a;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0787c;
import com.samsung.p047a.p048a.p049a.p050a.p063i.C0788d;
import com.samsung.p047a.p048a.p049a.p050a.p067h.C1301a;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/* compiled from: Tracker */
/* renamed from: com.samsung.a.a.a.a.b */
public class C0757b {
    /* renamed from: a */
    public static C0763b f2417a;
    /* renamed from: b */
    private Application f2418b;
    /* renamed from: c */
    private boolean f2419c = false;
    /* renamed from: d */
    private boolean f2420d = false;
    /* renamed from: e */
    private C0790b f2421e;
    /* renamed from: f */
    private Intent f2422f;

    /* compiled from: Tracker */
    /* renamed from: com.samsung.a.a.a.a.b$3 */
    class C07553 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C0757b f2406a;

        C07553(C0757b c0757b) {
            this.f2406a = c0757b;
        }

        public void onReceive(Context context, Intent intent) {
            context = intent.getStringExtra("DID");
            if (TextUtils.isEmpty(context) != null) {
                context = this.f2406a.m3157d();
                intent = true;
                C0784a.m3255b("Get CF id empty");
            } else {
                intent = null;
                C0784a.m3255b("Get CF id");
            }
            this.f2406a.m3151a((String) context, (int) intent);
            this.f2406a.f2418b.unregisterReceiver(this);
        }
    }

    /* compiled from: Tracker */
    /* renamed from: com.samsung.a.a.a.a.b$2 */
    class C12882 implements C0754a<Void, Boolean> {
        /* renamed from: a */
        final /* synthetic */ C0757b f4323a;

        C12882(C0757b c0757b) {
            this.f4323a = c0757b;
        }

        /* renamed from: a */
        public Void m6008a(Boolean bool) {
            if (bool.booleanValue() != null) {
                C0791c n = this.f4323a.f2421e.m3285n();
                if (n == null) {
                    C0779a.m3225a(this.f4323a.f2418b.getApplicationContext(), this.f4323a.f2421e).m3229a(this.f4323a.f2418b.getApplicationContext());
                } else {
                    C0779a.m3225a(this.f4323a.f2418b.getApplicationContext(), this.f4323a.f2421e).m3232a(n);
                }
            }
            return null;
        }
    }

    public C0757b(final Application application, C0790b c0790b) {
        this.f2418b = application;
        this.f2421e = c0790b;
        f2417a = c0790b.m3275d() ? C0763b.CUSTOM_TERMS : C0763b.DIAGNOSTIC_TERMS;
        if (!TextUtils.isEmpty(c0790b.m3272b())) {
            this.f2421e.m3270a(2);
        } else if (!m3156c() && c0790b.m3276e()) {
            if (f2417a.equals(C0763b.CUSTOM_TERMS)) {
                m3151a(m3157d(), 1);
            } else if (f2417a.m3170b() == C0777a.DLC && !m3154b()) {
                m3151a(m3157d(), 1);
            }
        }
        if (f2417a.m3169a()) {
            m3149a();
        }
        if (f2417a == C0763b.DIAGNOSTIC_TERMS) {
            this.f2421e.m3267a(new C0796f(this) {
                /* renamed from: b */
                final /* synthetic */ C0757b f4322b;

                /* renamed from: a */
                public boolean mo645a() {
                    return C0788d.m3265a(application.getApplicationContext());
                }
            });
        }
        if (m3159f()) {
            if (c0790b.m3282k()) {
                C0781d.m3239a(application, f2417a.m3170b(), c0790b);
            }
            m3158e();
        }
        m3162i();
        application = new StringBuilder();
        application.append("Tracker start:2.01.005");
        application.append(f2417a.m3170b().name());
        C0784a.m3254a("Tracker", application.toString());
    }

    /* renamed from: a */
    private void m3149a() {
        SharedPreferences a = C0787c.m3260a(this.f2418b);
        C0752c.f2399c.m3137a(a.getString("dom", ""));
        C0751b.DLS_DIR.m3135a(a.getString("uri", ""));
        C0751b.DLS_DIR_BAT.m3135a(a.getString("bat-uri", ""));
        if (C0764c.m3181a(this.f2418b.getApplicationContext())) {
            C0764c.m3179a(this.f2418b, this.f2421e, C1290d.m6011a(), new C0756a(this.f2418b), new C12882(this));
        }
    }

    /* renamed from: a */
    public int m3163a(Map<String, String> map, boolean z) {
        if (m3159f()) {
            if (map != null) {
                if (!map.isEmpty()) {
                    if (!m3160g()) {
                        if (this.f2422f != null) {
                            this.f2418b.sendBroadcast(this.f2422f);
                        }
                        return -5;
                    } else if (((String) map.get("t")).equals("pp") && !m3161h()) {
                        return -9;
                    } else {
                        if (z) {
                            return C0781d.m3239a(this.f2418b, f2417a.m3170b(), this.f2421e).mo1275f(map);
                        }
                        return C0781d.m3239a(this.f2418b, f2417a.m3170b(), this.f2421e).mo1274e(map);
                    }
                }
            }
            C0784a.m3255b("Failure to send Logs : No data");
            return -3;
        }
        C0784a.m3255b("user do not agree");
        return -2;
    }

    /* renamed from: a */
    private boolean m3152a(String str) {
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(this.f2418b.getApplicationContext().getPackageManager().getPackageInfo(str, 0).versionName, ".");
            str = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
            if (str < 2) {
                C0784a.m3253a("CF version < 2.0.9");
                return false;
            } else if (str != 2 || parseInt != 0 || parseInt2 >= 9) {
                return true;
            } else {
                C0784a.m3253a("CF version < 2.0.9");
                return false;
            }
        } catch (Exception e) {
            C0784a.m3252a(getClass(), e);
            return false;
        }
    }

    /* renamed from: a */
    private void m3151a(String str, int i) {
        C0787c.m3260a(this.f2418b.getApplicationContext()).edit().putString("deviceId", str).putInt("auidType", i).apply();
        this.f2421e.m3270a(i);
        this.f2421e.m3271b(str);
    }

    /* renamed from: b */
    private boolean m3154b() {
        if (!C0766d.m3186a() || !f2417a.equals(C0763b.DIAGNOSTIC_TERMS) || !TextUtils.isEmpty(this.f2421e.m3277f()) || !m3152a("com.samsung.android.providers.context")) {
            return false;
        }
        this.f2418b.registerReceiver(new C07553(this), new IntentFilter("com.samsung.android.providers.context.log.action.GET_DID"));
        this.f2422f = new Intent("com.samsung.android.providers.context.log.action.REQUEST_DID");
        this.f2422f.putExtra("PKGNAME", this.f2418b.getPackageName());
        this.f2422f.setPackage("com.samsung.android.providers.context");
        this.f2418b.sendBroadcast(this.f2422f);
        C0784a.m3255b("request CF id");
        return true;
    }

    /* renamed from: c */
    private boolean m3156c() {
        SharedPreferences a = C0787c.m3260a(this.f2418b);
        String string = a.getString("deviceId", "");
        int i = a.getInt("auidType", -1);
        if (!TextUtils.isEmpty(string) && string.length() == 32) {
            if (i != -1) {
                this.f2421e.m3270a(i);
                this.f2421e.m3271b(string);
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    private String m3157d() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bArr = new byte[16];
        StringBuilder stringBuilder = new StringBuilder(32);
        int i = 0;
        while (i < 32) {
            secureRandom.nextBytes(bArr);
            try {
                stringBuilder.append("0123456789abcdefghijklmjopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt((int) (Math.abs(new BigInteger(bArr).longValue()) % ((long) "0123456789abcdefghijklmjopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length()))));
                i++;
            } catch (Exception e) {
                C0784a.m3252a(getClass(), e);
                return null;
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: e */
    private void m3158e() {
        if (!m3159f()) {
            C0784a.m3255b("user do not agree setting");
        } else if (C0788d.m3264a(7, Long.valueOf(C0787c.m3260a(this.f2418b).getLong("status_sent_date", 0)))) {
            C0784a.m3255b("send setting");
            C1290d.m6011a().mo649a(new C1300a(this.f2418b, this.f2421e));
        } else {
            C0784a.m3255b("do not send setting < 7days");
        }
    }

    /* renamed from: f */
    private boolean m3159f() {
        return this.f2421e.m3279h().mo645a();
    }

    /* renamed from: g */
    private boolean m3160g() {
        if (f2417a.m3170b() == C0777a.DMA || !TextUtils.isEmpty(this.f2421e.m3272b())) {
            return true;
        }
        C0784a.m3255b("did is empty");
        return false;
    }

    /* renamed from: h */
    private boolean m3161h() {
        if (!f2417a.m3172d()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("property disable ");
            stringBuilder.append(f2417a.m3170b());
            C0784a.m3255b(stringBuilder.toString());
            return false;
        } else if (C0788d.m3264a(1, Long.valueOf(C0787c.m3260a(this.f2418b).getLong("property_sent_date", 0)))) {
            C0787c.m3260a(this.f2418b).edit().putLong("property_sent_date", System.currentTimeMillis()).apply();
            return true;
        } else {
            C0784a.m3255b("do not send property < 1day");
            return false;
        }
    }

    /* renamed from: i */
    private void m3162i() {
        final SharedPreferences sharedPreferences = this.f2418b.getSharedPreferences("SATerms", 0);
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            final String str = (String) entry.getKey();
            C1290d.m6011a().mo649a(new C1301a(this.f2421e.m3269a(), str, ((Long) entry.getValue()).longValue(), new C0758a(this) {
                /* renamed from: c */
                final /* synthetic */ C0757b f4326c;

                /* renamed from: b */
                public void mo648b(int i, String str, String str2, String str3) {
                }

                /* renamed from: a */
                public void mo647a(int i, String str, String str2, String str3) {
                    sharedPreferences.edit().remove(str).apply();
                }
            }));
        }
    }
}
