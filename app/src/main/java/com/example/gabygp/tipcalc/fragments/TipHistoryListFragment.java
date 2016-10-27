package com.example.gabygp.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.gabygp.tipcalc.R;
import com.example.gabygp.tipcalc.activities.ActivityDetails;
import com.example.gabygp.tipcalc.adapters.OnItemClickListener;
import com.example.gabygp.tipcalc.adapters.TipAdapter;
import com.example.gabygp.tipcalc.models.TipRecord;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.gabygp.tipcalc.activities.ActivityDetails.BILL_MESSAGE;
import static com.example.gabygp.tipcalc.activities.ActivityDetails.DATE_MESSAGE;
import static com.example.gabygp.tipcalc.activities.ActivityDetails.TIP_MESSAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    TipAdapter adapter;

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
        Intent intent = new Intent(getContext(),ActivityDetails.class);
        intent.putExtra(BILL_MESSAGE,String.valueOf(tipRecord.getBill()));
        intent.putExtra(TIP_MESSAGE,String.valueOf(tipRecord.getTip()));
        intent.putExtra(DATE_MESSAGE,tipRecord.getDateFormated());
        startActivity(intent);
        //Log.v("MENSAJE!!!!!", tipRecord.getDateFormated());

    }
}
