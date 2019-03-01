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
import com.samsung.android.lxd.EditActivity;
import com.samsung.android.lxd.ListActivity;
import com.samsung.android.lxd.LxdApplication;
import com.samsung.android.lxd.R;
import com.samsung.android.lxd.a.j;
import com.samsung.android.lxd.a.l;
import com.samsung.android.lxd.a.o;
import com.samsung.android.lxd.fragment.CardViewFragment.a;
import com.samsung.android.lxd.fragment.CardViewFragment.b;
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
public class f extends Fragment {
    private static final String a = "f";
    private String b;
    private SystemConfig c;
    private ListActivity d;
    private View e;

    public static f a() {
        return new f();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.e = layoutInflater.inflate(R.layout.fragment_image_detail, viewGroup, false);
        return this.e;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            a(bundle.getString("configId"));
        }
        d(this.e);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("configId", this.b);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        d(this.e);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.d = (ListActivity) context;
    }

    private boolean b() {
        return new File(this.c.get(SystemConfigType.IMAGE_PATH)).isFile() ^ 1;
    }

    private void a(View view) {
        view.findViewById(R.id.detailsLayout).setVisibility(4);
        view.findViewById(R.id.emptyImageLayout).setVisibility(0);
    }

    private void b(View view) {
        view.findViewById(R.id.detailsLayout).setVisibility(0);
        view.findViewById(R.id.emptyImageLayout).setVisibility(4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(o.a(getContext(), this.c.get(SystemConfigType.IMAGE_PATH)));
        List arrayList2 = new ArrayList();
        arrayList2.add(this.b);
        ((CardViewFragment) getChildFragmentManager().findFragmentById(R.id.cardView)).a(b.WITH_TEXT_BUTTON).a((int) R.string.run_image).a(arrayList, arrayList2).a(false).a(new a() {
            public void a(int i) {
                l.a(((com.samsung.android.lxd.a) f.this.getActivity()).a, String.valueOf(903));
                if (o.a(f.this.getActivity())) {
                    o.a(f.this.getActivity(), "Continue?", "Connect on low memory?", new OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            f.this.c();
                        }
                    }, true, null);
                } else {
                    f.this.c();
                }
            }
        }).b();
        ((TextViewFragment) getChildFragmentManager().findFragmentById(R.id.nameTextView)).a(getString(R.string.detail_name)).b(this.c.get(SystemConfigType.IMAGE_NAME)).a(true).c();
        ((TextViewFragment) getChildFragmentManager().findFragmentById(R.id.descTextView)).a(getString(R.string.description)).b(this.c.get(SystemConfigType.IMAGE_DESC)).a(true).c();
        TextViewFragment textViewFragment = (TextViewFragment) getChildFragmentManager().findFragmentById(R.id.imageSizeTextView);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.c.get(SystemConfigType.IMAGE_SIZE));
        stringBuilder.append(" ");
        stringBuilder.append(getString(R.string.gb));
        textViewFragment.b(stringBuilder.toString()).c();
        ((TextViewFragment) getChildFragmentManager().findFragmentById(R.id.useShareFolder)).b(getString(SystemConfigHelper.isConfigOptionOn(this.c, SystemConfigType.SHARE_FOLDER) ? R.string.share_folder_enabled : R.string.share_folder_disabled)).a(8).c();
        ((LinearLayout) view.findViewById(R.id.play_button)).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                l.a(((com.samsung.android.lxd.a) f.this.getActivity()).a, String.valueOf(904));
                f.this.runTerminalMode();
            }
        });
        ((LinearLayout) view.findViewById(R.id.edit_button)).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                l.a(((com.samsung.android.lxd.a) f.this.getActivity()).a, String.valueOf(906));
                f.this.editImage();
            }
        });
        ((LinearLayout) view.findViewById(R.id.delete_button)).setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                l.a(((com.samsung.android.lxd.a) f.this.getActivity()).a, String.valueOf(905));
                f.this.deleteImage();
            }
        });
    }

    private void c(View view) {
        view.findViewById(R.id.detailsLayout).setVisibility(0);
        view.findViewById(R.id.emptyImageLayout).setVisibility(4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(o.a(getContext(), this.c.get(SystemConfigType.IMAGE_PATH)));
        List arrayList2 = new ArrayList();
        arrayList2.add(this.b);
        ((CardViewFragment) getChildFragmentManager().findFragmentById(R.id.cardView)).a(b.WITH_UNMOUNT_IMAGE).a((int) R.string.unmount_button).a(arrayList, arrayList2).a(false).a(new a() {
            public void a(int i) {
                f.this.d();
            }
        }).b();
        ((RelativeLayout) view.findViewById(R.id.baseLayout)).setVisibility(4);
        ((LinearLayout) view.findViewById(R.id.faultImageLayout)).setVisibility(0);
        TextView textView = (TextView) view.findViewById(R.id.imageDetail);
        textView.setText(R.string.see_image_details);
        textView.setOnClickListener(new j() {
            public void onClick(View view) {
                super.onClick(view);
                ((com.samsung.android.lxd.a) f.this.getActivity()).a(new File(f.this.c.get(SystemConfigType.IMAGE_PATH)));
            }
        });
        ((TextButtonFragment) getChildFragmentManager().findFragmentById(R.id.browseButton)).a((int) R.string.browse).a(new j() {
            public void onClick(View view) {
                super.onClick(view);
                ((com.samsung.android.lxd.a) f.this.getActivity()).g(f.this.b);
            }
        }).b(20).b();
    }

    private void d(View view) {
        if (TextUtils.isEmpty(this.b)) {
            a(view);
        } else if (b()) {
            c(view);
        } else {
            b(view);
        }
    }

    private void runTerminalMode() {
        if (a(1)) {
            ((com.samsung.android.lxd.a) getActivity()).a(ExecutionType.CLI, this.b);
        }
    }

    private void c() {
        if (a(0)) {
            ((com.samsung.android.lxd.a) getActivity()).a(ExecutionType.GUI, this.b);
        }
    }

    private void editImage() {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra("configId", this.b);
        startActivity(intent);
    }

    private void deleteImage() {
        ((com.samsung.android.lxd.a) getActivity()).deleteImage(this.b);
    }

    private void d() {
        ((com.samsung.android.lxd.a) getActivity()).f(this.b);
    }

    public f a(String str) {
        this.b = str;
        if (!TextUtils.isEmpty(this.b)) {
            this.c = SystemConfigManager.load(Long.parseLong(this.b));
        }
        return this;
    }

    private boolean a(int i) {
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("checkPermission: ");
        stringBuilder.append(i);
        Log.d(str, stringBuilder.toString());
        if (Settings.canDrawOverlays(LxdApplication.a())) {
            return true;
        }
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("package:");
        stringBuilder2.append(LxdApplication.a().getPackageName());
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(stringBuilder2.toString())), i);
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        String str = a;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onActivityResult: resultCode: ");
        stringBuilder.append(i);
        stringBuilder.append(", resultCode: ");
        stringBuilder.append(i2);
        Log.d(str, stringBuilder.toString());
        switch (i) {
            case 0:
            case 1:
                if (Settings.canDrawOverlays(LxdApplication.a())) {
                    if (i != 0) {
                        if (i == 1) {
                            runTerminalMode();
                            break;
                        }
                    }
                    c();
                    break;
                }
                a(i);
                return;
                break;
            default:
                super.onActivityResult(i, i2, intent);
                break;
        }
    }
}
