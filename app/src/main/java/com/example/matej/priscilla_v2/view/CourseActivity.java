package com.example.matej.priscilla_v2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.adapters.RecyclerViewAreaAdapter;
import com.example.matej.priscilla_v2.adapters.RecyclerViewCoursesAdapter;
import com.example.matej.priscilla_v2.databinding.ActivityAreaBinding;
import com.example.matej.priscilla_v2.databinding.ActivityCourseBinding;
import com.example.matej.priscilla_v2.model.Area;
import com.example.matej.priscilla_v2.model.Course;
import com.example.matej.priscilla_v2.model.CoursePreview.CoursePreview;
import com.example.matej.priscilla_v2.model.Courses;
import com.example.matej.priscilla_v2.viewmodel.AreaViewModel;
import com.example.matej.priscilla_v2.viewmodel.CourseViewModel;
import com.google.gson.Gson;

import java.util.List;

public class CourseActivity extends AppCompatActivity {

    RecyclerView recyclerViewCourses;
    RecyclerViewCoursesAdapter adapterCourses;
    RecyclerView.LayoutManager layoutManagerCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_course);

        CourseViewModel courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);
        courseViewModel.init(getIntent().getIntExtra("areaId", 0 ));  // creating homeRepository if already does not exist

        ActivityCourseBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_course);
        binding.setViewmodel(courseViewModel);

        setupRecyclerView(this);

        courseViewModel.getCourseRepository().observe(this, new Observer<Courses>() {
            @Override
            public void onChanged(Courses courses) {
                Gson json = new Gson();
                Toast.makeText(CourseActivity.this, json.toJson(courses), Toast.LENGTH_LONG).show();
                adapterCourses.setCourses(courses.getCourses(), CourseActivity.this);

            }
        });
    }

    @SuppressLint("WrongConstant")
    public void setupRecyclerView(Context ctx){
        recyclerViewCourses = (RecyclerView) findViewById(R.id.recyclerViewCourses);
        recyclerViewCourses.setHasFixedSize(true);

        layoutManagerCourses = new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false);
        recyclerViewCourses.setLayoutManager(layoutManagerCourses);

        adapterCourses = new RecyclerViewCoursesAdapter();
        recyclerViewCourses.setAdapter(adapterCourses);

        adapterCourses.setOnItemClickListener(new RecyclerViewCoursesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Course course) {
                Intent intent = new Intent(CourseActivity.this, CoursePreviewActivity.class);
                intent.putExtra("courseId", course.getId());
                startActivity(intent);
            }
        });
    }
}
