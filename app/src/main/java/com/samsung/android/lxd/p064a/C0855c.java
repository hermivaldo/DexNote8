package com.samsung.android.lxd.p064a;

import android.content.Context;
import android.os.SemSystemProperties;
import android.telephony.TelephonyManager;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.HashMap;

/* compiled from: CountryCodeUtils */
/* renamed from: com.samsung.android.lxd.a.c */
public class C0855c {
    /* renamed from: a */
    private static final String f2665a = "c";

    /* renamed from: b */
    private static String m3364b(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("412", "af");
        hashMap.put("276", "al");
        hashMap.put("603", "dz");
        hashMap.put("544", "as");
        hashMap.put("213", "ad");
        hashMap.put("631", "ao");
        hashMap.put("365", "ai");
        hashMap.put("344", "ag");
        hashMap.put("722", "ar");
        hashMap.put("283", "am");
        hashMap.put("363", "aw");
        hashMap.put("505", "au");
        hashMap.put("232", "at");
        hashMap.put("400", "az");
        hashMap.put("364", "bs");
        hashMap.put("426", "bh");
        hashMap.put("470", "bd");
        hashMap.put("342", "bb");
        hashMap.put("257", "by");
        hashMap.put("206", "be");
        hashMap.put("702", "bz");
        hashMap.put("616", "bj");
        hashMap.put("350", "bm");
        hashMap.put("402", "bt");
        hashMap.put("736", "bo");
        hashMap.put("218", "ba");
        hashMap.put("652", "bw");
        hashMap.put("724", "br");
        hashMap.put("348", "vg");
        hashMap.put("528", "bn");
        hashMap.put("284", "bg");
        hashMap.put("613", "bf");
        hashMap.put("642", "bi");
        hashMap.put("456", "kh");
        hashMap.put("624", "cm");
        hashMap.put("302", "ca");
        hashMap.put("625", "cv");
        hashMap.put("346", "ky");
        hashMap.put("623", "cf");
        hashMap.put("622", "td");
        hashMap.put("730", "cl");
        hashMap.put("460", "cn");
        hashMap.put("461", "cn");
        hashMap.put("732", "co");
        hashMap.put("654", "km");
        hashMap.put("629", "cg");
        hashMap.put("548", "ck");
        hashMap.put("712", "cr");
        hashMap.put("612", "ci");
        hashMap.put("219", "hr");
        hashMap.put("368", "cu");
        hashMap.put("362", "cw");
        hashMap.put("280", "cy");
        hashMap.put("230", "cz");
        hashMap.put("630", "cd");
        hashMap.put("238", "dk");
        hashMap.put("638", "dj");
        hashMap.put("366", "dm");
        hashMap.put("370", "do");
        hashMap.put("514", "kh");
        hashMap.put("740", "ec");
        hashMap.put("602", "eg");
        hashMap.put("706", "sv");
        hashMap.put("627", "gq");
        hashMap.put("657", "er");
        hashMap.put("248", "ee");
        hashMap.put("636", "et");
        hashMap.put("750", "fk");
        hashMap.put("288", "fo");
        hashMap.put("542", "fj");
        hashMap.put("244", "fi");
        hashMap.put("208", "fr");
        hashMap.put("742", "gf");
        hashMap.put("547", "pf");
        hashMap.put("628", "ga");
        hashMap.put("607", "gm");
        hashMap.put("282", "ge");
        hashMap.put("262", "de");
        hashMap.put("620", "gh");
        hashMap.put("266", "gi");
        hashMap.put("202", "gr");
        hashMap.put("290", "gl");
        hashMap.put("352", "gd");
        hashMap.put("340", "gp");
        hashMap.put("535", "gu");
        hashMap.put("704", "gt");
        hashMap.put("611", "gn");
        hashMap.put("632", "gw");
        hashMap.put("738", "gy");
        hashMap.put("372", "ht");
        hashMap.put("708", "hn");
        hashMap.put("454", "hk");
        hashMap.put("216", "hu");
        hashMap.put("274", "is");
        hashMap.put("404", "in");
        hashMap.put("405", "in");
        hashMap.put("510", "id");
        hashMap.put("432", "ir");
        hashMap.put("418", "iq");
        hashMap.put("272", "ie");
        hashMap.put("425", "il");
        hashMap.put("222", "it");
        hashMap.put("338", "jm");
        hashMap.put("441", "jp");
        hashMap.put("440", "jp");
        hashMap.put("416", "jo");
        hashMap.put("401", "kz");
        hashMap.put("639", "ke");
        hashMap.put("545", "ki");
        hashMap.put("467", "kp");
        hashMap.put("450", "kr");
        hashMap.put("419", "kw");
        hashMap.put("437", "kg");
        hashMap.put("457", "la");
        hashMap.put("247", "lv");
        hashMap.put("415", "lb");
        hashMap.put("651", "ls");
        hashMap.put("618", "lr");
        hashMap.put("606", "ly");
        hashMap.put("295", "li");
        hashMap.put("246", "lt");
        hashMap.put("270", "lu");
        hashMap.put("455", "mo");
        hashMap.put("294", "mk");
        hashMap.put("646", "mg");
        hashMap.put("650", "mw");
        hashMap.put("502", "my");
        hashMap.put("472", "mv");
        hashMap.put("610", "ml");
        hashMap.put("278", "mt");
        hashMap.put("551", "mh");
        hashMap.put("340", "mq");
        hashMap.put("609", "mr");
        hashMap.put("617", "mu");
        hashMap.put("334", "mx");
        hashMap.put("550", "fm");
        hashMap.put("259", "md");
        hashMap.put("212", "mc");
        hashMap.put("428", "mn");
        hashMap.put("297", "me");
        hashMap.put("354", "ms");
        hashMap.put("604", "ma");
        hashMap.put("643", "mz");
        hashMap.put("414", "mm");
        hashMap.put("649", "na");
        hashMap.put("536", "nr");
        hashMap.put("429", "np");
        hashMap.put("204", "nl");
        hashMap.put("546", "nc");
        hashMap.put("530", "nz");
        hashMap.put("710", "ni");
        hashMap.put("614", "ne");
        hashMap.put("621", "ng");
        hashMap.put("555", "nu");
        hashMap.put("534", "mp");
        hashMap.put("242", "no");
        hashMap.put("422", "om");
        hashMap.put("410", "pk");
        hashMap.put("552", "pw");
        hashMap.put("423", "ps");
        hashMap.put("714", "pa");
        hashMap.put("537", "pg");
        hashMap.put("744", "py");
        hashMap.put("716", "pe");
        hashMap.put("515", "ph");
        hashMap.put("260", "pl");
        hashMap.put("268", "pt");
        hashMap.put("330", "pr");
        hashMap.put("427", "qa");
        hashMap.put("647", "re");
        hashMap.put("226", "ro");
        hashMap.put("250", "ru");
        hashMap.put("635", "rw");
        hashMap.put("356", "kn");
        hashMap.put("358", "lc");
        hashMap.put("308", "pm");
        hashMap.put("360", "vc");
        hashMap.put("549", "ws");
        hashMap.put("292", "sm");
        hashMap.put("626", "st");
        hashMap.put("420", "sa");
        hashMap.put("608", "sn");
        hashMap.put("220", "rs");
        hashMap.put("633", "sc");
        hashMap.put("619", "sl");
        hashMap.put("525", "sg");
        hashMap.put("231", "sk");
        hashMap.put("293", "si");
        hashMap.put("540", "sb");
        hashMap.put("637", "so");
        hashMap.put("655", "za");
        hashMap.put("214", "es");
        hashMap.put("413", "lk");
        hashMap.put("634", "sd");
        hashMap.put("746", "sr");
        hashMap.put("653", "sz");
        hashMap.put("240", "se");
        hashMap.put("228", "ch");
        hashMap.put("417", "sy");
        hashMap.put("466", "tw");
        hashMap.put("436", "tj");
        hashMap.put("640", "tz");
        hashMap.put("520", "th");
        hashMap.put("615", "tg");
        hashMap.put("539", "to");
        hashMap.put("374", "tt");
        hashMap.put("605", "tn");
        hashMap.put("286", "tr");
        hashMap.put("438", "tm");
        hashMap.put("376", "tc");
        hashMap.put("553", "tv");
        hashMap.put("641", "ug");
        hashMap.put("255", "ua");
        hashMap.put("424", "ae");
        hashMap.put("430", "ae");
        hashMap.put("431", "ae");
        hashMap.put("235", "gb");
        hashMap.put("234", "gb");
        hashMap.put("310", "us");
        hashMap.put("311", "us");
        hashMap.put("312", "us");
        hashMap.put("313", "us");
        hashMap.put("314", "us");
        hashMap.put("315", "us");
        hashMap.put("316", "us");
        hashMap.put("332", "vi");
        hashMap.put("748", "uy");
        hashMap.put("434", "uz");
        hashMap.put("541", "vu");
        hashMap.put("225", "va");
        hashMap.put("734", "ve");
        hashMap.put("452", "vn");
        hashMap.put("543", "wf");
        hashMap.put("421", "ye");
        hashMap.put("645", "zm");
        hashMap.put("648", "zw");
        return (str == null || !hashMap.containsKey(str)) ? null : (String) hashMap.get(str);
    }

