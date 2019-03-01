package com.samsung.a.a.a.a.f.d.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.samsung.a.a.a.a.f.e;
import com.samsung.a.a.a.c;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: DbManager */
public class a {
    private c a;
    private Queue<e> b;

    public a(Context context) {
        this(new b(context));
    }

    public a(c cVar) {
        this.b = new LinkedBlockingQueue();
        if (cVar != null) {
            this.a = cVar;
            cVar.getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS logs_v2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp INTEGER, logtype TEXT, data TEXT)");
        }
        a(5);
    }

    private Queue<e> a(String str) {
        this.b.clear();
        Cursor rawQuery = this.a.getReadableDatabase().rawQuery(str, null);
        while (rawQuery.moveToNext()) {
            e eVar = new e();
            eVar.a(rawQuery.getString(rawQuery.getColumnIndex("_id")));
            eVar.b(rawQuery.getString(rawQuery.getColumnIndex("data")));
            eVar.a(rawQuery.getLong(rawQuery.getColumnIndex("timestamp")));
            eVar.a(rawQuery.getString(rawQuery.getColumnIndex("logtype")).equals(com.samsung.a.a.a.a.f.c.DEVICE.a()) ? com.samsung.a.a.a.a.f.c.DEVICE : com.samsung.a.a.a.a.f.c.UIX);
            this.b.add(eVar);
        }
        rawQuery.close();
        return this.b;
    }

    public Queue<e> a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from logs_v2 LIMIT ");
        stringBuilder.append(i);
        return a(stringBuilder.toString());
    }

    public Queue<e> a() {
        return a("select * from logs_v2");
    }

    public void a(e eVar) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(eVar.b()));
        contentValues.put("data", eVar.c());
        contentValues.put("logtype", eVar.d().a());
        writableDatabase.insert("logs_v2", null, contentValues);
    }

    public void a(long j) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("timestamp <= ");
        stringBuilder.append(j);
        writableDatabase.delete("logs_v2", stringBuilder.toString(), null);
    }

    public void a(List<String> list) {
        SQLiteDatabase writableDatabase = this.a.getWritableDatabase();
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
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
        }
        writableDatabase.endTransaction();
    }
}
