package com.samsung.android.lxd.processor.utils.log;

import android.util.Log;

public class ConsoleLogger extends Logger {
    public int getAdbLoggingLevel(int i) {
        return i == 1 ? 3 : i;
    }

    public ConsoleLogger(String str) {
        super(str);
    }

    /* JADX WARNING: Missing block: B:9:0x005a, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void log(int i, String str, String str2) {
        if (!(str2 == null || str == null || !isLoggable(i))) {
            int length = str2.length() / 2000;
            int i2 = 0;
            while (i2 < length) {
                int adbLoggingLevel = getAdbLoggingLevel(i);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("LxD_");
                stringBuilder.append(str);
                int i3 = i2 * 2000;
                i2++;
                Log.println(adbLoggingLevel, stringBuilder.toString(), str2.substring(i3, i2 * 2000));
            }
            int adbLoggingLevel2 = getAdbLoggingLevel(i);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("LxD_");
            stringBuilder2.append(str);
            Log.println(adbLoggingLevel2, stringBuilder2.toString(), str2.substring(length * 2000, str2.length()));
        }
    }
}
