package com.example.matej.priscilla_v2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ExpandableAdapter extends ExpandableRecyclerViewAdapter<ChapterViewHolder, LectionViewHolder> {
    public ExpandableAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public ChapterViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_group, parent, false);
        return new ChapterViewHolder(v);
    }

    @Override
    public LectionViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expandable_recyclerview_item, parent, false);
        return new LectionViewHolder(v);
    }

    @Override
    public void onBindChildViewHolder(LectionViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final ExpandableItemTitle item = (ExpandableItemTitle) group.getItems().get(childIndex);
        holder.bind(item);
    }

    @Override
    public void onBindGroupViewHolder(ChapterViewHolder holder, int flatPosition, ExpandableGroup group) {
        final ExpandableGroupTitle GroupTitle = (ExpandableGroupTitle) group;
        holder.bind(GroupTitle);
    }
}
