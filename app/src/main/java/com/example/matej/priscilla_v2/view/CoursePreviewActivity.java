package com.example.matej.priscilla_v2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.matej.priscilla_v2.ExpandableAdapter;
import com.example.matej.priscilla_v2.ExpandableGroupTitle;
import com.example.matej.priscilla_v2.ExpandableItemTitle;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.adapters.RecyclerViewCoursesAdapter;
import com.example.matej.priscilla_v2.databinding.ActivityCourseBinding;
import com.example.matej.priscilla_v2.databinding.ActivityCoursePreviewBinding;
import com.example.matej.priscilla_v2.model.Course;
import com.example.matej.priscilla_v2.model.CoursePreview.ChapterList;
import com.example.matej.priscilla_v2.model.CoursePreview.CoursePreview;
import com.example.matej.priscilla_v2.model.CoursePreview.Lesson;
import com.example.matej.priscilla_v2.model.Courses;
import com.example.matej.priscilla_v2.viewmodel.CoursePreviewViewModel;
import com.example.matej.priscilla_v2.viewmodel.CourseViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

public class CoursePreviewActivity extends AppCompatActivity {
    RecyclerView recyclerViewCoursePreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_preview);

        CoursePreviewViewModel coursePreviewViewModel = ViewModelProviders.of(this).get(CoursePreviewViewModel.class);
        coursePreviewViewModel.init(getIntent().getIntExtra("courseId", 0 ));

        final ActivityCoursePreviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_course_preview);
        binding.setViewmodel(coursePreviewViewModel);

        setupRecyclerView(this);

        coursePreviewViewModel.getCoursePreviewRepository().observe(this, new Observer<CoursePreview>() {
            @Override
            public void onChanged(CoursePreview coursePreview) {
                binding.coursePreviewTitle.setText(coursePreview.getTitle());
                ArrayList<ExpandableGroupTitle> group = new ArrayList<>();

                for (ChapterList chapter : coursePreview.getChapterList()) {
                    ArrayList<ExpandableItemTitle> item = new ArrayList<>();
                    for (Lesson lesson : chapter.getLessons()) {
                        item.add(new ExpandableItemTitle(lesson.getLesson()));
                    }
                    ExpandableGroupTitle groupTitle = new ExpandableGroupTitle(chapter.getName(), item);
                    group.add(groupTitle);
                }
                ExpandableAdapter adapter = new ExpandableAdapter(group);
                recyclerViewCoursePreview.setAdapter(adapter);
            }
        });
    }

    public void setupRecyclerView(Context ctx){
        recyclerViewCoursePreview = (RecyclerView) findViewById(R.id.expandable_recyclerview);
        recyclerViewCoursePreview.setHasFixedSize(true);
        recyclerViewCoursePreview.setLayoutManager(new LinearLayoutManager(ctx));
    }
}
