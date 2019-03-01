package com.samsung.android.lxd.processor.utils.log;

import android.os.Build.VERSION;
import android.os.SemSystemProperties;

public abstract class Logger {
    private static final String LOG_LEVEL = SemSystemProperties.get(LOG_LEVEL_PROP, LOG_LEVEL_PROP_LOW);
    private static final String LOG_LEVEL_PROP = (VERSION.SDK_INT < 28 ? "ro.debug_level" : "ro.boot.debug_level");
    private static final String LOG_LEVEL_PROP_HIGH = "0x4948";
    private static final String LOG_LEVEL_PROP_LOW = "0x4f4c";
    private static final String LOG_LEVEL_PROP_MID = "0x494d";
    private static final boolean SHIP_BUILD = SemSystemProperties.getBoolean("ro.product_ship", true);
    protected static final String TAG_PREFIX = "LxD_";
    private int level;
    protected String loggerName;

    public void flush() {
    }

    public abstract void log(int i, String str, String str2);

    protected Logger(String str) {
        int i = LOG_LEVEL_PROP_LOW.equalsIgnoreCase(LOG_LEVEL) ? 4 : SHIP_BUILD ? 2 : 1;
        this.level = i;
        this.loggerName = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Logger)) {
            return SHIP_BUILD;
        }
        Logger logger = (Logger) obj;
        if (this.level != logger.level) {
            return SHIP_BUILD;
        }
        if (this.loggerName == null) {
            if (logger.loggerName != null) {
                return SHIP_BUILD;
            }
        } else if (!this.loggerName.equals(logger.loggerName)) {
            return SHIP_BUILD;
        }
        return true;
    }

    public int getLogLevel() {
        return this.level;
    }

    public int hashCode() {
        return ((this.level + 31) * 31) + (this.loggerName == null ? 0 : this.loggerName.hashCode());
    }

    public boolean isLoggable(int i) {
        return this.level <= i ? true : SHIP_BUILD;
    }

    public void setLogLevel(int i) {
        this.level = i;
    }
}
