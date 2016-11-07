package com.example.gabygp.tipcalc.utils;

import com.example.gabygp.tipcalc.entity.TipRecord;

import java.text.SimpleDateFormat;

/**
 * Created by gabygp07 on 3/11/16.
 */

public class TipUtils {
    public static double getTip(TipRecord tipRecord){
        return tipRecord.getBill()*(tipRecord.getTipPercentage()/100d);
    }

    public static String getDateFormated(TipRecord tipRecord){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd, yyyy HH:mm");
        return simpleDateFormat.format(tipRecord.getTimestamp());
    }

}
