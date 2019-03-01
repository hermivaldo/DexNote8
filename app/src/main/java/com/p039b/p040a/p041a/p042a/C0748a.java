package com.p039b.p040a.p041a.p042a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: IDMAInterface */
/* renamed from: com.b.a.a.a.a */
public interface C0748a extends IInterface {

    /* compiled from: IDMAInterface */
    /* renamed from: com.b.a.a.a.a$a */
    public static abstract class C1284a extends Binder implements C0748a {

        /* compiled from: IDMAInterface */
        /* renamed from: com.b.a.a.a.a$a$a */
        private static class C1283a implements C0748a {
            /* renamed from: a */
            private IBinder f4319a;

            C1283a(IBinder iBinder) {
                this.f4319a = iBinder;
            }

            public IBinder asBinder() {
                return this.f4319a;
            }

            /* renamed from: a */
            public String mo640a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sec.android.diagmonagent.sa.IDMAInterface");
                    this.f4319a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public int mo639a(int i, String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sec.android.diagmonagent.sa.IDMAInterface");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.f4319a.transact(2, obtain, obtain2, null);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* renamed from: a */
            public int mo638a(int i, String str, String str2, long j, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.sec.android.diagmonagent.sa.IDMAInterface");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeLong(j);
                    obtain.writeString(str3);
                    this.f4319a.transact(3, obtain, obtain2, null);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        /* renamed from: a */
        public static C0748a m6000a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.sec.android.diagmonagent.sa.IDMAInterface");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof C0748a)) {
                return new C1283a(iBinder);
            }
            return (C0748a) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i != 1598968902) {
                int a;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.sec.android.diagmonagent.sa.IDMAInterface");
                        String a2 = mo640a();
                        parcel2.writeNoException();
                        parcel2.writeString(a2);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.sec.android.diagmonagent.sa.IDMAInterface");
                        a = mo639a(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(a);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.sec.android.diagmonagent.sa.IDMAInterface");
                        a = mo638a(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(a);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString("com.sec.android.diagmonagent.sa.IDMAInterface");
            return true;
        }
    }

    /* renamed from: a */
    int mo638a(int i, String str, String str2, long j, String str3);

    /* renamed from: a */
    int mo639a(int i, String str, String str2, String str3);

    /* renamed from: a */
    String mo640a();
}
