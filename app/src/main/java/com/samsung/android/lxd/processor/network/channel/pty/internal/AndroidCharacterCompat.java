package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.os.Build.VERSION;
import android.text.AndroidCharacter;

public class AndroidCharacterCompat {
    public static final int EAST_ASIAN_WIDTH_AMBIGUOUS = 1;
    public static final int EAST_ASIAN_WIDTH_FULL_WIDTH = 3;
    public static final int EAST_ASIAN_WIDTH_HALF_WIDTH = 2;
    public static final int EAST_ASIAN_WIDTH_NARROW = 4;
    public static final int EAST_ASIAN_WIDTH_NEUTRAL = 0;
    public static final int EAST_ASIAN_WIDTH_WIDE = 5;

    private static class Api8OrLater {
        private Api8OrLater() {
        }

        public static int getEastAsianWidth(char c) {
            return AndroidCharacter.getEastAsianWidth(c);
        }
    }

    public static int getEastAsianWidth(char c) {
        return VERSION.SDK_INT >= 8 ? Api8OrLater.getEastAsianWidth(c) : 4;
    }
}
