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
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.matej.priscilla_v2.Constants;
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

    RecyclerView recyclerViewCategories;
    RecyclerViewCategoryAdapter adapterCategories;
    RecyclerView.LayoutManager layoutManagerCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        setupRecyclerView();

        CategoryViewModel categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);

//        ActivityCategoryBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
//        binding.setViewmodel(categoryViewModel);

        categoryViewModel.init();  // creating homeRepository if already does not exist
        categoryViewModel.getCategoryRepository().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                Gson json = new Gson();
                Toast.makeText(CategoryActivity.this, json.toJson(categories), Toast.LENGTH_LONG).show();
                adapterCategories.setMessages(categories, CategoryActivity.this);

            }
        });
    }

    @SuppressLint("WrongConstant")
    public void setupRecyclerView(){
        recyclerViewCategories = (RecyclerView) findViewById(R.id.recyclerViewCategories);
        recyclerViewCategories.setHasFixedSize(true);

        layoutManagerCategories = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategories.setLayoutManager(layoutManagerCategories);

        adapterCategories = new RecyclerViewCategoryAdapter();
        recyclerViewCategories.setAdapter(adapterCategories);

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
}
