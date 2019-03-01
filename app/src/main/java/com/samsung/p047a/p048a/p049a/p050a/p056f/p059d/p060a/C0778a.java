package com.samsung.p047a.p048a.p049a.p050a.p056f.p059d.p060a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.samsung.p047a.p048a.p049a.C0791c;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0776c;
import com.samsung.p047a.p048a.p049a.p050a.p056f.C0782e;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: DbManager */
/* renamed from: com.samsung.a.a.a.a.f.d.a.a */
public class C0778a {
    /* renamed from: a */
    private C0791c f2465a;
    /* renamed from: b */
    private Queue<C0782e> f2466b;

    public C0778a(Context context) {
        this(new C1299b(context));
    }

    public C0778a(C0791c c0791c) {
        this.f2466b = new LinkedBlockingQueue();
        if (c0791c != null) {
            this.f2465a = c0791c;
            c0791c.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS logs_v2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp INTEGER, logtype TEXT, data TEXT)");
        }
        m3222a(5);
    }

    /* renamed from: a */
    private Queue<C0782e> m3219a(String str) {
        this.f2466b.clear();
        str = this.f2465a.getReadableDatabase().rawQuery(str, null);
        while (str.moveToNext()) {
            C0782e c0782e = new C0782e();
            c0782e.m3243a(str.getString(str.getColumnIndex("_id")));
            c0782e.m3245b(str.getString(str.getColumnIndex("data")));
            c0782e.m3241a(str.getLong(str.getColumnIndex("timestamp")));
            c0782e.m3242a(str.getString(str.getColumnIndex("logtype")).equals(C0776c.DEVICE.m3218a()) ? C0776c.DEVICE : C0776c.UIX);
            this.f2466b.add(c0782e);
        }
        str.close();
        return this.f2466b;
    }

    /* renamed from: a */
    public Queue<C0782e> m3221a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from logs_v2 LIMIT ");
        stringBuilder.append(i);
        return m3219a(stringBuilder.toString());
    }

    /* renamed from: a */
    public Queue<C0782e> m3220a() {
        return m3219a("select * from logs_v2");
    }

    /* renamed from: a */
    public void m3223a(C0782e c0782e) {
        SQLiteDatabase writableDatabase = this.f2465a.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(c0782e.m3244b()));
        contentValues.put("data", c0782e.m3246c());
        contentValues.put("logtype", c0782e.m3247d().m3218a());
        writableDatabase.insert("logs_v2", null, contentValues);
    }

    /* renamed from: a */
    public void m3222a(long j) {
        SQLiteDatabase writableDatabase = this.f2465a.getWritableDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("timestamp <= ");
        stringBuilder.append(j);
        writableDatabase.delete("logs_v2", stringBuilder.toString(), null);
    }

    /* renamed from: a */
    public void m3224a(List<String> list) {
        SQLiteDatabase writableDatabase = this.f2465a.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            int size = list.size();
            int i = 0;
            while (size > 0) {
                int i2 = 900;
                if (size < 900) {
                    i2 = size;
                }
                int i3 = i + i2;
                List subList = list.subList(i, i3);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("_id IN(");
                stringBuilder.append(new String(new char[(subList.size() - 1)]).replaceAll("\u0000", "?,"));
                String stringBuilder2 = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(stringBuilder2);
                stringBuilder.append("?)");
                writableDatabase.delete("logs_v2", stringBuilder.toString(), (String[]) subList.toArray(new String[0]));
                size -= i2;
                i = i3;
            }
            list.clear();
            writableDatabase.setTransactionSuccessful();
        } catch (List<String> list2) {
            list2.printStackTrace();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
        }
        writableDatabase.endTransaction();
    }
}
