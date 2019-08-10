package com.example.matej.priscilla_v2;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewMessageAdapter extends RecyclerView.Adapter<RecyclerViewMessageAdapter.ViewHolder> {

    private List<Message> messages = new ArrayList<>();

    // representing item of a list
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);  // itemView corresponds to a view list_items

            title = itemView.findViewById(R.id.MessageTitle);
            text= itemView.findViewById(R.id.MessageText);
        }
    }

    @NonNull
    @Override
    public RecyclerViewMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item, parent, false);

        return new ViewHolder(v);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewMessageAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(messages.get(position));

        holder.title.setText(messages.get(position).getTitle());
        holder.text.setText(messages.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }
}

