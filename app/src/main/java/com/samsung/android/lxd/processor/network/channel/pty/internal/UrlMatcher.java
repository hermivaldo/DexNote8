package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.text.SpannableStringBuilder;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.text.util.Linkify.MatchFilter;
import java.util.Arrays;
import java.util.Hashtable;

public class UrlMatcher {
    private final ITerminalViewHelper mHelper;
    private final MatchFilter mHttpMatchFilter = new HttpMatchFilter();
    private final Hashtable<Integer, URLSpan[]> mLinkLayer = new Hashtable();

    private static class HttpMatchFilter implements MatchFilter {
        private HttpMatchFilter() {
        }

        public boolean acceptMatch(CharSequence charSequence, int i, int i2) {
            return startsWith(charSequence, i, i2, "http:") || startsWith(charSequence, i, i2, "https:");
        }

        private boolean startsWith(CharSequence charSequence, int i, int i2, String str) {
            int length = str.length();
            if (length > i2 - i) {
                return false;
            }
            for (i2 = 0; i2 < length; i2++) {
                if (charSequence.charAt(i + i2) != str.charAt(i2)) {
                    return false;
                }
            }
            return true;
        }
    }

    UrlMatcher(ITerminalViewHelper iTerminalViewHelper) {
        this.mHelper = iTerminalViewHelper;
    }

    void clear() {
        this.mLinkLayer.clear();
    }

    int createLinks(int i) {
        int i2 = i;
        TranscriptScreen screen = this.mHelper.getScreen();
        char[] scriptLine = screen.getScriptLine(i2);
        if (scriptLine == null) {
            return 1;
        }
        int length;
        int i3;
        boolean isBasicLine = screen.isBasicLine(i2);
        if (isBasicLine) {
            length = scriptLine.length;
        } else {
            length = 0;
            while (scriptLine[length] != 0) {
                length++;
            }
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(new String(scriptLine, 0, length));
        boolean scriptLineWrap = screen.getScriptLineWrap(i2);
        boolean z = isBasicLine;
        int i4 = 1;
        while (scriptLineWrap) {
            i3 = i2 + i4;
            char[] scriptLine2 = screen.getScriptLine(i3);
            if (scriptLine2 == null) {
                break;
            }
            int length2;
            boolean isBasicLine2 = screen.isBasicLine(i3);
            if (z && !isBasicLine2) {
                z = isBasicLine2;
            }
            if (isBasicLine2) {
                length2 = scriptLine2.length;
            } else {
                length2 = 0;
                while (scriptLine2[length2] != 0) {
                    length2++;
                }
            }
            spannableStringBuilder.append(new String(scriptLine2, 0, length2));
            scriptLineWrap = screen.getScriptLineWrap(i3);
            i4++;
        }
        Linkify.addLinks(spannableStringBuilder, Patterns.WEB_URL, null, this.mHttpMatchFilter, null);
        URLSpan[] uRLSpanArr = (URLSpan[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class);
        if (uRLSpanArr.length > 0) {
            int i5;
            i3 = this.mHelper.getScreenCols();
            i2 -= this.mHelper.getTopRow();
            URLSpan[][] uRLSpanArr2 = new URLSpan[i4][];
            for (int i6 = 0; i6 < i4; i6++) {
                uRLSpanArr2[i6] = new URLSpan[i3];
                Arrays.fill(uRLSpanArr2[i6], null);
            }
            int i7 = 0;
            while (i7 < uRLSpanArr.length) {
                int screenCols;
                int screenCols2;
                URLSpan[] uRLSpanArr3;
                boolean z2;
                int screenCols3;
                int i8;
                int i9;
                Object obj = uRLSpanArr[i7];
                int spanStart = spannableStringBuilder.getSpanStart(obj);
                int spanEnd = spannableStringBuilder.getSpanEnd(obj);
                if (z) {
                    spanEnd--;
                    screenCols = spanStart / this.mHelper.getScreenCols();
                    screenCols2 = spanEnd / this.mHelper.getScreenCols();
                    spanEnd %= this.mHelper.getScreenCols();
                    uRLSpanArr3 = uRLSpanArr;
                    z2 = z;
                    screenCols3 = spanStart % this.mHelper.getScreenCols();
                } else {
                    screenCols3 = 0;
                    screenCols = 0;
                    screenCols2 = 0;
                    while (screenCols3 < spanStart) {
                        char charAt = spannableStringBuilder.charAt(screenCols3);
                        if (Character.isHighSurrogate(charAt)) {
                            screenCols3++;
                            uRLSpanArr3 = uRLSpanArr;
                            screenCols2 += UnicodeTranscript.charWidth(charAt, spannableStringBuilder.charAt(screenCols3));
                        } else {
                            uRLSpanArr3 = uRLSpanArr;
                            screenCols2 += UnicodeTranscript.charWidth(charAt);
                        }
                        if (screenCols2 >= i3) {
                            screenCols++;
                            screenCols2 %= i3;
                        }
                        screenCols3++;
                        i8 = 1;
                        uRLSpanArr = uRLSpanArr3;
                    }
                    uRLSpanArr3 = uRLSpanArr;
                    i8 = screenCols;
                    i5 = screenCols2;
                    while (spanStart < spanEnd) {
                        char charAt2 = spannableStringBuilder.charAt(spanStart);
                        if (Character.isHighSurrogate(charAt2)) {
                            spanStart++;
                            z2 = z;
                            i5 += UnicodeTranscript.charWidth(charAt2, spannableStringBuilder.charAt(spanStart));
                        } else {
                            z2 = z;
                            i5 += UnicodeTranscript.charWidth(charAt2);
                        }
                        if (i5 >= i3) {
                            i8++;
                            i5 %= i3;
                        }
                        spanStart++;
                        z = z2;
                    }
                    z2 = z;
                    spanEnd = i5;
                    screenCols3 = screenCols2;
                    screenCols2 = i8;
                }
                i5 = screenCols;
                while (i5 <= screenCols2) {
                    i8 = i5 == screenCols ? screenCols3 : 0;
                    if (i5 == screenCols2) {
                        i9 = i3;
                        length = spanEnd;
                        spanStart = 1;
                    } else {
                        spanStart = 1;
                        length = this.mHelper.getScreenCols() - 1;
                        i9 = i3;
                    }
                    Arrays.fill(uRLSpanArr2[i5], i8, length + spanStart, obj);
                    i5++;
                    i3 = i9;
                }
                i9 = i3;
                i7++;
                i8 = 1;
                uRLSpanArr = uRLSpanArr3;
                z = z2;
            }
            for (i5 = 0; i5 < i4; i5++) {
                this.mLinkLayer.put(Integer.valueOf(i2 + i5), uRLSpanArr2[i5]);
            }
        }
        return i4;
    }

    public String getURLat(float f, float f2) {
        float width = (float) this.mHelper.getWidth();
        float height = (float) this.mHelper.getHeight();
        if (width == 0.0f || height == 0.0f) {
            return null;
        }
        int floor = (int) Math.floor((double) ((f / width) * ((float) this.mHelper.getScreenCols())));
        URLSpan[] uRLSpanArr = (URLSpan[]) this.mLinkLayer.get(Integer.valueOf((int) Math.floor((double) ((f2 / height) * ((float) this.mHelper.getScreenRows())))));
        if (uRLSpanArr != null) {
            URLSpan uRLSpan = uRLSpanArr[floor];
            if (uRLSpan != null) {
                return uRLSpan.getURL();
            }
        }
        return null;
    }
}
