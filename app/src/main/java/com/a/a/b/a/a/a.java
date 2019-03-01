package com.a.a.b.a.a;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/* compiled from: ISO8601Utils */
public class a {
    private static final TimeZone a = TimeZone.getTimeZone("UTC");

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cf A:{Catch:{ IndexOutOfBoundsException -> 0x01bd, NumberFormatException -> 0x01ba, IllegalArgumentException -> 0x01b7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c7 A:{Catch:{ IndexOutOfBoundsException -> 0x01bd, NumberFormatException -> 0x01ba, IllegalArgumentException -> 0x01b7 }} */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01c3  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Date a(String str, ParsePosition parsePosition) {
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
            index = a(str2, index, i);
            if (a(str2, i, '-')) {
                i++;
            }
            int i2 = i + 2;
            i = a(str2, i, i2);
            if (a(str2, i2, '-')) {
                i2++;
            }
            int i3 = i2 + 2;
            i2 = a(str2, i2, i3);
            boolean a = a(str2, i3, 'T');
            Calendar gregorianCalendar;
            if (a || str.length() > i3) {
                int i4;
                int i5;
                int min;
                int i6;
                if (a) {
                    i3++;
                    i6 = i3 + 2;
                    i3 = a(str2, i3, i6);
                    if (a(str2, i6, ':')) {
                        i6++;
                    }
                    i4 = i6 + 2;
                    i6 = a(str2, i6, i4);
                    if (a(str2, i4, ':')) {
                        i4++;
                    }
                    if (str.length() > i4) {
                        char charAt = str2.charAt(i4);
                        if (!(charAt == 'Z' || charAt == '+' || charAt == '-')) {
                            i5 = i4 + 2;
                            i4 = a(str2, i4, i5);
                            if (i4 > 59 && i4 < 63) {
                                i4 = 59;
                            }
                            if (a(str2, i5, '.')) {
                                i5++;
                                int a2 = a(str2, i5 + 1);
                                min = Math.min(a2, i5 + 3);
                                int a3 = a(str2, i5, min);
                                switch (min - i5) {
                                    case 1:
                                        a3 *= 100;
                                        break;
                                    case 2:
                                        a3 *= 10;
                                        break;
                                }
                                min = a3;
                                i5 = a2;
                            } else {
                                min = 0;
                            }
                            if (str.length() > i5) {
                                throw new IllegalArgumentException("No time zone indicator");
                            }
                            TimeZone timeZone;
                            char charAt2 = str2.charAt(i5);
                            if (charAt2 == 'Z') {
                                timeZone = a;
                                i5++;
                            } else if (charAt2 == '+' || charAt2 == '-') {
                                StringBuilder stringBuilder3;
                                String substring = str2.substring(i5);
                                if (substring.length() < 5) {
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append(substring);
                                    stringBuilder3.append("00");
                                    substring = stringBuilder3.toString();
                                }
                                i5 += substring.length();
                                if ("+0000".equals(substring) || "+00:00".equals(substring)) {
                                    timeZone = a;
                                } else {
                                    stringBuilder3 = new StringBuilder();
                                    stringBuilder3.append("GMT");
                                    stringBuilder3.append(substring);
                                    substring = stringBuilder3.toString();
                                    TimeZone timeZone2 = TimeZone.getTimeZone(substring);
                                    String id = timeZone2.getID();
                                    if (id.equals(substring) || id.replace(":", "").equals(substring)) {
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
                            } else {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Invalid time zone indicator '");
                                stringBuilder2.append(charAt2);
                                stringBuilder2.append("'");
                                throw new IndexOutOfBoundsException(stringBuilder2.toString());
                            }
                            gregorianCalendar = new GregorianCalendar(timeZone);
                            gregorianCalendar.setLenient(false);
                            gregorianCalendar.set(1, index);
                            gregorianCalendar.set(2, i - 1);
                            gregorianCalendar.set(5, i2);
                            gregorianCalendar.set(11, i3);
                            gregorianCalendar.set(12, i6);
                            gregorianCalendar.set(13, i4);
                            gregorianCalendar.set(14, min);
                            parsePosition2.setIndex(i5);
                            return gregorianCalendar.getTime();
                        }
                    }
                    i5 = i4;
                } else {
                    i5 = i3;
                    i3 = 0;
                    i6 = 0;
                }
                min = 0;
                i4 = 0;
                if (str.length() > i5) {
                }
            } else {
                gregorianCalendar = new GregorianCalendar(index, i - 1, i2);
                parsePosition2.setIndex(i3);
                return gregorianCalendar.getTime();
            }
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

    private static boolean a(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int a(String str, int i, int i2) {
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
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

    private static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
