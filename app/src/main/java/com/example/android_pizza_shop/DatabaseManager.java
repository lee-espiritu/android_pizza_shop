package com.example.android_pizza_shop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private SQLHelper sqlHelper;
    private SQLiteDatabase db;

    public DatabaseManager(Context context){
        sqlHelper = new SQLHelper(context);
        Log.d("DatabaseManager", "DatabaseManager constructor: DatabaseManager created.");
    }

    public void open() {
        db = sqlHelper.getWritableDatabase();
        Log.d("DatabaseManager", "open: Database opened.");
    }

    public void close() {
        db.close();
        Log.d("DatabaseManager", "close: Database closed.");
    }

    public boolean isDatabaseExists() {
        return sqlHelper.isDatabaseExists();
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }

    public boolean checkEmailExists(String email) {
        db = sqlHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DBConstants.USERS_TABLE + " WHERE " + DBConstants.COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email});
        boolean emailExists = cursor.getCount() > 0;
        cursor.close();
        return emailExists;
    }

    public List<FoodItem> getAllPizzas() {
        List<FoodItem> pizzaList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBConstants.PIZZAS_TABLE, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    int imageIndex = cursor.getColumnIndex(DBConstants.COLUMN_PIZZA_IMAGE);
                    int kilojoulesIndex = cursor.getColumnIndex(DBConstants.COLUMN_PIZZA_KILOJOULES);
                    int foodInfoIndex = cursor.getColumnIndex(DBConstants.COLUMN_PIZZA_DESCRIPTION);
                    int priceIndex = cursor.getColumnIndex(DBConstants.COLUMN_PIZZA_PRICE);
                    int nameIndex = cursor.getColumnIndex(DBConstants.COLUMN_PIZZA_NAME);

                    // Create a FoodItem object with the retrieved data
                    FoodItem pizza = new FoodItem(
                            cursor.getString(imageIndex),
                            cursor.getString(kilojoulesIndex),
                            cursor.getString(foodInfoIndex),
                            cursor.getString(priceIndex),
                            cursor.getString(nameIndex),
                            DBConstants.PIZZAS_TABLE
                    );

                    // Add the pizza to the list
                    pizzaList.add(pizza);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return pizzaList;
    }

    public List<FoodItem> getAllSides() {
        List<FoodItem> sideList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBConstants.SIDES_TABLE, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    int imageIndex = cursor.getColumnIndex(DBConstants.COLUMN_SIDES_IMAGE);
                    int kilojoulesIndex = cursor.getColumnIndex(DBConstants.COLUMN_SIDES_KILOJOULES);
                    int foodInfoIndex = cursor.getColumnIndex(DBConstants.COLUMN_SIDES_DESCRIPTION);
                    int priceIndex = cursor.getColumnIndex(DBConstants.COLUMN_SIDES_PRICE);
                    int nameIndex = cursor.getColumnIndex(DBConstants.COLUMN_SIDES_NAME);

                    // Create a FoodItem object with the retrieved data
                    FoodItem pizza = new FoodItem(
                            cursor.getString(imageIndex),
                            cursor.getString(kilojoulesIndex),
                            cursor.getString(foodInfoIndex),
                            cursor.getString(priceIndex),
                            cursor.getString(nameIndex),
                            DBConstants.SIDES_TABLE
                    );

                    // Add the sides to the list
                    sideList.add(pizza);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return sideList;
    }

    public List<FoodItem> getAllDrinks() {
        List<FoodItem> drinksList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBConstants.DRINKS_TABLE, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    // Assuming your columns have indices, replace them with actual indices
                    int imageIndex = cursor.getColumnIndex(DBConstants.COLUMN_DRINKS_IMAGE);
                    int kilojoulesIndex = cursor.getColumnIndex(DBConstants.COLUMN_DRINKS_KILOJOULES);
                    int foodInfoIndex = cursor.getColumnIndex(DBConstants.COLUMN_DRINKS_DESCRIPTION);
                    int priceIndex = cursor.getColumnIndex(DBConstants.COLUMN_DRINKS_PRICE);
                    int nameIndex = cursor.getColumnIndex(DBConstants.COLUMN_DRINKS_NAME);

                    // Create a FoodItem object with the retrieved data
                    FoodItem pizza = new FoodItem(
                            cursor.getString(imageIndex),
                            cursor.getString(kilojoulesIndex),
                            cursor.getString(foodInfoIndex),
                            cursor.getString(priceIndex),
                            cursor.getString(nameIndex),
                            DBConstants.DRINKS_TABLE
                    );

                    // Add the drinks to the list
                    drinksList.add(pizza);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return drinksList;
    }

    public List<FoodItem> getAllDesserts() {
        List<FoodItem> dessertsList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBConstants.DESSERTS_TABLE, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    // Assuming your columns have indices, replace them with actual indices
                    int imageIndex = cursor.getColumnIndex(DBConstants.COLUMN_DESSERTS_IMAGE);
                    int kilojoulesIndex = cursor.getColumnIndex(DBConstants.COLUMN_DESSERTS_KILOJOULES);
                    int foodInfoIndex = cursor.getColumnIndex(DBConstants.COLUMN_DESSERTS_DESCRIPTION);
                    int priceIndex = cursor.getColumnIndex(DBConstants.COLUMN_DESSERTS_PRICE);
                    int nameIndex = cursor.getColumnIndex(DBConstants.COLUMN_DESSERTS_NAME);

                    // Create a FoodItem object with the retrieved data
                    FoodItem pizza = new FoodItem(
                            cursor.getString(imageIndex),
                            cursor.getString(kilojoulesIndex),
                            cursor.getString(foodInfoIndex),
                            cursor.getString(priceIndex),
                            cursor.getString(nameIndex),
                            DBConstants.DESSERTS_TABLE
                    );

                    // Add the pizza to the list
                    dessertsList.add(pizza);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return dessertsList;
    }

    //Method to insert user entries into user table
    public boolean insertUserIntoDatabase(String email, String password) {
        // Open the database
        open();

        // Hash the password before storing it in the database
        String hashedPassword = PasswordHasher.hashPassword(password);

        // Create ContentValues to insert data into the database
        ContentValues values = new ContentValues();
        values.put(DBConstants.COLUMN_EMAIL, email);
        values.put(DBConstants.COLUMN_PASSWORD, hashedPassword);

        // Insert the user data into the database
        long result = db.insert(DBConstants.USERS_TABLE, null, values);

        // Close the database
        close();

        // Check if the insertion was successful
        return result != -1;
    }

    //Method to insert order entries into Order table
    public long insertOrder(String username, String orderDate, double totalAmount) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ORDERS_USER_NAME, username);
        values.put(DBConstants.ORDERS_ORDER_DATE, orderDate);
        values.put(DBConstants.ORDERS_TOTAL_AMOUNT, totalAmount);

        long orderId = db.insert(DBConstants.ORDERS_TABLE, null, values);
        close();

        return orderId;
    }

    //Method to insert order item entries into orderItem table
    public void insertOrderItem(long orderId, String productType, String productName, double kilojoules, String description, String image, double price, int quantity) {
        open();
        ContentValues values = new ContentValues();
        values.put(DBConstants.ORDERS_ORDER_ID, orderId);
        values.put(DBConstants.ORDERITEMS_PRODUCT_TYPE, productType);
        values.put(DBConstants.ORDERITEMS_PRODUCT_NAME, productName);
        values.put(DBConstants.ORDERITEMS_KILOJOULES, kilojoules);
        values.put(DBConstants.ORDERITEMS_ITEM_DESCRIPTION, description);
        values.put(DBConstants.ORDERITEMS_ITEM_IMAGE, image);
        values.put(DBConstants.ORDERITEMS_ITEM_PRICE, price);
        values.put(DBConstants.ORDERITEMS_ITEM_QUANTITY, quantity);

        db.insert(DBConstants.ORDER_ITEMS_TABLE, null, values);
        close();
    }

    //Method to retrieve previous orders from a signed in user
    public List<Order> getPreviousOrders(String userEmail) {
        List<Order> previousOrders = new ArrayList<>();

        open();

        // Define the columns you want to retrieve
        String[] projection = {
                DBConstants.ORDERS_ORDER_DATE,
                DBConstants.ORDERS_TOTAL_AMOUNT
        };

        // Define the selection criteria
        String selection = DBConstants.ORDERS_USER_NAME + " = ?";
        String[] selectionArgs = {userEmail};

        // Query the database
        Cursor cursor = db.query(
                DBConstants.ORDERS_TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                DBConstants.ORDERS_ORDER_DATE + " DESC"
        );

        // Check if the cursor is not null
        if (cursor != null) {
            try {
                // Iterate through the cursor to retrieve data
                while (cursor.moveToNext()) {
                    String orderDate = cursor.getString(cursor.getColumnIndexOrThrow(DBConstants.ORDERS_ORDER_DATE));
                    double totalAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(DBConstants.ORDERS_TOTAL_AMOUNT));

                    // Create an Order object and add it to the list
                    Order order = new Order(orderDate, totalAmount);
                    previousOrders.add(order);
                }
            } finally {
                // Close the cursor to release resources
                cursor.close();
            }
        }

        // Close the database
        close();

        return previousOrders;
    }

    //Method to retrieve all stores as a list from the database
    public List<Store> getPickupStores() {
        List<Store> pickupStores = new ArrayList<>();

        open(); // Open the database

        // Define the columns you want to retrieve
        String[] projection = {
                DBConstants.COLUMN_STORE_ID,
                DBConstants.COLUMN_STORE_NAME,
                DBConstants.COLUMN_STORE_ADDRESS,
                DBConstants.COLUMN_STORE_PHONE
        };

        // Query the database
        Cursor cursor = db.query(
                DBConstants.PICKUP_STORES_TABLE, // Table name
                projection,
                null,
                null,
                null,
                null,
                null
        );

        // Check if the cursor is not null
        if (cursor != null) {
            try {
                // Iterate through the cursor to retrieve data
                while (cursor.moveToNext()) {
                    int storeId = cursor.getInt(cursor.getColumnIndexOrThrow(DBConstants.COLUMN_STORE_ID));
                    String storeName = cursor.getString(cursor.getColumnIndexOrThrow(DBConstants.COLUMN_STORE_NAME));
                    String storeAddress = cursor.getString(cursor.getColumnIndexOrThrow(DBConstants.COLUMN_STORE_ADDRESS));
                    String storePhone = cursor.getString(cursor.getColumnIndexOrThrow(DBConstants.COLUMN_STORE_PHONE));

                    // Create a Store object and add it to the list
                    Store store = new Store(storeId, storeName, storeAddress, storePhone);
                    pickupStores.add(store);
                }
            } finally {
                // Close the cursor to release resources
                cursor.close();
            }
        }

        // Close the database
        close();

        return pickupStores;
    }
}
