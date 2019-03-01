package com.samsung.android.lxd.processor.network.channel.pty.internal;

import android.os.Build.VERSION;
import android.view.KeyCharacterMap;

public abstract class KeyCharacterMapCompat {
    public static final int MODIFIER_BEHAVIOR_CHORDED = 0;
    public static final int MODIFIER_BEHAVIOR_CHORDED_OR_TOGGLED = 1;

    private static class KeyCharacterMapApi11OrLater extends KeyCharacterMapCompat {
        private KeyCharacterMap mMap;

        public KeyCharacterMapApi11OrLater(Object obj) {
            this.mMap = (KeyCharacterMap) obj;
        }

        public int getModifierBehaviour() {
            return this.mMap.getModifierBehavior();
        }
    }

    public abstract int getModifierBehaviour();

    public static KeyCharacterMapCompat wrap(Object obj) {
        return (obj == null || VERSION.SDK_INT < 11) ? null : new KeyCharacterMapApi11OrLater(obj);
    }
}
