package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.test.Adapters.MainAdapter;
import com.example.test.Adapters.OrdersAdapter;
import com.example.test.Adapters.RestaurantAdapter;
import com.example.test.Models.MainModel;
import com.example.test.Models.OrdersModel;
import com.example.test.Models.RestaurantModel;
import com.example.test.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper helper =new dbHelper(this);
        if(getIntent().getIntExtra("type",0)==1){
            ArrayList<OrdersModel> list = helper.getOrders();

            OrdersAdapter adapter = new OrdersAdapter(list,this);
            binding.orderRecyclerView.setAdapter(adapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.orderRecyclerView.setLayoutManager(layoutManager);
        }else {
            ArrayList<OrdersModel> list = helper.getOrders();



            OrdersAdapter adapter = new OrdersAdapter(list,this);
            binding.orderRecyclerView.setAdapter(adapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            binding.orderRecyclerView.setLayoutManager(layoutManager);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.checkout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.checkout:
                dbHelper helper =new dbHelper(this);
               /* boolean isUpdated =  helper.updateCheckoutOrder(1);
                 isUpdated =  helper.updateCheckoutOrder(2);
                 isUpdated =  helper.updateCheckoutOrder(3);
                 isUpdated =  helper.updateCheckoutOrder(4);
                 isUpdated =  helper.updateCheckoutOrder(5);
                 isUpdated =  helper.updateCheckoutOrder(6);

                if (isUpdated) {
                    Toast.makeText(OrderActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(OrderActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                break;*/
            case R.id.checkedout:
                Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
                intent.putExtra("type",1);
                OrderActivity.this.startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}