package com.p031a.p032a;

import com.p031a.p032a.p034b.C0710a;
import com.p031a.p032a.p034b.C0717f;
import java.math.BigInteger;

/* compiled from: JsonPrimitive */
/* renamed from: com.a.a.o */
public final class C1278o extends C0741j {
    /* renamed from: a */
    private static final Class<?>[] f4316a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    /* renamed from: b */
    private Object f4317b;

    public C1278o(Boolean bool) {
        m5985a((Object) bool);
    }

    public C1278o(Number number) {
        m5985a((Object) number);
    }

    public C1278o(String str) {
        m5985a((Object) str);
    }

    /* renamed from: a */
    void m5985a(Object obj) {
        if (obj instanceof Character) {
            this.f4317b = String.valueOf(((Character) obj).charValue());
            return;
        }
        boolean z;
        if (!(obj instanceof Number)) {
            if (!C1278o.m5983b(obj)) {
                z = false;
                C0710a.m2967a(z);
                this.f4317b = obj;
            }
        }
        z = true;
        C0710a.m2967a(z);
        this.f4317b = obj;
    }

    /* renamed from: o */
    public boolean m5992o() {
        return this.f4317b instanceof Boolean;
    }

    /* renamed from: n */
    Boolean mo637n() {
        return (Boolean) this.f4317b;
    }

    /* renamed from: f */
    public boolean mo636f() {
        if (m5992o()) {
            return mo637n().booleanValue();
        }
        return Boolean.parseBoolean(mo632b());
    }

    /* renamed from: p */
    public boolean m5993p() {
        return this.f4317b instanceof Number;
    }

    /* renamed from: a */
    public Number mo631a() {
        return this.f4317b instanceof String ? new C0717f((String) this.f4317b) : (Number) this.f4317b;
    }

    /* renamed from: q */
    public boolean m5994q() {
        return this.f4317b instanceof String;
    }

    /* renamed from: b */
    public String mo632b() {
        if (m5993p()) {
            return mo631a().toString();
        }
        if (m5992o()) {
            return mo637n().toString();
        }
        return (String) this.f4317b;
    }

    /* renamed from: c */
    public double mo633c() {
        return m5993p() ? mo631a().doubleValue() : Double.parseDouble(mo632b());
    }

    /* renamed from: d */
    public long mo634d() {
        return m5993p() ? mo631a().longValue() : Long.parseLong(mo632b());
    }

    /* renamed from: e */
    public int mo635e() {
        return m5993p() ? mo631a().intValue() : Integer.parseInt(mo632b());
    }

    /* renamed from: b */
    private static boolean m5983b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        obj = obj.getClass();
        for (Class isAssignableFrom : f4316a) {
            if (isAssignableFrom.isAssignableFrom(obj)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.f4317b == null) {
            return 31;
        }
        long longValue;
        if (C1278o.m5982a(this)) {
            longValue = mo631a().longValue();
            return (int) ((longValue >>> 32) ^ longValue);
        } else if (!(this.f4317b instanceof Number)) {
            return this.f4317b.hashCode();
        } else {
            longValue = Double.doubleToLongBits(mo631a().doubleValue());
            return (int) ((longValue >>> 32) ^ longValue);
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (getClass() == obj.getClass()) {
                C1278o c1278o = (C1278o) obj;
                if (this.f4317b == null) {
                    if (c1278o.f4317b != null) {
                        z = false;
                    }
                    return z;
                } else if (C1278o.m5982a(this) && C1278o.m5982a(c1278o)) {
                    if (mo631a().longValue() != c1278o.mo631a().longValue()) {
                        z = false;
                    }
                    return z;
                } else if (!(this.f4317b instanceof Number) || !(c1278o.f4317b instanceof Number)) {
                    return this.f4317b.equals(c1278o.f4317b);
                } else {
                    double doubleValue = mo631a().doubleValue();
                    double doubleValue2 = c1278o.mo631a().doubleValue();
                    if (doubleValue != doubleValue2) {
                        if (!Double.isNaN(doubleValue) || !Double.isNaN(doubleValue2)) {
                            z = false;
                        }
                    }
                    return z;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m5982a(C1278o c1278o) {
        boolean z = false;
        if (!(c1278o.f4317b instanceof Number)) {
            return false;
        }
        Number number = (Number) c1278o.f4317b;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte) != null) {
            z = true;
        }
        return z;
    }
}
