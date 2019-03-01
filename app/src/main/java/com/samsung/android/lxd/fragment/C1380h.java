package com.samsung.android.lxd.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.processor.network.channel.pty.internal.KeycodeConstants;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: PermissionRationaleDialog */
/* renamed from: com.samsung.android.lxd.fragment.h */
public class C1380h extends C0936a {
    /* renamed from: r */
    private static final String f4528r = "h";
    /* renamed from: s */
    private ArrayList<String> f4529s = null;

    /* renamed from: f */
    protected /* synthetic */ C0936a mo764f() {
        return m6317j();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = layoutInflater.inflate(R.layout.fragment_permission_popup, viewGroup, false);
        return this.c;
    }

    /* renamed from: a */
    public C1380h m6315a(ArrayList<String> arrayList) {
        this.f4529s = arrayList;
        return this;
    }

    /* renamed from: j */
    protected C1380h m6317j() {
        if (getView() == null) {
            Log.m3855e(f4528r, "update : View had not been created yet!");
            return this;
        }
        super.mo764f();
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.permission_layout);
        TextView textView = (TextView) linearLayout.findViewById(R.id.description);
        String string = this.b.getString(R.string.app_name);
        CharSequence string2 = this.b.getString(R.string.permission_popup_description, new Object[]{string});
        int indexOf = string2.indexOf(string);
        int length = string.length() + indexOf;
        CharSequence spannableString = new SpannableString(string2);
        spannableString.setSpan(new StyleSpan(1), indexOf, length, 0);
        textView.setText(spannableString);
        Iterator it = this.f4529s.iterator();
        while (it.hasNext()) {
            m6313a(linearLayout, (String) it.next());
        }
        ((AlertDialog) getDialog()).setView(this.c);
        return this;
    }

    /* renamed from: a */
    private Drawable m6312a(Context context, String str) {
        Drawable drawable = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            drawable = context.getDrawable(packageManager.getPermissionGroupInfo(packageManager.getPermissionInfo(str, KeycodeConstants.META_CTRL_ON).group, KeycodeConstants.META_CTRL_ON).icon);
            return drawable;
        } catch (Context context2) {
            str = f4528r;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getPermissionIcon: ");
            stringBuilder.append(context2.toString());
            Log.m3855e(str, stringBuilder.toString());
        }
    }

    /* renamed from: b */
    private String m6314b(Context context, String str) {
        String str2 = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            str2 = context.getResources().getString(packageManager.getPermissionGroupInfo(packageManager.getPermissionInfo(str, KeycodeConstants.META_CTRL_ON).group, KeycodeConstants.META_CTRL_ON).labelRes);
            return str2;
        } catch (Context context2) {
            str = f4528r;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getPermissionGroupName: ");
            stringBuilder.append(context2.toString());
            Log.m3855e(str, stringBuilder.toString());
        }
    }

    /* renamed from: a */
    private void m6313a(LinearLayout linearLayout, String str) {
        View inflate = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.fragment_permission_list, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = (TextView) inflate.findViewById(R.id.name);
        ((ImageView) inflate.findViewById(R.id.icon)).setImageDrawable(m6312a(this.b, str));
        textView.setText(m6314b(this.b, str));
        linearLayout.addView(inflate, layoutParams);
    }
}
