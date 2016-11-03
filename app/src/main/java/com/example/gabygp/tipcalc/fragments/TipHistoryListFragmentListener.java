package com.example.gabygp.tipcalc.fragments;

import com.example.gabygp.tipcalc.entity.TipRecord;

/**
 * Created by gabygp07 on 10/10/16.
 */

public interface TipHistoryListFragmentListener {
    void addToList(TipRecord record);
    void clearList();

}
