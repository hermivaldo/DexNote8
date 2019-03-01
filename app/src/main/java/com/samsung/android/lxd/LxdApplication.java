package com.samsung.android.lxd;

import android.app.Application;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import com.samsung.android.lxd.a.m;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.processor.utils.IOExceptionHandler;
import com.samsung.android.lxd.processor.utils.IOExceptionHandler.IOProcessor;
import com.samsung.android.lxd.processor.utils.LxDUncaughtExceptionHandler;
import com.samsung.android.lxd.processor.utils.log.BufferedFileLogger;
import com.samsung.android.lxd.processor.utils.log.ConsoleLogger;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.io.IOException;

public class LxdApplication extends Application {
    private static final String a = "LxdApplication";
    private static LxdApplication b;

    public void onCreate() {
        super.onCreate();
        Thread.setDefaultUncaughtExceptionHandler(new LxDUncaughtExceptionHandler());
        HandlerThread handlerThread = new HandlerThread("BufferedLogFileWriterThread", 10);
        handlerThread.start();
        Log.addLogger(new ConsoleLogger("Console-Logger"));
        Log.addLogger(new BufferedFileLogger(this, "File-Logger", handlerThread.getLooper()));
        b = this;
        registerActivityLifecycleCallbacks(new f());
        Log.i(a, "onCreate, BUILD_CL: d9c471f");
        m.a(b);
        d.a().b();
        DesktopReceiver.a().b();
        DiagnosticReceiver.a().c();
    }

    public static LxdApplication a() {
        return b;
    }

    public static void a(String str) {
        try {
            File file = new File(b.getFilesDir(), str);
            if (file.exists()) {
                o.c(file);
            }
            file.mkdirs();
            final ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(file, "lxd.log"), 738197504);
            IOExceptionHandler.process(new IOProcessor<AutoCloseOutputStream>() {
                /* renamed from: a */
                public AutoCloseOutputStream open() {
                    return new AutoCloseOutputStream(open);
                }

                /* renamed from: a */
                public void process(AutoCloseOutputStream autoCloseOutputStream) {
                    String str = Build.FINGERPRINT;
                    String str2 = Build.MANUFACTURER;
                    String str3 = Build.MODEL;
                    String str4 = VERSION.SDK;
                    String u = o.u();
                    int v = o.v();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("FINGERPRINT: ");
                    stringBuilder.append(str);
                    stringBuilder.append("\nManufacturer: ");
                    stringBuilder.append(str2);
                    stringBuilder.append("\nModel: ");
                    stringBuilder.append(str3);
                    stringBuilder.append("\nAPI Version: ");
                    stringBuilder.append(str4);
                    stringBuilder.append("\nApplication Version Name: ");
                    stringBuilder.append(u);
                    stringBuilder.append("\nApplication Version Code: ");
                    stringBuilder.append(v);
                    stringBuilder.append("\nApplication Build CL: ");
                    stringBuilder.append("d9c471f");
                    stringBuilder.append("\n");
                    str = stringBuilder.toString();
                    try {
                        autoCloseOutputStream.write("---------------------------------------------------------------\n".getBytes());
                        autoCloseOutputStream.write(str.getBytes());
                        autoCloseOutputStream.write("---------------------------------------------------------------\n".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BufferedFileLogger bufferedFileLogger = (BufferedFileLogger) Log.getLogger("File-Logger");
                    if (bufferedFileLogger != null) {
                        bufferedFileLogger.copyLogs(autoCloseOutputStream);
                    } else {
                        Log.e(LxdApplication.a, "No FILE LOGGER.");
                    }
                }

                /* renamed from: b */
                public void flush(AutoCloseOutputStream autoCloseOutputStream) {
                    autoCloseOutputStream.flush();
                }

                /* renamed from: c */
                public void sync(AutoCloseOutputStream autoCloseOutputStream) {
                    autoCloseOutputStream.getFD().sync();
                }

                /* renamed from: d */
                public void close(AutoCloseOutputStream autoCloseOutputStream) {
                    autoCloseOutputStream.close();
                }
            }, true);
        } catch (Throwable e) {
            Log.e(a, e.getMessage(), e);
        }
    }
}
