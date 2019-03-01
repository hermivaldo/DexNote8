package com.samsung.android.lxd.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.lxd.C1347a;
import com.samsung.android.lxd.EditActivity;
import com.samsung.android.lxd.ListActivity;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.fragment.CardViewFragment.C0900a;
import com.samsung.android.lxd.fragment.CardViewFragment.C0901b;
import com.samsung.android.lxd.p064a.C0869j;
import com.samsung.android.lxd.p064a.C0874l;
import com.samsung.android.lxd.p064a.C0877o;
import com.samsung.android.lxd.processor.ExecutionType;
import com.samsung.android.lxd.processor.config.SystemConfig;
import com.samsung.android.lxd.processor.config.SystemConfigHelper;
import com.samsung.android.lxd.processor.config.SystemConfigManager;
import com.samsung.android.lxd.processor.config.SystemConfigType;
import com.samsung.android.lxd.processor.utils.log.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ImageDetailFragment */
/* renamed from: com.samsung.android.lxd.fragment.f */
public class C0942f extends Fragment {
    /* renamed from: a */
    private static final String f2971a = "f";
    /* renamed from: b */
    private String f2972b;
    /* renamed from: c */
    private SystemConfig f2973c;
    /* renamed from: d */
    private ListActivity f2974d;
    /* renamed from: e */
    private View f2975e;

    /* compiled from: ImageDetailFragment */
    /* renamed from: com.samsung.android.lxd.fragment.f$1 */
    class C13721 implements C0900a {
        /* renamed from: a */
        final /* synthetic */ C0942f f4518a;

        /* compiled from: ImageDetailFragment */
        /* renamed from: com.samsung.android.lxd.fragment.f$1$1 */
        class C09411 implements OnClickListener {
            /* renamed from: a */
            final /* synthetic */ C13721 f2970a;

            C09411(C13721 c13721) {
                this.f2970a = c13721;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.f2970a.f4518a.m3786c();
            }
        }

        C13721(C0942f c0942f) {
            this.f4518a = c0942f;
        }

        /* renamed from: a */
        public void mo659a(int i) {
            C0874l.m3432a(((C1347a) this.f4518a.getActivity()).f4424a, String.valueOf(903));
            if (C0877o.m3474a(this.f4518a.getActivity()) != 0) {
                C0877o.m3467a(this.f4518a.getActivity(), "Continue?", "Connect on low memory?", new C09411(this), true, null);
            } else {
                this.f4518a.m3786c();
            }
        }
    }

    /* compiled from: ImageDetailFragment */
    /* renamed from: com.samsung.android.lxd.fragment.f$2 */
    class C13732 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C0942f f4519a;

        C13732(C0942f c0942f) {
            this.f4519a = c0942f;
        }

