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

    public final static String TIP_KEY = "tip";
    public final static String DATE_KEY = "timestamp";
    public final static String BILL_TOTAL_KEY = "total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();


        String strTotal = String.format(getString(R.string.tipdetail_message_bill),
                intent.getDoubleExtra(BILL_TOTAL_KEY, 0d));
        String strTip = String.format(getString(R.string.global_message_bill),
                intent.getDoubleExtra(TIP_KEY, 0d));

        txtDateTime.setText(intent.getStringExtra(DATE_KEY));
        txtTotal.setText(strTotal);
        txtTip.setText(strTip);
    }

}

