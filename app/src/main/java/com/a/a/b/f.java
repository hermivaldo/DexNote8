package com.a.a.b;

import java.math.BigDecimal;

/* compiled from: LazilyParsedNumber */
public final class f extends Number {
    private final String a;

    public f(String str) {
        this.a = str;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:6:0x000e, code:
            return (int) java.lang.Long.parseLong(r2.a);
     */
    /* JADX WARNING: Missing block: B:8:0x001a, code:
            return new java.math.BigDecimal(r2.a).intValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int intValue() {
        return Integer.parseInt(this.a);
    }

    public long longValue() {
        try {
            return Long.parseLong(this.a);
        } catch (NumberFormatException unused) {
            return new BigDecimal(this.a).longValue();
        }
    }

    public float floatValue() {
        return Float.parseFloat(this.a);
    }

    public double doubleValue() {
        return Double.parseDouble(this.a);
    }

    public String toString() {
        return this.a;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (!(this.a == fVar.a || this.a.equals(fVar.a))) {
            z = false;
        }
        return z;
    }
}
