package com.example.matej.priscilla_v2;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class LectionViewHolder extends ChildViewHolder {
    private TextView tw;

    public LectionViewHolder(View itemView) {
        super(itemView);
        tw = itemView.findViewById(R.id.expandable_item);
    }

    public void bind(ExpandableItemTitle item){
        tw.setText(item.title);
    }
}
