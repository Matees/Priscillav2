package com.example.matej.priscilla_v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.matej.priscilla_v2.model.Category;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewCategoryAdapter extends RecyclerView.Adapter<RecyclerViewCategoryAdapter.ViewHolder> {
    private OnItemClickListener listener;
    private List<Category> categories = new ArrayList<>();
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Category category);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // representing item of a list
    public class ViewHolder extends RecyclerView.ViewHolder{

        Button button;

        public ViewHolder(View itemView) {
            super(itemView);  // itemView corresponds to a view list_items

            button = itemView.findViewById(R.id.category_item_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(categories.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerViewCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        return new ViewHolder(v);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCategoryAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(categories.get(position));

        List<String> colors = new ArrayList<>();
        colors.add("#FFAA66CC");
        colors.add("#FF99CC00");
        colors.add("#FFFFBB33");
        colors.add("#FFFF4444");

        int index = position;
        if (position > colors.size()) index = 0;

        String randomAndroidColor = colors.get(index);

        holder.button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(randomAndroidColor)));

        holder.button.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<Category> categories, Context ctx) {
        this.context = ctx;
        this.categories = categories;
        notifyDataSetChanged();
    }
}

