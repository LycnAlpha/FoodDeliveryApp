package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.test.Models.OrdersModel;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;

    public dbHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement," +
                        "email text," +
                        "password text," +
                        "price integer," +
                        "image integer," +
                        "quantity integer," +
                        "description text," +
                        "restaurant text," +
                        "time text," +
                        "foodName text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String email,String password,int price,int image,String desc,String foodName,String restaurant,String time,int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("restaurant", restaurant);
        values.put("time", time);
        values.put("foodName", foodName);
        values.put("quantity", quantity);
        //values.put("checked", false);

        long id = database.insert("orders", null, values);

        if (id <= 0) {
            return false;
        } else {
            return true;
        }

    }
    public ArrayList<OrdersModel> getOrders(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,price,image,quantity,description,restaurant,time,foodName from orders ",null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setPrice(cursor.getInt(1)+"");
                model.setOrderImage(cursor.getInt(2));
                model.setRestaurantName(cursor.getString(5));
                model.setTime(cursor.getString(6));
                model.setSoldItemName(cursor.getString(7));
                orders.add(model);

            }
        }

        cursor.close();
        database.close();
        return orders;
    }

    public ArrayList<OrdersModel> getCheckedOrders(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,price,image,quantity,description,restaurant,time,foodName from orders where checked=true",null);

        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0)+"");
                model.setPrice(cursor.getInt(1)+"");
                model.setOrderImage(cursor.getInt(2));
                model.setRestaurantName(cursor.getString(5));
                model.setTime(cursor.getString(6));
                model.setSoldItemName(cursor.getString(7));
                orders.add(model);

            }
        }

        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){

        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id="+id,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public boolean updateOrder(int price,String foodName,String time,int quantity,int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
       // values.put("email", email);
       // values.put("password", password);
        values.put("price", price);
        //values.put("image", image);
       // values.put("description", desc);
        //values.put("restaurant", restaurant);
        values.put("time", time);
        values.put("foodName", foodName);
        values.put("quantity", quantity);
        //values.put("checked", true);

        long row = database.update("orders",values,"id="+id,null);

        if (row <= 0) {
            return false;
        } else {
            return true;
        }

    }

    public int deleteOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders","id="+id,null);
    }

  /*  public boolean updateCheckoutOrder(int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("checked", true);

        long row = database.update("orders", values, "id=" + id, null);

        if (row <= 0) {
            return false;
        } else {
            return true;
        }

    }*/
}



