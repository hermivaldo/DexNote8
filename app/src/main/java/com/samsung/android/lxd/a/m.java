package com.samsung.android.lxd.a;

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
public class m {
    private static final String a = "m";

    public static void a(Context context) {
        Log.d(a, "addDynamicShortcuts");
        ShortcutManager shortcutManager = (ShortcutManager) context.getSystemService(ShortcutManager.class);
        if (shortcutManager.getDynamicShortcuts().size() != 0) {
            for (ShortcutInfo shortcutInfo : shortcutManager.getDynamicShortcuts()) {
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("shortcutInfo: ");
                stringBuilder.append(shortcutInfo);
                Log.d(str, stringBuilder.toString());
                if ("3".equals(shortcutInfo.getId())) {
                    Log.d(a, "shortcut already exist");
                    return;
                }
            }
        }
        o.j(context).putExtra("executionType", ExecutionType.QUICK_GUI.ordinal());
        shortcutManager.setDynamicShortcuts(Arrays.asList(new ShortcutInfo[]{new Builder(context, "3").setShortLabel(context.getString(R.string.run)).setLongLabel(context.getString(R.string.run)).setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher)).setIntent(r1).setRank(0).build()}));
    }
}
