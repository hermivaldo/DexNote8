package com.samsung.android.lxd.processor.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.ActionMode.Callback2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.ICommonContext;
import com.samsung.android.lxd.processor.INetworkService.ICallback;
import com.samsung.android.lxd.processor.control.channel.ControlChannelFactory;
import com.samsung.android.lxd.processor.control.channel.ControlChannelListener;
import com.samsung.android.lxd.processor.control.channel.ControlChannelType;
import com.samsung.android.lxd.processor.control.channel.IControlChannel;
import com.samsung.android.lxd.processor.network.audio.INetworkAudio;
import com.samsung.android.lxd.processor.network.audio.NetworkAudioFactory;
import com.samsung.android.lxd.processor.network.audio.NetworkAudioType;
import com.samsung.android.lxd.processor.network.channel.INetworkChannel;
import com.samsung.android.lxd.processor.network.channel.NetworkChannelFactory;
import com.samsung.android.lxd.processor.network.channel.NetworkChannelType;
import com.samsung.android.lxd.processor.network.display.INetworkDisplay;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayContext;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayFactory;
import com.samsung.android.lxd.processor.network.display.NetworkDisplayType;
import com.samsung.android.lxd.processor.network.display.NetworkScreenType;
import com.samsung.android.lxd.processor.network.input.INetworkInput;
import com.samsung.android.lxd.processor.network.input.NetworkInputFactory;
import com.samsung.android.lxd.processor.network.input.NetworkInputType;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;

public class MainHandler extends Handler implements ICommonContext {
    private static final String TAG = "MainHandler";
    private IControlChannel mControlChannel = null;
    private INetworkAudio mNetworkAudio = null;
    private INetworkChannel mNetworkChannel = null;
    private INetworkDisplay mNetworkDisplay = null;
    private INetworkInput mNetworkInput = null;
    private ICallback mServiceCallback = null;

    public MainHandler(Looper looper) {
        super(looper);
    }

