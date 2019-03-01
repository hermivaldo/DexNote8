package com.samsung.android.lxd.processor.config;

import java.util.HashMap;
import java.util.Map;

public class SystemConfig {
    private static final String CONFIG_DEFAULT_ADB_WIFI = "0";
    private static final String CONFIG_DEFAULT_CPU_BIG = "4";
    private static final String CONFIG_DEFAULT_CPU_LITTLE = "4";
    private static final String CONFIG_DEFAULT_IMAGE_DESC = "";
    private static final String CONFIG_DEFAULT_IMAGE_NAME = "Ubuntu 16";
    private static final String CONFIG_DEFAULT_IMAGE_PATH = "/storage/emulated/0/xenial.img";
    private static final String CONFIG_DEFAULT_IMAGE_SIZE = "4";
    private static final String CONFIG_DEFAULT_MEMORY_SIZE = "2662";
    private static final String CONFIG_DEFAULT_SHARE_FOLDER = "1";
    private static final String CONFIG_MAXIMUM_CPU_BIG = "4";
    private static final String CONFIG_MAXIMUM_CPU_LITTLE = "4";
    private static final String CONFIG_MAXIMUM_IMAGE_SIZE = "32";
    private static final String CONFIG_MAXIMUM_MEMORY_SIZE = "6144";
    private static final String CONFIG_MINIMUM_CPU_BIG = "2";
    private static final String CONFIG_MINIMUM_CPU_LITTLE = "2";
    private static final String CONFIG_MINIMUM_IMAGE_SIZE = "4";
    private static final String CONFIG_MINIMUM_MEMORY_SIZE = "2662";
    private static final String TAG = "SystemConfig";
    private static final Map<SystemConfigType, String> mDefaultConfigMap = new HashMap();
    private static final Map<SystemConfigType, String> mMaximumConfigMap = new HashMap();
    private static final Map<SystemConfigType, String> mMinimumConfigMap = new HashMap();
    private final Map<SystemConfigType, String> mConfigMap;

    static {
        mDefaultConfigMap.put(SystemConfigType.IMAGE_NAME, CONFIG_DEFAULT_IMAGE_NAME);
        mDefaultConfigMap.put(SystemConfigType.IMAGE_DESC, CONFIG_DEFAULT_IMAGE_DESC);
        mDefaultConfigMap.put(SystemConfigType.IMAGE_PATH, CONFIG_DEFAULT_IMAGE_PATH);
        mDefaultConfigMap.put(SystemConfigType.SHARE_FOLDER, "1");
        mDefaultConfigMap.put(SystemConfigType.IMAGE_SIZE, "4");
        mDefaultConfigMap.put(SystemConfigType.MEMORY_SIZE, "2662");
        mDefaultConfigMap.put(SystemConfigType.CPU_BIG, "4");
        mDefaultConfigMap.put(SystemConfigType.CPU_LITTLE, "4");
        mDefaultConfigMap.put(SystemConfigType.ADB_WiFi, "0");
        mMinimumConfigMap.put(SystemConfigType.IMAGE_SIZE, "4");
        mMinimumConfigMap.put(SystemConfigType.MEMORY_SIZE, "2662");
        mMinimumConfigMap.put(SystemConfigType.CPU_BIG, "2");
        mMinimumConfigMap.put(SystemConfigType.CPU_LITTLE, "2");
        mMaximumConfigMap.put(SystemConfigType.IMAGE_SIZE, CONFIG_MAXIMUM_IMAGE_SIZE);
        mMaximumConfigMap.put(SystemConfigType.MEMORY_SIZE, CONFIG_MAXIMUM_MEMORY_SIZE);
        mMaximumConfigMap.put(SystemConfigType.CPU_BIG, "4");
        mMaximumConfigMap.put(SystemConfigType.CPU_LITTLE, "4");
    }

    public SystemConfig() {
        this.mConfigMap = new HashMap();
    }

    public SystemConfig(Map<SystemConfigType, String> map) {
        this.mConfigMap = new HashMap(map);
    }

    public boolean set(SystemConfigType systemConfigType, String str) {
        this.mConfigMap.put(systemConfigType, str);
        return true;
    }

    public String get(SystemConfigType systemConfigType) {
        return (String) this.mConfigMap.get(systemConfigType);
    }

    public HashMap<SystemConfigType, String> getAll() {
        return new HashMap(this.mConfigMap);
    }

    public static HashMap<SystemConfigType, String> getDefaultAll() {
        return new HashMap(mDefaultConfigMap);
    }

    public static String getDefault(SystemConfigType systemConfigType) {
        return (String) mDefaultConfigMap.get(systemConfigType);
    }

    public static String getMinimum(SystemConfigType systemConfigType) {
        return (String) mMinimumConfigMap.get(systemConfigType);
    }

    public static String getMaximum(SystemConfigType systemConfigType) {
        return (String) mMaximumConfigMap.get(systemConfigType);
    }

    public String dump() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SystemConfigType.IMAGE_NAME.toString());
        stringBuilder.append(": ");
        stringBuilder.append((String) this.mConfigMap.get(SystemConfigType.IMAGE_NAME));
        stringBuilder.append(", ");
        stringBuilder.append(SystemConfigType.IMAGE_DESC.toString());
        stringBuilder.append(": ");
        stringBuilder.append((String) this.mConfigMap.get(SystemConfigType.IMAGE_DESC));
        stringBuilder.append(", ");
        stringBuilder.append(SystemConfigType.IMAGE_PATH.toString());
        stringBuilder.append(": ");
        stringBuilder.append((String) this.mConfigMap.get(SystemConfigType.IMAGE_PATH));
        stringBuilder.append(", ");
        stringBuilder.append(SystemConfigType.IMAGE_SIZE.toString());
        stringBuilder.append(": ");
        stringBuilder.append((String) this.mConfigMap.get(SystemConfigType.IMAGE_SIZE));
        stringBuilder.append(", ");
        stringBuilder.append(SystemConfigType.ADB_WiFi.toString());
        stringBuilder.append(": ");
        stringBuilder.append((String) this.mConfigMap.get(SystemConfigType.ADB_WiFi));
        return stringBuilder.toString();
    }
}
