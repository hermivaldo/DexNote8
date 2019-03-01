package com.samsung.android.lxd;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import com.samsung.android.lxd.a.b;

public class ContactUsActivity extends a {
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_contact_us);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.contact_us_menu);
        }
        findViewById(R.id.myQuestions).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ContactUsActivity.this.startActivity(b.a("/ticket/searchTicketList.do"));
            }
        });
        findViewById(R.id.faq).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ContactUsActivity.this.startActivity(b.a("/faq/searchFaq.do"));
            }
        });
        findViewById(R.id.contactUs).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ContactUsActivity.this.startActivity(b.a("/ticket/createQuestionTicket.do"));
            }
        });
    }
}