    /* renamed from: a */
    public static String m3361a(Context context) {
        context = C0855c.m3364b(C0855c.m3363b(context));
        if (context == null) {
            Log.m3857i(f2665a, "MCC is null , so get country code from csc");
            context = SemSystemProperties.get("ro.csc.countryiso_code").toLowerCase();
        }
        String str = f2665a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("mCountryCodeForTerms:");
        stringBuilder.append(context);
        Log.m3857i(str, stringBuilder.toString());
        return context;
    }

    /* renamed from: b */
    private static String m3363b(Context context) {
        String substring;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            context = telephonyManager.getSimOperator();
            if (context != null && context.length() >= 3) {
                substring = context.substring(0, 3);
                if (substring == null) {
                    return substring != null ? C0855c.m3360a() : substring;
                } else {
                    C0855c.m3362a(substring);
                    return substring;
                }
            }
        }
        substring = null;
        if (substring == null) {
            C0855c.m3362a(substring);
            return substring;
        } else if (substring != null) {
        }
    }

    /* renamed from: a */
    public static void m3362a(String str) {
        LxdApplication.m3344a().getSharedPreferences("prefs", 0).edit().putString("mobileCountryCode", str).apply();
    }

    /* renamed from: a */
    public static String m3360a() {
        return LxdApplication.m3344a().getSharedPreferences("prefs", 0).getString("mobileCountryCode", "");
    }
}
