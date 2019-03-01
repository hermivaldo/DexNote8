package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.HashMap;
import java.util.Map;

class TerminalKeyListener {
    public static final int KEYCODE_OFFSET = 10485760;
    private static final int KEYMOD_ALT = Integer.MIN_VALUE;
    private static final int KEYMOD_CTRL = 1073741824;
    private static final int KEYMOD_SCAN = 268435456;
    private static final int KEYMOD_SHIFT = 536870912;
    private static final boolean SUPPORT_8_BIT_META = false;
    private static final String TAG = "TerminalKeyListener";
    private static Map<Integer, String> mKeyMap;
    private boolean mAltSendsEsc;
    private String[] mAppKeyCodes = new String[256];
    private int mCombiningAccent;
    private String[] mKeyCodes = new String[256];
    private TerminalSession mTerminalSession;
    private boolean mVirtualControlKey;
    private boolean mVirtualFunctionKey;

    public void keyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case 57:
            case 58:
            case 59:
            case 60:
                return;
            default:
                switch (i) {
                }
                return;
        }
    }

    public int mapControlChar(boolean z, boolean z2, int i) {
        if (z) {
            if (i >= 97 && i <= KeycodeConstants.KEYCODE_MOVE_HOME) {
                return (char) ((i - 97) + 1);
            }
            if (i >= 65 && i <= 90) {
                return (char) ((i - 65) + 1);
            }
            if (i == 32 || i == 50) {
                return 0;
            }
            if (!(i == 91 || i == 51)) {
                if (!(i == 92 || i == 52)) {
                    return (i == 93 || i == 53) ? 29 : (i == 94 || i == 54) ? 30 : (i == 95 || i == 55) ? 31 : i == 56 ? KeycodeConstants.KEYCODE_MEDIA_PAUSE : i == 57 ? 10485901 : i == 48 ? 10485902 : i;
                }
            }
            return 27;
        } else if (!z2) {
            return i;
        } else {
            if (i == 119 || i == 87) {
                return 10485779;
            }
            if (i == 97 || i == 65) {
                return 10485781;
            }
            if (i == 115 || i == 83) {
                return 10485780;
            }
            if (i == 100 || i == 68) {
                return 10485782;
            }
            if (i == 112 || i == 80) {
                return 10485852;
            }
            if (i == 110 || i == 78) {
                return 10485853;
            }
            if (i == 116 || i == 84) {
                return 10485821;
            }
            if (i == 108 || i == 76) {
                return KeycodeConstants.KEYCODE_INSERT;
            }
            if (i == 117 || i == 85) {
                return 95;
            }
            if (!(i == 101 || i == 69)) {
                if (i != 46) {
                    return (i <= 48 || i > 57) ? i == 48 ? 10485900 : (i == 105 || i == 73) ? 10485884 : (i == 120 || i == 88) ? 10485872 : (i == 104 || i == 72) ? 10485882 : (i == 102 || i == 70) ? 10485883 : i : ((i - 48) + 10485891) - 1;
                }
            }
            return 27;
        }
        return 28;
    }

    public void onResume() {
    }

    private void initKeyCodes() {
        mKeyMap = new HashMap();
        mKeyMap.put(Integer.valueOf(536870933), "\u001b[1;2D");
        mKeyMap.put(Integer.valueOf(-2147483627), "\u001b[1;3D");
        mKeyMap.put(Integer.valueOf(-1610612715), "\u001b[1;4D");
        mKeyMap.put(Integer.valueOf(1073741845), "\u001b[1;5D");
        mKeyMap.put(Integer.valueOf(1610612757), "\u001b[1;6D");
        mKeyMap.put(Integer.valueOf(-1073741803), "\u001b[1;7D");
        mKeyMap.put(Integer.valueOf(-536870891), "\u001b[1;8D");
        mKeyMap.put(Integer.valueOf(536870934), "\u001b[1;2C");
        mKeyMap.put(Integer.valueOf(-2147483626), "\u001b[1;3C");
        mKeyMap.put(Integer.valueOf(-1610612714), "\u001b[1;4C");
        mKeyMap.put(Integer.valueOf(1073741846), "\u001b[1;5C");
        mKeyMap.put(Integer.valueOf(1610612758), "\u001b[1;6C");
        mKeyMap.put(Integer.valueOf(-1073741802), "\u001b[1;7C");
        mKeyMap.put(Integer.valueOf(-536870890), "\u001b[1;8C");
        mKeyMap.put(Integer.valueOf(536870931), "\u001b[1;2A");
        mKeyMap.put(Integer.valueOf(-2147483629), "\u001b[1;3A");
        mKeyMap.put(Integer.valueOf(-1610612717), "\u001b[1;4A");
        mKeyMap.put(Integer.valueOf(1073741843), "\u001b[1;5A");
        mKeyMap.put(Integer.valueOf(1610612755), "\u001b[1;6A");
        mKeyMap.put(Integer.valueOf(-1073741805), "\u001b[1;7A");
        mKeyMap.put(Integer.valueOf(-536870893), "\u001b[1;8A");
        mKeyMap.put(Integer.valueOf(536870932), "\u001b[1;2B");
        mKeyMap.put(Integer.valueOf(-2147483628), "\u001b[1;3B");
        mKeyMap.put(Integer.valueOf(-1610612716), "\u001b[1;4B");
        mKeyMap.put(Integer.valueOf(1073741844), "\u001b[1;5B");
        mKeyMap.put(Integer.valueOf(1610612756), "\u001b[1;6B");
        mKeyMap.put(Integer.valueOf(-1073741804), "\u001b[1;7B");
        mKeyMap.put(Integer.valueOf(-536870892), "\u001b[1;8B");
        mKeyMap.put(Integer.valueOf(536871024), "\u001b[3;2~");
        mKeyMap.put(Integer.valueOf(-2147483536), "\u001b[3;3~");
        mKeyMap.put(Integer.valueOf(1073741936), "\u001b[3;5~");
        mKeyMap.put(Integer.valueOf(536871036), "\u001b[2;2~");
        mKeyMap.put(Integer.valueOf(-2147483524), "\u001b[2;3~");
        mKeyMap.put(Integer.valueOf(1073741948), "\u001b[2;5~");
        mKeyMap.put(Integer.valueOf(1073741946), "\u001b[1;5H");
        mKeyMap.put(Integer.valueOf(1073741947), "\u001b[1;5F");
        mKeyMap.put(Integer.valueOf(-2147483582), "\u001b\r");
        mKeyMap.put(Integer.valueOf(1073741890), "\n");
        mKeyMap.put(Integer.valueOf(1073741886), "\u0000");
        mKeyMap.put(Integer.valueOf(536871043), "\u001b[1;2P");
        mKeyMap.put(Integer.valueOf(536871044), "\u001b[1;2Q");
        mKeyMap.put(Integer.valueOf(536871045), "\u001b[1;2R");
        mKeyMap.put(Integer.valueOf(536871046), "\u001b[1;2S");
        mKeyMap.put(Integer.valueOf(536871047), "\u001b[15;2~");
        mKeyMap.put(Integer.valueOf(536871048), "\u001b[17;2~");
        mKeyMap.put(Integer.valueOf(536871049), "\u001b[18;2~");
        mKeyMap.put(Integer.valueOf(536871050), "\u001b[19;2~");
        mKeyMap.put(Integer.valueOf(536871051), "\u001b[20;2~");
        mKeyMap.put(Integer.valueOf(536871052), "\u001b[21;2~");
        this.mKeyCodes[23] = "\r";
        this.mKeyCodes[19] = "\u001b[A";
        this.mKeyCodes[20] = "\u001b[B";
        this.mKeyCodes[22] = "\u001b[C";
        this.mKeyCodes[21] = "\u001b[D";
        setFnKeys("vt100");
        this.mKeyCodes[120] = "\u001b[32~";
        this.mKeyCodes[KeycodeConstants.KEYCODE_BREAK] = "\u001b[34~";
        this.mKeyCodes[61] = "\t";
        this.mKeyCodes[66] = "\r";
        this.mKeyCodes[111] = "\u001b";
        this.mKeyCodes[KeycodeConstants.KEYCODE_INSERT] = "\u001b[2~";
        this.mKeyCodes[112] = "\u001b[3~";
        this.mKeyCodes[92] = "\u001b[5~";
        this.mKeyCodes[93] = "\u001b[6~";
        this.mKeyCodes[67] = "";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUM_LOCK] = "\u001bOP";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_DIVIDE] = "/";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_MULTIPLY] = "*";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_SUBTRACT] = "-";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_ADD] = "+";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_ENTER] = "\r";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_EQUALS] = "=";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_COMMA] = ",";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_DOT] = this.mKeyCodes[112];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_0] = this.mKeyCodes[KeycodeConstants.KEYCODE_INSERT];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_1] = this.mKeyCodes[KeycodeConstants.KEYCODE_MOVE_END];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_2] = this.mKeyCodes[20];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_3] = this.mKeyCodes[93];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_4] = this.mKeyCodes[21];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_5] = "5";
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_6] = this.mKeyCodes[22];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_7] = this.mKeyCodes[KeycodeConstants.KEYCODE_MOVE_HOME];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_8] = this.mKeyCodes[19];
        this.mKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_9] = this.mKeyCodes[92];
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_DIVIDE] = "\u001bOo";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_MULTIPLY] = "\u001bOj";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_SUBTRACT] = "\u001bOm";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_ADD] = "\u001bOk";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_ENTER] = "\u001bOM";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_EQUALS] = "\u001bOX";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_DOT] = "\u001bOn";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_COMMA] = "\u001bOl";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_0] = "\u001bOp";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_1] = "\u001bOq";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_2] = "\u001bOr";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_3] = "\u001bOs";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_4] = "\u001bOt";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_5] = "\u001bOu";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_6] = "\u001bOv";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_7] = "\u001bOw";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_8] = "\u001bOx";
        this.mAppKeyCodes[KeycodeConstants.KEYCODE_NUMPAD_9] = "\u001bOy";
    }

    public void setCursorKeysApplicationMode(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CursorKeysApplicationMode=");
        stringBuilder.append(z);
        Log.p(str, stringBuilder.toString());
        String[] strArr;
        String str2;
        String str3;
        String str4;
        String str5;
        if (z) {
            strArr = this.mKeyCodes;
            str2 = "\u001bOA";
            this.mKeyCodes[19] = str2;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_8] = str2;
            strArr = this.mKeyCodes;
            str3 = "\u001bOB";
            this.mKeyCodes[20] = str3;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_2] = str3;
            strArr = this.mKeyCodes;
            str4 = "\u001bOC";
            this.mKeyCodes[22] = str4;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_6] = str4;
            strArr = this.mKeyCodes;
            str5 = "\u001bOD";
            this.mKeyCodes[21] = str5;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_4] = str5;
            return;
        }
        strArr = this.mKeyCodes;
        str2 = "\u001b[A";
        this.mKeyCodes[19] = str2;
        strArr[KeycodeConstants.KEYCODE_NUMPAD_8] = str2;
        strArr = this.mKeyCodes;
        str3 = "\u001b[B";
        this.mKeyCodes[20] = str3;
        strArr[KeycodeConstants.KEYCODE_NUMPAD_2] = str3;
        strArr = this.mKeyCodes;
        str4 = "\u001b[C";
        this.mKeyCodes[22] = str4;
        strArr[KeycodeConstants.KEYCODE_NUMPAD_6] = str4;
        strArr = this.mKeyCodes;
        str5 = "\u001b[D";
        this.mKeyCodes[21] = str5;
        strArr[KeycodeConstants.KEYCODE_NUMPAD_4] = str5;
    }

    public TerminalKeyListener(TerminalSession terminalSession) {
        this.mTerminalSession = terminalSession;
        initKeyCodes();
    }

    public void setAltSendsEsc(boolean z) {
        this.mAltSendsEsc = z;
    }

    public void handleVirtualControlKey(boolean z) {
        this.mVirtualControlKey = z;
    }

    public void handleVirtualFunctionKey(boolean z) {
        this.mVirtualFunctionKey = z;
    }

    public void onPause() {
        this.mVirtualControlKey = SUPPORT_8_BIT_META;
        this.mVirtualFunctionKey = SUPPORT_8_BIT_META;
    }

    public void setTerminalType(String str) {
        setFnKeys(str);
    }

    private void setFnKeys(String str) {
        String[] strArr;
        String str2;
        String str3;
        if (str.equals("xterm")) {
            strArr = this.mKeyCodes;
            str2 = "\u001bOH";
            this.mKeyCodes[KeycodeConstants.KEYCODE_MOVE_HOME] = str2;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_7] = str2;
            strArr = this.mKeyCodes;
            str3 = "\u001bOF";
            this.mKeyCodes[KeycodeConstants.KEYCODE_MOVE_END] = str3;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_1] = str3;
        } else {
            strArr = this.mKeyCodes;
            str2 = "\u001b[1~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_MOVE_HOME] = str2;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_7] = str2;
            strArr = this.mKeyCodes;
            str3 = "\u001b[4~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_MOVE_END] = str3;
            strArr[KeycodeConstants.KEYCODE_NUMPAD_1] = str3;
        }
        if (str.equals("vt100")) {
            this.mKeyCodes[KeycodeConstants.KEYCODE_F1] = "\u001bOP";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F2] = "\u001bOQ";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F3] = "\u001bOR";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F4] = "\u001bOS";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F5] = "\u001bOt";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F6] = "\u001bOu";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F7] = "\u001bOv";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F8] = "\u001bOl";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F9] = "\u001bOw";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F10] = "\u001bOx";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F11] = "\u001b[23~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F12] = "\u001b[24~";
        } else if (str.startsWith("linux")) {
            this.mKeyCodes[KeycodeConstants.KEYCODE_F1] = "\u001b[[A";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F2] = "\u001b[[B";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F3] = "\u001b[[C";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F4] = "\u001b[[D";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F5] = "\u001b[[E";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F6] = "\u001b[17~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F7] = "\u001b[18~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F8] = "\u001b[19~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F9] = "\u001b[20~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F10] = "\u001b[21~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F11] = "\u001b[23~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F12] = "\u001b[24~";
        } else {
            this.mKeyCodes[KeycodeConstants.KEYCODE_F1] = "\u001bOP";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F2] = "\u001bOQ";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F3] = "\u001bOR";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F4] = "\u001bOS";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F5] = "\u001b[15~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F6] = "\u001b[17~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F7] = "\u001b[18~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F8] = "\u001b[19~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F9] = "\u001b[20~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F10] = "\u001b[21~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F11] = "\u001b[23~";
            this.mKeyCodes[KeycodeConstants.KEYCODE_F12] = "\u001b[24~";
        }
    }

    public int mapControlChar(int i) {
        return mapControlChar(isControlPressed(), isFunctionPressed(), i);
    }

    public void keyDown(int i, KeyEvent keyEvent, boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("keyDown(");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(keyEvent);
        stringBuilder.append(",");
        stringBuilder.append(z);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        if (!handleKeyCode(i, keyEvent, z)) {
            int i2 = -1;
            if (i != 4) {
                if (i != 119) {
                    switch (i) {
                        case 57:
                        case 58:
                        case 59:
                        case 60:
                            break;
                        default:
                            switch (i) {
                                case 113:
                                case 114:
                                    return;
                                case 115:
                                    return;
                                default:
                                    i = keyEvent.getMetaState();
                                    i2 = i & -28673;
                                    if ((i2 & 2) != 0) {
                                        if (this.mAltSendsEsc) {
                                            this.mTerminalSession.write(new byte[]{(byte) 27}, 0, 1);
                                            i2 &= -51;
                                        } else {
                                            i2 |= 2;
                                        }
                                    }
                                    if ((i & KeycodeConstants.META_META_ON) != 0 && this.mAltSendsEsc) {
                                        this.mTerminalSession.write(new byte[]{(byte) 27}, 0, 1);
                                        i2 &= -458753;
                                    }
                                    i2 = keyEvent.getUnicodeChar(i2);
                                    if ((KEYMOD_ALT & i2) == 0) {
                                        if (this.mCombiningAccent != 0) {
                                            i = KeyCharacterMap.getDeadChar(this.mCombiningAccent, i2);
                                            String str2 = TAG;
                                            stringBuilder = new StringBuilder();
                                            stringBuilder.append("getDeadChar(");
                                            stringBuilder.append(this.mCombiningAccent);
                                            stringBuilder.append(", ");
                                            stringBuilder.append(i2);
                                            stringBuilder.append(") -> ");
                                            stringBuilder.append(i);
                                            Log.p(str2, stringBuilder.toString());
                                            this.mCombiningAccent = 0;
                                            i2 = i;
                                            break;
                                        }
                                    }
                                    String str3 = TAG;
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append("Got combining accent ");
                                    stringBuilder2.append(i2);
                                    Log.p(str3, stringBuilder2.toString());
                                    this.mCombiningAccent = Integer.MAX_VALUE & i2;
                                    return;
                                    break;
                            }
                    }
                }
                return;
            }
            i = mapControlChar(isControlPressed(), isFunctionPressed(), i2);
            if (i >= KEYCODE_OFFSET) {
                handleKeyCode(i - KEYCODE_OFFSET, null, z);
            } else if (i >= 0) {
                this.mTerminalSession.write(i);
            }
        }
    }

    public int getCombiningAccent() {
        return this.mCombiningAccent;
    }

    public boolean handleKeyCode(int i, KeyEvent keyEvent, boolean z) {
        String str;
        if (keyEvent != null) {
            int i2 = isControlPressed() ? KEYMOD_CTRL : 0;
            if ((keyEvent.getMetaState() & 2) != 0) {
                i2 |= KEYMOD_ALT;
            }
            if ((keyEvent.getMetaState() & 1) != 0) {
                i2 |= KEYMOD_SHIFT;
            }
            str = (String) mKeyMap.get(Integer.valueOf((keyEvent.getScanCode() | KEYMOD_SCAN) | i2));
            if (str == null) {
                str = (String) mKeyMap.get(Integer.valueOf(i2 | i));
            }
        } else {
            str = null;
        }
        if (str == null && i >= 0 && i < this.mKeyCodes.length) {
            if (z) {
                str = this.mAppKeyCodes[i];
            }
            if (str == null) {
                str = this.mKeyCodes[i];
            }
        }
        if (str == null) {
            return SUPPORT_8_BIT_META;
        }
        byte[] bytes = str.getBytes();
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Out: '");
        stringBuilder.append(Utils.bytesToString(bytes, 0, bytes.length));
        stringBuilder.append("'");
        Log.p(str2, stringBuilder.toString());
        this.mTerminalSession.write(str);
        return true;
    }

    public boolean getAltSendsEsc() {
        return this.mAltSendsEsc;
    }

    boolean isControlPressed() {
        return this.mVirtualControlKey;
    }

    boolean isFunctionPressed() {
        return this.mVirtualFunctionKey;
    }
}
