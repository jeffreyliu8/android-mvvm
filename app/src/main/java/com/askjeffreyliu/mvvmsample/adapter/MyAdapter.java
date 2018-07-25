package com.askjeffreyliu.mvvmsample.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.askjeffreyliu.mvvmsample.R;
import com.askjeffreyliu.mvvmsample.room.model.Project;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Project> mList;

    private static final int SINGLE_VIEWHOLDER_TYPE = 0;

    public MyAdapter() {
    }

    public void updateList(List<Project> list) {
        final MyDiffUtilCallback diffCallback = new MyDiffUtilCallback(mList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        mList = list;
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        switch (viewType) {
            default: {
                View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
                return new CellReorderViewHolder(v);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle o = (Bundle) payloads.get(0);
            CellReorderViewHolder cellViewHolder = (CellReorderViewHolder) holder;
            for (String key : o.keySet()) {
                if (key.equals("id")) {
                    cellViewHolder.mId.setText(mList.get(position).getId() + "");
                }
                if (key.equals("name")) {
                    cellViewHolder.mName.setText(mList.get(position).getName());
                }
            }
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        CellReorderViewHolder cellViewHolder = (CellReorderViewHolder) viewHolder;
        cellViewHolder.mId.setText(mList.get(position).getId() + "");
        cellViewHolder.mName.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return SINGLE_VIEWHOLDER_TYPE;
    }

    private class CellReorderViewHolder extends RecyclerView.ViewHolder {
        private TextView mId;
        private TextView mName;

        CellReorderViewHolder(View itemView) {
            super(itemView);

            mId = itemView.findViewById(R.id.id);
            mName = itemView.findViewById(R.id.name);
        }
    }
}
