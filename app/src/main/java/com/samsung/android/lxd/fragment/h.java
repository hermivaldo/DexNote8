package com.samsung.android.lxd.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
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
public class h extends a {
    private static final String r = "h";
    private ArrayList<String> s = null;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = layoutInflater.inflate(R.layout.fragment_permission_popup, viewGroup, false);
        return this.c;
    }

    public h a(ArrayList<String> arrayList) {
        this.s = arrayList;
        return this;
    }

    /* renamed from: j */
    protected h f() {
        if (getView() == null) {
            Log.e(r, "update : View had not been created yet!");
            return this;
        }
        super.f();
        LinearLayout linearLayout = (LinearLayout) getView().findViewById(R.id.permission_layout);
        TextView textView = (TextView) linearLayout.findViewById(R.id.description);
        String string = this.b.getString(R.string.app_name);
        CharSequence string2 = this.b.getString(R.string.permission_popup_description, new Object[]{string});
        int indexOf = string2.indexOf(string);
        int length = string.length() + indexOf;
        CharSequence spannableString = new SpannableString(string2);
        spannableString.setSpan(new StyleSpan(1), indexOf, length, 0);
        textView.setText(spannableString);
        Iterator it = this.s.iterator();
        while (it.hasNext()) {
            a(linearLayout, (String) it.next());
        }
        ((AlertDialog) getDialog()).setView(this.c);
        return this;
    }

    private Drawable a(Context context, String str) {
        Drawable drawable = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            drawable = context.getDrawable(packageManager.getPermissionGroupInfo(packageManager.getPermissionInfo(str, KeycodeConstants.META_CTRL_ON).group, KeycodeConstants.META_CTRL_ON).icon);
            return drawable;
        } catch (NameNotFoundException e) {
            str = r;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getPermissionIcon: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
    }

    private String b(Context context, String str) {
        String str2 = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            str2 = context.getResources().getString(packageManager.getPermissionGroupInfo(packageManager.getPermissionInfo(str, KeycodeConstants.META_CTRL_ON).group, KeycodeConstants.META_CTRL_ON).labelRes);
            return str2;
        } catch (NameNotFoundException e) {
            str = r;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("getPermissionGroupName: ");
            stringBuilder.append(e.toString());
            Log.e(str, stringBuilder.toString());
        }
    }

    private void a(LinearLayout linearLayout, String str) {
        View inflate = ((LayoutInflater) this.b.getSystemService("layout_inflater")).inflate(R.layout.fragment_permission_list, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        TextView textView = (TextView) inflate.findViewById(R.id.name);
        ((ImageView) inflate.findViewById(R.id.icon)).setImageDrawable(a(this.b, str));
        textView.setText(b(this.b, str));
        linearLayout.addView(inflate, layoutParams);
    }
}
