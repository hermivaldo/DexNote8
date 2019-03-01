package com.samsung.android.lxd.processor.network.channel.rfb;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.ScaleGestureDetector;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.control.channel.ControlChannelFactory;
import com.samsung.android.lxd.processor.control.channel.ControlChannelListener;
import com.samsung.android.lxd.processor.control.channel.ControlChannelType;
import com.samsung.android.lxd.processor.control.channel.IControlChannel;
import com.samsung.android.lxd.processor.control.channel.socket.IpcMessage;
import com.samsung.android.lxd.processor.network.channel.INetworkChannel;
import com.samsung.android.lxd.processor.network.channel.NetworkChannel;
import com.samsung.android.lxd.processor.network.channel.NetworkChannelType;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.network.channel.rfb.IRfbNetworkChannel.ICallback;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;
import java.util.Map;

public class RfbNetworkChannel extends NetworkChannel implements IRfbNetworkChannel {
    private static final boolean DISABLE_SCALE_HORIZONTAL_SCROLL = true;
    private static final int MOUSE_BUTTON_BACK = 32;
    private static final int MOUSE_BUTTON_FORWARD = 64;
    private static final int MOUSE_BUTTON_LEFT = 1;
    private static final int MOUSE_BUTTON_MIDDLE = 2;
    private static final int MOUSE_BUTTON_NONE = 0;
    private static final int MOUSE_BUTTON_RIGHT = 4;
    private static final int MOUSE_BUTTON_SCROLL_DOWN = 16;
    private static final int MOUSE_BUTTON_SCROLL_UP = 8;
    private static final int SCALE_MIN_DISTANCE = 100;
    private static final int SCALE_TIME_INTERVAL = 1000;
    private static final int SCROLL_THRESHOLD_MOVE = 5;
    private static final String TAG = "RfbNetworkChannel";
    private IControlChannel mControlChannel = null;
    private final NetworkDisplayContext mDisplayContext = new NetworkDisplayContext();
    private boolean mFakeCtrl = false;
    private boolean mFakeShift = false;
    private ICallback mInnerListener = new DummyCallback(this, null);
    private Thread mInnerThread = null;
    private boolean mKeepConnected = DISABLE_SCALE_HORIZONTAL_SCROLL;
    private Map<Integer, Integer> mKeyCodeMap = new HashMap();
    private int mMouseX = 0;
    private int mMouseY = 0;
    private int mPointerMask = 0;
    private float mPreVScroll = 0.0f;
    private long mPrevScaleTime = 0;
    private float mPrevSpan = 0.0f;
    private RfbProtocol mProtocol = null;
    private RemoteContext mRemoteContext = new RemoteContext(this, null);

    private class MouseScrollRunnable implements Runnable {
        private int mScrollButton = 0;

        private MouseScrollRunnable() {
        }

        private int getScrollButton() {
            return this.mScrollButton;
        }

        private void setScrollButton(int i) {
            this.mScrollButton = i;
        }

