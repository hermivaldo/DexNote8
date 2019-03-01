package com.samsung.a.a.a.a;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.samsung.a.a.a.a.f.d.a;
import com.samsung.a.a.a.a.i.c;
import com.samsung.a.a.a.a.i.d;
import com.samsung.a.a.a.f;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

/* compiled from: Tracker */
public class b {
    public static com.samsung.a.a.a.a.d.b a;
    private Application b;
    private boolean c = false;
    private boolean d = false;
    private com.samsung.a.a.a.b e;
    private Intent f;

    public b(final Application application, com.samsung.a.a.a.b bVar) {
        this.b = application;
        this.e = bVar;
        a = bVar.d() ? com.samsung.a.a.a.a.d.b.CUSTOM_TERMS : com.samsung.a.a.a.a.d.b.DIAGNOSTIC_TERMS;
        if (!TextUtils.isEmpty(bVar.b())) {
            this.e.a(2);
        } else if (!c() && bVar.e()) {
            if (a.equals(com.samsung.a.a.a.a.d.b.CUSTOM_TERMS)) {
                a(d(), 1);
            } else if (a.b() == a.DLC && !b()) {
                a(d(), 1);
            }
        }
        if (a.a()) {
            a();
        }
        if (a == com.samsung.a.a.a.a.d.b.DIAGNOSTIC_TERMS) {
            this.e.a(new f() {
                public boolean a() {
                    return d.a(application.getApplicationContext());
                }
            });
        }
        if (f()) {
            if (bVar.k()) {
                com.samsung.a.a.a.a.f.d.a(application, a.b(), bVar);
            }
            e();
        }
        i();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Tracker start:2.01.005");
        stringBuilder.append(a.b().name());
        com.samsung.a.a.a.a.i.a.a("Tracker", stringBuilder.toString());
    }

    private void a() {
        SharedPreferences a = c.a(this.b);
        com.samsung.a.a.a.a.a.c.c.a(a.getString("dom", ""));
        com.samsung.a.a.a.a.a.b.DLS_DIR.a(a.getString("uri", ""));
        com.samsung.a.a.a.a.a.b.DLS_DIR_BAT.a(a.getString("bat-uri", ""));
        if (com.samsung.a.a.a.a.d.c.a(this.b.getApplicationContext())) {
            com.samsung.a.a.a.a.d.c.a(this.b, this.e, com.samsung.a.a.a.a.c.d.a(), new com.samsung.a.a.a.a.b.a(this.b), new a<Void, Boolean>() {
                public Void a(Boolean bool) {
                    if (bool.booleanValue()) {
                        com.samsung.a.a.a.c n = b.this.e.n();
                        if (n == null) {
                            com.samsung.a.a.a.a.f.d.a.a(b.this.b.getApplicationContext(), b.this.e).a(b.this.b.getApplicationContext());
                        } else {
                            com.samsung.a.a.a.a.f.d.a.a(b.this.b.getApplicationContext(), b.this.e).a(n);
                        }
                    }
                    return null;
                }
            });
        }
    }

    public int a(Map<String, String> map, boolean z) {
        if (!f()) {
            com.samsung.a.a.a.a.i.a.b("user do not agree");
            return -2;
        } else if (map == null || map.isEmpty()) {
            com.samsung.a.a.a.a.i.a.b("Failure to send Logs : No data");
            return -3;
        } else if (!g()) {
            if (this.f != null) {
                this.b.sendBroadcast(this.f);
            }
            return -5;
        } else if (((String) map.get("t")).equals("pp") && !h()) {
            return -9;
        } else {
            if (z) {
                return com.samsung.a.a.a.a.f.d.a(this.b, a.b(), this.e).f(map);
            }
            return com.samsung.a.a.a.a.f.d.a(this.b, a.b(), this.e).e(map);
        }
    }

