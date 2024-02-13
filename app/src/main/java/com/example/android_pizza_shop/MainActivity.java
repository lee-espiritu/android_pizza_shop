package com.example.android_pizza_shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Default code
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of DatabaseManager
        dbManager = new DatabaseManager(this);

        // Check if the database already exists
        if (isDatabaseAlreadyExists()) {
            Log.d("MainActivity", "Database already exists.");
            deleteDatabase();
            dbManager.open();
            //dbManager.close();
        } else {
            Log.d("MainActivity", "Database does not exist. Creating...");
            // Open the database when needed
            dbManager.open();
            //dbManager.close();
        }

        //Add home fragment to the fragment_container (check layout inspector)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment(), "homeFragmentTag")
                    .commit();
        }
    }

    //Method to delete the database.
    private void deleteDatabase() {
        boolean isDeleted = this.deleteDatabase(DBConstants.DATABASE_NAME);

        if (isDeleted) {
            Log.d("MainActivity", "Database deleted successfully.");
        } else {
            Log.d("MainActivity", "Failed to delete database.");
        }
    }

    //Method to check if the database already exists
    private boolean isDatabaseAlreadyExists() {
        return dbManager.isDatabaseExists();
    }


}