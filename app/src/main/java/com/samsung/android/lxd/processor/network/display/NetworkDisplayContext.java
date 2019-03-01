package com.samsung.android.lxd.processor.network.display;

import android.graphics.drawable.Drawable;

public class NetworkDisplayContext {
    private int[] mBitmapPixels = null;
    private Drawable mDrawable = null;
    private int mHorizontalScrollExtent = 0;
    private int mHorizontalScrollOffset = 0;
    private int mHorizontalScrollRange = 0;
    private boolean mShowHorizontalScroll = false;
    private boolean mShowVerticalScroll = false;
    private int mVerticalScrollExtent = 0;
    private int mVerticalScrollOffset = 0;
    private int mVerticalScrollRange = 0;

    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public int[] getBitmapPixels() {
        return this.mBitmapPixels;
    }

    public boolean showHorizontalScroll() {
        return this.mShowHorizontalScroll;
    }

    public boolean showVerticalScroll() {
        return this.mShowVerticalScroll;
    }

    public int getHorizontalScrollRange() {
        return this.mHorizontalScrollRange;
    }

    public int getHorizontalScrollOffset() {
        return this.mHorizontalScrollOffset;
    }

    public int getHorizontalScrollExtent() {
        return this.mHorizontalScrollExtent;
    }

    public int getVerticalScrollRange() {
        return this.mVerticalScrollRange;
    }

    public int getVerticalScrollOffset() {
        return this.mVerticalScrollOffset;
    }

    public int getVerticalScrollExtent() {
        return this.mVerticalScrollExtent;
    }

    public NetworkDisplayContext setDrawable(Drawable drawable) {
        this.mDrawable = drawable;
        return this;
    }

    public NetworkDisplayContext setBitmapPixels(int[] iArr) {
        this.mBitmapPixels = iArr;
        return this;
    }

    public NetworkDisplayContext setShowHorizontalScroll(boolean z) {
        this.mShowHorizontalScroll = z;
        return this;
    }

    public NetworkDisplayContext setShowVerticalScroll(boolean z) {
        this.mShowVerticalScroll = z;
        return this;
    }

    public NetworkDisplayContext setHorizontalScrollRange(int i) {
        this.mHorizontalScrollRange = i;
        return this;
    }

    public NetworkDisplayContext setHorizontalScrollOffset(int i) {
        this.mHorizontalScrollOffset = i;
        return this;
    }

    public NetworkDisplayContext setHorizontalScrollExtent(int i) {
        this.mHorizontalScrollExtent = i;
        return this;
    }

    public NetworkDisplayContext setVerticalScrollRange(int i) {
        this.mVerticalScrollRange = i;
        return this;
    }

    public NetworkDisplayContext setVerticalScrollOffset(int i) {
        this.mVerticalScrollOffset = i;
        return this;
    }

    public NetworkDisplayContext setVerticalScrollExtent(int i) {
        this.mVerticalScrollExtent = i;
        return this;
    }
}