    private boolean a(String str) {
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(this.b.getApplicationContext().getPackageManager().getPackageInfo(str, 0).versionName, ".");
            int parseInt = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt2 = Integer.parseInt(stringTokenizer.nextToken());
            int parseInt3 = Integer.parseInt(stringTokenizer.nextToken());
            if (parseInt < 2) {
                com.samsung.a.a.a.a.i.a.a("CF version < 2.0.9");
                return false;
            } else if (parseInt != 2 || parseInt2 != 0 || parseInt3 >= 9) {
                return true;
            } else {
                com.samsung.a.a.a.a.i.a.a("CF version < 2.0.9");
                return false;
            }
        } catch (Exception e) {
            com.samsung.a.a.a.a.i.a.a(getClass(), e);
            return false;
        }
    }

    private void a(String str, int i) {
        c.a(this.b.getApplicationContext()).edit().putString("deviceId", str).putInt("auidType", i).apply();
        this.e.a(i);
        this.e.b(str);
    }

    private boolean b() {
        if (!com.samsung.a.a.a.a.d.d.a() || !a.equals(com.samsung.a.a.a.a.d.b.DIAGNOSTIC_TERMS) || !TextUtils.isEmpty(this.e.f()) || !a("com.samsung.android.providers.context")) {
            return false;
        }
        this.b.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                int i;
                String stringExtra = intent.getStringExtra("DID");
                if (TextUtils.isEmpty(stringExtra)) {
                    stringExtra = b.this.d();
                    i = 1;
                    com.samsung.a.a.a.a.i.a.b("Get CF id empty");
                } else {
                    i = 0;
                    com.samsung.a.a.a.a.i.a.b("Get CF id");
                }
                b.this.a(stringExtra, i);
                b.this.b.unregisterReceiver(this);
            }
        }, new IntentFilter("com.samsung.android.providers.context.log.action.GET_DID"));
        this.f = new Intent("com.samsung.android.providers.context.log.action.REQUEST_DID");
        this.f.putExtra("PKGNAME", this.b.getPackageName());
        this.f.setPackage("com.samsung.android.providers.context");
        this.b.sendBroadcast(this.f);
        com.samsung.a.a.a.a.i.a.b("request CF id");
        return true;
    }

    private boolean c() {
        SharedPreferences a = c.a(this.b);
        String string = a.getString("deviceId", "");
        int i = a.getInt("auidType", -1);
        if (TextUtils.isEmpty(string) || string.length() != 32 || i == -1) {
            return false;
        }
        this.e.a(i);
        this.e.b(string);
        return true;
    }

    private String d() {
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
                com.samsung.a.a.a.a.i.a.a(getClass(), e);
                return null;
            }
        }
        return stringBuilder.toString();
    }

    private void e() {
        if (!f()) {
            com.samsung.a.a.a.a.i.a.b("user do not agree setting");
        } else if (d.a(7, Long.valueOf(c.a(this.b).getLong("status_sent_date", 0)))) {
            com.samsung.a.a.a.a.i.a.b("send setting");
            com.samsung.a.a.a.a.c.d.a().a(new com.samsung.a.a.a.a.g.a(this.b, this.e));
        } else {
            com.samsung.a.a.a.a.i.a.b("do not send setting < 7days");
        }
    }

    private boolean f() {
        return this.e.h().a();
    }

    private boolean g() {
        if (a.b() == a.DMA || !TextUtils.isEmpty(this.e.b())) {
            return true;
        }
        com.samsung.a.a.a.a.i.a.b("did is empty");
        return false;
    }

    private boolean h() {
        if (!a.d()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("property disable ");
            stringBuilder.append(a.b());
            com.samsung.a.a.a.a.i.a.b(stringBuilder.toString());
            return false;
        } else if (d.a(1, Long.valueOf(c.a(this.b).getLong("property_sent_date", 0)))) {
            c.a(this.b).edit().putLong("property_sent_date", System.currentTimeMillis()).apply();
            return true;
        } else {
            com.samsung.a.a.a.a.i.a.b("do not send property < 1day");
            return false;
        }
    }

    private void i() {
        final SharedPreferences sharedPreferences = this.b.getSharedPreferences("SATerms", 0);
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            final String str = (String) entry.getKey();
            com.samsung.a.a.a.a.c.d.a().a(new com.samsung.a.a.a.a.h.a(this.e.a(), str, ((Long) entry.getValue()).longValue(), new com.samsung.a.a.a.a.c.a() {
                public void b(int i, String str, String str2, String str3) {
                }

                public void a(int i, String str, String str2, String str3) {
                    sharedPreferences.edit().remove(str).apply();
                }
            }));
        }
    }
}
