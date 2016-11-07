package com.example.gabygp.tipcalc.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gabygp.tipcalc.R;
import com.example.gabygp.tipcalc.TipCalcApp;
import com.example.gabygp.tipcalc.bd.TipsDatabase;
import com.example.gabygp.tipcalc.fragments.TipHistoryListFragment;
import com.example.gabygp.tipcalc.fragments.TipHistoryListFragmentListener;
import com.example.gabygp.tipcalc.entity.TipRecord;
import com.example.gabygp.tipcalc.utils.TipUtils;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnincrease)
    Button btnincrease;
    @Bind(R.id.btndecrease)
    Button btndecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;


    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_CHANGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDB();

        TipHistoryListFragment fragment = (TipHistoryListFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentList);

        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener)fragment;

        fragmentListener.initList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(new FlowConfig.Builder(this).build());

        FlowManager.getDatabase(TipsDatabase.class).getWritableDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear()
    {
        fragmentListener.clearList();
        txtTip.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btnSubmit)
    public void handleSubmit() {
        hideKeyboard();

        String strInputTotal = inputBill.getText().toString().trim();

        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord record = new TipRecord();
            record.setBill(total);
            record.setTipPercentage(tipPercentage);
            record.setTimestamp(new Date());

            String strTip = String.format(getString(R.string.global_message_bill), TipUtils.getTip(record));
            fragmentListener.addToList(record);

            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }

    }
    @OnClick(R.id.btnincrease)
    public void handleClickIncrease() {
        //Cuando des click a + debe llamar a handleTipChange y sumar 1
        handleTipChange(1);
        return;
    }

    @OnClick(R.id.btndecrease)
    public void handleClickDecrease() {
        //Cuando des click a - debe llamar a handleTipChange y restar 1
        handleTipChange(-1);
        return;
    }


    public int getTipPercentage() {
        // 1 Crear una variable tipPercentage en la que guardemos DEFAULT_TIP_CHANGE
        int tipPercentage = DEFAULT_TIP_CHANGE;
        // 2 Crear una variable String strInputTipPercentage que tome el valor del inputPercentage (No olvidar el trim)
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        // 3 Verificar que la cadena no venga vacía.
        if(!strInputTipPercentage.isEmpty())
        //      3.1 Si no viene vacía, Sobreescribir tipPercentage con el valor de strInputTipPercentage. (Convertir a entero)
        {
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }
        else
        //      3.2 Si es vacía: inputPercentage.setText(String.valueOf(Default_TIP_PERCENTAGE));
        {
            inputPercentage.setText(String.valueOf(DEFAULT_TIP_CHANGE));
        }
        // 4 Devolver el valor de TipPercentage.
        return tipPercentage;
    }

    public void handleTipChange(int change) {
        //1 Llamar a Get tip percentage (en una variable)
        int OtherTip = getTipPercentage();
        //2 aplicar el incremento/decremento que viene en la variable change
        OtherTip = OtherTip + change;
        // 3 si tippercentage mayor que 0 entonces colocar el valor en input
        if(OtherTip>0)
        {
            inputPercentage.setText(String.valueOf(OtherTip));
        }
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }

    private void about() {
        Log.v("ABOUT", "HOLA ESTE ES EL BOTON ABOUT");
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }

}
