package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.test.Adapters.MainAdapter;
import com.example.test.Adapters.RestaurantAdapter;
import com.example.test.Models.MainModel;
import com.example.test.Models.RestaurantModel;
import com.example.test.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger5, "Chicken burger", "5", "Chicken"));
        list.add(new MainModel(R.drawable.burger, "Burger bomb", "10", "Chicken, Beef"));
        list.add(new MainModel(R.drawable.burger3, "Extra spicy", "15", "Chicken"));
        list.add(new MainModel(R.drawable.burger4, "Loaded burger", "20", "Chicken, Veg"));
        list.add(new MainModel(R.drawable.burger2, "Loaded burger", "20", "Chicken, Veg"));
        list.add(new MainModel(R.drawable.burger5, "Loaded burger", "20", "Chicken, Veg"));



        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,OrderActivity.class));
                break;
            case R.id.restaurants:

                ArrayList<RestaurantModel> list = new ArrayList<>();

                list.add(new RestaurantModel(R.drawable.applebee, "AppleBee", "5", "Have a Lovely day"));
                list.add(new RestaurantModel(R.drawable.burgerking, "Burger King", "10", "Yee burgers!!"));
                list.add(new RestaurantModel(R.drawable.chick, "Chick Fe Le", "15", "Chicken feasta"));
                list.add(new RestaurantModel(R.drawable.dominos, "Dominos", "20", "Pizza lovers"));
                list.add(new RestaurantModel(R.drawable.kfc, "KFC", "25", "Finger lickin good"));
                list.add(new RestaurantModel(R.drawable.krispikreme, "Krispy Kreme", "25", "Best doughnuts"));
                list.add(new RestaurantModel(R.drawable.mc, "McDonalds", "25", "Let's have some fun"));
                list.add(new RestaurantModel(R.drawable.pizzahut, "Pizza Hut", "25", "You know it"));
                list.add(new RestaurantModel(R.drawable.subway, "Subway", "25", "Have a sandwitch"));
                list.add(new RestaurantModel(R.drawable.wendys, "Wendy's", "25", "Lovely!"));

                RestaurantAdapter adapter = new RestaurantAdapter(list,this);
                binding.recyclerview.setAdapter(adapter);

                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                binding.recyclerview.setLayoutManager(layoutManager);
        }
        return super.onOptionsItemSelected(item);
    }
}