package com.example.android_pizza_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class FoodAddFragment extends Fragment {

    private TextView counterTextView;
    private int counter = 1; // Initial counter value
    private FoodItem foodItem;

    public FoodAddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_add, container, false);

        // Retrieve information from arguments
        Bundle args = getArguments();
        if (args != null) {
            foodItem = args.getParcelable("foodItem");
            if (foodItem != null) {
                // Use the information to populate views in the fragment
                TextView foodNameTextView = view.findViewById(R.id.foodNameTextView);
                TextView priceTextView = view.findViewById(R.id.priceTextView);
                TextView kilojoulesTextView = view.findViewById(R.id.kilojoulesTextView);
                TextView descriptionTextView = view.findViewById(R.id.foodInfoTextView);
                TextView priceSumTextView = view.findViewById(R.id.priceSum);
                ImageView foodImageView = view.findViewById(R.id.foodImageView);
                priceSumTextView.setText("Total: $" + Double.parseDouble(foodItem.getPriceInfo()));

                foodNameTextView.setText(foodItem.getName());
                priceTextView.setText("Price: " + foodItem.getPriceInfo());
                kilojoulesTextView.setText("Kilojoules: " + foodItem.getKilojoules());
                descriptionTextView.setText("Description: " + foodItem.getFoodInfo());
                foodImageView.setImageResource(getResources().getIdentifier(foodItem.getImageResource(), "drawable", requireContext().getPackageName()));

            }

            // Set up TextView for the counter
            counterTextView = view.findViewById(R.id.counter);
            counterTextView.setText(String.valueOf(counter));

            // Set up click listeners for decrement and increment buttons
            ImageButton decrementButton = view.findViewById(R.id.decrementButton);
            ImageButton incrementButton = view.findViewById(R.id.incrementButton);
            decrementButton.setOnClickListener(v -> {
                decrementCounter();
                updateTotalPrice();
            });
            incrementButton.setOnClickListener(v -> {
                incrementCounter();
                updateTotalPrice();
            });

            ImageButton closeButton = view.findViewById(R.id.closeButton);
            closeButton.setOnClickListener(v -> {
                // Close the current fragment and go back to the previous one
                getParentFragmentManager().popBackStack();
            });

            // Set OnClickListener for the "Add to Order" button
            Button addToOrderButton = view.findViewById(R.id.addToOrderButton);
            addToOrderButton.setOnClickListener(v -> {
                // Add the item to the cart
                Cart.getInstance().addToCart(foodItem, counter);
                getParentFragmentManager().popBackStack();
            });
        }

        return view;
    }

    private void decrementCounter() {
        if (counter > 1) {
            counter--;
            counterTextView.setText(String.valueOf(counter));
        }
    }

    private void incrementCounter() {
        counter++;
        counterTextView.setText(String.valueOf(counter));
    }

    private void updateTotalPrice() {
        // Calculate the total price based on the counter and individual food item's price
        double totalPrice = counter * Double.parseDouble(foodItem.getPriceInfo());

        // Find the TextView for the total price and update it
        TextView priceSumTextView = requireView().findViewById(R.id.priceSum);
        priceSumTextView.setText("Total: $" + totalPrice);
    }
}