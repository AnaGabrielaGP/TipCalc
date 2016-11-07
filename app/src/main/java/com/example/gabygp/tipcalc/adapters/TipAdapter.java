package com.example.gabygp.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabygp.tipcalc.R;
import com.example.gabygp.tipcalc.entity.TipRecord;
import com.example.gabygp.tipcalc.utils.TipUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gabygp07 on 13/10/16.
 */

public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private Context context;
    private List<TipRecord> dataset;
    private OnItemClickListener onItemClickListener;

    public TipAdapter(Context context, List<TipRecord> dataset, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = dataset;
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dataset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element = dataset.get(position);
        String strTip = String.format(context.getString(R.string.global_message_bill), TipUtils.getTip(element));
        holder.txtContent.setText(strTip);
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(TipRecord record) {
        //dataset.add(0, record);
        record.save();
        notifyDataSetChanged();
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtContent)
        TextView txtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(element);
                }
            });

        }
    }
}
