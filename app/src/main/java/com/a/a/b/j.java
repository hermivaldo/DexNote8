package com.a.a.b;

import com.a.a.b.a.n;
import com.a.a.d.c;
import java.io.EOFException;
import java.io.Writer;

/* compiled from: Streams */
public final class j {

    /* compiled from: Streams */
    private static final class a extends Writer {
        private final Appendable a;
        private final a b = new a();

        /* compiled from: Streams */
        static class a implements CharSequence {
            char[] a;

            a() {
            }

            public int length() {
                return this.a.length;
            }

            public char charAt(int i) {
                return this.a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }

        public void close() {
        }

        public void flush() {
        }

        a(Appendable appendable) {
            this.a = appendable;
        }

        public void write(char[] cArr, int i, int i2) {
            this.b.a = cArr;
            this.a.append(this.b, i, i2 + i);
        }

        public void write(int i) {
            this.a.append((char) i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A:{Splitter: B:0:0x0000, ExcHandler: com.a.a.d.d (r2_6 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016 A:{Splitter: B:0:0x0000, ExcHandler: java.io.IOException (r2_5 'e' java.lang.Throwable)} */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f A:{Splitter: B:0:0x0000, ExcHandler: java.lang.NumberFormatException (r2_4 'e' java.lang.Throwable)} */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing block: B:6:0x000d, code:
            r2 = e;
     */
    /* JADX WARNING: Missing block: B:7:0x000f, code:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:9:0x0015, code:
            throw new com.a.a.r(r2);
     */
    /* JADX WARNING: Missing block: B:10:0x0016, code:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:12:0x001c, code:
            throw new com.a.a.k(r2);
     */
    /* JADX WARNING: Missing block: B:13:0x001d, code:
            r2 = move-exception;
     */
    /* JADX WARNING: Missing block: B:15:0x0023, code:
            throw new com.a.a.r(r2);
     */
    /* JADX WARNING: Missing block: B:20:0x002a, code:
            return com.a.a.l.a;
     */
    /* JADX WARNING: Missing block: B:22:0x0030, code:
            throw new com.a.a.r(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.a.a.j a(com.a.a.d.a aVar) {
        Object obj;
        try {
            aVar.f();
            obj = null;
            return (com.a.a.j) n.X.b(aVar);
        } catch (EOFException e) {
            Throwable e2 = e;
            obj = 1;
            if (obj == null) {
            }
        } catch (Throwable e3) {
        } catch (Throwable e4) {
        } catch (Throwable e5) {
        }
    }

    public static void a(com.a.a.j jVar, c cVar) {
        n.X.a(cVar, jVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }
}
