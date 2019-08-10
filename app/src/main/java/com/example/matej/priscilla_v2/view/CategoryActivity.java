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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.matej.priscilla_v2.LoginResolver;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.SpacesItemDecoration;
import com.example.matej.priscilla_v2.adapters.RecyclerViewCategoryAdapter;
import com.example.matej.priscilla_v2.databinding.ActivityCategoryBinding;
import com.example.matej.priscilla_v2.model.Category;
import com.example.matej.priscilla_v2.viewmodel.CategoryViewModel;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    CategoryViewModel categoryViewModel;

    RecyclerView recyclerViewCategories;
    RecyclerViewCategoryAdapter adapterCategories;
    RecyclerView.LayoutManager layoutManagerCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_category);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.init();  // creating homeRepository if already does not exist

        ActivityCategoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        binding.setViewmodel(categoryViewModel);

        setupRecyclerView(this);



        categoryViewModel.getCategoryRepository().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
//                Gson json = new Gson();
//                Toast.makeText(CategoryActivity.this, json.toJson(categories), Toast.LENGTH_LONG).show();

                adapterCategories.setCategories(categories, CategoryActivity.this);

            }
        });
    }

    @SuppressLint("WrongConstant")
    public void setupRecyclerView(Context ctx){
        recyclerViewCategories = (RecyclerView) findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setHasFixedSize(true);

        recyclerViewCategories.addItemDecoration(new SpacesItemDecoration(20));

        layoutManagerCategories = new GridLayoutManager(ctx, 2, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategories.setLayoutManager(layoutManagerCategories);

        adapterCategories = new RecyclerViewCategoryAdapter();
        recyclerViewCategories.setAdapter(adapterCategories);

        adapterCategories.setOnItemClickListener(new RecyclerViewCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                    Intent intent = new Intent(CategoryActivity.this, AreaActivity.class);
                    intent.putExtra("categoryId", category.getId());
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
