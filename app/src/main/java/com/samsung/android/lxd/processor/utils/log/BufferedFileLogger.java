package com.samsung.android.lxd.processor.utils.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.utils.IOExceptionHandler;
import com.samsung.android.lxd.processor.utils.IOExceptionHandler.IOProcessor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class BufferedFileLogger extends Logger {
    private static final int DAY_IN_SECONDS = 86400;
    private static final int LIMIT_FOR_LOGS_IN_DAYS = 1;
    private static String LOG_ENTRY_SECTION_SEPARATOR = " | ";
    private static final int MSG_FLUSH_LOG = 2;
    private static final int MSG_WRITE_LOG = 1;
    private static final String TAG = "BufferedFileLogger";
    private String LOG_FILES_DIR;
    private FileLoggerHandler mHandler;

    private class FileLoggerHandler extends Handler {
        private static final int RESET_LOG_COUNT_THRESHOLD = 100;
        private BufferedWriter mBufferedWriter;
        private String mFilename;
        private int mLogCount = 1;

        FileLoggerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (this.mLogCount > 100) {
                        this.mLogCount = 0;
                        BufferedFileLogger.this.cleanupLogs();
                    }
                    this.mLogCount++;
                    Date date = new Date();
                    StringBuilder stringBuilder = (StringBuilder) message.obj;
                    stringBuilder.insert(0, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ZZZZ", Locale.getDefault()).format(date));
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(BufferedFileLogger.this.LOG_FILES_DIR);
                    stringBuilder2.append("/");
                    stringBuilder2.append(BufferedFileLogger.getDayBoundaryTimestamp(date.getTime()));
                    String stringBuilder3 = stringBuilder2.toString();
                    if (this.mFilename == null || !this.mFilename.equals(stringBuilder3)) {
                        if (this.mBufferedWriter != null) {
                            Log.d(BufferedFileLogger.TAG, "Close File");
                            try {
                                this.mBufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d(BufferedFileLogger.TAG, "Create File");
                        this.mFilename = stringBuilder3;
                        File file = new File(this.mFilename);
                        if (!(file.exists() || file.getParentFile() == null)) {
                            file.getParentFile().mkdirs();
                        }
                        try {
                            this.mBufferedWriter = new BufferedWriter(new FileWriter(this.mFilename, true));
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            this.mFilename = null;
                            return;
                        }
                    }
                    try {
                        this.mBufferedWriter.write(stringBuilder.toString());
                        this.mBufferedWriter.newLine();
                        break;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return;
                    }
                case 2:
                    Log.d(BufferedFileLogger.TAG, "Flush File");
                    if (this.mBufferedWriter != null) {
                        try {
                            this.mBufferedWriter.flush();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    if (message.obj != null && (message.obj instanceof CountDownLatch)) {
                        ((CountDownLatch) message.obj).countDown();
                        break;
                    }
                default:
                    String str = BufferedFileLogger.TAG;
                    StringBuilder stringBuilder4 = new StringBuilder();
                    stringBuilder4.append("Invalid File Log Handler Message Type");
                    stringBuilder4.append(message.what);
                    Log.e(str, stringBuilder4.toString());
                    break;
            }
        }
    }

    private static final long getDayBoundaryTimestamp(long j) {
        j /= 1000;
        return j - (j % 86400);
    }

    public BufferedFileLogger(Context context, String str, Looper looper) {
        super(str);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getFilesDir().getAbsolutePath());
        stringBuilder.append("/logs");
        this.LOG_FILES_DIR = stringBuilder.toString();
        cleanupLogs();
        this.mHandler = new FileLoggerHandler(looper);
    }

    public void copyLogs(final FileOutputStream fileOutputStream) {
        File file = new File(this.LOG_FILES_DIR);
        if (file.exists()) {
            flush();
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                Arrays.sort(listFiles, new Comparator<File>() {
                    public int compare(File file, File file2) {
                        long parseLong = Long.parseLong(file.getName());
                        long parseLong2 = Long.parseLong(file2.getName());
                        if (parseLong > parseLong2) {
                            return 1;
                        }
                        return parseLong2 > parseLong ? -1 : 0;
                    }
                });
                for (final File file2 : listFiles) {
                    IOExceptionHandler.process(new IOProcessor<FileInputStream>() {
                        public FileInputStream open() {
                            return new FileInputStream(file2);
                        }

                        public void process(FileInputStream fileInputStream) {
                            byte[] bArr = new byte[KeycodeConstants.META_CTRL_ON];
                            while (true) {
                                int read = fileInputStream.read(bArr);
                                if (read >= 0) {
                                    fileOutputStream.write(bArr, 0, read);
                                } else {
                                    return;
                                }
                            }
                        }

                        public void close(FileInputStream fileInputStream) {
                            fileInputStream.close();
                        }
                    }, false);
                }
            }
        }
    }

    public void flush() {
        Log.i(TAG, "flush start");
        cleanupLogs();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(2, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "flush complete");
    }

    @SuppressLint({"LogTagMismatch"})
    public void log(int i, String str, String str2) {
        if (isLoggable(i)) {
            StringBuilder stringBuilder = new StringBuilder();
            if (str2 == null) {
                str2 = "MSG IS NULL";
            }
            if (str2.length() > 100001) {
                Log.w("BufferedFileLoggerAVOIDOOM", "trimming msg to avoid OOM");
                str2 = str2.substring(0, 100000);
            }
            stringBuilder.append(LOG_ENTRY_SECTION_SEPARATOR);
            stringBuilder.append(Process.myPid());
            stringBuilder.append(LOG_ENTRY_SECTION_SEPARATOR);
            stringBuilder.append(Process.myTid());
            stringBuilder.append(LOG_ENTRY_SECTION_SEPARATOR);
            stringBuilder.append(Log.stringValueOf(i));
            stringBuilder.append(LOG_ENTRY_SECTION_SEPARATOR);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("LxD_");
            stringBuilder2.append(str);
            stringBuilder.append(stringBuilder2.toString());
            stringBuilder.append(LOG_ENTRY_SECTION_SEPARATOR);
            stringBuilder.append(str2);
            this.mHandler.obtainMessage(1, stringBuilder).sendToTarget();
        }
    }

    private synchronized void cleanupLogs() {
        Log.d(TAG, "cleanupLogs");
        File file = new File(this.LOG_FILES_DIR);
        if (file.exists()) {
            File[] listFiles = file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return BufferedFileLogger.getDayBoundaryTimestamp(System.currentTimeMillis()) - Long.parseLong(str) > 86400;
                }
            });
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }

    public synchronized void forceCleanLogs() {
        Log.d(TAG, "forceCleanLogs");
        File file = new File(this.LOG_FILES_DIR);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
        }
    }
}
