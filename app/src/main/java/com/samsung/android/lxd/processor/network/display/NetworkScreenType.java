package com.samsung.android.lxd.processor.network.display;

public enum NetworkScreenType {
    RESOLUTION_16_9("1920x1080"),
    RESOLUTION_16_10("1920x1200"),
    RESOLUTION_16_10_STANDALONE("1400x875"),
    RESOLUTION_21_9_MONITOR("2560x1080");
    
    private final String mSize;

    private NetworkScreenType(String str) {
        this.mSize = str;
    }

    public String getSize() {
        return this.mSize;
    }
}
