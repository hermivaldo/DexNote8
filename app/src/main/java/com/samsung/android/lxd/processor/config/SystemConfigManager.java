package com.samsung.android.lxd.processor.config;

import com.a.a.c.a;
import com.a.a.e;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.HashMap;
import java.util.Map;

public class SystemConfigManager {
    private static final String PREF_SYSTEM_CONFIG = "PREF_SYSTEM_CONFIG";
    private static final String TAG = "SystemConfigManager";

    public static boolean contains(long j) {
        return LxdApplication.a().getSharedPreferences(PREF_SYSTEM_CONFIG, 0).contains(String.valueOf(j));
    }

    public static long save(SystemConfig systemConfig) {
        if (systemConfig != null) {
            return update(-1, systemConfig);
        }
        throw new LxdException("Failed to save : invalid argument!");
    }

    public static long update(long j, SystemConfig systemConfig) {
        int i = (j > -1 ? 1 : (j == -1 ? 0 : -1));
        if ((i == 0 || contains(j)) && systemConfig != null) {
            if (i == 0) {
                j = System.currentTimeMillis();
            }
            if (contains(j)) {
                fillMissingFields(load(j), systemConfig);
            } else {
                fillMissingFields(SystemConfig.getDefaultAll(), systemConfig);
            }
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("update [");
            stringBuilder.append(j);
            stringBuilder.append("] : ");
            stringBuilder.append(systemConfig.dump());
            Log.d(str, stringBuilder.toString());
            if (LxdApplication.a().getSharedPreferences(PREF_SYSTEM_CONFIG, 0).edit().putString(String.valueOf(j), new e().a(systemConfig.getAll())).commit()) {
                return j;
            }
            throw new LxdException("Failed to update : commit error!");
        }
        throw new LxdException("Failed to update : invalid argument!");
    }

    public static SystemConfig load(long j) {
        if (contains(j)) {
            try {
                SystemConfig systemConfig = new SystemConfig((Map) new e().a(LxdApplication.a().getSharedPreferences(PREF_SYSTEM_CONFIG, 0).getString(String.valueOf(j), null), new a<HashMap<SystemConfigType, String>>() {
                }.getType()));
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("load [");
                stringBuilder.append(j);
                stringBuilder.append("] : ");
                stringBuilder.append(systemConfig.dump());
                Log.d(str, stringBuilder.toString());
                return systemConfig;
            } catch (Exception e) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Failed to load : ");
                stringBuilder2.append(e.toString());
                throw new LxdException(stringBuilder2.toString());
            }
        }
        throw new LxdException("Failed to load : invalid argument!");
    }

    public static boolean removeAll() {
        Log.d(TAG, "removeAll");
        return LxdApplication.a().getSharedPreferences(PREF_SYSTEM_CONFIG, 0).edit().clear().commit();
    }

    public static boolean remove(long j) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("remove [");
        stringBuilder.append(j);
        stringBuilder.append("]");
        Log.d(str, stringBuilder.toString());
        return LxdApplication.a().getSharedPreferences(PREF_SYSTEM_CONFIG, 0).edit().remove(String.valueOf(j)).commit();
    }

    public static Map<String, SystemConfig> loadAll() {
        Map<String, SystemConfig> hashMap = new HashMap();
        for (String str : LxdApplication.a().getSharedPreferences(PREF_SYSTEM_CONFIG, 0).getAll().keySet()) {
            try {
                hashMap.put(str, load(Long.parseLong(str)));
            } catch (Exception e) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Failed to load [");
                stringBuilder.append(str);
                stringBuilder.append("] ");
                stringBuilder.append(e.toString());
                throw new LxdException(stringBuilder.toString());
            }
        }
        return hashMap;
    }

    private static void fillMissingFields(SystemConfig systemConfig, SystemConfig systemConfig2) {
        fillMissingFields(systemConfig.getAll(), systemConfig2);
    }

    private static void fillMissingFields(Map<SystemConfigType, String> map, SystemConfig systemConfig) {
        for (SystemConfigType systemConfigType : map.keySet()) {
            if (!systemConfig.getAll().containsKey(systemConfigType)) {
                systemConfig.set(systemConfigType, (String) map.get(systemConfigType));
            }
        }
    }
}
