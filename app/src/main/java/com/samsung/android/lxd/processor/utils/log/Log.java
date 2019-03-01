package com.samsung.android.lxd.processor.utils.log;

import com.samsung.android.lxd.processor.utils.Utils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

public class Log {
    public static final int LVL_DEBUG = 2;
    public static final int LVL_ERROR = 6;
    public static final int LVL_INFO = 4;
    public static final int LVL_SENSITIVE = 1;
    public static final int LVL_VERBOSE = 3;
    public static final int LVL_WARNING = 5;
    private static ArrayList<Logger> mLoggerList = new ArrayList();

    public static String stringValueOf(int i) {
        switch (i) {
            case 1:
                return "S";
            case 2:
                return "D";
            case 3:
                return "V";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            default:
                return "UNKNOWN";
        }
    }

    private Log() {
    }

    public static synchronized boolean addLogger(Logger logger) {
        synchronized (Log.class) {
            if (logger == null) {
                return false;
            } else if (mLoggerList.contains(logger)) {
                return false;
            } else {
                boolean add = mLoggerList.add(logger);
                return add;
            }
        }
    }

    public static synchronized boolean removeLogger(Logger logger) {
        synchronized (Log.class) {
            if (logger == null) {
                return false;
            }
            logger.flush();
            boolean remove = mLoggerList.remove(logger);
            return remove;
        }
    }

    public static synchronized Logger getLogger(String str) {
        synchronized (Log.class) {
            if (str == null) {
                return null;
            }
            Iterator it = mLoggerList.iterator();
            while (it.hasNext()) {
                Logger logger = (Logger) it.next();
                if (logger.loggerName.equals(str)) {
                    return logger;
                }
            }
            return null;
        }
    }

    public static synchronized void flushLogger() {
        synchronized (Log.class) {
            Iterator it = mLoggerList.iterator();
            while (it.hasNext()) {
                ((Logger) it.next()).flush();
            }
        }
    }

    public static void p(String str, String str2) {
        if (Utils.isDebug()) {
            Iterator it = mLoggerList.iterator();
            while (it.hasNext()) {
                ((Logger) it.next()).log(2, str, str2);
            }
        }
    }

    public static void s(String str, String str2) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).log(1, str, str2);
        }
    }

    public static void d(String str, String str2) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).log(2, str, str2);
        }
    }

    public static void v(String str, String str2) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).log(3, str, str2);
        }
    }

    public static void i(String str, String str2) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).log(4, str, str2);
        }
    }

    public static void w(String str, String str2) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).log(5, str, str2);
        }
    }

    public static void e(String str, String str2) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            ((Logger) it.next()).log(6, str, str2);
        }
    }

    public static void s(String str, String str2, Throwable th) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            Logger logger = (Logger) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(10);
            stringBuilder.append(getStackTraceString(th));
            logger.log(6, str, stringBuilder.toString());
        }
    }

    public static void d(String str, String str2, Throwable th) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            Logger logger = (Logger) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(10);
            stringBuilder.append(getStackTraceString(th));
            logger.log(6, str, stringBuilder.toString());
        }
    }

    public static void v(String str, String str2, Throwable th) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            Logger logger = (Logger) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(10);
            stringBuilder.append(getStackTraceString(th));
            logger.log(6, str, stringBuilder.toString());
        }
    }

    public static void i(String str, String str2, Throwable th) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            Logger logger = (Logger) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(10);
            stringBuilder.append(getStackTraceString(th));
            logger.log(6, str, stringBuilder.toString());
        }
    }

    public static void w(String str, String str2, Throwable th) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            Logger logger = (Logger) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(10);
            stringBuilder.append(getStackTraceString(th));
            logger.log(6, str, stringBuilder.toString());
        }
    }

    public static void e(String str, String str2, Throwable th) {
        Iterator it = mLoggerList.iterator();
        while (it.hasNext()) {
            Logger logger = (Logger) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str2);
            stringBuilder.append(10);
            stringBuilder.append(getStackTraceString(th));
            logger.log(6, str, stringBuilder.toString());
        }
    }

    public static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
