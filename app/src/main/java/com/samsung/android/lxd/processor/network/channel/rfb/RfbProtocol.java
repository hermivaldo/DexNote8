package com.samsung.android.lxd.processor.network.channel.rfb;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.LocalSocketAddress.Namespace;
import android.view.PointerIcon;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class RfbProtocol {
    private static final boolean BIG_ENDIAN = false;
    private static final int BIT_PER_PIXEL = 32;
    private static final int BLUE_MAX = 255;
    private static final int BLUE_SHIFT = 0;
    private static final String CLIENT_VERSION = "RFB 003.008\n";
    private static final int DEPTH = 24;
    private static final int GREEN_MAX = 255;
    private static final int GREEN_SHIFT = 8;
    private static final boolean GREY_SCALE = false;
    private static final int KEY_MASK_ALT = 2;
    private static final int KEY_MASK_CTRL = 4;
    private static final int KEY_MASK_SHIFT = 1;
    private static final int MAX_TRY_COUNT = 25;
    private static final int MSG_CLIENT_CUT_TEXT = 6;
    private static final int MSG_CLIENT_KEYBOARD_EVENT = 4;
    private static final int MSG_CLIENT_POINTER_EVENT = 5;
    private static final int MSG_CLIENT_REQUEST_UPDATE = 3;
    private static final int MSG_CLIENT_SET_ENCODINGS = 2;
    private static final int MSG_CLIENT_SET_PIXEL_FORMAT = 0;
    private static final int MSG_SERVER_AUTH_FAILED = 1;
    private static final int MSG_SERVER_AUTH_OK = 0;
    private static final int MSG_SERVER_AUTH_TOO_MANY = 2;
    static final int MSG_SERVER_BELL = 2;
    static final int MSG_SERVER_CUT_TEXT = 3;
    static final int MSG_SERVER_FRAMEBUFFER_SWAP = 4;
    static final int MSG_SERVER_FRAMEBUFFER_UPDATE = 0;
    private static final int RECONNECT_INTERVAL = 1000;
    private static final int RED_MAX = 255;
    private static final int RED_SHIFT = 16;
    private static final String SOCKET_ADDRESS = "run/vncsrv.usk";
    private static final String TAG = "RfbProtocol";
    private static final boolean TRUE_COLOR = true;
    static final int TYPE_ENCODING_RICH_CURSOR = -239;
    private static final int TYPE_SECURITY_INVALID = 0;
    private static final int TYPE_SECURITY_NONE = 1;
    private static final int TYPE_SECURITY_VNC_AUTH = 2;
    static final boolean USE_CURSOR_SHAPE_UPDATE = true;
    private boolean mClosed = false;
    private int mEncoding = -1;
    private int mEncodingCounts = 0;
    private int mEncodingType = 0;
    private final byte[] mEventBuffer = new byte[72];
    private int mFramebufferHeight;
    private int mFramebufferWidth;
    private DataInputStream mInputStream;
    private boolean mIsReady = false;
    private boolean mKeepRetryingToConnect = false;
    private int mLengthOfEventBuffer = 0;
    private OutputStream mOutputStream;
    private int mRectH = 0;
    private int mRectW = 0;
    private int mRectX = 0;
    private int mRectY = 0;
    private LocalSocket mRfbSocket = new LocalSocket();
    private int[] mSavedEncodings = null;
    private String mSystemName;

    private void writeModifierKeyEvents(int i) {
    }

    RfbProtocol() {
    }

    synchronized void close() {
        if (!this.mClosed) {
            try {
                this.mKeepRetryingToConnect = false;
                this.mRfbSocket.shutdownInput();
                this.mRfbSocket.shutdownOutput();
                this.mRfbSocket.close();
                this.mClosed = true;
                Log.i(TAG, "Closed successfully");
            } catch (Exception e) {
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("exception during close: ");
                stringBuilder.append(e.toString());
                Log.i(str, stringBuilder.toString());
                e.printStackTrace();
            }
        } else {
            return;
        }
        return;
    }

    FileDescriptor[] getAncillaryFileDescriptors() {
        return this.mInputStream.read() == 65 ? this.mRfbSocket.getAncillaryFileDescriptors() : null;
    }

    int getFramebufferHeight() {
        return this.mFramebufferHeight;
    }

    int getFramebufferWidth() {
        return this.mFramebufferWidth;
    }

    String getSystemName() {
        return this.mSystemName;
    }

    void connect() {
        File file = new File("/data/lxd/run/vncsrv.usk");
        LocalSocketAddress localSocketAddress = new LocalSocketAddress(file.getPath(), Namespace.FILESYSTEM);
        Log.i(TAG, "Connecting...");
        this.mKeepRetryingToConnect = true;
        int i = 0;
        while (i < 25 && this.mKeepRetryingToConnect) {
            if (file.exists()) {
                try {
                    this.mRfbSocket.connect(localSocketAddress);
                    if (this.mRfbSocket.isConnected()) {
                        break;
                    }
                } catch (IOException unused) {
                    Log.d(TAG, "Not connected to server!");
                }
            }
            try {
                Thread.sleep(1000);
                String str = TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("Reconnecting to server...");
                stringBuilder.append(i);
                Log.d(str, stringBuilder.toString());
                i++;
            } catch (Exception unused2) {
                Log.d(TAG, "Failed to put thread to sleep!");
            }
        }
        if (this.mRfbSocket.isConnected()) {
            initializeIoStream();
            readServerVersion();
            writeClientVersion();
            negotiateSecurity();
            return;
        }
        Log.d(TAG, "Failed to connect to server!");
        throw new Exception("Failed to connect to server!");
    }

    boolean isReady() {
        return this.mIsReady;
    }

    private void negotiateSecurity() {
        int readUnsignedByte = this.mInputStream.readUnsignedByte();
        if (readUnsignedByte == 0) {
            readAuthFailureReason();
            throw new Exception("Failed to read valid security type");
        }
        byte[] bArr = new byte[readUnsignedByte];
        readFully(bArr);
        int i = 0;
        int i2 = 0;
        int i3 = i2;
        int i4 = i3;
        while (i2 < readUnsignedByte) {
            if (bArr[i2] == (byte) 1) {
                i3 = 1;
            } else if (bArr[i2] == (byte) 2) {
                i4 = 1;
            }
            i2++;
        }
        if (i3 != 0) {
            i = 1;
        } else if (i4 != 0) {
            i = 2;
        }
        switch (i) {
            case 1:
                Log.i(TAG, "Perform NONE authentication");
                this.mOutputStream.write(i);
                break;
            case 2:
                Log.i(TAG, "Perform VNC AUTH authentication");
                this.mOutputStream.write(i);
                authenticate("secret");
                break;
            default:
                throw new LxdException("Invalid auth type");
        }
        readAuthResult();
    }

    private void authenticate(String str) {
        readFully(new byte[16]);
        if (str.length() > 8) {
            str = str.substring(0, 8);
        }
        int indexOf = str.indexOf(0);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        Object obj = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
        System.arraycopy(str.getBytes(), 0, obj, 0, str.length());
        this.mOutputStream.write(obj);
    }

    private void initializeIoStream() {
        this.mInputStream = new DataInputStream(new BufferedInputStream(this.mRfbSocket.getInputStream(), 16384));
        this.mOutputStream = this.mRfbSocket.getOutputStream();
    }

    private void readServerVersion() {
        byte[] bArr = new byte[12];
        readFully(bArr);
        if (bArr[0] != (byte) 82 || bArr[1] != (byte) 70 || bArr[2] != (byte) 66 || bArr[3] != (byte) 32 || bArr[4] < (byte) 48 || bArr[4] > (byte) 57 || bArr[5] < (byte) 48 || bArr[5] > (byte) 57 || bArr[6] < (byte) 48 || bArr[6] > (byte) 57 || bArr[7] != (byte) 46 || bArr[8] < (byte) 48 || bArr[8] > (byte) 57 || bArr[9] < (byte) 48 || bArr[9] > (byte) 57 || bArr[10] < (byte) 48 || bArr[10] > (byte) 57 || bArr[11] != (byte) 10 || (((bArr[4] - 48) * 100) + ((bArr[5] - 48) * 10)) + (bArr[6] - 48) < 3) {
            throw new Exception("Invalid server version received");
        }
    }

    private synchronized void writeClientVersion() {
        this.mOutputStream.write(CLIENT_VERSION.getBytes());
    }

    private void readAuthResult() {
        int readInt = this.mInputStream.readInt();
        StringBuilder stringBuilder;
        switch (readInt) {
            case 0:
                Log.d(TAG, "Authentication succeeded!");
                return;
            case 1:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Authentication : Failed - ");
                stringBuilder.append(readAuthFailureReason());
                throw new Exception(stringBuilder.toString());
            case 2:
                throw new Exception("Authentication : Too many");
            default:
                stringBuilder = new StringBuilder();
                stringBuilder.append("Authentication : Unknown result ");
                stringBuilder.append(readInt);
                throw new Exception(stringBuilder.toString());
        }
    }

    private String readAuthFailureReason() {
        byte[] bArr = new byte[this.mInputStream.readInt()];
        readFully(bArr);
        return new String(bArr);
    }

    void writeClientInit() {
        this.mOutputStream.write(0);
    }

    void readServerInit() {
        this.mFramebufferWidth = this.mInputStream.readUnsignedShort();
        this.mFramebufferHeight = this.mInputStream.readUnsignedShort();
        int readUnsignedByte = this.mInputStream.readUnsignedByte();
        int readUnsignedByte2 = this.mInputStream.readUnsignedByte();
        int readUnsignedByte3 = this.mInputStream.readUnsignedByte();
        int readUnsignedByte4 = this.mInputStream.readUnsignedByte();
        int readUnsignedShort = this.mInputStream.readUnsignedShort();
        int readUnsignedShort2 = this.mInputStream.readUnsignedShort();
        int readUnsignedShort3 = this.mInputStream.readUnsignedShort();
        int readUnsignedByte5 = this.mInputStream.readUnsignedByte();
        int readUnsignedByte6 = this.mInputStream.readUnsignedByte();
        int readUnsignedByte7 = this.mInputStream.readUnsignedByte();
        readFully(new byte[3]);
        byte[] bArr = new byte[this.mInputStream.readInt()];
        readFully(bArr);
        this.mSystemName = new String(bArr);
        this.mKeepRetryingToConnect = true;
        this.mIsReady = true;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pixel: ");
        stringBuilder.append(readUnsignedByte);
        stringBuilder.append(", depth: ");
        stringBuilder.append(readUnsignedByte2);
        stringBuilder.append(", bigEndian: ");
        stringBuilder.append(readUnsignedByte3);
        stringBuilder.append("trueColor: ");
        stringBuilder.append(readUnsignedByte4);
        stringBuilder.append(", redMax: ");
        stringBuilder.append(readUnsignedShort);
        stringBuilder.append(", greenMax: ");
        stringBuilder.append(readUnsignedShort2);
        stringBuilder.append(", blueMax: ");
        stringBuilder.append(readUnsignedShort3);
        stringBuilder.append(", redShift: ");
        stringBuilder.append(readUnsignedByte5);
        stringBuilder.append(", greenShift: ");
        stringBuilder.append(readUnsignedByte6);
        stringBuilder.append(", blueShift: ");
        stringBuilder.append(readUnsignedByte7);
        stringBuilder.append(", SystemName: ");
        stringBuilder.append(this.mSystemName);
        Log.i(str, stringBuilder.toString());
    }

    int readServerMessageType() {
        return this.mInputStream.readUnsignedByte();
    }

    int readFramebufferUpdate() {
        this.mInputStream.readByte();
        return this.mInputStream.readUnsignedShort();
    }

    int readFramebufferUpdateRectHdr() {
        this.mRectX = this.mInputStream.readUnsignedShort();
        this.mRectY = this.mInputStream.readUnsignedShort();
        this.mRectW = this.mInputStream.readUnsignedShort();
        this.mRectH = this.mInputStream.readUnsignedShort();
        this.mEncodingType = this.mInputStream.readInt();
        return this.mEncodingType;
    }

    String readServerCutText() {
        readFully(new byte[3]);
        byte[] bArr = new byte[this.mInputStream.readInt()];
        readFully(bArr);
        return new String(bArr);
    }

    synchronized void writeFramebufferUpdateRequest(int i, int i2) {
        this.mOutputStream.write(new byte[]{(byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((i2 >> 8) & 255), (byte) (i2 & 255)});
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003b A:{SYNTHETIC, Splitter: B:16:0x003b} */
    /* JADX WARNING: Missing block: B:26:0x009d, code:
            return;
     */
    /* JADX WARNING: Missing block: B:28:0x009f, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    synchronized void writeSetEncodings() {
        if (isReady() && this.mEncoding == -1) {
            int i;
            Log.d(TAG, "writeSetEncodings");
            this.mEncoding = TYPE_ENCODING_RICH_CURSOR;
            int[] iArr = new int[20];
            int i2 = 0;
            iArr[0] = this.mEncoding;
            if (1 == this.mEncodingCounts) {
                i = 0;
                while (i < 1) {
                    if (iArr[i] == this.mSavedEncodings[i]) {
                        i++;
                    }
                }
                i = 0;
                if (i != 0) {
                    StringBuilder stringBuilder;
                    try {
                        String str = TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("writeSetEncodings: ");
                        stringBuilder.append(1);
                        Log.d(str, stringBuilder.toString());
                        while (i2 < 1) {
                            str = TAG;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("encodings: [");
                            stringBuilder.append(i2);
                            stringBuilder.append("]: ");
                            stringBuilder.append(iArr[i2]);
                            Log.d(str, stringBuilder.toString());
                            i2++;
                        }
                        writeSetEncodings(iArr, 1);
                    } catch (Exception e) {
                        String str2 = TAG;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("e: ");
                        stringBuilder.append(e.toString());
                        Log.d(str2, stringBuilder.toString());
                        e.printStackTrace();
                    }
                    this.mSavedEncodings = iArr;
                    this.mEncodingCounts = 1;
                }
            }
            i = 1;
            if (i != 0) {
            }
        }
    }

    private synchronized void writeSetEncodings(int[] iArr, int i) {
        byte[] bArr = new byte[((i * 4) + 4)];
        int i2 = 0;
        bArr[0] = (byte) 2;
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        while (i2 < i) {
            int i3 = i2 * 4;
            bArr[i3 + 4] = (byte) ((iArr[i2] >> 24) & 255);
            bArr[i3 + 5] = (byte) ((iArr[i2] >> 16) & 255);
            bArr[i3 + 6] = (byte) ((iArr[i2] >> 8) & 255);
            bArr[i3 + 7] = (byte) (iArr[i2] & 255);
            i2++;
        }
        this.mOutputStream.write(bArr);
    }

    synchronized void writePointerEvent(int i, int i2, int i3, int i4) {
        this.mLengthOfEventBuffer = 0;
        writeModifierKeyEvents(i3);
        byte[] bArr = this.mEventBuffer;
        int i5 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i5 + 1;
        bArr[i5] = (byte) 5;
        bArr = this.mEventBuffer;
        i5 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i5 + 1;
        bArr[i5] = (byte) i4;
        bArr = this.mEventBuffer;
        i5 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i5 + 1;
        bArr[i5] = (byte) ((i >> 8) & 255);
        bArr = this.mEventBuffer;
        i5 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i5 + 1;
        bArr[i5] = (byte) (i & 255);
        byte[] bArr2 = this.mEventBuffer;
        i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) ((i2 >> 8) & 255);
        bArr2 = this.mEventBuffer;
        i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) (i2 & 255);
        if (i4 == 0) {
            writeModifierKeyEvents(0);
        }
        this.mOutputStream.write(this.mEventBuffer, 0, this.mLengthOfEventBuffer);
    }

    synchronized void writeKeyEvent(int i, int i2, boolean z) {
        this.mLengthOfEventBuffer = 0;
        if (z) {
            writeModifierKeyEvents(i2);
        }
        if (i != 0) {
            writeKeyEvent(i, z);
        }
        if (!z) {
            writeModifierKeyEvents(0);
        }
        this.mOutputStream.write(this.mEventBuffer, 0, this.mLengthOfEventBuffer);
    }

    private void writeKeyEvent(int i, boolean z) {
        byte[] bArr = this.mEventBuffer;
        int i2 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i2 + 1;
        bArr[i2] = (byte) 4;
        bArr = this.mEventBuffer;
        i2 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i2 + 1;
        bArr[i2] = (byte) z;
        byte[] bArr2 = this.mEventBuffer;
        int i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) 0;
        bArr2 = this.mEventBuffer;
        i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) 0;
        bArr2 = this.mEventBuffer;
        i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) ((i >> 24) & 255);
        bArr2 = this.mEventBuffer;
        i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) ((i >> 16) & 255);
        bArr2 = this.mEventBuffer;
        i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) ((i >> 8) & 255);
        bArr2 = this.mEventBuffer;
        i3 = this.mLengthOfEventBuffer;
        this.mLengthOfEventBuffer = i3 + 1;
        bArr2[i3] = (byte) (i & 255);
    }

    synchronized void sendCutText(String str) {
        int length = str.getBytes().length;
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sendCutText: ");
        stringBuilder.append(str);
        stringBuilder.append("length: ");
        stringBuilder.append(length);
        Log.d(str2, stringBuilder.toString());
        Object obj = new byte[(length + 8)];
        obj[0] = 6;
        obj[4] = (byte) ((length >> 24) & 255);
        obj[5] = (byte) ((length >> 16) & 255);
        obj[6] = (byte) ((length >> 8) & 255);
        obj[7] = (byte) (length & 255);
        System.arraycopy(str.getBytes(), 0, obj, 8, length);
        this.mOutputStream.write(obj);
    }

    private void readFully(byte[] bArr) {
        this.mInputStream.readFully(bArr, 0, bArr.length);
    }

    public boolean isTextInputType() {
        if (this.mRectX == 3 && this.mRectY == 8 && this.mRectW == 10 && this.mRectH == 18) {
            return true;
        }
        if (this.mRectX == 6 && this.mRectY == 16 && this.mRectW == 18 && this.mRectH == 36) {
            return true;
        }
        if (this.mRectX == 4 && this.mRectY == 8 && this.mRectW == 9 && this.mRectH == 16) {
            return true;
        }
        return false;
    }

    public PointerIcon readSetCursor() {
        if (this.mRectW * this.mRectH == 0) {
            Log.i(TAG, "readSetCursor: no buffer data");
            return PointerIcon.getSystemIcon(LxdApplication.a(), RECONNECT_INTERVAL);
        }
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("readSetCursor: ");
        stringBuilder.append(this.mRectX);
        stringBuilder.append(", ");
        stringBuilder.append(this.mRectY);
        stringBuilder.append(", ");
        stringBuilder.append(this.mRectW);
        stringBuilder.append(", ");
        stringBuilder.append(this.mRectH);
        Log.d(str, stringBuilder.toString());
        byte[] bArr = new byte[(((this.mRectW * this.mRectH) * 32) / 8)];
        byte[] bArr2 = new byte[(((this.mRectW + 7) / 8) * this.mRectH)];
        this.mInputStream.readFully(bArr);
        this.mInputStream.readFully(bArr2);
        byte[] bArr3 = new byte[((this.mRectW * this.mRectH) * 4)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
        int i = (this.mRectW + 7) / 8;
        wrap = (ByteBuffer) wrap.duplicate().mark();
        ByteBuffer byteBuffer = (ByteBuffer) ByteBuffer.wrap(bArr3).mark();
        for (int i2 = 0; i2 < this.mRectH; i2++) {
            for (int i3 = 0; i3 < this.mRectW; i3++) {
                if ((wrap2.get((i2 * i) + (i3 / 8)) & (1 << (7 - (i3 % 8)))) != 0) {
                    byteBuffer.put((byte) -1);
                } else {
                    byteBuffer.put((byte) 0);
                }
                rgbFromBuffer(byteBuffer, wrap.duplicate(), 1);
                wrap.position(wrap.position() + 4);
                byteBuffer.position(byteBuffer.reset().position() + 4).mark();
            }
        }
        return PointerIcon.create(getBitmapFromByteArray(bArr3), (float) this.mRectX, (float) this.mRectY);
    }

    private Bitmap getBitmapFromByteArray(byte[] bArr) {
        IntBuffer allocate = IntBuffer.allocate(this.mRectW * this.mRectH);
        allocate.put(ByteBuffer.wrap(bArr).asIntBuffer());
        Bitmap createBitmap = Bitmap.createBitmap(this.mRectW, this.mRectH, Config.ARGB_8888);
        createBitmap.setPixels(allocate.array(), 0, this.mRectW, 0, 0, this.mRectW, this.mRectH);
        return createBitmap;
    }

    private void rgbFromBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i) {
        rgbFromBuffer(byteBuffer, byteBuffer2, i, i, 1);
    }

    private void rgbFromBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, int i3) {
        int position = byteBuffer2.position() + 2;
        int position2 = byteBuffer2.position() + 1;
        int position3 = byteBuffer2.position() + 0;
        i2 = (i2 - i) * 4;
        while (true) {
            int i4 = i3 - 1;
            if (i3 > 0) {
                i3 = position;
                position = i;
                while (true) {
                    int i5 = position - 1;
                    if (position <= 0) {
                        break;
                    }
                    byteBuffer.put(byteBuffer2.get(i3));
                    byteBuffer.put(byteBuffer2.get(position2));
                    byteBuffer.put(byteBuffer2.get(position3));
                    i3 += 4;
                    position2 += 4;
                    position3 += 4;
                    position = i5;
                }
                position = i3 + i2;
                position2 += i2;
                position3 += i2;
                i3 = i4;
            } else {
                return;
            }
        }
    }

    public void setPixelFormat() {
        writeSetPixelFormat(32, 24, false, true, 255, 255, 255, 16, 8, 0, false);
    }

    private synchronized void writeSetPixelFormat(int i, int i2, boolean z, boolean z2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z3) {
        byte[] bArr = new byte[20];
        bArr[0] = (byte) 0;
        bArr[4] = (byte) i;
        bArr[5] = (byte) i2;
        bArr[6] = (byte) z;
        bArr[7] = (byte) z2;
        bArr[8] = (byte) ((i3 >> 8) & 255);
        bArr[9] = (byte) (i3 & 255);
        bArr[10] = (byte) ((i4 >> 8) & 255);
        bArr[11] = (byte) (i4 & 255);
        bArr[12] = (byte) ((i5 >> 8) & 255);
        bArr[13] = (byte) (i5 & 255);
        bArr[14] = (byte) i6;
        bArr[15] = (byte) i7;
        bArr[16] = (byte) i8;
        bArr[17] = (byte) z3;
        this.mOutputStream.write(bArr);
    }
}
