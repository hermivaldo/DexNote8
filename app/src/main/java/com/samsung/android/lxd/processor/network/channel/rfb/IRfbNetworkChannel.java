package com.samsung.android.lxd.processor.network.channel.rfb;

import android.view.PointerIcon;
import com.samsung.android.lxd.processor.network.channel.INetworkChannel;
import java.nio.MappedByteBuffer;

public interface IRfbNetworkChannel extends INetworkChannel {

    public interface ICallback {
        void onConnect(MappedByteBuffer mappedByteBuffer);

        void onCursorUpdated(PointerIcon pointerIcon, boolean z);

        void onCutTextReceived(String str);

        void onError(Throwable th);

        void onReceive();
    }
}
