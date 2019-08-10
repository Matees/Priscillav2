package com.example.matej.priscilla_v2.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.model.Area;
import com.example.matej.priscilla_v2.model.Course;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewCoursesAdapter extends RecyclerView.Adapter<RecyclerViewCoursesAdapter.ViewHolder> {
    private OnItemClickListener listener;
    private List<Course> courses = new ArrayList<>();
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(Course course);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // representing item of a list
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView status;
        TextView description;
        TextView details;
        CardView courseCard;

        public ViewHolder(View itemView) {
            super(itemView);  // itemView corresponds to a view list_items

            title = itemView.findViewById(R.id.course_title);
            status = itemView.findViewById(R.id.course_status);
            description = itemView.findViewById(R.id.course_description);
            details = itemView.findViewById(R.id.course_details);
            courseCard = itemView.findViewById(R.id.course_card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(courses.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerViewCoursesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);

        return new ViewHolder(v);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewCoursesAdapter.ViewHolder holder, final int position) {
        holder.itemView.setTag(courses.get(position));

        holder.title.setText(courses.get(position).getTitle());
        holder.description.setText(courses.get(position).getDescription());

        if (courses.get(position).getCourseStatus() != null){
            holder.status.setBackgroundResource(R.drawable.ic_lock_open_black_24dp);
        }else{
            holder.status.setBackgroundResource(R.drawable.ic_lock_outline_black_24dp);
        }

        String detailsText = "";
        if (courses.get(position).getStartDate() == null) {
            detailsText += "<b>Starting at :</b> not started<br>";
        }else{
            detailsText += "<b>Starting at :</b> " + courses.get(position).getStartDate() + "<br>";

            if (courses.get(position).getFinishDate() == null){
                detailsText += "<b>Finished :</b> not finished yet<br>";
            }else{
                detailsText += "<b>Finished :</b> " + courses.get(position).getFinishDate() + "<br>";
            }
        }

        detailsText += "<b>Tasks:</b> " + courses.get(position).getTaskFinished() + "/" + courses.get(position).getTaskAll() + "<br>"
                + "<b>Programs:</b> " + courses.get(position).getProgramFinished() + "/" + courses.get(position).getProgramAll();

        holder.details.setText(Html.fromHtml(detailsText));

        holder.courseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(courses.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public void setCourses(List<Course> courses, Context ctx) {
        this.context = ctx;
        this.courses = courses;
        notifyDataSetChanged();
    }
}

