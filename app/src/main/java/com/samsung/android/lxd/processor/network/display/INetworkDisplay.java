package com.samsung.android.lxd.processor.network.display;

import android.view.ActionMode.Callback2;
import android.view.PointerIcon;
import com.samsung.android.lxd.processor.ICommonContext;

public interface INetworkDisplay {

    public interface ICallback {
        void onInputConnectionReady();

        void onSizeChanged(int i, int i2);
    }

    void disableUpdate();

    void draw(NetworkDisplayContext networkDisplayContext);

    INetworkDisplay duplicate(INetworkDisplay iNetworkDisplay);

    void enableUpdate();

    NetworkDisplayType getDisplayType();

    int getHeight();

    void getLocationInWindow(int[] iArr);

    NetworkScreenType getScreenType();

    int getWidth();

    INetworkDisplay init(ICallback iCallback);

    INetworkDisplay init(INetworkDisplay iNetworkDisplay);

    INetworkDisplay setContext(ICommonContext iCommonContext);

    INetworkDisplay setScreenType(NetworkScreenType networkScreenType);

    INetworkDisplay showActionMode(Callback2 callback2);

    INetworkDisplay start();

    INetworkDisplay stop();

    void updateCursorType(PointerIcon pointerIcon);
}