    public synchronized void handleMessage(Message message) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("handleMessage : ");
        stringBuilder.append(Integer.toHexString(message.what));
        Log.d(str, stringBuilder.toString());
        int i = message.what;
        switch (i) {
            case 1:
                handleOpenService(message);
                break;
            case 2:
                handleCloseService();
                break;
            case 3:
                handleOpenContainer(message);
                break;
            case 4:
                handleCloseContainer();
                break;
            case 5:
                handlePauseContainer();
                break;
            case 6:
                handleResumeContainer();
                break;
            default:
                switch (i) {
                    case 33:
                        handleStartContainer(message);
                        break;
                    case 34:
                        handleStopContainer();
                        break;
                    default:
                        switch (i) {
                            case 49:
                                handleKeyDown(message);
                                break;
                            case 50:
                                handleKeyUp(message);
                                break;
                            case 51:
                                handleTouchEvent(message);
                                break;
                            case 52:
                                handleGenericMotion(message);
                                break;
                            case 53:
                                handleSendCutText(message);
                                break;
                            case 54:
                                handleGetImageVersion(message);
                                break;
                            case 55:
                                handleGetImageMinSize(message);
                                break;
                            case 56:
                                handleResizeImage(message);
                                break;
                            case 57:
                                handleSDCardStatus(message);
                                break;
                            default:
                                switch (i) {
                                    case 64:
                                        handleGetDebugLog(message);
                                        break;
                                    case 65:
                                        handleRebaseImage(message);
                                        break;
                                    case 66:
                                        handleStartMonitoring(message);
                                        break;
                                    case 67:
                                        handleStopMonitoring(message);
                                        break;
                                }
                                break;
                        }
                }
        }
    }

    public Context getDisplayContext() {
        return ((View) this.mNetworkDisplay).getContext();
    }

    public synchronized InputConnection getInputConnection(View view, EditorInfo editorInfo) {
        return this.mNetworkChannel.getInputConnection(view, editorInfo);
    }

    public INetworkDisplay getLocalDisplay() {
        return this.mNetworkDisplay;
    }

    public synchronized int getLocalHeight() {
        return this.mNetworkDisplay.getHeight();
    }

    public synchronized int getLocalWidth() {
        return this.mNetworkDisplay.getWidth();
    }

    public void getLocationInWindow(int[] iArr) {
        this.mNetworkDisplay.getLocationInWindow(iArr);
    }

    public synchronized int getRemoteHeight() {
        return this.mNetworkChannel.getFramebufferHeight();
    }

    public synchronized int getRemoteWidth() {
        return this.mNetworkChannel.getFramebufferWidth();
    }

    public synchronized NetworkScreenType getScreenType() {
        return this.mNetworkDisplay.getScreenType();
    }

    public synchronized boolean isTextEditor() {
        return this.mNetworkChannel.isTextEditor();
    }

    public synchronized INetworkInput enableSoftInput(boolean z) {
        return this.mNetworkInput.enableSoftInput(z);
    }

    private void handleOpenService(Message message) {
        LxdMessage lxdMessage = (LxdMessage) message.obj;
        ControlChannelType controlChannelType = (ControlChannelType) lxdMessage.obj1;
        this.mServiceCallback = (ICallback) lxdMessage.callback;
        this.mControlChannel = ControlChannelFactory.getInstance(controlChannelType);
        if (this.mControlChannel.isSupportedVersion(Utils.getNstVersion())) {
            this.mControlChannel.setContext(this).openService(new ControlChannelListener() {
                public void onServiceConnected() {
                }

                public void onServiceOpened() {
                    MainHandler.this.mServiceCallback.onServiceOpened();
                }

                public void onServiceClosed() {
                    MainHandler.this.mServiceCallback.onServiceClosed();
                }

                public void onServiceError(Throwable th) {
                    MainHandler.this.mServiceCallback.onServiceError(th.getMessage());
                }

                public void onContainerOpened() {
                    MainHandler.this.mServiceCallback.onContainerOpened();
                }

                public void onContainerResumed() {
                    MainHandler.this.mServiceCallback.onContainerResumed();
                }

                public void onContainerPaused() {
                    MainHandler.this.mServiceCallback.onContainerPaused();
                }

                public void onContainerClosed() {
                    MainHandler.this.mServiceCallback.onContainerClosed();
                }

                public void onDebugLogReceived(String str, boolean z) {
                    MainHandler.this.mServiceCallback.onDebugLogReceived(str, z);
                }

                public void onImageVersionReceived(String str, int i) {
                    MainHandler.this.mServiceCallback.onImageVersionReceived(str, i);
                }

                public void onImageMinSizeReceived(String str, boolean z) {
                    MainHandler.this.mServiceCallback.onImageMinSizeReceived(str, z);
                }

                public void onImageSizeUpdated(String str, boolean z) {
                    MainHandler.this.mServiceCallback.onImageSizeUpdated(str, z);
                }

                public void onMemoryUsageReceived(String str, int i) {
                    MainHandler.this.mServiceCallback.onMemoryUsageReceived(str, i);
                }

                public void onImageRebased(String str, boolean z) {
                    MainHandler.this.mServiceCallback.onImageRebased(str, z);
                }
            });
        }
    }

    private void handleCloseService() {
        try {
            handleStopContainer();
            this.mControlChannel.closeService();
        } catch (NullPointerException e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("e: ");
            stringBuilder.append(e.toString());
            Log.d(str, stringBuilder.toString());
        }
    }

    private void handleOpenContainer(Message message) {
        LxdMessage lxdMessage = (LxdMessage) message.obj;
        final String str = (String) lxdMessage.obj1;
        String str2 = (String) lxdMessage.obj2;
        final ExecutionType executionType = (ExecutionType) lxdMessage.obj3;
        NetworkDisplayType networkDisplayType = (NetworkDisplayType) lxdMessage.obj4;
        NetworkAudioType networkAudioType = (NetworkAudioType) lxdMessage.obj5;
        NetworkScreenType networkScreenType = (NetworkScreenType) lxdMessage.obj6;
        NetworkChannelType networkChannelType = executionType.equals(ExecutionType.CLI) ? NetworkChannelType.PTY : NetworkChannelType.RFB;
        this.mNetworkInput = NetworkInputFactory.getInstance(executionType.equals(ExecutionType.CLI) ? NetworkInputType.PTY : NetworkInputType.RFB).setContext(this).init(new INetworkInput.ICallback() {
            public boolean onGenericMotion(MotionEvent motionEvent) {
                return MainHandler.this.mNetworkChannel.sendMouseEvent(motionEvent);
            }

            public boolean onKeyDown(int i, KeyEvent keyEvent) {
                return MainHandler.this.mNetworkChannel.sendKeyEvent(i, keyEvent);
            }

            public boolean onKeyUp(int i, KeyEvent keyEvent) {
                return MainHandler.this.mNetworkChannel.sendKeyEvent(i, keyEvent);
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                return MainHandler.this.mNetworkChannel.sendScaleEvent(scaleGestureDetector);
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                return MainHandler.this.mNetworkChannel.sendScaleBeginEvent(scaleGestureDetector);
            }

            public boolean onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                return MainHandler.this.mNetworkChannel.sendScaleEndEvent(scaleGestureDetector);
            }

            public boolean onTouchEvent(MotionEvent motionEvent) {
                return MainHandler.this.mNetworkChannel.sendMouseEvent(motionEvent);
            }

            public boolean onTouchEvent(MotionEvent motionEvent, boolean z) {
                return MainHandler.this.mNetworkChannel.sendMouseEvent(motionEvent, z);
            }

            public boolean onDown(MotionEvent motionEvent) {
                return MainHandler.this.mNetworkChannel.sendDownEvent(motionEvent);
            }

            public boolean onLongPress(MotionEvent motionEvent) {
                return MainHandler.this.mNetworkChannel.sendLongPressEvent(motionEvent);
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return MainHandler.this.mNetworkChannel.sendSingleTapUpEvent(motionEvent);
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                return MainHandler.this.mNetworkChannel.sendDoubleTapEvent(motionEvent);
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return MainHandler.this.mNetworkChannel.sendScrollEvent(motionEvent, motionEvent2, f, f2);
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return MainHandler.this.mNetworkChannel.sendFlingEvent(motionEvent, motionEvent2, f, f2);
            }

            public void onTextCut(String str) {
                MainHandler.this.mNetworkChannel.sendCutText(str);
            }
        });
        this.mNetworkDisplay = NetworkDisplayFactory.getInstance(networkDisplayType).setContext(this).setScreenType(networkScreenType).init(new INetworkDisplay.ICallback() {
            public void onInputConnectionReady() {
                MainHandler.this.mNetworkInput.notifyInputConnectionReady();
            }

            public void onSizeChanged(int i, int i2) {
                MainHandler.this.mNetworkChannel.notifyDisplaySizeChange(i, i2);
            }
        });
        this.mNetworkAudio = NetworkAudioFactory.getInstance(networkAudioType).setContext(this).init(new INetworkAudio.ICallback() {
            public void onAudioConnected(String str) {
                MainHandler.this.mServiceCallback.onAudioConnected(str);
            }

            public void onAudioDisconnected(String str) {
                MainHandler.this.mServiceCallback.onAudioDisconnected(str);
            }
        });
        this.mNetworkChannel = NetworkChannelFactory.getInstance(networkChannelType).setContext(this).setImageVersion(str2).init(new INetworkChannel.ICallback() {
            public void onInit(String str) {
                MainHandler.this.mControlChannel.setConfigId(str).setExecutionType(executionType).openContainer(str);
            }

            public void onTextCommitted(String str) {
                MainHandler.this.mServiceCallback.onTextCommitted(str);
            }

            public void onContainerStarted(String str) {
                MainHandler.this.mServiceCallback.onContainerStarted(str);
            }

            public void onContainerStopped() {
                MainHandler.this.mServiceCallback.onContainerStopped();
            }

            public void onContainerDraw(NetworkDisplayContext networkDisplayContext) {
                MainHandler.this.mNetworkDisplay.draw(networkDisplayContext);
            }

            public void onContainerError(Throwable th) {
                MainHandler.this.mServiceCallback.onContainerError(th.getMessage());
            }

            public void onCutTextReceived(String str) {
                MainHandler.this.mServiceCallback.onCutTextReceived(str);
            }

            public void onCursorUpdated(PointerIcon pointerIcon, boolean z) {
                MainHandler.this.mNetworkDisplay.updateCursorType(pointerIcon);
                MainHandler.this.mNetworkInput.updateCursorType(pointerIcon, z);
            }

            public void onMonitoringStarted() {
                MainHandler.this.mServiceCallback.onMonitoringStarted();
            }

            public void onMonitoringStopped() {
                MainHandler.this.mServiceCallback.onMonitoringStopped();
            }

            public void onMonitoringNotified(String str) {
                MainHandler.this.mServiceCallback.onMonitoringNotified(str);
            }

            public void onShowActionMode(Callback2 callback2) {
                MainHandler.this.mNetworkDisplay.showActionMode(callback2);
            }
        });
    }

    private void handleCloseContainer() {
        handleStopContainer();
        this.mControlChannel.closeContainer();
    }

    private void handlePauseContainer() {
        this.mNetworkDisplay.disableUpdate();
        this.mNetworkChannel.pause();
        this.mNetworkAudio.stop();
        this.mControlChannel.pauseContainer();
    }

    private void handleResumeContainer() {
        this.mNetworkDisplay = NetworkDisplayFactory.duplicate(this.mNetworkDisplay).start();
        this.mNetworkDisplay.enableUpdate();
        this.mNetworkDisplay.updateCursorType(this.mNetworkInput.getCursorInfo());
        this.mNetworkChannel.resume();
        this.mNetworkAudio.reStart();
        this.mControlChannel.resumeContainer();
    }

    private void handleStartContainer(Message message) {
        LxdMessage lxdMessage = (LxdMessage) message.obj;
        this.mNetworkDisplay.start();
        this.mNetworkChannel.start();
        this.mNetworkInput.start();
        this.mNetworkAudio.start();
    }

    private void handleStopContainer() {
        try {
            this.mNetworkAudio.stop();
            this.mNetworkDisplay.stop();
            this.mNetworkInput.stop();
            this.mNetworkChannel.stop();
        } catch (NullPointerException e) {
            String str = TAG;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("handleStopContainer: ");
            stringBuilder.append(e.toString());
            Log.i(str, stringBuilder.toString());
        }
    }

    private void handleStartMonitoring(Message message) {
        this.mNetworkChannel.startMonitoring();
    }

    private void handleStopMonitoring(Message message) {
        this.mNetworkChannel.stopMonitoring();
    }

    private void handleGetDebugLog(Message message) {
        this.mControlChannel.getDebugLog((String) ((LxdMessage) message.obj).obj1);
    }

    private void handleGetImageVersion(Message message) {
        this.mControlChannel.getImageVersion((String) ((LxdMessage) message.obj).obj1);
    }

    private void handleGetImageMinSize(Message message) {
        this.mControlChannel.getImageMinSize((String) ((LxdMessage) message.obj).obj1);
    }

    private void handleRebaseImage(Message message) {
        this.mControlChannel.rebaseImage((String) ((LxdMessage) message.obj).obj1);
    }

    private void handleResizeImage(Message message) {
        LxdMessage lxdMessage = (LxdMessage) message.obj;
        this.mControlChannel.changeImageSize((String) lxdMessage.obj1, ((Integer) lxdMessage.obj2).intValue(), ((Boolean) lxdMessage.obj3).booleanValue());
    }

    private void handleGenericMotion(Message message) {
        MotionEvent motionEvent = (MotionEvent) ((LxdMessage) message.obj).obj1;
        if (this.mNetworkInput != null && this.mNetworkChannel.isReady()) {
            this.mNetworkInput.handleGenericMotion(motionEvent);
        }
    }

    private void handleKeyDown(Message message) {
        LxdMessage lxdMessage = (LxdMessage) message.obj;
        int intValue = ((Integer) lxdMessage.obj1).intValue();
        KeyEvent keyEvent = (KeyEvent) lxdMessage.obj2;
        if (this.mNetworkInput != null && this.mNetworkChannel.isReady()) {
            this.mNetworkInput.handleKeyDown(intValue, keyEvent);
        }
    }

    private void handleKeyUp(Message message) {
        LxdMessage lxdMessage = (LxdMessage) message.obj;
        int intValue = ((Integer) lxdMessage.obj1).intValue();
        KeyEvent keyEvent = (KeyEvent) lxdMessage.obj2;
        if (this.mNetworkInput != null && this.mNetworkChannel.isReady()) {
            this.mNetworkInput.handleKeyUp(intValue, keyEvent);
        }
    }

    private void handleTouchEvent(Message message) {
        MotionEvent motionEvent = (MotionEvent) ((LxdMessage) message.obj).obj1;
        if (this.mNetworkInput != null && this.mNetworkChannel.isReady()) {
            this.mNetworkInput.handleTouchEvent(motionEvent);
        }
    }

    private void handleSendCutText(Message message) {
        String str = (String) ((LxdMessage) message.obj).obj1;
        if (this.mNetworkInput != null && this.mNetworkChannel.isReady()) {
            this.mNetworkInput.handleSendCutText(str);
        }
    }

    private void handleSDCardStatus(Message message) {
        LxdMessage lxdMessage = (LxdMessage) message.obj;
        String str = (String) lxdMessage.obj1;
        String str2 = (String) lxdMessage.obj2;
        if (this.mControlChannel != null) {
            this.mControlChannel.notifySdCardStatus(str, str2);
        }
    }
}
