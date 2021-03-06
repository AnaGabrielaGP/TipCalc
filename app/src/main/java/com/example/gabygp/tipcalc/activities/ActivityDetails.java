package com.example.gabygp.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gabygp.tipcalc.fragments.TipHistoryListFragment;
import com.example.gabygp.tipcalc.R;


import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityDetails extends AppCompatActivity {

    @Bind(R.id.txtDateTime)
    TextView txtDateTime;
    @Bind(R.id.txtTotal)
    TextView txtTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.activity_details)
    RelativeLayout activityDetails;

    public final static String BILL_MESSAGE = "me.tipcalc.billmessage";
    public final static String TIP_MESSAGE = "me.tipcalc.tipmessage";
    public final static String DATE_MESSAGE = "me.tipcalc.datemessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
    }

    Intent intent = getIntent();
    Bundle extras = intent.getExtras();

    String totalSTxt = String.format(getString(R.string.tipdetail_message_bill), intent.getDoubleExtra(BILL_MESSAGE, 2d));
    txtTotal.setText(totalSTxt);
    String tipSTxt = String.format(getString(R.string.global_message_bill), intent.getDoubleExtra(TIP_MESSAGE, 2d));
    txtTip.setText(tipSTxt);
    txtDateTime.setText(intent.getStringExtra(DATE_MESSAGE));

}

