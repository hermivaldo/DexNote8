package com.p031a.p032a.p034b;

import com.p031a.p032a.C0741j;
import com.p031a.p032a.p034b.p035a.C0709n;
import com.p031a.p032a.p038d.C0734c;
import java.io.Writer;

/* compiled from: Streams */
/* renamed from: com.a.a.b.j */
public final class C0728j {

    /* compiled from: Streams */
    /* renamed from: com.a.a.b.j$a */
    private static final class C0727a extends Writer {
        /* renamed from: a */
        private final Appendable f2326a;
        /* renamed from: b */
        private final C0726a f2327b = new C0726a();

        /* compiled from: Streams */
        /* renamed from: com.a.a.b.j$a$a */
        static class C0726a implements CharSequence {
            /* renamed from: a */
            char[] f2325a;

            C0726a() {
            }

            public int length() {
                return this.f2325a.length;
            }

            public char charAt(int i) {
                return this.f2325a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.f2325a, i, i2 - i);
            }
        }

        public void close() {
        }

        public void flush() {
        }

        C0727a(Appendable appendable) {
            this.f2326a = appendable;
        }

        public void write(char[] cArr, int i, int i2) {
            this.f2327b.f2325a = cArr;
            this.f2326a.append(this.f2327b, i, i2 + i);
        }

        public void write(int i) {
            this.f2326a.append((char) i);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static com.p031a.p032a.C0741j m3011a(com.p031a.p032a.p038d.C0732a r2) {
        /*
        r2.mo599f();	 Catch:{ EOFException -> 0x0024, d -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        r0 = 0;
        r1 = com.p031a.p032a.p034b.p035a.C0709n.f2261X;	 Catch:{ EOFException -> 0x000d, d -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        r2 = r1.mo592b(r2);	 Catch:{ EOFException -> 0x000d, d -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        r2 = (com.p031a.p032a.C0741j) r2;	 Catch:{ EOFException -> 0x000d, d -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
        return r2;
    L_0x000d:
        r2 = move-exception;
        goto L_0x0026;
    L_0x000f:
        r2 = move-exception;
        r0 = new com.a.a.r;
        r0.<init>(r2);
        throw r0;
    L_0x0016:
        r2 = move-exception;
        r0 = new com.a.a.k;
        r0.<init>(r2);
        throw r0;
    L_0x001d:
        r2 = move-exception;
        r0 = new com.a.a.r;
        r0.<init>(r2);
        throw r0;
    L_0x0024:
        r2 = move-exception;
        r0 = 1;
    L_0x0026:
        if (r0 == 0) goto L_0x002b;
    L_0x0028:
        r2 = com.p031a.p032a.C1276l.f4314a;
        return r2;
    L_0x002b:
        r0 = new com.a.a.r;
        r0.<init>(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.b.j.a(com.a.a.d.a):com.a.a.j");
    }

    /* renamed from: a */
    public static void m3013a(C0741j c0741j, C0734c c0734c) {
        C0709n.f2261X.mo591a(c0734c, c0741j);
    }

    /* renamed from: a */
    public static Writer m3012a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new C0727a(appendable);
    }
}
