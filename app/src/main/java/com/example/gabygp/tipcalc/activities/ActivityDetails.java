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

    private String tipText;
    private String billText;
    private String dateText;

    public final static String BILL_MESSAGE = "me.tipcalc.billmessage";
    public final static String TIP_MESSAGE = "me.tipcalc.tipmessage";
    public final static String DATE_MESSAGE = "me.tipcalc.datemessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();


        billText = getString(R.string.tipdetail_message_bill).concat(intent.getStringExtra(TipHistoryListFragment.BILL_MESSAGE));
        tipText = getString(R.string.tipdetail_message_tip).concat(intent.getStringExtra(TipHistoryListFragment.TIP_MESSAGE));
        dateText = getString(R.string.tipdetail_message_date).concat(intent.getStringExtra(TipHistoryListFragment.DATE_MESSAGE));

        txtTotal.setText(billText);
        txtTip.setText(tipText);
        txtDateTime.setText(dateText);
    }

}

