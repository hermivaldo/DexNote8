package com.p031a.p032a;

import com.p031a.p032a.p034b.C0723g;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: JsonObject */
/* renamed from: com.a.a.m */
public final class C1277m extends C0741j {
    /* renamed from: a */
    private final C0723g<String, C0741j> f4315a = new C0723g();

    /* renamed from: a */
    public void m5980a(String str, C0741j c0741j) {
        if (c0741j == null) {
            c0741j = C1276l.f4314a;
        }
        this.f4315a.put(str, c0741j);
    }

    /* renamed from: o */
    public Set<Entry<String, C0741j>> m5981o() {
        return this.f4315a.entrySet();
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof C1277m) || !((C1277m) obj).f4315a.equals(this.f4315a)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.f4315a.hashCode();
    }
}
