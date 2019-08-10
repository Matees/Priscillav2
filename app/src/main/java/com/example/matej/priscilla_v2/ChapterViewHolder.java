package com.example.matej.priscilla_v2;

import android.media.Image;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import org.w3c.dom.Text;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class ChapterViewHolder extends GroupViewHolder {
    private TextView twTitle;
    private TextView twCount;
    private ImageView iwArrow;
    private ImageView iwQuestionMark;

    public ChapterViewHolder(View itemView) {
        super(itemView);
        twTitle = itemView.findViewById(R.id.expandable_group);
        twCount= itemView.findViewById(R.id.expandable_count);
        iwArrow = itemView.findViewById(R.id.expand_arrow);
        iwQuestionMark = itemView.findViewById(R.id.expand_question_mark);
    }

    public void bind(ExpandableGroupTitle group){
        twTitle.setText(group.getTitle());
        twCount.setText(String.valueOf(group.getItemCount()));
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        iwArrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        iwArrow.setAnimation(rotate);
    }
}
