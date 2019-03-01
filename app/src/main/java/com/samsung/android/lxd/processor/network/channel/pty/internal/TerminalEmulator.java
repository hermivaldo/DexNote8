package com.samsung.android.lxd.processor.network.channel.pty.internal;

import com.samsung.android.lxd.processor.utils.Utils;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.Locale;

class TerminalEmulator {
    private static final int CHAR_SET_ALT_SPECIAL_GRAPICS = 4;
    private static final int CHAR_SET_ALT_STANDARD = 3;
    private static final int CHAR_SET_ASCII = 1;
    private static final int CHAR_SET_SPECIAL_GRAPHICS = 2;
    private static final int CHAR_SET_UK = 0;
    private static final boolean DEFAULT_TO_AUTOWRAP_ENABLED = true;
    private static final int ESC = 1;
    private static final int ESC_LEFT_SQUARE_BRACKET = 5;
    private static final int ESC_LEFT_SQUARE_BRACKET_QUESTION_MARK = 6;
    private static final int ESC_LEFT_SQUARE_BRACKET_RIGHT_REDIRECTION = 10;
    private static final int ESC_NONE = 0;
    private static final int ESC_PERCENT = 7;
    private static final int ESC_POUND = 2;
    private static final int ESC_RIGHT_SQUARE_BRACKET = 8;
    private static final int ESC_RIGHT_SQUARE_BRACKET_ESC = 9;
    private static final int ESC_SELECT_LEFT_PAREN = 3;
    private static final int ESC_SELECT_RIGHT_PAREN = 4;
    private static final int K_132_COLUMN_MODE_MASK = 8;
    private static final int K_DECSC_DECRC_MASK = 192;
    private static final int K_ORIGIN_MODE_MASK = 64;
    private static final int K_REVERSE_VIDEO_MASK = 32;
    private static final int K_SHOW_CURSOR_MASK = 33554432;
    private static final int K_WRAPAROUND_MODE_MASK = 128;
    private static final int MAX_ESCAPE_PARAMETERS = 16;
    private static final int MAX_OSC_STRING_LENGTH = 512;
    private static final String TAG = "TerminalEmulator";
    private static final int TRANSCRIPT_ROWS = 10000;
    private static final int UNICODE_REPLACEMENT_CHAR = 65533;
    private static final char[] mSpecialGraphicsCharMap = new char[128];
    private boolean mAboutToAutoWrap;
    private TranscriptScreen mAltBuffer;
    private boolean mAlternateCharSet;
    private int mArgIndex;
    private int[] mArgs = new int[16];
    private int mBackColor;
    private int mBottomMargin;
    private int[] mCharSet = new int[2];
    private int mColumns;
    private boolean mContinueSequence;
    private int mCursorCol;
    private int mCursorRow;
    private int mDecFlags;
    private int mDefaultBackColor;
    private int mDefaultForeColor;
    private boolean mDefaultUTF8Mode = false;
    private int mEffect;
    private int mEscapeState;
    private int mForeColor;
    private CharBuffer mInputCharBuffer;
    private boolean mInsertMode;
    private boolean mJustWrapped = false;
    private TerminalKeyListener mKeyListener;
    private int mLastEmittedCharWidth = 0;
    private TranscriptScreen mMainBuffer;
    private int mMouseTrackingMode;
    private byte[] mOSCArg = new byte[MAX_OSC_STRING_LENGTH];
    private int mOSCArgLength;
    private int mOSCArgTokenizerIndex;
    private int mProcessedCharCount;
    private int mRows;
    private int mSavedCursorCol;
    private int mSavedCursorRow;
    private int mSavedDecFlags;
    private int mSavedDecFlags_DECSC_DECRC;
    private int mSavedEffect;
    private TranscriptScreen mScreen;
    private int mScrollCounter = 0;
    private TerminalSession mSession;
    private boolean[] mTabStop;
    private int mTopMargin;
    private ByteBuffer mUTF8ByteBuffer;
    private CharsetDecoder mUTF8Decoder;
    private boolean mUTF8EscapeUsed = false;
    private boolean mUTF8Mode = false;
    private OnTerminalListener mUTF8ModeListener;
    private int mUTF8ToFollow = 0;
    private boolean mUseAlternateCharSet;
    private boolean mbKeypadApplicationMode;

    private int getDecFlagsMask(int i) {
        return (i < 1 || i > 32) ? 0 : 1 << i;
    }

    private boolean isValidColor(int i) {
        return (i < 0 || i >= 260) ? false : DEFAULT_TO_AUTOWRAP_ENABLED;
    }

    static {
        for (char c = 0; c < 128; c = (char) (c + 1)) {
            mSpecialGraphicsCharMap[c] = c;
        }
        mSpecialGraphicsCharMap[95] = ' ';
        mSpecialGraphicsCharMap[98] = 9225;
        mSpecialGraphicsCharMap[99] = 9228;
        mSpecialGraphicsCharMap[100] = 9229;
        mSpecialGraphicsCharMap[101] = 9226;
        mSpecialGraphicsCharMap[104] = 9252;
        mSpecialGraphicsCharMap[105] = 9227;
        mSpecialGraphicsCharMap[KeycodeConstants.KEYCODE_FORWARD] = 163;
        mSpecialGraphicsCharMap[102] = 176;
        mSpecialGraphicsCharMap[96] = 11045;
        mSpecialGraphicsCharMap[KeycodeConstants.KEYCODE_MEDIA_PLAY] = 8226;
        mSpecialGraphicsCharMap[KeycodeConstants.KEYCODE_BREAK] = 8804;
        mSpecialGraphicsCharMap[KeycodeConstants.KEYCODE_INSERT] = 8800;
        mSpecialGraphicsCharMap[KeycodeConstants.KEYCODE_MOVE_HOME] = 8805;
        mSpecialGraphicsCharMap[103] = 177;
        mSpecialGraphicsCharMap[KeycodeConstants.KEYCODE_MOVE_END] = 960;
        mSpecialGraphicsCharMap[46] = 9660;
        mSpecialGraphicsCharMap[44] = 9664;
        mSpecialGraphicsCharMap[43] = 9654;
        mSpecialGraphicsCharMap[45] = 9650;
        mSpecialGraphicsCharMap[104] = '#';
        mSpecialGraphicsCharMap[97] = 9618;
        mSpecialGraphicsCharMap[48] = 9608;
        mSpecialGraphicsCharMap[113] = 9472;
        mSpecialGraphicsCharMap[120] = 9474;
        mSpecialGraphicsCharMap[109] = 9492;
        mSpecialGraphicsCharMap[106] = 9496;
        mSpecialGraphicsCharMap[108] = 9484;
        mSpecialGraphicsCharMap[107] = 9488;
        mSpecialGraphicsCharMap[119] = 9516;
        mSpecialGraphicsCharMap[117] = 9508;
        mSpecialGraphicsCharMap[116] = 9500;
        mSpecialGraphicsCharMap[118] = 9524;
        mSpecialGraphicsCharMap[110] = 9532;
        mSpecialGraphicsCharMap[111] = 9146;
        mSpecialGraphicsCharMap[112] = 9147;
        mSpecialGraphicsCharMap[114] = 9148;
        mSpecialGraphicsCharMap[115] = 9149;
    }

