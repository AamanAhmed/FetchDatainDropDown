package com.example.fetchdataindropdown;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Db extends SQLiteOpenHelper {
    String Databasename = "shopping.db";

    public Db(@Nullable Context context) { super (context, "shopping.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table category(Id integer primary key autoincrement,catname text)");
        sqLiteDatabase.execSQL("create table product(Id integer primary key autoincrement,proname text,proprice integer,categoryid integer)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists category");
        sqLiteDatabase.execSQL("drop table if exists product");

    }

    public  void categoryinsert (String categoryname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("catname", categoryname);
        db.insert("category", null, c);
    }

    public  void productinsert (String productname, Integer productprice, Integer catid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("proname", productname);
        c.put("proprice", productprice);
        c.put("categoryid", catid);
        db.insert("product", null, c);
    }

    public List<String> fetchdata (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor find = db.rawQuery("Select * from category" , null);
        List<String> tablerecord = new ArrayList<>();
        while (find.moveToNext()){
            tablerecord.add(find.getString(1));
        }
        return tablerecord;
    }


}
