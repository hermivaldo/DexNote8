package com.samsung.android.lxd.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.a.p;
import com.samsung.android.lxd.processor.LxdException;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;

public class ImageSizeSeekBarFragment extends i {
    private static final String a = "ImageSizeSeekBarFragment";
    private a b;
    private String c;
    private p d = new p();
    private boolean e = false;
    private boolean f = true;
    private int g;
    private int h = -1;
    private int i;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (!(bundle == null || bundle.getInt("MIN_FILE_SIZE") == 0)) {
            this.h = bundle.getInt("MIN_FILE_SIZE");
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("MIN_FILE_SIZE", this.h);
        super.onSaveInstanceState(bundle);
    }

    public ImageSizeSeekBarFragment a(String str) {
        this.c = str;
        String str2 = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("setImagePath : ");
        stringBuilder.append(str);
        Log.d(str2, stringBuilder.toString());
        return this;
    }

    public String a() {
        return String.valueOf(j());
    }

    private void k() {
        if (!this.e) {
            this.e = true;
            Log.d(a, "getImageSizeInfo");
            File file = new File(this.c);
            if (file.exists()) {
                this.d.a((int) R.string.app_name, (int) R.string.wait, this.b, null);
                ((a) getActivity()).d(this.c);
                return;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("updateImageSizeInfo : Invalid file ");
            stringBuilder.append(file.getName());
            throw new LxdException(stringBuilder.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(String str, boolean z) {
        int parseInt;
        String str2;
        StringBuilder stringBuilder;
        this.e = false;
        if (z) {
            try {
                parseInt = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                String str3 = a;
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("onImageMinSizeReceived: ");
                stringBuilder2.append(e.toString());
                Log.i(str3, stringBuilder2.toString());
            }
            if (parseInt <= 0) {
                parseInt = o.a(new File(this.c));
            }
            this.h = parseInt;
            this.d.a();
            str2 = a;
            stringBuilder = new StringBuilder();
            stringBuilder.append("onImageMinSizeReceived ");
            stringBuilder.append(z);
            stringBuilder.append(", ");
            stringBuilder.append(str);
            stringBuilder.append(", ");
            stringBuilder.append(this.h);
            Log.d(str2, stringBuilder.toString());
            c();
        }
        parseInt = -1;
        if (parseInt <= 0) {
        }
        this.h = parseInt;
        this.d.a();
        str2 = a;
        stringBuilder = new StringBuilder();
        stringBuilder.append("onImageMinSizeReceived ");
        stringBuilder.append(z);
        stringBuilder.append(", ");
        stringBuilder.append(str);
        stringBuilder.append(", ");
        stringBuilder.append(this.h);
        Log.d(str2, stringBuilder.toString());
        c();
    }

    public i a(boolean z) {
        super.a(z);
        this.f = z;
        return this;
    }

    /* renamed from: b */
    public ImageSizeSeekBarFragment c() {
        if (getView() == null) {
            Log.e(a, "update : View had not been created yet!");
            return this;
        }
        this.b = (a) getActivity();
        if (this.f) {
            this.g = o.g(this.c);
            if (this.h == -1) {
                if (o.e(this.c) != 0.0d) {
                    this.h = o.a(new File(this.c));
                } else {
                    k();
                }
                return this;
            }
        }
        this.g = 4;
        this.h = 4;
        l();
        super.c();
        return this;
    }

    private void l() {
        Log.d(a, "updateImageSizeInfo");
        m();
        a(new j() {
            public void onClick(final View view) {
                super.onClick(view);
                ImageSizeSeekBarFragment.this.h().setFocusableInTouchMode(true);
                ImageSizeSeekBarFragment.this.h().requestFocus();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ImageSizeSeekBarFragment.this.h);
                stringBuilder.append(ImageSizeSeekBarFragment.this.getString(R.string.gb));
                String stringBuilder2 = stringBuilder.toString();
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(ImageSizeSeekBarFragment.this.i);
                stringBuilder3.append(ImageSizeSeekBarFragment.this.getString(R.string.gb));
                String stringBuilder4 = stringBuilder3.toString();
                String replaceAll = ImageSizeSeekBarFragment.this.getString(R.string.invalid_input_for_storage).replaceAll("1stGB", stringBuilder2).replaceAll("2ndGB", stringBuilder4);
                final d j = d.j();
                a a = j.a(replaceAll).b(replaceAll).h(3).a(ImageSizeSeekBarFragment.this.b).a((int) R.string.storage_space);
                StringBuilder stringBuilder5 = new StringBuilder();
                stringBuilder5.append(stringBuilder2);
                stringBuilder5.append(" - ");
                stringBuilder5.append(stringBuilder4);
                a.a(stringBuilder5.toString()).c((int) R.string.popup_done).d(R.string.popup_cancel).a(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ImageSizeSeekBarFragment.this.getActivity().getWindow().setSoftInputMode(3);
                        ImageSizeSeekBarFragment.this.d(Integer.parseInt(j.k()));
                        j.dismiss();
                    }
                }).b(new OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ImageSizeSeekBarFragment.this.getActivity().getWindow().setSoftInputMode(3);
                        j.dismiss();
                    }
                }).a(new OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (ImageSizeSeekBarFragment.this.h() != null) {
                            ImageSizeSeekBarFragment.this.h().setFocusableInTouchMode(false);
                        }
                        try {
                            if (ImageSizeSeekBarFragment.this.b != null) {
                                ImageSizeSeekBarFragment.this.b.getCurrentFocus().postDelayed(new Runnable() {
                                    public void run() {
                                        o.b(ImageSizeSeekBarFragment.this.b.getCurrentFocus(), 0);
                                    }
                                }, 50);
                            }
                        } catch (NullPointerException e) {
                            Log.d(ImageSizeSeekBarFragment.a, e.getMessage());
                        }
                    }
                });
                j.a(ImageSizeSeekBarFragment.this.h, ImageSizeSeekBarFragment.this.i).e();
                view.setClickable(false);
                view.postDelayed(new Runnable() {
                    public void run() {
                        view.setClickable(true);
                    }
                }, 500);
            }
        });
        Log.d(a, "updateImageSizeInfo done");
    }

    private void m() {
        int i;
        if (this.f) {
            File file = new File(this.c);
            if (file.exists()) {
                i = this.h > this.g ? this.h : this.g;
                this.i = o.b(file);
                if (this.i < i) {
                    this.i = i;
                }
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("updateImageSizeInfo : Invalid file ");
                stringBuilder.append(file.getName());
                throw new LxdException(stringBuilder.toString());
            }
        }
        i = 4;
        this.i = 32;
        b(getString(R.string.image_size_desc));
        c(i);
        c(getString(R.string.gb));
        a(this.h);
        b(this.i);
    }
}
