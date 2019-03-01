package com.samsung.p047a.p048a.p049a.p050a.p056f.p059d.p060a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.samsung.p047a.p048a.p049a.C0791c;

/* compiled from: DefaultDBOpenHelper */
/* renamed from: com.samsung.a.a.a.a.f.d.a.b */
public class C1299b extends SQLiteOpenHelper implements C0791c {
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public C1299b(Context context) {
        super(context, "SamsungAnalytics.db", null, 1);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS logs_v2 (_id INTEGER PRIMARY KEY AUTOINCREMENT, timestamp INTEGER, logtype TEXT, data TEXT)");
    }

    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