        public void run() {
            try {
                RfbNetworkChannel.this.sendMouseEventInternal(RfbNetworkChannel.this.mMouseX, RfbNetworkChannel.this.mMouseY, 0, this.mScrollButton);
                RfbNetworkChannel.this.sendMouseEventInternal(RfbNetworkChannel.this.mMouseX, RfbNetworkChannel.this.mMouseY, 0, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class RemoteContext {
        private final int DEFAULT_FRAMEBUFFER_HEIGHT;
        private final int DEFAULT_FRAMEBUFFER_WIDTH;
        private MappedByteBuffer mBitmapBuffer;
        private int[] mBitmapData;
        private int mFramebufferHeight;
        private int mFramebufferWidth;

        /* synthetic */ RemoteContext(RfbNetworkChannel rfbNetworkChannel, AnonymousClass1 anonymousClass1) {
            this();
        }

        private RemoteContext() {
            this.DEFAULT_FRAMEBUFFER_WIDTH = 1920;
            this.DEFAULT_FRAMEBUFFER_HEIGHT = 1080;
            this.mFramebufferWidth = 1920;
            this.mFramebufferHeight = 1080;
            this.mBitmapBuffer = null;
            this.mBitmapData = new int[(this.mFramebufferWidth * this.mFramebufferHeight)];
        }

        private int getFrameBufferWidth() {
            return this.mFramebufferWidth;
        }

        private int getFrameBufferHeight() {
            return this.mFramebufferHeight;
        }

        private int[] readBitmapData() {
            this.mBitmapBuffer.asIntBuffer().get(this.mBitmapData);
            return this.mBitmapData;
        }

        private void init(MappedByteBuffer mappedByteBuffer) {
            if (!(this.mFramebufferWidth == RfbNetworkChannel.this.mProtocol.getFramebufferWidth() && this.mFramebufferHeight == RfbNetworkChannel.this.mProtocol.getFramebufferHeight())) {
                this.mFramebufferWidth = RfbNetworkChannel.this.mProtocol.getFramebufferWidth();
                this.mFramebufferHeight = RfbNetworkChannel.this.mProtocol.getFramebufferHeight();
                this.mBitmapData = new int[(this.mFramebufferWidth * this.mFramebufferHeight)];
            }
            this.mBitmapBuffer = mappedByteBuffer;
        }
    }

    private class DummyCallback implements ICallback {
        public void onConnect(MappedByteBuffer mappedByteBuffer) {
        }

        public void onCursorUpdated(PointerIcon pointerIcon, boolean z) {
        }

        public void onCutTextReceived(String str) {
        }

        public void onError(Throwable th) {
        }

        public void onReceive() {
        }

        private DummyCallback() {
        }

        /* synthetic */ DummyCallback(RfbNetworkChannel rfbNetworkChannel, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public boolean sendScaleBeginEvent(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    public boolean sendScaleEndEvent(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    public boolean sendScaleEvent(ScaleGestureDetector scaleGestureDetector) {
        return false;
    }

    public RfbNetworkChannel() {
        initKeyCodeMap();
    }

    public NetworkChannelType getChannelType() {
        return NetworkChannelType.RFB;
    }

    public int getFramebufferWidth() {
        return this.mProtocol.getFramebufferWidth();
    }

    public int getFramebufferHeight() {
        return this.mProtocol.getFramebufferHeight();
    }

    private void attachListener() {
        this.mInnerListener = new ICallback() {
            public void onConnect(MappedByteBuffer mappedByteBuffer) {
                RfbNetworkChannel.this.mRemoteContext.init(mappedByteBuffer);
                RfbNetworkChannel.this.mProtocol.writeFramebufferUpdateRequest(RfbNetworkChannel.this.mRemoteContext.getFrameBufferWidth(), RfbNetworkChannel.this.mRemoteContext.getFrameBufferHeight());
                RfbNetworkChannel.this.mListener.onContainerStarted(RfbNetworkChannel.this.getConnectionInfo());
            }

            public void onReceive() {
                RfbNetworkChannel.this.mListener.onContainerDraw(RfbNetworkChannel.this.mDisplayContext.setBitmapPixels(RfbNetworkChannel.this.mRemoteContext.readBitmapData()));
            }

            public void onCursorUpdated(PointerIcon pointerIcon, boolean z) {
                RfbNetworkChannel.this.mListener.onCursorUpdated(pointerIcon, z);
            }

            public void onCutTextReceived(String str) {
                String access$1200 = RfbNetworkChannel.TAG;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("onCutTextReceived: ");
                stringBuilder.append(str);
                Log.d(access$1200, stringBuilder.toString());
                RfbNetworkChannel.this.mListener.onCutTextReceived(str);
            }

            public void onError(Throwable th) {
                RfbNetworkChannel.this.mListener.onContainerError(th);
            }
        };
    }

    private void detachListener() {
        this.mInnerListener = new DummyCallback(this, null);
    }

    private boolean supportControlChannel() {
        boolean y = o.y();
        int imageVersionNumber = Utils.getImageVersionNumber(this.mImageVersion);
        boolean z = (!y || imageVersionNumber < 11) ? false : DISABLE_SCALE_HORIZONTAL_SCROLL;
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supportControlChannel [");
        stringBuilder.append(z);
        stringBuilder.append("] : ");
        stringBuilder.append("isMonitoringEnabled : ");
        stringBuilder.append(y);
        stringBuilder.append(" ");
        stringBuilder.append("imageVersion : ");
        stringBuilder.append(imageVersionNumber);
        Log.d(str, stringBuilder.toString());
        return z;
    }

    public INetworkChannel start() {
        super.start();
        if (supportControlChannel()) {
            startControlChannel();
        } else {
            startNetworkChannel();
        }
        return this;
    }

    public INetworkChannel init(INetworkChannel.ICallback iCallback) {
        super.init(iCallback);
        this.mListener.onInit("");
        return this;
    }

    public INetworkChannel stop() {
        super.stop();
        if (supportControlChannel()) {
            stopControlChannel();
        } else {
            stopNetworkChannel();
        }
        return this;
    }

    public INetworkChannel pause() {
        super.pause();
        if (supportControlChannel()) {
            pauseControlChannel();
        }
        return this;
    }

    public INetworkChannel resume() {
        super.resume();
        if (supportControlChannel()) {
            resumeControlChannel();
        }
        if (this.mListener != null) {
            Log.d(TAG, "call onContainerDraw");
            this.mListener.onContainerDraw(this.mDisplayContext.setBitmapPixels(this.mRemoteContext.readBitmapData()));
        }
        return this;
    }

    private void startNetworkChannel() {
        clear();
        attachListener();
        this.mKeepConnected = DISABLE_SCALE_HORIZONTAL_SCROLL;
        this.mInnerThread = new Thread() {
            public void run() {
                try {
                    RfbNetworkChannel.this.connect();
                    RfbNetworkChannel.this.initialize(RfbNetworkChannel.this.mInnerListener);
                    RfbNetworkChannel.this.process(RfbNetworkChannel.this.mInnerListener);
                } catch (Throwable th) {
                    RfbNetworkChannel.this.mInnerThread = null;
                }
                RfbNetworkChannel.this.mInnerThread = null;
            }
        };
        this.mInnerThread.start();
    }

    private void stopNetworkChannel() {
        clear();
        this.mListener.onContainerStopped();
    }

    private void startControlChannel() {
        this.mControlChannel = ControlChannelFactory.getInstance(ControlChannelType.CONTAINER);
        this.mControlChannel.setContext(this.mCommonContext).openService(new ControlChannelListener() {
            public void onServiceConnected() {
                Log.i(RfbNetworkChannel.TAG, "Control Channel Connected");
            }

            public void onServiceOpened() {
                Log.i(RfbNetworkChannel.TAG, "Control Channel Opened");
                RfbNetworkChannel.this.startNetworkChannel();
            }

            public void onServiceClosed() {
                Log.i(RfbNetworkChannel.TAG, "Control Channel Closed");
                RfbNetworkChannel.this.stopNetworkChannel();
            }

            public void onServiceError(Throwable th) {
                Log.i(RfbNetworkChannel.TAG, "Control Channel Error");
                RfbNetworkChannel.this.mListener.onContainerError(th);
            }

            public void onMonitoringStarted() {
                RfbNetworkChannel.this.mListener.onMonitoringStarted();
            }

            public void onMonitoringStopped() {
                RfbNetworkChannel.this.mListener.onMonitoringStopped();
            }

            public void onMonitoringNotified(String str) {
                RfbNetworkChannel.this.mListener.onMonitoringNotified(str);
            }
        });
    }

    private void stopControlChannel() {
        this.mControlChannel.closeService();
    }

    private void pauseControlChannel() {
        this.mControlChannel.pauseService();
    }

    private void resumeControlChannel() {
        this.mControlChannel.resumeService();
    }

    private void clear() {
        this.mKeepConnected = false;
        if (this.mProtocol != null) {
            this.mProtocol.close();
        }
        try {
            if (this.mInnerThread != null) {
                Log.i(TAG, "before join");
                this.mInnerThread.join(300);
                Log.i(TAG, "after join");
                this.mInnerThread = null;
            }
        } catch (Exception e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("exception during clear");
            stringBuilder.append(e.toString());
            Log.i(str, stringBuilder.toString());
            e.printStackTrace();
        }
        detachListener();
    }

    private void connect() {
        this.mProtocol = new RfbProtocol();
        this.mProtocol.connect();
        Log.i(TAG, "Connected to server");
    }

    private MappedByteBuffer getRemoteFrameBuffer() {
        FileDescriptor[] ancillaryFileDescriptors = this.mProtocol.getAncillaryFileDescriptors();
        if (ancillaryFileDescriptors == null) {
            Log.e(TAG, "getAncillaryFileDescriptors failed");
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(ancillaryFileDescriptors[0]);
        MappedByteBuffer map = fileInputStream.getChannel().map(MapMode.READ_ONLY, 0, fileInputStream.getChannel().size());
        map.order(ByteOrder.LITTLE_ENDIAN);
        fileInputStream.close();
        return map;
    }

    private void initialize(ICallback iCallback) {
        this.mProtocol.writeClientInit();
        MappedByteBuffer remoteFrameBuffer = getRemoteFrameBuffer();
        if (remoteFrameBuffer == null) {
            throw new LxdException("getRemoteFrameBuffer() failed");
        }
        this.mProtocol.readServerInit();
        this.mProtocol.setPixelFormat();
        iCallback.onConnect(remoteFrameBuffer);
    }

    private void initKeyCodeMap() {
        this.mKeyCodeMap.put(Integer.valueOf(0), Integer.valueOf(16777215));
        this.mKeyCodeMap.put(Integer.valueOf(3), Integer.valueOf(65360));
        this.mKeyCodeMap.put(Integer.valueOf(4), Integer.valueOf(65307));
        this.mKeyCodeMap.put(Integer.valueOf(7), Integer.valueOf(48));
        this.mKeyCodeMap.put(Integer.valueOf(8), Integer.valueOf(49));
        this.mKeyCodeMap.put(Integer.valueOf(9), Integer.valueOf(50));
        this.mKeyCodeMap.put(Integer.valueOf(10), Integer.valueOf(51));
        this.mKeyCodeMap.put(Integer.valueOf(11), Integer.valueOf(52));
        this.mKeyCodeMap.put(Integer.valueOf(12), Integer.valueOf(53));
        this.mKeyCodeMap.put(Integer.valueOf(13), Integer.valueOf(54));
        this.mKeyCodeMap.put(Integer.valueOf(14), Integer.valueOf(55));
        this.mKeyCodeMap.put(Integer.valueOf(15), Integer.valueOf(56));
        this.mKeyCodeMap.put(Integer.valueOf(16), Integer.valueOf(57));
        this.mKeyCodeMap.put(Integer.valueOf(19), Integer.valueOf(65362));
        this.mKeyCodeMap.put(Integer.valueOf(20), Integer.valueOf(65364));
        this.mKeyCodeMap.put(Integer.valueOf(21), Integer.valueOf(65361));
        this.mKeyCodeMap.put(Integer.valueOf(22), Integer.valueOf(65363));
        this.mKeyCodeMap.put(Integer.valueOf(23), Integer.valueOf(65293));
        this.mKeyCodeMap.put(Integer.valueOf(24), Integer.valueOf(269025043));
        this.mKeyCodeMap.put(Integer.valueOf(25), Integer.valueOf(269025041));
        this.mKeyCodeMap.put(Integer.valueOf(29), Integer.valueOf(97));
        this.mKeyCodeMap.put(Integer.valueOf(30), Integer.valueOf(98));
        this.mKeyCodeMap.put(Integer.valueOf(31), Integer.valueOf(99));
        this.mKeyCodeMap.put(Integer.valueOf(32), Integer.valueOf(100));
        this.mKeyCodeMap.put(Integer.valueOf(33), Integer.valueOf(101));
        this.mKeyCodeMap.put(Integer.valueOf(34), Integer.valueOf(102));
        this.mKeyCodeMap.put(Integer.valueOf(35), Integer.valueOf(103));
        this.mKeyCodeMap.put(Integer.valueOf(36), Integer.valueOf(104));
        this.mKeyCodeMap.put(Integer.valueOf(37), Integer.valueOf(105));
        this.mKeyCodeMap.put(Integer.valueOf(38), Integer.valueOf(106));
        this.mKeyCodeMap.put(Integer.valueOf(39), Integer.valueOf(107));
        this.mKeyCodeMap.put(Integer.valueOf(40), Integer.valueOf(108));
        this.mKeyCodeMap.put(Integer.valueOf(41), Integer.valueOf(109));
        this.mKeyCodeMap.put(Integer.valueOf(42), Integer.valueOf(110));
        this.mKeyCodeMap.put(Integer.valueOf(43), Integer.valueOf(111));
        this.mKeyCodeMap.put(Integer.valueOf(44), Integer.valueOf(112));
        this.mKeyCodeMap.put(Integer.valueOf(45), Integer.valueOf(113));
        this.mKeyCodeMap.put(Integer.valueOf(46), Integer.valueOf(114));
        this.mKeyCodeMap.put(Integer.valueOf(47), Integer.valueOf(115));
        this.mKeyCodeMap.put(Integer.valueOf(48), Integer.valueOf(116));
        this.mKeyCodeMap.put(Integer.valueOf(49), Integer.valueOf(117));
        this.mKeyCodeMap.put(Integer.valueOf(50), Integer.valueOf(118));
        this.mKeyCodeMap.put(Integer.valueOf(51), Integer.valueOf(119));
        this.mKeyCodeMap.put(Integer.valueOf(52), Integer.valueOf(120));
        this.mKeyCodeMap.put(Integer.valueOf(53), Integer.valueOf(KeycodeConstants.KEYCODE_BREAK));
        this.mKeyCodeMap.put(Integer.valueOf(54), Integer.valueOf(KeycodeConstants.KEYCODE_MOVE_HOME));
        this.mKeyCodeMap.put(Integer.valueOf(55), Integer.valueOf(44));
        this.mKeyCodeMap.put(Integer.valueOf(56), Integer.valueOf(46));
        this.mKeyCodeMap.put(Integer.valueOf(57), Integer.valueOf(65513));
        this.mKeyCodeMap.put(Integer.valueOf(58), Integer.valueOf(65514));
        this.mKeyCodeMap.put(Integer.valueOf(59), Integer.valueOf(65505));
        this.mKeyCodeMap.put(Integer.valueOf(60), Integer.valueOf(65506));
        this.mKeyCodeMap.put(Integer.valueOf(61), Integer.valueOf(65289));
        this.mKeyCodeMap.put(Integer.valueOf(62), Integer.valueOf(32));
        this.mKeyCodeMap.put(Integer.valueOf(64), Integer.valueOf(269025117));
        this.mKeyCodeMap.put(Integer.valueOf(65), Integer.valueOf(269025049));
        this.mKeyCodeMap.put(Integer.valueOf(66), Integer.valueOf(65293));
        this.mKeyCodeMap.put(Integer.valueOf(67), Integer.valueOf(65288));
        this.mKeyCodeMap.put(Integer.valueOf(68), Integer.valueOf(96));
        this.mKeyCodeMap.put(Integer.valueOf(69), Integer.valueOf(45));
        this.mKeyCodeMap.put(Integer.valueOf(70), Integer.valueOf(61));
        this.mKeyCodeMap.put(Integer.valueOf(71), Integer.valueOf(91));
        this.mKeyCodeMap.put(Integer.valueOf(72), Integer.valueOf(93));
        this.mKeyCodeMap.put(Integer.valueOf(73), Integer.valueOf(92));
        this.mKeyCodeMap.put(Integer.valueOf(74), Integer.valueOf(59));
        this.mKeyCodeMap.put(Integer.valueOf(75), Integer.valueOf(39));
        this.mKeyCodeMap.put(Integer.valueOf(76), Integer.valueOf(47));
        this.mKeyCodeMap.put(Integer.valueOf(77), Integer.valueOf(64));
        this.mKeyCodeMap.put(Integer.valueOf(78), Integer.valueOf(35));
        this.mKeyCodeMap.put(Integer.valueOf(81), Integer.valueOf(65451));
        this.mKeyCodeMap.put(Integer.valueOf(82), Integer.valueOf(65383));
        this.mKeyCodeMap.put(Integer.valueOf(84), Integer.valueOf(269025051));
        this.mKeyCodeMap.put(Integer.valueOf(86), Integer.valueOf(269025045));
        this.mKeyCodeMap.put(Integer.valueOf(87), Integer.valueOf(269025047));
        this.mKeyCodeMap.put(Integer.valueOf(88), Integer.valueOf(269025046));
        this.mKeyCodeMap.put(Integer.valueOf(89), Integer.valueOf(269025086));
        this.mKeyCodeMap.put(Integer.valueOf(90), Integer.valueOf(269025175));
        this.mKeyCodeMap.put(Integer.valueOf(91), Integer.valueOf(269025042));
        this.mKeyCodeMap.put(Integer.valueOf(92), Integer.valueOf(65365));
        this.mKeyCodeMap.put(Integer.valueOf(93), Integer.valueOf(65366));
        this.mKeyCodeMap.put(Integer.valueOf(111), Integer.valueOf(65307));
        this.mKeyCodeMap.put(Integer.valueOf(112), Integer.valueOf(65535));
        this.mKeyCodeMap.put(Integer.valueOf(113), Integer.valueOf(65507));
        this.mKeyCodeMap.put(Integer.valueOf(114), Integer.valueOf(65508));
        this.mKeyCodeMap.put(Integer.valueOf(115), Integer.valueOf(65509));
        this.mKeyCodeMap.put(Integer.valueOf(116), Integer.valueOf(65300));
        this.mKeyCodeMap.put(Integer.valueOf(117), Integer.valueOf(65515));
        this.mKeyCodeMap.put(Integer.valueOf(118), Integer.valueOf(65516));
        this.mKeyCodeMap.put(Integer.valueOf(119), Integer.valueOf(2294));
        this.mKeyCodeMap.put(Integer.valueOf(120), Integer.valueOf(65377));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_BREAK), Integer.valueOf(65387));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_MOVE_HOME), Integer.valueOf(65360));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_MOVE_END), Integer.valueOf(65367));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_INSERT), Integer.valueOf(65379));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_FORWARD), Integer.valueOf(269025063));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_MEDIA_PLAY), Integer.valueOf(269025044));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_MEDIA_PAUSE), Integer.valueOf(269025073));
        this.mKeyCodeMap.put(Integer.valueOf(128), Integer.valueOf(269025110));
        this.mKeyCodeMap.put(Integer.valueOf(129), Integer.valueOf(269025068));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_MEDIA_RECORD), Integer.valueOf(269025052));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F1), Integer.valueOf(65470));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F2), Integer.valueOf(65471));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F3), Integer.valueOf(65472));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F4), Integer.valueOf(65473));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F5), Integer.valueOf(65474));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F6), Integer.valueOf(65475));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F7), Integer.valueOf(65476));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F8), Integer.valueOf(65477));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F9), Integer.valueOf(65478));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F10), Integer.valueOf(65479));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F11), Integer.valueOf(65480));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_F12), Integer.valueOf(65481));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUM_LOCK), Integer.valueOf(65407));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_0), Integer.valueOf(65456));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_1), Integer.valueOf(65457));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_2), Integer.valueOf(65458));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_3), Integer.valueOf(65459));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_4), Integer.valueOf(65460));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_5), Integer.valueOf(65461));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_6), Integer.valueOf(65462));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_7), Integer.valueOf(65463));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_8), Integer.valueOf(65464));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_9), Integer.valueOf(65465));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_DIVIDE), Integer.valueOf(65455));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_MULTIPLY), Integer.valueOf(65450));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_SUBTRACT), Integer.valueOf(65453));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_ADD), Integer.valueOf(65451));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_DOT), Integer.valueOf(65454));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_ENTER), Integer.valueOf(65421));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_NUMPAD_EQUALS), Integer.valueOf(65469));
        this.mKeyCodeMap.put(Integer.valueOf(KeycodeConstants.KEYCODE_VOLUME_MUTE), Integer.valueOf(269025042));
        this.mKeyCodeMap.put(Integer.valueOf(207), Integer.valueOf(269025106));
        this.mKeyCodeMap.put(Integer.valueOf(IpcMessage.STATUS_TYPE_BASE_RES_2), Integer.valueOf(269025056));
        this.mKeyCodeMap.put(Integer.valueOf(209), Integer.valueOf(269025170));
        this.mKeyCodeMap.put(Integer.valueOf(210), Integer.valueOf(269025053));
        this.mKeyCodeMap.put(Integer.valueOf(220), Integer.valueOf(269025027));
        this.mKeyCodeMap.put(Integer.valueOf(221), Integer.valueOf(269025026));
        this.mKeyCodeMap.put(Integer.valueOf(265), Integer.valueOf(269025070));
    }

    private void process(ICallback iCallback) {
        while (this.mKeepConnected) {
            StringBuilder stringBuilder;
            try {
                int readServerMessageType = this.mProtocol.readServerMessageType();
                if (readServerMessageType != 0) {
                    switch (readServerMessageType) {
                        case 2:
                            break;
                        case 3:
                            String readServerCutText = this.mProtocol.readServerCutText();
                            if (readServerCutText != null && readServerCutText.length() > 0) {
                                iCallback.onCutTextReceived(readServerCutText);
                                break;
                            }
                        case 4:
                            iCallback.onReceive();
                            this.mProtocol.writeSetEncodings();
                            break;
                        default:
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Unknown RFB message type ");
                            stringBuilder.append(readServerMessageType);
                            throw new LxdException(stringBuilder.toString());
                    }
                }
                readServerMessageType = this.mProtocol.readFramebufferUpdate();
                for (int i = 0; i < readServerMessageType; i++) {
                    int readFramebufferUpdateRectHdr = this.mProtocol.readFramebufferUpdateRectHdr();
                    String str = TAG;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("readFramebufferUpdateRectHdr: ");
                    stringBuilder2.append(readFramebufferUpdateRectHdr);
                    Log.d(str, stringBuilder2.toString());
                    if (readFramebufferUpdateRectHdr == -239) {
                        iCallback.onCursorUpdated(this.mProtocol.readSetCursor(), this.mProtocol.isTextInputType());
                    }
                }
            } catch (Exception e) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("process : ");
                stringBuilder.append(e.toString());
                throw new LxdException(stringBuilder.toString());
            } catch (Throwable th) {
                Log.d(TAG, "Closing connection");
                this.mProtocol.close();
            }
        }
        Log.d(TAG, "Closing connection");
        this.mProtocol.close();
    }

    public String getSystemName() {
        return this.mProtocol.getSystemName();
    }

    public boolean isReady() {
        return (this.mProtocol == null || !this.mProtocol.isReady()) ? false : DISABLE_SCALE_HORIZONTAL_SCROLL;
    }

    public boolean sendMouseEvent(MotionEvent motionEvent) {
        return sendMouseEvent(motionEvent, false);
    }

    public boolean sendMouseEvent(MotionEvent motionEvent, boolean z) {
        if (motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue < 0.0f) {
                try {
                    this.mPointerMask = 16;
                    this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), this.mPointerMask);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (axisValue > 0.0f) {
                this.mPointerMask = 8;
                this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), this.mPointerMask);
            }
            return DISABLE_SCALE_HORIZONTAL_SCROLL;
        } else if (Utils.isTouchPad(motionEvent) && Utils.isMultiTouch(motionEvent)) {
            if (motionEvent.getAction() == 0) {
                this.mPreVScroll = motionEvent.getY();
            }
            if (motionEvent.getAction() == 2) {
                try {
                    if (this.mPreVScroll - motionEvent.getY() > 5.0f) {
                        this.mPointerMask = 16;
                        this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), this.mPointerMask);
                        this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), 0);
                    }
                    if (this.mPreVScroll - motionEvent.getY() < 5.0f) {
                        this.mPointerMask = 8;
                        this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), this.mPointerMask);
                        this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), 0);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.mPreVScroll = motionEvent.getY();
            }
            return DISABLE_SCALE_HORIZONTAL_SCROLL;
        } else {
            this.mPointerMask = convertMouseButtons(motionEvent, z);
            return sendMouseEventInternal((int) motionEvent.getX(), (int) motionEvent.getY(), motionEvent.getMetaState());
        }
    }

    public boolean sendScrollEvent(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        try {
            if (this.mFakeCtrl) {
                return DISABLE_SCALE_HORIZONTAL_SCROLL;
            }
            Math.abs(f);
            if (Math.abs(f2) <= 5.0f) {
                f2 = 0.0f;
            }
            int i = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            if (i != 0) {
                boolean z = i > 0 ? DISABLE_SCALE_HORIZONTAL_SCROLL : false;
                checkSendScroll(motionEvent2, false, z);
                this.mPointerMask = z ? 16 : 8;
                this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), this.mPointerMask);
                this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, motionEvent.getMetaState(), 0);
            } else {
                this.mPointerMask = 0;
            }
            return DISABLE_SCALE_HORIZONTAL_SCROLL;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkSendScroll(MotionEvent motionEvent, boolean z, boolean z2) {
        if (motionEvent.getHistorySize() < 2) {
            return false;
        }
        for (int i = 1; i < motionEvent.getHistorySize(); i++) {
            for (int i2 = 0; i2 < motionEvent.getPointerCount(); i2++) {
                if (z) {
                    if (motionEvent.getHistoricalX(i2, z2 ? i : i - 1) >= motionEvent.getHistoricalX(i2, z2 ? i - 1 : i)) {
                        return false;
                    }
                } else {
                    if (motionEvent.getHistoricalY(i2, z2 ? i : i - 1) >= motionEvent.getHistoricalY(i2, z2 ? i - 1 : i)) {
                        return false;
                    }
                }
            }
        }
        return DISABLE_SCALE_HORIZONTAL_SCROLL;
    }

    public boolean sendKeyEvent(int i, KeyEvent keyEvent) {
        boolean z = false;
        if (this.mProtocol == null || !this.mProtocol.isReady()) {
            return false;
        }
        try {
            if (keyEvent.getAction() != 2) {
                int unicodeChar = keyEvent.getUnicodeChar();
                if (unicodeChar < 32) {
                    unicodeChar = convertKeyCode(i);
                }
                RfbProtocol rfbProtocol = this.mProtocol;
                i = keyEvent.getMetaState();
                if (keyEvent.getAction() == 0) {
                    z = DISABLE_SCALE_HORIZONTAL_SCROLL;
                }
                rfbProtocol.writeKeyEvent(unicodeChar, i, z);
            } else {
                i = RfbKeySymbolsUtils.getInstance().getRfbKeySymbol(keyEvent);
                if (i != 0) {
                    this.mProtocol.writeKeyEvent(i, keyEvent.getMetaState(), DISABLE_SCALE_HORIZONTAL_SCROLL);
                    this.mProtocol.writeKeyEvent(i, keyEvent.getMetaState(), false);
                }
            }
        } catch (Exception e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("sendKey: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
        return DISABLE_SCALE_HORIZONTAL_SCROLL;
    }

    public void sendCutText(String str) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sendCutText: ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        try {
            this.mProtocol.sendCutText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startMonitoring() {
        if (supportControlChannel()) {
            this.mControlChannel.startMonitoring();
        }
    }

    public void stopMonitoring() {
        if (supportControlChannel()) {
            this.mControlChannel.stopMonitoring();
        }
    }

    private void pressFakeCtrl() {
        if (!this.mFakeCtrl) {
            this.mFakeCtrl = DISABLE_SCALE_HORIZONTAL_SCROLL;
            KeyEvent keyEvent = new KeyEvent(0, 0, 0, 113, 0, 12288);
            sendKeyEvent(keyEvent.getKeyCode(), keyEvent);
        }
    }

    private void releaseFakeCtrl() {
        if (this.mFakeCtrl) {
            this.mFakeCtrl = false;
            KeyEvent keyEvent = new KeyEvent(0, 0, 1, 113, 0, 0);
            sendKeyEvent(keyEvent.getKeyCode(), keyEvent);
        }
    }

    private void pressFakeShift() {
        if (!this.mFakeShift) {
            this.mFakeShift = DISABLE_SCALE_HORIZONTAL_SCROLL;
            KeyEvent keyEvent = new KeyEvent(0, 0, 0, 59, 0, 65);
            sendKeyEvent(keyEvent.getKeyCode(), keyEvent);
        }
    }

    private void releaseFakeShift() {
        if (this.mFakeShift) {
            this.mFakeShift = false;
            KeyEvent keyEvent = new KeyEvent(0, 0, 1, 59, 0, 0);
            sendKeyEvent(keyEvent.getKeyCode(), keyEvent);
        }
    }

    private boolean sendMouseEventInternal(int i, int i2, int i3) {
        if (this.mProtocol == null || !this.mProtocol.isReady()) {
            return false;
        }
        this.mMouseX = i;
        this.mMouseY = i2;
        if (this.mMouseX < 0) {
            this.mMouseX = 0;
        } else if (this.mMouseX >= this.mProtocol.getFramebufferWidth()) {
            this.mMouseX = this.mProtocol.getFramebufferWidth() - 1;
        }
        if (this.mMouseY < 0) {
            this.mMouseY = 0;
        } else if (this.mMouseY >= this.mProtocol.getFramebufferHeight()) {
            this.mMouseY = this.mProtocol.getFramebufferHeight() - 1;
        }
        try {
            this.mProtocol.writePointerEvent(this.mMouseX, this.mMouseY, i3, this.mPointerMask);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DISABLE_SCALE_HORIZONTAL_SCROLL;
    }

    private void sendMouseEventInternal(int i, int i2, int i3, int i4) {
        if (this.mProtocol != null && this.mProtocol.isReady()) {
            this.mProtocol.writePointerEvent(i, i2, i3, i4);
        }
    }

    private int convertKeyCode(int i) {
        return this.mKeyCodeMap.containsKey(Integer.valueOf(i)) ? ((Integer) this.mKeyCodeMap.get(Integer.valueOf(i))).intValue() : 0;
    }

    private int convertMouseButtons(MotionEvent motionEvent, boolean z) {
        int buttonState = motionEvent.getButtonState();
        int i = 0;
        int i2 = 0;
        while (i < 31) {
            int i3 = 1 << i;
            if ((buttonState & i3) != 0) {
                if (i3 == 4) {
                    i2 |= 2;
                } else if (i3 == 8) {
                    i2 |= 32;
                } else if (i3 != 16) {
                    switch (i3) {
                        case 1:
                            i2 |= 1;
                            break;
                        case 2:
                            i2 |= 4;
                            break;
                        default:
                            break;
                    }
                } else {
                    i2 |= 64;
                }
            }
            i++;
        }
        if (!Utils.isTouchPad(motionEvent)) {
            return i2;
        }
        if ((motionEvent.getAction() == 0 || motionEvent.getAction() == 2) && motionEvent.getButtonState() == 0) {
            return z ? 4 : 1;
        } else {
            return i2;
        }
    }
}
