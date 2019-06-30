package com.example.matej.priscilla_v2;

import android.annotation.TargetApi;
import android.content.Context;
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

    private List<Category> categories = new ArrayList<>();
    private Context context;

    // representing item of a list
    public class ViewHolder extends RecyclerView.ViewHolder{

        Button button;

        public ViewHolder(View itemView) {
            super(itemView);  // itemView corresponds to a view list_items

            button = itemView.findViewById(R.id.category_item_button);
        }
    }

    @NonNull
    @Override
    public RecyclerViewCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        return new RecyclerViewCategoryAdapter.ViewHolder(v);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCategoryAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(categories.get(position));

        List<Integer> colors = new ArrayList<>();
//        colors.add(R.color.purple);
        colors.add(R.color.green);
        colors.add(R.color.orange);
        colors.add(R.color.red);

        int randomAndroidColor = colors.get(new Random().nextInt(2));

//        Drawable drawable = context.getResources().getDrawable(R.drawable.circle_button);
//        drawable.setColorFilter(new PorterDuffColorFilter(randomAndroidColor, PorterDuff.Mode.SCREEN));

        holder.button.setBackgroundColor(randomAndroidColor);

        holder.button.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setMessages(List<Category> categories, Context ctx) {
        this.context = ctx;
        this.categories = categories;
        notifyDataSetChanged();
    }
}

