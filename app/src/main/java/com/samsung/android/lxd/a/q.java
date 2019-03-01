package com.samsung.android.lxd.a;

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
public class q {
    private static final String a = "q";

    public static void a(List<File> list, File file) {
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        BufferedInputStream bufferedInputStream;
        try {
            byte[] bArr = new byte[2048];
            for (File file2 : list) {
                String str = a;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("File: ");
                stringBuilder.append(file2.toString());
                Log.d(str, stringBuilder.toString());
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file2), 2048);
                String absolutePath = file2.getAbsolutePath();
                zipOutputStream.putNextEntry(new ZipEntry(absolutePath.substring(absolutePath.lastIndexOf("/") + 1)));
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 2048);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
                bufferedInputStream.close();
            }
            zipOutputStream.close();
        } catch (Throwable th) {
            zipOutputStream.close();
        }
    }
}
