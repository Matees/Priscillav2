package com.example.matej.priscilla_v2.view;

import com.example.matej.priscilla_v2.Event;
import com.example.matej.priscilla_v2.databinding.ActivityHomeBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.matej.priscilla_v2.LoginResolver;
import com.example.matej.priscilla_v2.Message;
import com.example.matej.priscilla_v2.R;
import com.example.matej.priscilla_v2.adapters.RecyclerViewMessageAdapter;
import com.example.matej.priscilla_v2.model.MainMenu;
import com.example.matej.priscilla_v2.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    HomeViewModel homeViewModel;

    RecyclerView recyclerViewMessages;
    RecyclerViewMessageAdapter adapterMessages;
    RecyclerView.LayoutManager layoutManagerMessages;

    ListView listView;
//    String [] values;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toast.makeText(this, "You were successfully logged in.", Toast.LENGTH_SHORT).show();

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.init();  // creating homeRepository if already does not exist

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home); // this must be called as first in activity, we can then remove setContentView
        binding.setViewmodel(homeViewModel);

//        Constants.instance(this.getApplicationContext());

        setupRecyclerView(this);

        homeViewModel.getHomeRepository().observe(this, new Observer<MainMenu>() {   // observing response of request
            @Override
            public void onChanged(MainMenu mainMenu) {
                if (mainMenu.getT() != null){
                    Toast.makeText(HomeActivity.this, "Check your internet connection.", Toast.LENGTH_LONG).show();
                    return;
                }

                if (mainMenu.getCode() != 0){
                    logout(HomeActivity.this, LoginActivity.class);
                    return;
                }
                // temporary
                Message message = new Message("fdsafdasfa", "Title");
                List<Message> messages = new ArrayList<>();
                messages.add(message);
                adapterMessages.setMessages(messages);

                setupListView(mainMenu.getProfilmenu().getValues());

//                Gson json = new Gson();
//                Toast.makeText(HomeActivity.this, json.toJson(mainMenu), Toast.LENGTH_LONG).show();
            }
        });

        homeViewModel.getNavigateToDetails().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> stringEvent) {
                String content = stringEvent.getContentIfNotHandled();
                if (content == null){   // if another observer already observed event
                    return;
                }

//                Toast.makeText(HomeActivity.this, content, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(HomeActivity.this, CategoryActivity.class));
            }
        });

    }

    @SuppressLint("WrongConstant")
    public void setupRecyclerView(Context context){
        recyclerViewMessages = (RecyclerView) findViewById(R.id.recyclerViewMessages);
        recyclerViewMessages.setHasFixedSize(true);

        layoutManagerMessages = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerViewMessages.setLayoutManager(layoutManagerMessages);

        adapterMessages = new RecyclerViewMessageAdapter();
        recyclerViewMessages.setAdapter(adapterMessages);

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

    public void setupListView(String [] values){
        listView = (ListView) findViewById(R.id.listViewMenu);
//
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);
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
