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
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.matej.priscilla_v2.Constants;
import com.example.matej.priscilla_v2.LoginResolver;
import com.example.matej.priscilla_v2.Message;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.RecyclerViewCategoryAdapter;
import com.example.matej.priscilla_v2.RecyclerViewMessageAdapter;
import com.example.matej.priscilla_v2.databinding.ActivityCategoryBinding;
import com.example.matej.priscilla_v2.databinding.ActivityHomeBinding;
import com.example.matej.priscilla_v2.model.Category;
import com.example.matej.priscilla_v2.model.MainMenu;
import com.example.matej.priscilla_v2.model.Profilmenu;
import com.example.matej.priscilla_v2.viewmodel.CategoryViewModel;
import com.example.matej.priscilla_v2.viewmodel.HomeViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
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

        ActivityCategoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        binding.setViewmodel(categoryViewModel);

        setupRecyclerView(this);

        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);


        categoryViewModel.init();  // creating homeRepository if already does not exist
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

        layoutManagerCategories = new GridLayoutManager(ctx, 2, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategories.setLayoutManager(layoutManagerCategories);

        adapterCategories = new RecyclerViewCategoryAdapter();
        recyclerViewCategories.setAdapter(adapterCategories);

        adapterCategories.setOnItemClickListener(new RecyclerViewCategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category category) {
                //startActivity(new Intent(CategoryActivity.this, CategoryActivity.class));
            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                // ListView Clicked item value
//                String  itemValue    = (String) listView.getItemAtPosition(position);
//
//                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :" + position + "  ListItem : " + itemValue , Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
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
