package com.example.android_pizza_shop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

public class SQLHelper extends SQLiteOpenHelper {
    private final Context context;

    public SQLHelper(Context context){
        super(context,DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
        this.context = context;
        //Log.d("SQHelper Constructor", "SQLHelper constructor: Database created or opened.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.d("SQHelper", "onCreate: Database created.");

        db.execSQL(DBConstants.createTableUsers);
        //Log.d("SQHelper", "onCreate: User table created.");

        db.execSQL(DBConstants.insertInitialUsers);
        //Log.d("SQHelper", "onCreate: User table populated.");

        db.execSQL(DBConstants.createTablePizzas);
        //Log.d("SQHelper", "onCreate: Pizzas table created.");
        db.execSQL(DBConstants.insertInitialPizzas);
        //Log.d("SQHelper", "onCreate: Pizzas populated.");

        db.execSQL(DBConstants.createTableSides);
        //Log.d("SQHelper", "onCreate: Sides table created.");
        db.execSQL(DBConstants.insertInitialSides);
        //Log.d("SQHelper", "onCreate: Sides populated.");

        db.execSQL(DBConstants.createTableDrinks);
        //Log.d("SQHelper", "onCreate: Drinks table created.");
        db.execSQL(DBConstants.insertInitialDrinks);
        //Log.d("SQHelper", "onCreate: Drinks populated.");

        db.execSQL(DBConstants.createTableDesserts);
        //Log.d("SQHelper", "onCreate: Desserts table created.");
        db.execSQL(DBConstants.insertInitialDesserts);
        //Log.d("SQHelper", "onCreate: Desserts populated.");

        db.execSQL(DBConstants.createTablePickupStores);
        //Log.d("SQHelper", "onCreate: Pickup Stores table created.");
        db.execSQL(DBConstants.insertInitialPickupStores);
        //Log.d("SQHelper", "onCreate: Pickup Stores populated.");

        db.execSQL(DBConstants.createTableOrders);
        //Log.d("SQHelper", "onCreate: Orders table created.");
        db.execSQL(DBConstants.createTableOrderItems);
        //Log.d("SQHelper", "onCreate: OrderItems table populated.");

        //NOTE: No dependencies yet on products table. Possible future implementation if there is enough time
        db.execSQL(DBConstants.createTableProducts);
        //Log.d("SQHelper", "onCreate: Products table created.");
        db.execSQL(DBConstants.insertInitialProducts);
        //Log.d("SQHelper", "onCreate: Products populated.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean isDatabaseExists() {
        String databasePath = context.getDatabasePath(DBConstants.DATABASE_NAME).getPath();
        File file = new File(databasePath);
        return file.exists() && !file.isDirectory();
    }
}
