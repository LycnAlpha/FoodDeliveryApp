package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

import com.example.test.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper helper = new dbHelper(this);

        if(getIntent().getIntExtra("type",0)==1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");
            final String restaurant = "KFC";
            final String time = "10.00";


            binding.itemImage.setImageResource(image);
            binding.totalPrice.setText(String.format("%d", price));
            binding.itemName.setText(name);
            binding.detailDescription.setText(description);



            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qty = Integer.parseInt(binding.quantiy.getText().toString());
                    qty=qty+1;
                    int totPrice = price * qty;

                    binding.quantiy.setText(String.format("%d", qty));
                    binding.totalPrice.setText(String.format("%d", totPrice));
                }
            });

            binding.substract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qty = Integer.parseInt(binding.quantiy.getText().toString());
                    qty = qty - 1;
                    int totPrice = price * qty;

                    binding.quantiy.setText(String.format("%d", qty));
                    binding.totalPrice.setText(String.format("%d", totPrice));
                }
            });

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertOrder(

                            binding.emaiBox.getText().toString(),
                            binding.passwordBox.getText().toString(),
                            Integer.parseInt(binding.totalPrice.getText().toString()),
                            image,
                            name,
                            description,
                            restaurant,
                            time,
                            Integer.parseInt(binding.quantiy.getText().toString())


                    );

                    if (isInserted) {
                        Toast.makeText(DetailActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            int id = getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int qty = Integer.parseInt(binding.quantiy.getText().toString());
                    int prc = Integer.parseInt(binding.totalPrice.getText().toString())/qty;
                    qty=qty+1;
                    int totPrice = prc * qty;

                    binding.quantiy.setText(String.format("%d", qty));
                    binding.totalPrice.setText(String.format("%d", totPrice));
                }
            });

            binding.substract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int qty = Integer.parseInt(binding.quantiy.getText().toString());
                    int prc = Integer.parseInt(binding.totalPrice.getText().toString())/qty;
                    qty = qty - 1;
                    int totPrice = prc * qty;

                    binding.quantiy.setText(String.format("%d", qty));
                    binding.totalPrice.setText(String.format("%d", totPrice));
                }
            });

            binding.itemImage.setImageResource(cursor.getInt(4));
            binding.totalPrice.setText(Integer.toString(cursor.getInt(3)));
            binding.itemName.setText(cursor.getString(9));
            binding.detailDescription.setText(cursor.getString(6));
            binding.quantiy.setText(cursor.getString(5));
            binding.insertBtn.setText("Update now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();
                   boolean isUpdated =  helper.updateOrder(
                            Integer.parseInt(binding.totalPrice.getText().toString()),
                            binding.itemName.getText().toString(),
                            "10.05",
                            Integer.parseInt(binding.quantiy.getText().toString()),
                            id



                    );

                    if (isUpdated) {
                        Toast.makeText(DetailActivity.this, "Data Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }
}