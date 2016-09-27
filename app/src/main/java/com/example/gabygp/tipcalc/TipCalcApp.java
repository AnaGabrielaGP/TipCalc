package com.example.gabygp.tipcalc;

import android.app.Application;

/**
 * Created by gabygp on 26/09/16.
 */
public class TipCalcApp extends Application {
    private final static String ABOUT_URL= "http://github.com/AnaGabrielaGP";

    public String getAboutUrl() {
        return ABOUT_URL;
    }

}
