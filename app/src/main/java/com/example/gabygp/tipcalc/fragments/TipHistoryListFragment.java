package com.example.gabygp.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabygp.tipcalc.R;
import com.example.gabygp.tipcalc.activities.ActivityDetails;
import com.example.gabygp.tipcalc.adapters.OnItemClickListener;
import com.example.gabygp.tipcalc.adapters.TipAdapter;
import com.example.gabygp.tipcalc.entity.TipRecord;
import com.example.gabygp.tipcalc.utils.TipUtils;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    TipAdapter adapter;

    public final static String BILL_MESSAGE = "me.tipcalc.billmessage";
    public final static String TIP_MESSAGE = "me.tipcalc.tipmessage";
    public final static String DATE_MESSAGE = "me.tipcalc.datemessage";

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initList() {
        adapter.init();
    }

    @Override
    public void addToList(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    private void initAdapter() {
        if(adapter == null){
            adapter = new TipAdapter(getActivity().getApplicationContext(), this) {
            };
        }
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        //TODO Implementar la logica para llamar a una actividad enviandole la información de la propina
        //Log.v("MENSAJE!!!!!", tipRecord.getDateFormated());

        Intent intent = new Intent(getActivity(), ActivityDetails.class);

        intent.putExtra(ActivityDetails.TIP_MESSAGE, TipUtils.getTip(tipRecord));
        intent.putExtra(ActivityDetails.BILL_MESSAGE, tipRecord.getBill());
        intent.putExtra(ActivityDetails.DATE_MESSAGE, TipUtils.getDateFormated(tipRecord));
        startActivity(intent);


    }
}
