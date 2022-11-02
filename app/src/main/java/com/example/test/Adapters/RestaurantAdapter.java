package com.example.test.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.DetailActivity;
import com.example.test.MainActivity;
import com.example.test.Models.MainModel;
import com.example.test.Models.RestaurantModel;
import com.example.test.R;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.viewholder>{

    ArrayList <RestaurantModel> list;


    Context context;

    public RestaurantAdapter(ArrayList<RestaurantModel> list, Context context) {
        this.list = list;

        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final RestaurantModel model= list.get(position);
        holder.foodImage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MainActivity.class);

                ArrayList<MainModel> list = new ArrayList<>();

                list.add(new MainModel(R.drawable.burger5, "AppleBee", "5", "Chicken burger"));
                list.add(new MainModel(R.drawable.burger, "Burger King", "10", "Chicken burger!"));
                list.add(new MainModel(R.drawable.burger3, "Chick Fe Le", "15", "Chicken burger!!"));
                list.add(new MainModel(R.drawable.burger4, "Dominos", "20", "Chicken burger!!!"));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView mainName,price,description;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            foodImage =itemView.findViewById(R.id.imageView);
            mainName =itemView.findViewById(R.id.name);
            price =itemView.findViewById(R.id.orderPrice);
            description =itemView.findViewById(R.id.descripion);
        }
    }
}
