package com.samsung.android.lxd.processor.config;

public class SystemConfigHelper {
    public static final String CONFIG_OPTION_OFF = "0";
    public static final String CONFIG_OPTION_ON = "1";

    public static boolean isConfigOptionOn(SystemConfig systemConfig, SystemConfigType systemConfigType) {
        if (systemConfig.get(systemConfigType) == null) {
            return false;
        }
        return systemConfig.get(systemConfigType).equalsIgnoreCase(CONFIG_OPTION_ON);
    }
}
