package com.samsung.android.lxd.processor;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay;
import com.samsung.android.lxd.processor.network.display.NetworkScreenType;
import com.samsung.android.lxd.processor.network.input.INetworkInput;

public interface ICommonContext {
    INetworkInput enableSoftInput(boolean z);

    Context getDisplayContext();

    InputConnection getInputConnection(View view, EditorInfo editorInfo);

    INetworkDisplay getLocalDisplay();

    int getLocalHeight();

    int getLocalWidth();

    void getLocationInWindow(int[] iArr);

    int getRemoteHeight();

    int getRemoteWidth();

    NetworkScreenType getScreenType();

    boolean isTextEditor();
}
