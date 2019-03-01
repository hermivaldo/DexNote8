package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.content.SharedPreferences;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.utils.log.Log;

public class TerminalSetting {
    private static final int AMBER = -18863;
    private static final int BLACK = -16777216;
    private static final int BLUE = -13349187;
    private static final int[][] COLOR_SCHEMES = new int[][]{new int[]{BLACK, WHITE}, new int[]{WHITE, BLACK}, new int[]{WHITE, BLUE}, new int[]{GREEN, BLACK}, new int[]{AMBER, BLACK}, new int[]{RED, BLACK}, new int[]{HOLO_BLUE, BLACK}, new int[]{SOLARIZED_FG, SOLARIZED_BG}, new int[]{SOLARIZED_DARK_FG, SOLARIZED_DARK_BG}, new int[]{LINUX_CONSOLE_WHITE, BLACK}};
    private static final String FONTSIZE_KEY = "fontSize";
    private static final int GREEN = -16711936;
    private static final int HOLO_BLUE = -13388315;
    private static final int LINUX_CONSOLE_WHITE = -5592406;
    private static final int MAX_FONT_SIZE_RATIO = 3;
    private static final int RED = -65261;
    private static final int SOLARIZED_BG = -133405;
    private static final int SOLARIZED_DARK_BG = -16766154;
    private static final int SOLARIZED_DARK_FG = -8153962;
    private static final int SOLARIZED_FG = -10126461;
    private static final String TAG = "TerminalSetting";
    private static final int WHITE = -1;
    private boolean mAltSendsEsc = false;
    private int mColorId = 1;
    private ColorScheme mColorScheme = new ColorScheme(COLOR_SCHEMES[this.mColorId]);
    private final ICommonContext mContext;
    private boolean mCursorBlink = true;
    private String mInitialCommand = "";
    private boolean mMouseTracking = true;
    private SharedPreferences mPrefs;
    private String mTermType = "";
    private boolean mUTF8ByDefault = true;

    public TerminalSetting(ICommonContext iCommonContext) {
        this.mContext = iCommonContext;
        this.mPrefs = iCommonContext.getDisplayContext().getSharedPreferences("pty_preference", 0);
    }

    private float readFloatPrefs(String str, float f) {
        return this.mPrefs.getFloat(str, f);
    }

    private void writeFloatPrefs(String str, float f) {
        this.mPrefs.edit().putFloat(str, f).apply();
    }

    boolean getCursorBlink() {
        return this.mCursorBlink;
    }

    float getMinFontSize() {
        boolean f = o.f();
        boolean d = o.d(this.mContext.getDisplayContext());
        if (f) {
            return 16.5f;
        }
        return d ? 7.5f : 12.0f;
    }

    float getMaxFontSize() {
        return getMinFontSize() * 3.0f;
    }

    TerminalSetting setFontSize(float f) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setFontSize : ");
        stringBuilder.append(f);
        Log.i(str, stringBuilder.toString());
        writeFloatPrefs(FONTSIZE_KEY, f);
        return this;
    }

    float getFontSize() {
        return readFloatPrefs(FONTSIZE_KEY, getMinFontSize());
    }

    ColorScheme getColorScheme() {
        return this.mColorScheme;
    }

    boolean defaultToUTF8Mode() {
        return this.mUTF8ByDefault;
    }

    boolean getAltSendsEscFlag() {
        return this.mAltSendsEsc;
    }

    boolean getMouseTrackingFlag() {
        return this.mMouseTracking;
    }

    String getInitialCommand() {
        return this.mInitialCommand;
    }

    String getTermType() {
        return this.mTermType;
    }
}
