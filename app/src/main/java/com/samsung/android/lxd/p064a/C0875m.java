package com.samsung.android.lxd.p064a;

import android.content.Context;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutInfo.Builder;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.Arrays;

/* compiled from: ShortcutHelper */
/* renamed from: com.samsung.android.lxd.a.m */
public class C0875m {
    /* renamed from: a */
    private static final String f2713a = "m";

    /* renamed from: a */
    public static void m3437a(Context context) {
        Log.m3853d(f2713a, "addDynamicShortcuts");
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        if (shortcutManager.getDynamicShortcuts().size() != 0) {
            for (ShortcutInfo shortcutInfo : shortcutManager.getDynamicShortcuts()) {
                String str = f2713a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("shortcutInfo: ");
                stringBuilder.append(shortcutInfo);
                Log.m3853d(str, stringBuilder.toString());
                if ("3".equals(shortcutInfo.getId())) {
                    Log.m3853d(f2713a, "shortcut already exist");
                    return;
                }
            }
        }
        C0877o.m3518j(context).putExtra("executionType", ExecutionType.QUICK_GUI.ordinal());
        shortcutManager.setDynamicShortcuts(Arrays.asList(new ShortcutInfo[]{new Builder(context, "3").setShortLabel(context.getString(R.string.run)).setLongLabel(context.getString(R.string.run)).setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher)).setIntent(r1).setRank(0).build()}));
    }
}
