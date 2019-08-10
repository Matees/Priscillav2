package com.example.matej.priscilla_v2.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.matej.priscilla_v2.LoginResolver;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.adapters.RecyclerViewAreaAdapter;
import com.example.matej.priscilla_v2.adapters.RecyclerViewCategoryAdapter;
import com.example.matej.priscilla_v2.databinding.ActivityAreaBinding;
import com.example.matej.priscilla_v2.databinding.ActivityCategoryBinding;
import com.example.matej.priscilla_v2.model.Area;
import com.example.matej.priscilla_v2.model.Category;
import com.example.matej.priscilla_v2.viewmodel.AreaViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AreaActivity extends AppCompatActivity {
    AreaViewModel areaViewModel;

    RecyclerView recyclerViewAreas;
    RecyclerViewAreaAdapter adapterAreas;
    RecyclerView.LayoutManager layoutManagerAreas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_category);

        areaViewModel = ViewModelProviders.of(this).get(AreaViewModel.class);
        areaViewModel.init(getIntent().getIntExtra("categoryId", 0 ));  // creating homeRepository if already does not exist

        ActivityAreaBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_area);
        binding.setViewmodel(areaViewModel);

        setupRecyclerView(this);

        areaViewModel.getAreaRepository().observe(this, new Observer<List<Area>>() {
            @Override
            public void onChanged(List<Area> areas) {
//                Gson json = new Gson();
//                Toast.makeText(CategoryActivity.this, json.toJson(area), Toast.LENGTH_LONG).show();
                adapterAreas.setAreas(areas, AreaActivity.this);

            }
        });
    }

    @SuppressLint("WrongConstant")
    public void setupRecyclerView(Context ctx){
        recyclerViewAreas = (RecyclerView) findViewById(R.id.recyclerViewAreas);
        recyclerViewAreas.setHasFixedSize(true);

        layoutManagerAreas = new GridLayoutManager(ctx, 2, LinearLayoutManager.VERTICAL, false);
        recyclerViewAreas.setLayoutManager(layoutManagerAreas);

        adapterAreas = new RecyclerViewAreaAdapter();
        recyclerViewAreas.setAdapter(adapterAreas);

        adapterAreas.setOnItemClickListener(new RecyclerViewAreaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Area area) {
                Intent intent = new Intent(AreaActivity.this, CourseActivity.class);
                intent.putExtra("areaId", area.getId() + 1);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                logout(this, LoginActivity.class);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void logout(Context finish, Class start){
        LoginResolver.clearSp(this);

        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }
}
