package com.samsung.android.lxd.processor.control.channel.socket;

import com.samsung.android.lxd.processor.LxdException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class IpcMessage {
    public static final int CGROUP_FREEZER = 3;
    public static final int EXTERN_SDCARD_MOUNTED = 8;
    public static final int EXTERN_SDCARD_UNMOUNTED = 9;
    public static final int GET_CGROUP_MEMORY_USAGE = 41;
    public static final int GET_DAEMON_VERSION = 0;
    public static final int GET_DEBUG_LOG = 35;
    public static final int GET_IMAGE_MIN_SIZE = 34;
    public static final int GET_IMAGE_VERSION = 33;
    public static final int GET_STATUS = 6;
    public static final int LXD_IPC_TYPE_BASE_NOTI = 128;
    public static final int LXD_IPC_TYPE_BASE_REQ = 0;
    public static final int NOTIFY_LOW_MEMORY = 128;
    public static final int NOTIFY_MONITORING = 129;
    public static final int REBASE_COPY_PAYLOAD = 36;
    public static final int REBASE_INSTALL_PAYLOAD = 37;
    public static final int REBASE_ONLY = 38;
    public static final int RESET_CUSTOM_ARGV = 40;
    public static final int RESIZE_IMAGE = 7;
    public static final int RESULT_IGNORE = 1;
    public static final int RESULT_NON_SETUP_IMAGE = 17;
    public static final int RESULT_OK = 0;
    public static final int RESULT_WRONG_IMAGE = 13;
    public static final int SET_ADB_TCPIP_MODE = 19;
    public static final int SET_CLI_MODE = 16;
    public static final int SET_CONFIG = 10;
    public static final int SET_CONFIG_DONE = 32;
    public static final int SET_CPU_BIG = 14;
    public static final int SET_CPU_LITTLE = 15;
    public static final int SET_CUSTOM_ARGV = 39;
    public static final int SET_GUI_MODE = 17;
    public static final int SET_IMAGE_PATH = 11;
    public static final int SET_MEMORY_SIZE = 13;
    public static final int SET_SCREEN_SIZE = 21;
    public static final int SET_SHARED_DIR = 12;
    public static final int SET_TERMINAL_MODE = 18;
    public static final int SET_TIMEZONE = 20;
    public static final int START_CONTAINER = 1;
    public static final int START_MONITORING = 42;
    public static final int STATUS_TYPE_BASE_REQ = 128;
    public static final int STATUS_TYPE_BASE_RES = 0;
    public static final int STATUS_TYPE_BASE_RES_2 = 208;
    public static final int STOP_CONTAINER = 4;
    public static final int STOP_MONITORING = 43;
    public static final int SYNC_REQUEST = 5;
    private static final String TAG = "IpcMessage";
    protected byte[] mIpcBody;
    protected byte[] mIpcData;
    protected byte[] mIpcHeader;
    protected int mMainCmd;
    protected int mStatus = 128;

    public IpcMessage(int i, byte[] bArr) {
        this.mMainCmd = i;
        this.mIpcBody = bArr;
    }

    public void makeHeader() {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.write(this.mMainCmd);
            dataOutputStream.write(this.mStatus);
            dataOutputStream.close();
            this.mIpcHeader = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to make header : ");
            stringBuilder.append(e.toString());
            throw new LxdException(stringBuilder.toString());
        }
    }

    public byte[] createIpcMessage() {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        makeHeader();
        int length = this.mIpcBody == null ? 0 : this.mIpcBody.length;
        try {
            dataOutputStream.write(this.mIpcHeader, 0, this.mIpcHeader.length);
            dataOutputStream.writeInt(length);
            if (this.mIpcBody != null) {
                dataOutputStream.write(this.mIpcBody, 0, this.mIpcBody.length);
            }
            this.mIpcData = byteArrayOutputStream.toByteArray();
            dataOutputStream.close();
            return this.mIpcData;
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("createIpcMessage: ");
            stringBuilder.append(e.toString());
            throw new LxdException(stringBuilder.toString());
        }
    }
}
