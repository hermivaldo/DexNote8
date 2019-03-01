package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import com.samsung.android.lxd.processor.utils.log.Log;

public class TerminalInputConnection extends BaseInputConnection {
    private static final String TAG = "TerminalInputConnection";
    private int mComposingTextEnd;
    private int mComposingTextStart;
    private int mCursor;
    private final ITerminalViewHelper mHelper;
    private int mSelectedTextEnd;
    private int mSelectedTextStart;

    TerminalInputConnection(ITerminalViewHelper iTerminalViewHelper, View view, boolean z) {
        super(view, z);
        this.mHelper = iTerminalViewHelper;
    }

    private String getImeBuffer() {
        return this.mHelper.getImeBuffer();
    }

    private void setImeBuffer(String str) {
        if (!str.equals(getImeBuffer())) {
            this.mHelper.invalidate();
        }
        this.mHelper.setImeBuffer(str);
    }

    private void sendText(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (Character.isHighSurrogate(charAt)) {
                i++;
                mapAndSend(i < length ? Character.toCodePoint(charAt, charSequence.charAt(i)) : 65533);
            } else {
                mapAndSend(charAt);
            }
            i++;
        }
    }

    private void mapAndSend(int i) {
        i = this.mHelper.getKeyListener().mapControlChar(i);
        if (i < TerminalKeyListener.KEYCODE_OFFSET) {
            this.mHelper.getSession().write(i);
        } else {
            this.mHelper.getKeyListener().handleKeyCode(i - TerminalKeyListener.KEYCODE_OFFSET, null, this.mHelper.getKeypadApplicationMode());
        }
    }

    public boolean beginBatchEdit() {
        Log.p(TAG, "beginBatchEdit");
        setImeBuffer("");
        this.mCursor = 0;
        this.mComposingTextStart = 0;
        this.mComposingTextEnd = 0;
        return true;
    }

    public boolean clearMetaKeyStates(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("clearMetaKeyStates ");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        return false;
    }

    public boolean commitCompletion(CompletionInfo completionInfo) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("commitCompletion ");
        stringBuilder.append(completionInfo);
        Log.p(str, stringBuilder.toString());
        return false;
    }

    public boolean endBatchEdit() {
        Log.p(TAG, "endBatchEdit");
        return true;
    }

    public boolean finishComposingText() {
        Log.p(TAG, "finishComposingText");
        sendText(getImeBuffer());
        setImeBuffer("");
        this.mComposingTextStart = 0;
        this.mComposingTextEnd = 0;
        this.mCursor = 0;
        return true;
    }

    public int getCursorCapsMode(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getCursorCapsMode(");
        stringBuilder.append(i);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        return (i & KeycodeConstants.META_CTRL_ON) != 0 ? KeycodeConstants.META_CTRL_ON : 0;
    }

    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getExtractedText");
        stringBuilder.append(extractedTextRequest);
        stringBuilder.append(",");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        return null;
    }

    public CharSequence getTextAfterCursor(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getTextAfterCursor(");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        i = Math.min(i, getImeBuffer().length() - this.mCursor);
        return (i <= 0 || this.mCursor < 0 || this.mCursor >= getImeBuffer().length()) ? "" : getImeBuffer().substring(this.mCursor, this.mCursor + i);
    }

    public CharSequence getTextBeforeCursor(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getTextBeforeCursor(");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        i = Math.min(i, this.mCursor);
        return (i <= 0 || this.mCursor < 0 || this.mCursor >= getImeBuffer().length()) ? "" : getImeBuffer().substring(this.mCursor - i, this.mCursor);
    }

    public boolean performContextMenuAction(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("performContextMenuAction");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        return true;
    }

    public boolean performPrivateCommand(String str, Bundle bundle) {
        String str2 = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("performPrivateCommand");
        stringBuilder.append(str);
        stringBuilder.append(",");
        stringBuilder.append(bundle);
        Log.p(str2, stringBuilder.toString());
        return true;
    }

    public boolean reportFullscreenMode(boolean z) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("reportFullscreenMode");
        stringBuilder.append(z);
        Log.p(str, stringBuilder.toString());
        return true;
    }

    public boolean commitCorrection(CorrectionInfo correctionInfo) {
        Log.p(TAG, "commitCorrection");
        return true;
    }

    public boolean commitText(CharSequence charSequence, int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("commitText(\"");
        stringBuilder.append(charSequence);
        stringBuilder.append("\", ");
        stringBuilder.append(i);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        clearComposingText();
        sendText(charSequence);
        setImeBuffer("");
        this.mCursor = 0;
        return true;
    }

    private void clearComposingText() {
        int length = getImeBuffer().length();
        if (this.mComposingTextStart > length || this.mComposingTextEnd > length) {
            this.mComposingTextStart = 0;
            this.mComposingTextEnd = 0;
            return;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getImeBuffer().substring(0, this.mComposingTextStart));
        stringBuilder.append(getImeBuffer().substring(this.mComposingTextEnd));
        setImeBuffer(stringBuilder.toString());
        if (this.mCursor >= this.mComposingTextStart) {
            if (this.mCursor < this.mComposingTextEnd) {
                this.mCursor = this.mComposingTextStart;
            } else {
                this.mCursor -= this.mComposingTextEnd - this.mComposingTextStart;
            }
        }
        this.mComposingTextStart = 0;
        this.mComposingTextEnd = 0;
    }

    public boolean deleteSurroundingText(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("deleteSurroundingText(");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        if (i > 0) {
            for (i2 = 0; i2 < i; i2++) {
                sendKeyEvent(new KeyEvent(0, 67));
            }
        } else if (i == 0 && i2 == 0) {
            sendKeyEvent(new KeyEvent(0, 67));
        }
        return true;
    }

    public boolean performEditorAction(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("performEditorAction(");
        stringBuilder.append(i);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        if (i == 0) {
            sendText("\r");
        }
        return true;
    }

    public boolean sendKeyEvent(KeyEvent keyEvent) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sendKeyEvent(");
        stringBuilder.append(keyEvent);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        this.mHelper.dispatchKeyEvent(keyEvent);
        return true;
    }

    public boolean setComposingText(CharSequence charSequence, int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setComposingText(\"");
        stringBuilder.append(charSequence);
        stringBuilder.append("\", ");
        stringBuilder.append(i);
        stringBuilder.append(")");
        Log.p(str, stringBuilder.toString());
        int length = getImeBuffer().length();
        if (this.mComposingTextStart > length || this.mComposingTextEnd > length) {
            return false;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(getImeBuffer().substring(0, this.mComposingTextStart));
        stringBuilder2.append(charSequence);
        stringBuilder2.append(getImeBuffer().substring(this.mComposingTextEnd));
        setImeBuffer(stringBuilder2.toString());
        this.mComposingTextEnd = this.mComposingTextStart + charSequence.length();
        this.mCursor = i > 0 ? (this.mComposingTextEnd + i) - 1 : this.mComposingTextStart - i;
        return true;
    }

    public boolean setSelection(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setSelection");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        Log.p(str, stringBuilder.toString());
        int length = getImeBuffer().length();
        if (i == i2 && i > 0 && i < length) {
            this.mSelectedTextEnd = 0;
            this.mSelectedTextStart = 0;
            this.mCursor = i;
        } else if (i < i2 && i > 0 && i2 < length) {
            this.mSelectedTextStart = i;
            this.mSelectedTextEnd = i2;
            this.mCursor = i;
        }
        return true;
    }

    public boolean setComposingRegion(int i, int i2) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setComposingRegion ");
        stringBuilder.append(i);
        stringBuilder.append(",");
        stringBuilder.append(i2);
        Log.p(str, stringBuilder.toString());
        if (i < i2 && i > 0 && i2 < getImeBuffer().length()) {
            clearComposingText();
            this.mComposingTextStart = i;
            this.mComposingTextEnd = i2;
        }
        return true;
    }

    public CharSequence getSelectedText(int i) {
        String str = TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("getSelectedText ");
        stringBuilder.append(i);
        Log.p(str, stringBuilder.toString());
        return (this.mSelectedTextEnd >= getImeBuffer().length() || this.mSelectedTextStart > this.mSelectedTextEnd) ? "" : getImeBuffer().substring(this.mSelectedTextStart, this.mSelectedTextEnd + 1);
    }
}