    TerminalEmulator(TerminalSession terminalSession, int i, int i2, ColorScheme colorScheme) {
        this.mSession = terminalSession;
        this.mMainBuffer = new TranscriptScreen(i, TRANSCRIPT_ROWS, i2, DEFAULT_TO_AUTOWRAP_ENABLED);
        this.mScreen = this.mMainBuffer;
        this.mAltBuffer = new TranscriptScreen(i, i2, i2);
        this.mRows = i2;
        this.mColumns = i;
        this.mTabStop = new boolean[this.mColumns];
        setColorScheme(colorScheme);
        this.mUTF8ByteBuffer = ByteBuffer.allocate(4);
        this.mInputCharBuffer = CharBuffer.allocate(2);
        this.mUTF8Decoder = Charset.forName("UTF-8").newDecoder();
        this.mUTF8Decoder.onMalformedInput(CodingErrorAction.REPLACE);
        this.mUTF8Decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        reset();
    }

    public TranscriptScreen getScreen() {
        return this.mScreen;
    }

    void updateSize(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        if (this.mRows != i4 || this.mColumns != i3) {
            if (i3 <= 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("rows:");
                stringBuilder.append(i3);
                throw new IllegalArgumentException(stringBuilder.toString());
            } else if (i4 <= 0) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("rows:");
                stringBuilder2.append(i4);
                throw new IllegalArgumentException(stringBuilder2.toString());
            } else {
                TranscriptScreen transcriptScreen;
                GrowableIntArray growableIntArray;
                String str;
                GrowableIntArray growableIntArray2;
                String str2;
                GrowableIntArray growableIntArray3;
                String str3;
                boolean fastResize;
                GrowableIntArray growableIntArray4;
                int i5;
                int i6;
                int length;
                int i7;
                TranscriptScreen transcriptScreen2 = this.mScreen;
                if (transcriptScreen2 != this.mMainBuffer) {
                    transcriptScreen = this.mMainBuffer;
                } else {
                    transcriptScreen = this.mAltBuffer;
                }
                TranscriptScreen transcriptScreen3 = transcriptScreen;
                int[] iArr = new int[]{this.mCursorCol, this.mCursorRow};
                boolean fastResize2 = transcriptScreen2.fastResize(i3, i4, iArr);
                if (fastResize2) {
                    growableIntArray = null;
                    str = null;
                    growableIntArray2 = null;
                    str2 = null;
                } else {
                    growableIntArray3 = new GrowableIntArray(1);
                    int i8 = this.mCursorCol;
                    int i9 = this.mCursorRow;
                    int i10 = this.mCursorCol;
                    int i11 = i9;
                    GrowableIntArray growableIntArray5 = growableIntArray3;
                    Object obj = null;
                    str2 = transcriptScreen2.getSelectedText(growableIntArray3, i8, i11, i10, this.mCursorRow);
                    transcriptScreen2.set(this.mCursorCol, this.mCursorRow, 27, 0);
                    growableIntArray = new GrowableIntArray(1024);
                    str = transcriptScreen2.getTranscriptText(growableIntArray);
                    transcriptScreen2.resize(i3, i4, getStyle());
                    growableIntArray2 = growableIntArray5;
                }
                if (transcriptScreen3 != null) {
                    str3 = null;
                    fastResize = transcriptScreen3.fastResize(i3, i4, null);
                    if (fastResize) {
                        growableIntArray4 = null;
                    } else {
                        growableIntArray3 = new GrowableIntArray(1024);
                        String transcriptText = transcriptScreen3.getTranscriptText(growableIntArray3);
                        transcriptScreen3.resize(i3, i4, getStyle());
                        growableIntArray4 = growableIntArray3;
                        str3 = transcriptText;
                    }
                } else {
                    str3 = null;
                    growableIntArray4 = null;
                    fastResize = DEFAULT_TO_AUTOWRAP_ENABLED;
                }
                if (this.mRows != i4) {
                    this.mRows = i4;
                    this.mTopMargin = 0;
                    this.mBottomMargin = this.mRows;
                }
                if (this.mColumns != i3) {
                    this.mColumns = i3;
                    this.mTabStop = new boolean[this.mColumns];
                    setDefaultTabStops();
                }
                if (fastResize) {
                    i3 = 1;
                } else {
                    boolean z = this.mAboutToAutoWrap;
                    this.mScreen = transcriptScreen3;
                    this.mCursorRow = 0;
                    this.mCursorCol = 0;
                    this.mAboutToAutoWrap = false;
                    i5 = 0;
                    i6 = i5;
                    int i12;
                    for (length = str3.length() - 1; i5 <= length; length = i12) {
                        char charAt = str3.charAt(i5);
                        i3 = growableIntArray4.at(i5 - i6);
                        if (Character.isHighSurrogate(charAt)) {
                            i5++;
                            i12 = length;
                            emit(Character.toCodePoint(charAt, str3.charAt(i5)), i3);
                            i6++;
                        } else {
                            i12 = length;
                            if (charAt == 10) {
                                if (this.mScreen.getScriptLineWrap(this.mCursorRow)) {
                                    i7 = 0;
                                    this.mScreen.setScriptLineWrap(this.mCursorRow, false);
                                } else {
                                    i7 = 0;
                                }
                                setCursorCol(i7);
                                doLinefeed();
                            } else {
                                emit(charAt, i3);
                            }
                        }
                        i5++;
                    }
                    i3 = 1;
                    this.mScreen = transcriptScreen2;
                    this.mAboutToAutoWrap = z;
                }
                if (fastResize2) {
                    if (iArr[0] < 0 || iArr[i3] < 0) {
                        this.mCursorCol = 0;
                        this.mCursorRow = 0;
                    } else {
                        this.mCursorCol = iArr[0];
                        this.mCursorRow = iArr[i3];
                    }
                    return;
                }
                this.mCursorRow = 0;
                this.mCursorCol = 0;
                this.mAboutToAutoWrap = false;
                i3 = str.length() - 1;
                while (i3 >= 0 && str.charAt(i3) == 10) {
                    i3--;
                }
                int i13 = 0;
                length = 0;
                i5 = -1;
                int i14 = -1;
                i7 = -1;
                while (i13 <= i3) {
                    char charAt2 = str.charAt(i13);
                    int at = growableIntArray.at(i13 - length);
                    if (Character.isHighSurrogate(charAt2)) {
                        i13++;
                        emit(Character.toCodePoint(charAt2, str.charAt(i13)), at);
                        length++;
                    } else if (charAt2 == 10) {
                        if (this.mScreen.getScriptLineWrap(this.mCursorRow)) {
                            i6 = 0;
                            this.mScreen.setScriptLineWrap(this.mCursorRow, false);
                        } else {
                            i6 = 0;
                        }
                        setCursorCol(i6);
                        doLinefeed();
                    } else if (charAt2 == 27) {
                        i5 = this.mCursorRow;
                        i14 = this.mCursorCol;
                        i7 = transcriptScreen2.getActiveRows();
                        if (str2 != null && str2.length() > 0) {
                            emit(str2.toCharArray(), 0, str2.length(), growableIntArray2.at(0));
                        }
                    } else {
                        emit(charAt2, at);
                    }
                    i13++;
                }
                if (!(i5 == -1 || i14 == -1)) {
                    this.mCursorRow = i5;
                    this.mCursorCol = i14;
                    i3 = transcriptScreen2.getActiveRows() - i7;
                    if (i3 > 0 && i3 <= i5) {
                        this.mCursorRow -= i3;
                    } else if (i3 > i5) {
                        this.mCursorRow = 0;
                        this.mCursorCol = 0;
                    }
                }
            }
        }
    }

    final int getCursorRow() {
        return this.mCursorRow;
    }

    final int getCursorCol() {
        return this.mCursorCol;
    }

    final boolean getReverseVideo() {
        return (this.mDecFlags & 32) != 0 ? DEFAULT_TO_AUTOWRAP_ENABLED : false;
    }

    final boolean getShowCursor() {
        return (this.mDecFlags & K_SHOW_CURSOR_MASK) != 0 ? DEFAULT_TO_AUTOWRAP_ENABLED : false;
    }

    final boolean getKeypadApplicationMode() {
        return this.mbKeypadApplicationMode;
    }

    final int getMouseTrackingMode() {
        return this.mMouseTrackingMode;
    }

    private void setDefaultTabStops() {
        int i = 0;
        while (i < this.mColumns) {
            boolean[] zArr = this.mTabStop;
            boolean z = ((i & 7) != 0 || i == 0) ? false : DEFAULT_TO_AUTOWRAP_ENABLED;
            zArr[i] = z;
            i++;
        }
    }

    public void append(byte[] bArr, int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("In: '");
        stringBuilder.append(Utils.bytesToString(bArr, i, i2));
        stringBuilder.append("'");
        Log.p(str, stringBuilder.toString());
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3];
            try {
                process(b);
                this.mProcessedCharCount++;
            } catch (Throwable e) {
                String str2 = TAG;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("Exception while processing character ");
                stringBuilder2.append(Integer.toString(this.mProcessedCharCount));
                stringBuilder2.append(" code ");
                stringBuilder2.append(Integer.toString(b));
                Log.e(str2, stringBuilder2.toString(), e);
            }
        }
    }

    private void process(byte b) {
        process(b, DEFAULT_TO_AUTOWRAP_ENABLED);
    }

    private void process(byte b, boolean z) {
        if (!z || !this.mUTF8Mode || !handleUTF8Sequence(b)) {
            if ((b & 128) == 128) {
                int i = b & KeycodeConstants.KEYCODE_MEDIA_PAUSE;
                if (i <= 31) {
                    process((byte) 27, false);
                    process((byte) (i + 64), false);
                    return;
                }
            }
            if (b != (byte) 0) {
                if (b != (byte) 24) {
                    switch (b) {
                        case (byte) 7:
                            if (this.mEscapeState == 8) {
                                doEscRightSquareBracket(b);
                                break;
                            }
                            break;
                        case (byte) 8:
                            setCursorCol(Math.max(0, this.mCursorCol - 1));
                            break;
                        case (byte) 9:
                            setCursorCol(nextTabStop(this.mCursorCol));
                            break;
                        case (byte) 10:
                        case (byte) 11:
                        case (byte) 12:
                            if (this.mScreen.getScriptLineWrap(this.mCursorRow)) {
                                this.mScreen.setScriptLineWrap(this.mCursorRow, false);
                            }
                            doLinefeed();
                            break;
                        case (byte) 13:
                            setCursorCol(0);
                            break;
                        case (byte) 14:
                            setAltCharSet(DEFAULT_TO_AUTOWRAP_ENABLED);
                            break;
                        case (byte) 15:
                            setAltCharSet(false);
                            break;
                        default:
                            switch (b) {
                                case (byte) 26:
                                    break;
                                case (byte) 27:
                                    if (this.mEscapeState == 8) {
                                        doEscRightSquareBracket(b);
                                        break;
                                    } else {
                                        startEscapeSequence(1);
                                        break;
                                    }
                                default:
                                    this.mContinueSequence = false;
                                    switch (this.mEscapeState) {
                                        case 0:
                                            if (b >= (byte) 32) {
                                                emit(b);
                                                break;
                                            }
                                            break;
                                        case 1:
                                            doEsc(b);
                                            break;
                                        case 2:
                                            doEscPound(b);
                                            break;
                                        case 3:
                                            doEscSelectLeftParen(b);
                                            break;
                                        case 4:
                                            doEscSelectRightParen(b);
                                            break;
                                        case 5:
                                            doEscLeftSquareBracket(b);
                                            break;
                                        case 6:
                                            doEscLSBQuest(b);
                                            break;
                                        case 7:
                                            doEscPercent(b);
                                            break;
                                        case 8:
                                            doEscRightSquareBracket(b);
                                            break;
                                        case 9:
                                            doEscRightSquareBracketEsc(b);
                                            break;
                                        case 10:
                                            doEscLSBRightRedirection(b);
                                            break;
                                        default:
                                            unknownSequence(b);
                                            break;
                                    }
                                    if (!this.mContinueSequence) {
                                        this.mEscapeState = 0;
                                        break;
                                    }
                                    break;
                            }
                    }
                }
                if (this.mEscapeState != 0) {
                    this.mEscapeState = 0;
                    emit(Byte.MAX_VALUE);
                }
            }
        }
    }

    private boolean handleUTF8Sequence(byte b) {
        if (this.mUTF8ToFollow == 0 && (b & 128) == 0) {
            return false;
        }
        if (this.mUTF8ToFollow <= 0) {
            if ((b & 224) == K_DECSC_DECRC_MASK) {
                this.mUTF8ToFollow = 1;
            } else if ((b & 240) == 224) {
                this.mUTF8ToFollow = 2;
            } else if ((b & 248) == 240) {
                this.mUTF8ToFollow = 3;
            } else {
                emit((int) UNICODE_REPLACEMENT_CHAR);
                return DEFAULT_TO_AUTOWRAP_ENABLED;
            }
            this.mUTF8ByteBuffer.put(b);
        } else if ((b & K_DECSC_DECRC_MASK) != 128) {
            this.mUTF8ToFollow = 0;
            this.mUTF8ByteBuffer.clear();
            emit((int) UNICODE_REPLACEMENT_CHAR);
            return handleUTF8Sequence(b);
        } else {
            this.mUTF8ByteBuffer.put(b);
            int i = this.mUTF8ToFollow - 1;
            this.mUTF8ToFollow = i;
            if (i == 0) {
                ByteBuffer byteBuffer = this.mUTF8ByteBuffer;
                CharBuffer charBuffer = this.mInputCharBuffer;
                CharsetDecoder charsetDecoder = this.mUTF8Decoder;
                byteBuffer.rewind();
                charsetDecoder.reset();
                charsetDecoder.decode(byteBuffer, charBuffer, DEFAULT_TO_AUTOWRAP_ENABLED);
                charsetDecoder.flush(charBuffer);
                char[] array = charBuffer.array();
                if (array[0] < 128 || array[0] > 159) {
                    emit(array);
                } else {
                    process((byte) array[0], false);
                }
                byteBuffer.clear();
                charBuffer.clear();
            }
        }
        return DEFAULT_TO_AUTOWRAP_ENABLED;
    }

    private void setAltCharSet(boolean z) {
        this.mAlternateCharSet = z;
        computeEffectiveCharSet();
    }

    private void computeEffectiveCharSet() {
        this.mUseAlternateCharSet = this.mCharSet[this.mAlternateCharSet] == 2 ? DEFAULT_TO_AUTOWRAP_ENABLED : false;
    }

    private int nextTabStop(int i) {
        do {
            i++;
            if (i >= this.mColumns) {
                return this.mColumns - 1;
            }
        } while (!this.mTabStop[i]);
        return i;
    }

    private int prevTabStop(int i) {
        for (i--; i >= 0; i--) {
            if (this.mTabStop[i]) {
                return i;
            }
        }
        return 0;
    }

    private void doEscPercent(byte b) {
        if (b == (byte) 64) {
            setUTF8Mode(false);
            this.mUTF8EscapeUsed = DEFAULT_TO_AUTOWRAP_ENABLED;
        } else if (b == (byte) 71) {
            setUTF8Mode(DEFAULT_TO_AUTOWRAP_ENABLED);
            this.mUTF8EscapeUsed = DEFAULT_TO_AUTOWRAP_ENABLED;
        }
    }

    private void doEscLSBQuest(byte b) {
        int arg0 = getArg0(0);
        int decFlagsMask = getDecFlagsMask(arg0);
        int i = this.mDecFlags;
        if (b == (byte) 104) {
            this.mDecFlags |= decFlagsMask;
            if (arg0 == 1) {
                this.mKeyListener.setCursorKeysApplicationMode(DEFAULT_TO_AUTOWRAP_ENABLED);
            } else if ((arg0 == 47 || arg0 == 1047 || arg0 == 1049) && this.mAltBuffer != null) {
                this.mScreen = this.mAltBuffer.initScreen(getStyle());
            }
            if (arg0 >= 1000 && arg0 <= 1003) {
                this.mMouseTrackingMode = arg0;
            }
        } else if (b != (byte) 108) {
            switch (b) {
                case (byte) 114:
                    this.mDecFlags = (this.mDecFlags & (~decFlagsMask)) | (this.mSavedDecFlags & decFlagsMask);
                    break;
                case (byte) 115:
                    this.mSavedDecFlags = (this.mSavedDecFlags & (~decFlagsMask)) | (this.mDecFlags & decFlagsMask);
                    break;
                default:
                    parseArg(b);
                    break;
            }
        } else {
            this.mDecFlags &= ~decFlagsMask;
            if (arg0 == 1) {
                this.mKeyListener.setCursorKeysApplicationMode(false);
            } else if (arg0 == 47 || arg0 == 1047 || arg0 == 1049) {
                this.mScreen = this.mMainBuffer;
            }
            if (arg0 >= 1000 && arg0 <= 1003) {
                this.mMouseTrackingMode = 0;
            }
        }
        int i2 = (~i) & this.mDecFlags;
        if (((this.mDecFlags ^ i) & 8) != 0) {
            blockClear(0, 0, this.mColumns, this.mRows);
            setCursorRowCol(0, 0);
        }
        if ((i2 & 64) != 0) {
            setCursorPosition(0, 0);
        }
    }

    private void startEscapeSequence(int i) {
        this.mEscapeState = i;
        i = 0;
        this.mArgIndex = 0;
        while (i < 16) {
            this.mArgs[i] = -1;
            i++;
        }
    }

    private void doLinefeed() {
        int i = this.mCursorRow + 1;
        if (i >= this.mBottomMargin) {
            scroll();
            i = this.mBottomMargin - 1;
        }
        setCursorRow(i);
    }

    private void continueSequence() {
        this.mContinueSequence = DEFAULT_TO_AUTOWRAP_ENABLED;
    }

    private void continueSequence(int i) {
        this.mEscapeState = i;
        this.mContinueSequence = DEFAULT_TO_AUTOWRAP_ENABLED;
    }

    private void doEscSelectLeftParen(byte b) {
        doSelectCharSet(0, b);
    }

    private void doEscSelectRightParen(byte b) {
        doSelectCharSet(1, b);
    }

    private void doSelectCharSet(int i, byte b) {
        int i2;
        switch (b) {
            case (byte) 48:
                i2 = 2;
                break;
            case (byte) 49:
                i2 = 3;
                break;
            case (byte) 50:
                i2 = 4;
                break;
            default:
                switch (b) {
                    case (byte) 65:
                        i2 = 0;
                        break;
                    case (byte) 66:
                        i2 = 1;
                        break;
                    default:
                        unknownSequence(b);
                        return;
                }
        }
        this.mCharSet[i] = i2;
        computeEffectiveCharSet();
    }

    private void doEscPound(byte b) {
        if (b != (byte) 56) {
            unknownSequence(b);
        } else {
            this.mScreen.blockSet(0, 0, this.mColumns, this.mRows, 69, getStyle());
        }
    }

    private void doEsc(byte b) {
        switch (b) {
            case (byte) 35:
                continueSequence(2);
                return;
            case (byte) 40:
                continueSequence(3);
                return;
            case (byte) 41:
                continueSequence(4);
                return;
            case (byte) 48:
                unimplementedSequence(b);
                return;
            case (byte) 55:
                this.mSavedCursorRow = this.mCursorRow;
                this.mSavedCursorCol = this.mCursorCol;
                this.mSavedEffect = this.mEffect;
                this.mSavedDecFlags_DECSC_DECRC = this.mDecFlags & K_DECSC_DECRC_MASK;
                return;
            case (byte) 56:
                setCursorRowCol(this.mSavedCursorRow, this.mSavedCursorCol);
                this.mEffect = this.mSavedEffect;
                this.mDecFlags = (this.mDecFlags & -193) | this.mSavedDecFlags_DECSC_DECRC;
                return;
            case (byte) 61:
                this.mbKeypadApplicationMode = DEFAULT_TO_AUTOWRAP_ENABLED;
                return;
            case (byte) 62:
                this.mbKeypadApplicationMode = false;
                return;
            case (byte) 68:
                doLinefeed();
                return;
            case (byte) 69:
                setCursorCol(0);
                doLinefeed();
                return;
            case (byte) 70:
                setCursorRowCol(0, this.mBottomMargin - 1);
                return;
            case (byte) 72:
                this.mTabStop[this.mCursorCol] = DEFAULT_TO_AUTOWRAP_ENABLED;
                return;
            case (byte) 77:
                if (this.mCursorRow <= this.mTopMargin) {
                    this.mScreen.blockCopy(0, this.mTopMargin, this.mColumns, this.mBottomMargin - (this.mTopMargin + 1), 0, this.mTopMargin + 1);
                    blockClear(0, this.mTopMargin, this.mColumns);
                    return;
                }
                this.mCursorRow--;
                return;
            case (byte) 78:
                unimplementedSequence(b);
                return;
            case (byte) 80:
                unimplementedSequence(b);
                return;
            case (byte) 90:
                sendDeviceAttributes();
                return;
            case (byte) 91:
                continueSequence(5);
                return;
            case (byte) 93:
                startCollectingOSCArgs();
                continueSequence(8);
                return;
            default:
                unknownSequence(b);
                return;
        }
    }

    private void doEscLeftSquareBracket(byte b) {
        int i;
        int min;
        switch (b) {
            case (byte) 62:
                continueSequence(10);
                return;
            case (byte) 63:
                continueSequence(6);
                return;
            case (byte) 64:
                i = this.mColumns - this.mCursorCol;
                min = Math.min(getArg0(1), i);
                this.mScreen.blockCopy(this.mCursorCol, this.mCursorRow, i - min, 1, this.mCursorCol + min, this.mCursorRow);
                blockClear(this.mCursorCol, this.mCursorRow, min);
                return;
            case (byte) 65:
                setCursorRow(Math.max(this.mTopMargin, this.mCursorRow - getArg0(1)));
                return;
            case (byte) 66:
                setCursorRow(Math.min(this.mBottomMargin - 1, this.mCursorRow + getArg0(1)));
                return;
            case (byte) 67:
                setCursorCol(Math.min(this.mColumns - 1, this.mCursorCol + getArg0(1)));
                return;
            case (byte) 68:
                setCursorCol(Math.max(0, this.mCursorCol - getArg0(1)));
                return;
            case (byte) 71:
                setCursorCol(Math.min(Math.max(1, getArg0(1)), this.mColumns) - 1);
                return;
            case (byte) 72:
                setHorizontalVerticalPosition();
                return;
            case (byte) 74:
                switch (getArg0(0)) {
                    case 0:
                        blockClear(this.mCursorCol, this.mCursorRow, this.mColumns - this.mCursorCol);
                        blockClear(0, this.mCursorRow + 1, this.mColumns, this.mRows - (this.mCursorRow + 1));
                        return;
                    case 1:
                        blockClear(0, 0, this.mColumns, this.mCursorRow);
                        blockClear(0, this.mCursorRow, this.mCursorCol + 1);
                        return;
                    case 2:
                        blockClear(0, 0, this.mColumns, this.mRows);
                        return;
                    default:
                        unknownSequence(b);
                        return;
                }
            case (byte) 75:
                switch (getArg0(0)) {
                    case 0:
                        blockClear(this.mCursorCol, this.mCursorRow, this.mColumns - this.mCursorCol);
                        return;
                    case 1:
                        blockClear(0, this.mCursorRow, this.mCursorCol + 1);
                        return;
                    case 2:
                        blockClear(0, this.mCursorRow, this.mColumns);
                        return;
                    default:
                        unknownSequence(b);
                        return;
                }
            case (byte) 76:
                i = this.mBottomMargin - this.mCursorRow;
                min = Math.min(getArg0(1), i);
                this.mScreen.blockCopy(0, this.mCursorRow, this.mColumns, i - min, 0, this.mCursorRow + min);
                blockClear(0, this.mCursorRow, this.mColumns, min);
                return;
            case (byte) 77:
                i = this.mBottomMargin - this.mCursorRow;
                min = Math.min(getArg0(1), i);
                i -= min;
                this.mScreen.blockCopy(0, this.mCursorRow + min, this.mColumns, i, 0, this.mCursorRow);
                blockClear(0, this.mCursorRow + i, this.mColumns, min);
                return;
            case (byte) 80:
                i = this.mColumns - this.mCursorCol;
                min = Math.min(getArg0(1), i);
                i -= min;
                this.mScreen.blockCopy(this.mCursorCol + min, this.mCursorRow, i, 1, this.mCursorCol, this.mCursorRow);
                blockClear(this.mCursorCol + i, this.mCursorRow, min);
                return;
            case (byte) 84:
                unimplementedSequence(b);
                return;
            case (byte) 88:
                blockClear(this.mCursorCol, this.mCursorRow, getArg0(0));
                return;
            case (byte) 90:
                setCursorCol(prevTabStop(this.mCursorCol));
                return;
            case (byte) 99:
                sendDeviceAttributes();
                return;
            case (byte) 100:
                setCursorRow(Math.min(Math.max(1, getArg0(1)), this.mRows) - 1);
                return;
            case (byte) 102:
                setHorizontalVerticalPosition();
                return;
            case (byte) 103:
                i = getArg0(0);
                if (i == 0) {
                    this.mTabStop[this.mCursorCol] = false;
                    return;
                } else if (i == 3) {
                    for (i = 0; i < this.mColumns; i++) {
                        this.mTabStop[i] = false;
                    }
                    return;
                } else {
                    return;
                }
            case (byte) 104:
                doSetMode(DEFAULT_TO_AUTOWRAP_ENABLED);
                return;
            case (byte) 108:
                doSetMode(false);
                return;
            case (byte) 109:
                selectGraphicRendition();
                return;
            case (byte) 110:
                byte[] bArr;
                switch (getArg0(0)) {
                    case 5:
                        bArr = new byte[]{(byte) 27, (byte) 91, (byte) 48, (byte) 110};
                        this.mSession.write(bArr, 0, bArr.length);
                        return;
                    case 6:
                        bArr = String.format(Locale.US, "\u001b[%d;%dR", new Object[]{Integer.valueOf(this.mCursorRow + 1), Integer.valueOf(this.mCursorCol + 1)}).getBytes();
                        this.mSession.write(bArr, 0, bArr.length);
                        return;
                    default:
                        return;
                }
            case (byte) 114:
                i = Math.max(0, Math.min(getArg0(1) - 1, this.mRows - 2));
                min = Math.max(i + 2, Math.min(getArg1(this.mRows), this.mRows));
                this.mTopMargin = i;
                this.mBottomMargin = min;
                setCursorRowCol(this.mTopMargin, 0);
                return;
            default:
                parseArg(b);
                return;
        }
    }

    private void doEscLSBRightRedirection(byte b) {
        if (b != (byte) 99) {
            unknownSequence(b);
        } else {
            unimplementedSequence(b);
        }
    }

    private void selectGraphicRendition() {
        int i = 0;
        while (i <= this.mArgIndex) {
            int i2;
            int i3 = this.mArgs[i];
            if (i3 < 0) {
                if (this.mArgIndex <= 0) {
                    i3 = 0;
                }
                i2 = i;
                i = i2 + 1;
            }
            if (i3 == 0) {
                this.mForeColor = this.mDefaultForeColor;
                this.mBackColor = this.mDefaultBackColor;
                this.mEffect = 0;
            } else if (i3 == 1) {
                this.mEffect |= 1;
            } else if (i3 == 3) {
                this.mEffect |= 2;
            } else if (i3 == 4) {
                this.mEffect |= 4;
            } else if (i3 == 5) {
                this.mEffect |= 8;
            } else if (i3 == 7) {
                this.mEffect |= 16;
            } else if (i3 == 8) {
                this.mEffect |= 32;
            } else if (i3 == 10) {
                setAltCharSet(false);
            } else if (i3 == 11) {
                setAltCharSet(DEFAULT_TO_AUTOWRAP_ENABLED);
            } else if (i3 == 22) {
                this.mEffect &= -2;
            } else if (i3 == 23) {
                this.mEffect &= -3;
            } else if (i3 == 24) {
                this.mEffect &= -5;
            } else if (i3 == 25) {
                this.mEffect &= -9;
            } else if (i3 == 27) {
                this.mEffect &= -17;
            } else if (i3 == 28) {
                this.mEffect &= -33;
            } else if (i3 < 30 || i3 > 37) {
                if (i3 == 38) {
                    i2 = i + 2;
                    if (i2 <= this.mArgIndex && this.mArgs[i + 1] == 5) {
                        i = this.mArgs[i2];
                        if (checkColor(i)) {
                            this.mForeColor = i;
                        }
                        i = i2 + 1;
                    }
                }
                if (i3 == 39) {
                    this.mForeColor = this.mDefaultForeColor;
                } else if (i3 < 40 || i3 > 47) {
                    if (i3 == 48) {
                        i2 = i + 2;
                        if (i2 <= this.mArgIndex && this.mArgs[i + 1] == 5) {
                            this.mBackColor = this.mArgs[i2];
                            i = this.mArgs[i2];
                            if (checkColor(i)) {
                                this.mBackColor = i;
                            }
                            i = i2 + 1;
                        }
                    }
                    if (i3 == 49) {
                        this.mBackColor = this.mDefaultBackColor;
                    } else if (i3 >= 90 && i3 <= 97) {
                        this.mForeColor = (i3 - 90) + 8;
                    } else if (i3 < 100 || i3 > 107) {
                        Log.p(TAG, String.format("SGR unknown code %d", new Object[]{Integer.valueOf(i3)}));
                    } else {
                        this.mBackColor = (i3 - 100) + 8;
                    }
                } else {
                    this.mBackColor = i3 - 40;
                }
            } else {
                this.mForeColor = i3 - 30;
            }
            i2 = i;
            i = i2 + 1;
        }
    }

    private boolean checkColor(int i) {
        boolean isValidColor = isValidColor(i);
        if (!isValidColor) {
            Log.p(TAG, String.format("Invalid color %d", new Object[]{Integer.valueOf(i)}));
        }
        return isValidColor;
    }

    private void doEscRightSquareBracket(byte b) {
        if (b == (byte) 7) {
            doOSC();
        } else if (b != (byte) 27) {
            collectOSCArgs(b);
        } else {
            continueSequence(9);
        }
    }

    private void doEscRightSquareBracketEsc(byte b) {
        if (b != (byte) 92) {
            collectOSCArgs((byte) 27);
            collectOSCArgs(b);
            continueSequence(8);
            return;
        }
        doOSC();
    }

    private void doOSC() {
        startTokenizingOSC();
        int nextOSCInt = nextOSCInt(59);
        switch (nextOSCInt) {
            case 0:
            case 1:
            case 2:
                changeTitle(nextOSCInt, nextOSCString(-1));
                break;
            default:
                unknownParameter(nextOSCInt);
                break;
        }
        finishSequence();
    }

    private void changeTitle(int i, String str) {
        if (i == 0 || i == 2) {
            this.mSession.setTitle(str);
        }
    }

    private void blockClear(int i, int i2, int i3) {
        blockClear(i, i2, i3, 1);
    }

    private void blockClear(int i, int i2, int i3, int i4) {
        this.mScreen.blockSet(i, i2, i3, i4, 32, getStyle());
    }

    private int getForeColor() {
        return this.mForeColor;
    }

    private int getBackColor() {
        return this.mBackColor;
    }

    private int getEffect() {
        return this.mEffect;
    }

    private int getStyle() {
        return TextStyle.encode(getForeColor(), getBackColor(), getEffect());
    }

    private void doSetMode(boolean z) {
        int arg0 = getArg0(0);
        if (arg0 != 4) {
            unknownParameter(arg0);
        } else {
            this.mInsertMode = z;
        }
    }

    private void setHorizontalVerticalPosition() {
        setCursorPosition(getArg1(1) - 1, getArg0(1) - 1);
    }

    private void setCursorPosition(int i, int i2) {
        int i3;
        int i4 = this.mRows;
        if ((this.mDecFlags & 64) != 0) {
            i4 = this.mTopMargin;
            i3 = this.mBottomMargin;
        } else {
            i3 = i4;
            i4 = 0;
        }
        setCursorRowCol(Math.max(i4, Math.min(i2 + i4, i3 - 1)), Math.max(0, Math.min(i, this.mColumns - 1)));
    }

    private void sendDeviceAttributes() {
        byte[] bArr = new byte[]{(byte) 27, (byte) 91, (byte) 63, (byte) 49, (byte) 59, (byte) 50, (byte) 99};
        this.mSession.write(bArr, 0, bArr.length);
    }

    private void scroll() {
        this.mScrollCounter++;
        this.mScreen.scroll(this.mTopMargin, this.mBottomMargin, getStyle());
    }

    private void parseArg(byte b) {
        if (b >= (byte) 48 && b <= (byte) 57) {
            if (this.mArgIndex < this.mArgs.length) {
                int i = this.mArgs[this.mArgIndex];
                int i2 = b - 48;
                if (i >= 0) {
                    i2 += i * 10;
                }
                this.mArgs[this.mArgIndex] = i2;
            }
            continueSequence();
        } else if (b == (byte) 59) {
            if (this.mArgIndex < this.mArgs.length) {
                this.mArgIndex++;
            }
            continueSequence();
        } else {
            unknownSequence(b);
        }
    }

    private int getArg0(int i) {
        return getArg(0, i, DEFAULT_TO_AUTOWRAP_ENABLED);
    }

    private int getArg1(int i) {
        return getArg(1, i, DEFAULT_TO_AUTOWRAP_ENABLED);
    }

    private int getArg(int i, int i2, boolean z) {
        int i3 = this.mArgs[i];
        return (i3 < 0 || (i3 == 0 && z)) ? i2 : i3;
    }

    private void startCollectingOSCArgs() {
        this.mOSCArgLength = 0;
    }

    private void collectOSCArgs(byte b) {
        if (this.mOSCArgLength < MAX_OSC_STRING_LENGTH) {
            byte[] bArr = this.mOSCArg;
            int i = this.mOSCArgLength;
            this.mOSCArgLength = i + 1;
            bArr[i] = b;
            continueSequence();
            return;
        }
        unknownSequence(b);
    }

    private void startTokenizingOSC() {
        this.mOSCArgTokenizerIndex = 0;
    }

    private String nextOSCString(int i) {
        int i2 = this.mOSCArgTokenizerIndex;
        int i3 = i2;
        while (this.mOSCArgTokenizerIndex < this.mOSCArgLength) {
            byte[] bArr = this.mOSCArg;
            int i4 = this.mOSCArgTokenizerIndex;
            this.mOSCArgTokenizerIndex = i4 + 1;
            if (bArr[i4] == i) {
                break;
            }
            i3++;
        }
        if (i2 == i3) {
            return "";
        }
        try {
            return new String(this.mOSCArg, i2, i3 - i2, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return new String(this.mOSCArg, i2, i3 - i2);
        }
    }

    private int nextOSCInt(int i) {
        int i2 = -1;
        while (this.mOSCArgTokenizerIndex < this.mOSCArgLength) {
            byte[] bArr = this.mOSCArg;
            int i3 = this.mOSCArgTokenizerIndex;
            this.mOSCArgTokenizerIndex = i3 + 1;
            byte b = bArr[i3];
            if (b == i) {
                break;
            } else if (b < (byte) 48 || b > (byte) 57) {
                unknownSequence(b);
            } else {
                if (i2 < 0) {
                    i2 = 0;
                }
                i2 = ((i2 * 10) + b) - 48;
            }
        }
        return i2;
    }

    private void unimplementedSequence(byte b) {
        Log.p(TAG, generateErrorString("unimplemented", b));
        finishSequence();
    }

    private void unknownSequence(byte b) {
        Log.p(TAG, generateErrorString("unknown", b));
        finishSequence();
    }

    private void unknownParameter(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown parameter");
        stringBuilder.append(i);
        Log.p(TAG, stringBuilder.toString());
        finishSequence();
    }

    private String generateErrorString(String str, byte b) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" sequence ");
        stringBuilder.append(" EscapeState: ");
        stringBuilder.append(this.mEscapeState);
        stringBuilder.append(" char: '");
        stringBuilder.append((char) b);
        stringBuilder.append("' (");
        stringBuilder.append(b);
        stringBuilder.append(")");
        int i = 1;
        for (int i2 = 0; i2 <= this.mArgIndex; i2++) {
            if (this.mArgs[i2] >= 0) {
                if (i != 0) {
                    stringBuilder.append("args = ");
                    i = 0;
                }
                stringBuilder.append(String.format("%d; ", new Object[]{Integer.valueOf(r3)}));
            }
        }
        return stringBuilder.toString();
    }

    private void finishSequence() {
        this.mEscapeState = 0;
    }

    private boolean autoWrapEnabled() {
        return (this.mDecFlags & 128) != 0 ? DEFAULT_TO_AUTOWRAP_ENABLED : false;
    }

    private void emit(int i, int i2) {
        boolean autoWrapEnabled = autoWrapEnabled();
        int charWidth = UnicodeTranscript.charWidth(i);
        boolean z = false;
        if (autoWrapEnabled && this.mCursorCol == this.mColumns - 1 && (this.mAboutToAutoWrap || charWidth == 2)) {
            this.mScreen.setLineWrap(this.mCursorRow);
            this.mCursorCol = 0;
            this.mJustWrapped = DEFAULT_TO_AUTOWRAP_ENABLED;
            if (this.mCursorRow + 1 < this.mBottomMargin) {
                this.mCursorRow++;
            } else {
                scroll();
            }
        }
        if (this.mInsertMode && charWidth != 0) {
            int i3 = this.mCursorCol + charWidth;
            if (i3 < this.mColumns) {
                this.mScreen.blockCopy(this.mCursorCol, this.mCursorRow, this.mColumns - i3, 1, i3, this.mCursorRow);
            }
        }
        if (charWidth != 0) {
            this.mScreen.set(this.mCursorCol, this.mCursorRow, i, i2);
            this.mJustWrapped = false;
        } else if (this.mJustWrapped) {
            this.mScreen.set(this.mColumns - this.mLastEmittedCharWidth, this.mCursorRow - 1, i, i2);
        } else {
            this.mScreen.set(this.mCursorCol - this.mLastEmittedCharWidth, this.mCursorRow, i, i2);
        }
        if (autoWrapEnabled) {
            if (this.mCursorCol == this.mColumns - 1) {
                z = DEFAULT_TO_AUTOWRAP_ENABLED;
            }
            this.mAboutToAutoWrap = z;
            if (this.mAboutToAutoWrap) {
                this.mScreen.setLineWrap(this.mCursorRow);
            }
        }
        this.mCursorCol = Math.min(this.mCursorCol + charWidth, this.mColumns - 1);
        if (charWidth > 0) {
            this.mLastEmittedCharWidth = charWidth;
        }
    }

    private void emit(int i) {
        emit(i, getStyle());
    }

    private void emit(byte b) {
        if (this.mUseAlternateCharSet) {
            emit(mSpecialGraphicsCharMap[b]);
        } else {
            emit((int) b);
        }
    }

    private void emit(char[] cArr) {
        if (Character.isHighSurrogate(cArr[0])) {
            emit(Character.toCodePoint(cArr[0], cArr[1]));
        } else {
            emit(cArr[0]);
        }
    }

    private void emit(char[] cArr, int i, int i2, int i3) {
        while (i < i2 && cArr[i] != 0) {
            if (Character.isHighSurrogate(cArr[i])) {
                char c = cArr[i];
                i++;
                emit(Character.toCodePoint(c, cArr[i]), i3);
            } else {
                emit(cArr[i], i3);
            }
            i++;
        }
    }

    private void setCursorRow(int i) {
        this.mCursorRow = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorCol(int i) {
        this.mCursorCol = i;
        this.mAboutToAutoWrap = false;
    }

    private void setCursorRowCol(int i, int i2) {
        this.mCursorRow = Math.min(i, this.mRows - 1);
        this.mCursorCol = Math.min(i2, this.mColumns - 1);
        this.mAboutToAutoWrap = false;
    }

    int getScrollCounter() {
        return this.mScrollCounter;
    }

    void clearScrollCounter() {
        this.mScrollCounter = 0;
    }

    public void reset() {
        this.mCursorRow = 0;
        this.mCursorCol = 0;
        this.mArgIndex = 0;
        this.mContinueSequence = false;
        this.mEscapeState = 0;
        this.mSavedCursorRow = 0;
        this.mSavedCursorCol = 0;
        this.mSavedEffect = 0;
        this.mSavedDecFlags_DECSC_DECRC = 0;
        this.mDecFlags = 0;
        this.mDecFlags |= 128;
        this.mDecFlags |= K_SHOW_CURSOR_MASK;
        this.mSavedDecFlags = 0;
        this.mInsertMode = false;
        this.mTopMargin = 0;
        this.mBottomMargin = this.mRows;
        this.mAboutToAutoWrap = false;
        this.mForeColor = this.mDefaultForeColor;
        this.mBackColor = this.mDefaultBackColor;
        this.mbKeypadApplicationMode = false;
        this.mAlternateCharSet = false;
        this.mCharSet[0] = 1;
        this.mCharSet[1] = 2;
        computeEffectiveCharSet();
        setDefaultTabStops();
        blockClear(0, 0, this.mColumns, this.mRows);
        setUTF8Mode(this.mDefaultUTF8Mode);
        this.mUTF8EscapeUsed = false;
        this.mUTF8ToFollow = 0;
        this.mUTF8ByteBuffer.clear();
        this.mInputCharBuffer.clear();
    }

    TerminalEmulator setKeyListener(TerminalKeyListener terminalKeyListener) {
        this.mKeyListener = terminalKeyListener;
        return this;
    }

    TerminalEmulator setDefaultUTF8Mode(boolean z) {
        this.mDefaultUTF8Mode = z;
        if (!this.mUTF8EscapeUsed) {
            setUTF8Mode(z);
        }
        return this;
    }

    private void setUTF8Mode(boolean z) {
        if (z && !this.mUTF8Mode) {
            this.mUTF8ToFollow = 0;
            this.mUTF8ByteBuffer.clear();
            this.mInputCharBuffer.clear();
        }
        this.mUTF8Mode = z;
        if (this.mUTF8ModeListener != null) {
            this.mUTF8ModeListener.onUpdate();
        }
    }

    boolean getUTF8Mode() {
        return this.mUTF8Mode;
    }

    TerminalEmulator setUTF8ModeListener(OnTerminalListener onTerminalListener) {
        this.mUTF8ModeListener = onTerminalListener;
        return this;
    }

    void setColorScheme(ColorScheme colorScheme) {
        this.mDefaultForeColor = 256;
        this.mDefaultBackColor = 257;
        this.mMainBuffer.setColorScheme(colorScheme);
        if (this.mAltBuffer != null) {
            this.mAltBuffer.setColorScheme(colorScheme);
        }
    }

    String getSelectedText(int i, int i2, int i3, int i4) {
        return this.mScreen.getSelectedText(null, i, i2, i3, i4, false);
    }

    public void finish() {
        if (this.mMainBuffer != null) {
            this.mMainBuffer.finish();
            this.mMainBuffer = null;
        }
        if (this.mAltBuffer != null) {
            this.mAltBuffer.finish();
            this.mAltBuffer = null;
        }
    }
}
