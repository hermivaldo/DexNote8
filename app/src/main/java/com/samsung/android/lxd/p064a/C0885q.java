package com.samsung.android.lxd.p064a;

import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* compiled from: ZipHelper */
/* renamed from: com.samsung.android.lxd.a.q */
public class C0885q {
    /* renamed from: a */
    private static final String f2740a = "q";

    /* renamed from: a */
    public static void m3550a(List<File> list, File file) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        byte[] bArr = new byte[2048];
        for (File file2 : list) {
            String str = f2740a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("File: ");
            stringBuilder.append(file2.toString());
            Log.m3853d(str, stringBuilder.toString());
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file2), 2048);
            String absolutePath = file2.getAbsolutePath();
            try {
                zipOutputStream.putNextEntry(new ZipEntry(absolutePath.substring(absolutePath.lastIndexOf("/") + 1)));
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 2048);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
                bufferedInputStream.close();
            } catch (Throwable th) {
                zipOutputStream.close();
            }
        }
        zipOutputStream.close();
    }
}
