package com.askjeffreyliu.mvvmsample.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.askjeffreyliu.mvvmsample.room.model.Project;


import java.util.List;

public class MyDiffUtilCallback extends DiffUtil.Callback {
    private List<Project> newList;
    private List<Project> oldList;

    public MyDiffUtilCallback(List<Project> newList, List<Project> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        if (oldList == null) {
            return 0;
        }
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        if (newList == null) {
            return 0;
        }
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition).getName().equals(oldList.get(oldItemPosition).getName());
        //  && newList.get(newItemPosition).getDescription().equals(oldList.get(oldItemPosition).getDescription());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Project newConfig = newList.get(newItemPosition);
        Project oldCOnfig = oldList.get(oldItemPosition);

        Bundle diff = new Bundle();
        if (newConfig.getId() != oldCOnfig.getId()) {
            diff.getLong("id", newConfig.getId());
        }
        if (!newConfig.getName().equals(oldCOnfig.getName())) {
            diff.putString("name", newConfig.getName());
        }
        if (diff.size() == 0) {
            return null;
        }
        return diff;
    }
}