        public void onClick(View view) {
            super.onClick(view);
            C0874l.m3432a(((C1347a) this.f4519a.getActivity()).f4424a, String.valueOf(904));
            this.f4519a.runTerminalMode();
        }
    }

    /* compiled from: ImageDetailFragment */
    /* renamed from: com.samsung.android.lxd.fragment.f$3 */
    class C13743 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C0942f f4520a;

        C13743(C0942f c0942f) {
            this.f4520a = c0942f;
        }

        public void onClick(View view) {
            super.onClick(view);
            C0874l.m3432a(((C1347a) this.f4520a.getActivity()).f4424a, String.valueOf(906));
            this.f4520a.editImage();
        }
    }

    /* compiled from: ImageDetailFragment */
    /* renamed from: com.samsung.android.lxd.fragment.f$4 */
    class C13754 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C0942f f4521a;

        C13754(C0942f c0942f) {
            this.f4521a = c0942f;
        }

        public void onClick(View view) {
            super.onClick(view);
            C0874l.m3432a(((C1347a) this.f4521a.getActivity()).f4424a, String.valueOf(905));
            this.f4521a.deleteImage();
        }
    }

    /* compiled from: ImageDetailFragment */
    /* renamed from: com.samsung.android.lxd.fragment.f$5 */
    class C13765 implements C0900a {
        /* renamed from: a */
        final /* synthetic */ C0942f f4522a;

        C13765(C0942f c0942f) {
            this.f4522a = c0942f;
        }

        /* renamed from: a */
        public void mo659a(int i) {
            this.f4522a.m3789d();
        }
    }

    /* compiled from: ImageDetailFragment */
    /* renamed from: com.samsung.android.lxd.fragment.f$6 */
    class C13776 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C0942f f4523a;

        C13776(C0942f c0942f) {
            this.f4523a = c0942f;
        }

        public void onClick(View view) {
            super.onClick(view);
            ((C1347a) this.f4523a.getActivity()).m6158a(new File(this.f4523a.f2973c.get(SystemConfigType.IMAGE_PATH)));
        }
    }

    /* compiled from: ImageDetailFragment */
    /* renamed from: com.samsung.android.lxd.fragment.f$7 */
    class C13787 extends C0869j {
        /* renamed from: a */
        final /* synthetic */ C0942f f4524a;

        C13787(C0942f c0942f) {
            this.f4524a = c0942f;
        }

        public void onClick(View view) {
            super.onClick(view);
            ((C1347a) this.f4524a.getActivity()).m6189g(this.f4524a.f2972b);
        }
    }

    /* renamed from: a */
    public static C0942f m3779a() {
        return new C0942f();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2975e = layoutInflater.inflate(R.layout.fragment_image_detail, viewGroup, false);
        return this.f2975e;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            m3795a(bundle.getString("configId"));
        }
        m3790d(this.f2975e);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("configId", this.f2972b);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m3790d(this.f2975e);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.f2974d = (ListActivity) context;
    }

    /* renamed from: b */
    private boolean m3785b() {
        return new File(this.f2973c.get(SystemConfigType.IMAGE_PATH)).isFile() ^ 1;
    }

    /* renamed from: a */
    private void m3780a(View view) {
        view.findViewById(R.id.detailsLayout).setVisibility(4);
        view.findViewById(R.id.emptyImageLayout).setVisibility(null);
    }

    /* renamed from: b */
    private void m3783b(View view) {
        view.findViewById(R.id.detailsLayout).setVisibility(0);
        view.findViewById(R.id.emptyImageLayout).setVisibility(4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(C0877o.m3461a(getContext(), this.f2973c.get(SystemConfigType.IMAGE_PATH)));
        List arrayList2 = new ArrayList();
        arrayList2.add(this.f2972b);
        ((CardViewFragment) getChildFragmentManager().findFragmentById(R.id.cardView)).m3611a(C0901b.WITH_TEXT_BUTTON).m3609a((int) R.string.run_image).m3615a(arrayList, arrayList2).m3617a(false).m3610a(new C13721(this)).m3618b();
        ((TextViewFragment) getChildFragmentManager().findFragmentById(R.id.nameTextView)).m3715a(getString(R.string.detail_name)).m3718b(this.f2973c.get(SystemConfigType.IMAGE_NAME)).m3716a(true).m3719c();
        ((TextViewFragment) getChildFragmentManager().findFragmentById(R.id.descTextView)).m3715a(getString(R.string.description)).m3718b(this.f2973c.get(SystemConfigType.IMAGE_DESC)).m3716a(true).m3719c();
        TextViewFragment textViewFragment = (TextViewFragment) getChildFragmentManager().findFragmentById(R.id.imageSizeTextView);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.f2973c.get(SystemConfigType.IMAGE_SIZE));
        stringBuilder.append(" ");
        stringBuilder.append(getString(R.string.gb));
        textViewFragment.m3718b(stringBuilder.toString()).m3719c();
        ((TextViewFragment) getChildFragmentManager().findFragmentById(R.id.useShareFolder)).m3718b(getString(SystemConfigHelper.isConfigOptionOn(this.f2973c, SystemConfigType.SHARE_FOLDER) ? R.string.share_folder_enabled : R.string.share_folder_disabled)).m3714a(8).m3719c();
        ((LinearLayout) view.findViewById(R.id.play_button)).setOnClickListener(new C13732(this));
        ((LinearLayout) view.findViewById(R.id.edit_button)).setOnClickListener(new C13743(this));
        ((LinearLayout) view.findViewById(R.id.delete_button)).setOnClickListener(new C13754(this));
    }

    /* renamed from: c */
    private void m3787c(View view) {
        view.findViewById(R.id.detailsLayout).setVisibility(0);
        view.findViewById(R.id.emptyImageLayout).setVisibility(4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(C0877o.m3461a(getContext(), this.f2973c.get(SystemConfigType.IMAGE_PATH)));
        List arrayList2 = new ArrayList();
        arrayList2.add(this.f2972b);
        ((CardViewFragment) getChildFragmentManager().findFragmentById(R.id.cardView)).m3611a(C0901b.WITH_UNMOUNT_IMAGE).m3609a((int) R.string.unmount_button).m3615a(arrayList, arrayList2).m3617a(false).m3610a(new C13765(this)).m3618b();
        ((RelativeLayout) view.findViewById(R.id.baseLayout)).setVisibility(4);
        ((LinearLayout) view.findViewById(R.id.faultImageLayout)).setVisibility(0);
        TextView textView = (TextView) view.findViewById(R.id.imageDetail);
        textView.setText(R.string.see_image_details);
        textView.setOnClickListener(new C13776(this));
        ((TextButtonFragment) getChildFragmentManager().findFragmentById(R.id.browseButton)).m3706a((int) R.string.browse).m3707a(new C13787(this)).m3710b(20).m3709b();
    }

    /* renamed from: d */
    private void m3790d(View view) {
        if (TextUtils.isEmpty(this.f2972b)) {
            m3780a(view);
        } else if (m3785b()) {
            m3787c(view);
        } else {
            m3783b(view);
        }
    }

    private void runTerminalMode() {
        if (m3782a(1)) {
            ((C1347a) getActivity()).mo1307a(ExecutionType.CLI, this.f2972b);
        }
    }

    /* renamed from: c */
    private void m3786c() {
        if (m3782a(0)) {
            ((C1347a) getActivity()).mo1307a(ExecutionType.GUI, this.f2972b);
        }
    }

    private void editImage() {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("configId", this.f2972b);
        startActivity(intent);
    }

    private void deleteImage() {
        ((C1347a) getActivity()).deleteImage(this.f2972b);
    }

    /* renamed from: d */
    private void m3789d() {
        ((C1347a) getActivity()).m6187f(this.f2972b);
    }

    /* renamed from: a */
    public C0942f m3795a(String str) {
        this.f2972b = str;
        if (TextUtils.isEmpty(this.f2972b) == null) {
            this.f2973c = SystemConfigManager.load(Long.parseLong(this.f2972b));
        }
        return this;
    }

    /* renamed from: a */
    private boolean m3782a(int i) {
        String str = f2971a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkPermission: ");
        stringBuilder.append(i);
        Log.m3853d(str, stringBuilder.toString());
        if (Settings.canDrawOverlays(LxdApplication.m3344a())) {
            return true;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("package:");
        stringBuilder2.append(LxdApplication.m3344a().getPackageName());
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(stringBuilder2.toString())), i);
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = f2971a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: resultCode: ");
        stringBuilder.append(i);
        stringBuilder.append(", resultCode: ");
        stringBuilder.append(i2);
        Log.m3853d(str, stringBuilder.toString());
        switch (i) {
            case 0:
            case 1:
                if (Settings.canDrawOverlays(LxdApplication.m3344a()) != 0) {
                    if (i != 0) {
                        if (i == 1) {
                            runTerminalMode();
                            break;
                        }
                    }
                    m3786c();
                    break;
                }
                m3782a(i);
                return;
                break;
            default:
                super.onActivityResult(i, i2, intent);
                break;
        }
    }
}
