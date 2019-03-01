package com.p031a.p032a.p034b.p035a.p036a;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* compiled from: ISO8601Utils */
/* renamed from: com.a.a.b.a.a.a */
public class C0703a {
    /* renamed from: a */
    private static final TimeZone f2232a = TimeZone.getTimeZone("UTC");

    /* renamed from: a */
    public static Date m2957a(String str, ParsePosition parsePosition) {
        Throwable th;
        String message;
        StringBuilder stringBuilder;
        ParseException parseException;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        StringBuilder stringBuilder2;
        try {
            int index = parsePosition.getIndex();
            int i = index + 4;
            index = C0703a.m2956a(str2, index, i);
            if (C0703a.m2958a(str2, i, '-')) {
                i++;
            }
            int i2 = i + 2;
            i = C0703a.m2956a(str2, i, i2);
            if (C0703a.m2958a(str2, i2, '-')) {
                i2++;
            }
            int i3 = i2 + 2;
            i2 = C0703a.m2956a(str2, i2, i3);
            boolean a = C0703a.m2958a(str2, i3, 'T');
            Calendar gregorianCalendar;
            if (a || str.length() > i3) {
                int i4;
                int i5;
                int i6;
                int min;
                char charAt;
                TimeZone timeZone;
                String substring;
                StringBuilder stringBuilder3;
                TimeZone timeZone2;
                String id;
                if (a) {
                    i3++;
                    i4 = i3 + 2;
                    i3 = C0703a.m2956a(str2, i3, i4);
                    if (C0703a.m2958a(str2, i4, ':')) {
                        i4++;
                    }
                    i5 = i4 + 2;
                    i4 = C0703a.m2956a(str2, i4, i5);
                    if (C0703a.m2958a(str2, i5, ':')) {
                        i5++;
                    }
                    if (str.length() > i5) {
                        char charAt2 = str2.charAt(i5);
                        if (!(charAt2 == 'Z' || charAt2 == '+' || charAt2 == '-')) {
                            i6 = i5 + 2;
                            i5 = C0703a.m2956a(str2, i5, i6);
                            if (i5 > 59 && i5 < 63) {
                                i5 = 59;
                            }
                            if (C0703a.m2958a(str2, i6, '.')) {
                                i6++;
                                int a2 = C0703a.m2955a(str2, i6 + 1);
                                min = Math.min(a2, i6 + 3);
                                int a3 = C0703a.m2956a(str2, i6, min);
                                switch (min - i6) {
                                    case 1:
                                        a3 *= 100;
                                        break;
                                    case 2:
                                        a3 *= 10;
                                        break;
                                }
                                min = a3;
                                i6 = a2;
                            } else {
                                min = 0;
                            }
                            if (str.length() > i6) {
                                throw new IllegalArgumentException("No time zone indicator");
                            }
                            charAt = str2.charAt(i6);
                            if (charAt != 'Z') {
                                timeZone = f2232a;
                                i6++;
                            } else {
                                if (charAt != '+') {
                                    if (charAt == '-') {
                                        stringBuilder2 = new StringBuilder();
                                        stringBuilder2.append("Invalid time zone indicator '");
                                        stringBuilder2.append(charAt);
                                        stringBuilder2.append("'");
                                        throw new IndexOutOfBoundsException(stringBuilder2.toString());
                                    }
                                }
                                substring = str2.substring(i6);
                                if (substring.length() >= 5) {
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(substring);
                                    stringBuilder3.append("00");
                                    substring = stringBuilder3.toString();
                                }
                                i6 += substring.length();
                                if (!"+0000".equals(substring)) {
                                    if ("+00:00".equals(substring)) {
                                        stringBuilder3 = new StringBuilder();
                                        stringBuilder3.append("GMT");
                                        stringBuilder3.append(substring);
                                        substring = stringBuilder3.toString();
                                        timeZone2 = TimeZone.getTimeZone(substring);
                                        id = timeZone2.getID();
                                        if (!id.equals(substring) || id.replace(":", "").equals(substring)) {
                                            timeZone = timeZone2;
                                        } else {
                                            stringBuilder2 = new StringBuilder();
                                            stringBuilder2.append("Mismatching time zone indicator: ");
                                            stringBuilder2.append(substring);
                                            stringBuilder2.append(" given, resolves to ");
                                            stringBuilder2.append(timeZone2.getID());
                                            throw new IndexOutOfBoundsException(stringBuilder2.toString());
                                        }
                                    }
                                }
                                timeZone = f2232a;
                            }
                            gregorianCalendar = new GregorianCalendar(timeZone);
                            gregorianCalendar.setLenient(false);
                            gregorianCalendar.set(1, index);
                            gregorianCalendar.set(2, i - 1);
                            gregorianCalendar.set(5, i2);
                            gregorianCalendar.set(11, i3);
                            gregorianCalendar.set(12, i4);
                            gregorianCalendar.set(13, i5);
                            gregorianCalendar.set(14, min);
                            parsePosition2.setIndex(i6);
                            return gregorianCalendar.getTime();
                        }
                    }
                    i6 = i5;
                } else {
                    i6 = i3;
                    i3 = 0;
                    i4 = 0;
                }
                min = 0;
                i5 = 0;
                if (str.length() > i6) {
                    charAt = str2.charAt(i6);
                    if (charAt != 'Z') {
                        if (charAt != '+') {
                            if (charAt == '-') {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Invalid time zone indicator '");
                                stringBuilder2.append(charAt);
                                stringBuilder2.append("'");
                                throw new IndexOutOfBoundsException(stringBuilder2.toString());
                            }
                        }
                        substring = str2.substring(i6);
                        if (substring.length() >= 5) {
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(substring);
                            stringBuilder3.append("00");
                            substring = stringBuilder3.toString();
                        }
                        i6 += substring.length();
                        if ("+0000".equals(substring)) {
                            if ("+00:00".equals(substring)) {
                                stringBuilder3 = new StringBuilder();
                                stringBuilder3.append("GMT");
                                stringBuilder3.append(substring);
                                substring = stringBuilder3.toString();
                                timeZone2 = TimeZone.getTimeZone(substring);
                                id = timeZone2.getID();
                                if (id.equals(substring)) {
                                }
                                timeZone = timeZone2;
                            }
                        }
                        timeZone = f2232a;
                    } else {
                        timeZone = f2232a;
                        i6++;
                    }
                    gregorianCalendar = new GregorianCalendar(timeZone);
                    gregorianCalendar.setLenient(false);
                    gregorianCalendar.set(1, index);
                    gregorianCalendar.set(2, i - 1);
                    gregorianCalendar.set(5, i2);
                    gregorianCalendar.set(11, i3);
                    gregorianCalendar.set(12, i4);
                    gregorianCalendar.set(13, i5);
                    gregorianCalendar.set(14, min);
                    parsePosition2.setIndex(i6);
                    return gregorianCalendar.getTime();
                }
                throw new IllegalArgumentException("No time zone indicator");
            }
            gregorianCalendar = new GregorianCalendar(index, i - 1, i2);
            parsePosition2.setIndex(i3);
            return gregorianCalendar.getTime();
        } catch (Throwable e) {
            th = e;
            if (str2 == null) {
                str2 = null;
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append('\"');
                stringBuilder2.append(str2);
                stringBuilder2.append("'");
                str2 = stringBuilder2.toString();
            }
            message = th.getMessage();
            if (message == null || message.isEmpty()) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("(");
                stringBuilder2.append(th.getClass().getName());
                stringBuilder2.append(")");
                message = stringBuilder2.toString();
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to parse date [");
            stringBuilder.append(str2);
            stringBuilder.append("]: ");
            stringBuilder.append(message);
            parseException = new ParseException(stringBuilder.toString(), parsePosition.getIndex());
            parseException.initCause(th);
            throw parseException;
        } catch (Throwable e2) {
            th = e2;
            if (str2 == null) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append('\"');
                stringBuilder2.append(str2);
                stringBuilder2.append("'");
                str2 = stringBuilder2.toString();
            } else {
                str2 = null;
            }
            message = th.getMessage();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("(");
            stringBuilder2.append(th.getClass().getName());
            stringBuilder2.append(")");
            message = stringBuilder2.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to parse date [");
            stringBuilder.append(str2);
            stringBuilder.append("]: ");
            stringBuilder.append(message);
            parseException = new ParseException(stringBuilder.toString(), parsePosition.getIndex());
            parseException.initCause(th);
            throw parseException;
        } catch (Throwable e22) {
            th = e22;
            if (str2 == null) {
                str2 = null;
            } else {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append('\"');
                stringBuilder2.append(str2);
                stringBuilder2.append("'");
                str2 = stringBuilder2.toString();
            }
            message = th.getMessage();
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("(");
            stringBuilder2.append(th.getClass().getName());
            stringBuilder2.append(")");
            message = stringBuilder2.toString();
            stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to parse date [");
            stringBuilder.append(str2);
            stringBuilder.append("]: ");
            stringBuilder.append(message);
            parseException = new ParseException(stringBuilder.toString(), parsePosition.getIndex());
            parseException.initCause(th);
            throw parseException;
        }
    }

    /* renamed from: a */
    private static boolean m2958a(String str, int i, char c) {
        return (i >= str.length() || str.charAt(i) != c) ? null : true;
    }

    /* renamed from: a */
    private static int m2956a(String str, int i, int i2) {
        if (i >= 0 && i2 <= str.length()) {
            if (i <= i2) {
                int i3;
                int digit;
                StringBuilder stringBuilder;
                if (i < i2) {
                    i3 = i + 1;
                    digit = Character.digit(str.charAt(i), 10);
                    if (digit < 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid number: ");
                        stringBuilder.append(str.substring(i, i2));
                        throw new NumberFormatException(stringBuilder.toString());
                    }
                    digit = -digit;
                } else {
                    digit = 0;
                    i3 = i;
                }
                while (i3 < i2) {
                    int i4 = i3 + 1;
                    i3 = Character.digit(str.charAt(i3), 10);
                    if (i3 < 0) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("Invalid number: ");
                        stringBuilder.append(str.substring(i, i2));
                        throw new NumberFormatException(stringBuilder.toString());
                    }
                    digit = (digit * 10) - i3;
                    i3 = i4;
                }
                return -digit;
            }
        }
        throw new NumberFormatException(str);
    }

    /* renamed from: a */
    private static int m2955a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt >= '0') {
                if (charAt <= '9') {
                    i++;
                }
            }
            return i;
        }
        return str.length();
    }
}
