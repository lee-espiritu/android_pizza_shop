package com.example.android_pizza_shop;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    // Singleton instance for the Cart
    private static Cart instance;

    // List to store cart items
    private List<CartItem> cartItems;

    // Private constructor to enforce singleton pattern
    private Cart() {
        cartItems = new ArrayList<>();
    }

    // Add a food item to the cart with a specified quantity
    public void addToCart(FoodItem foodItem, int quantity) {
        CartItem newItem = new CartItem(foodItem, quantity);

        // Check if the food item is already in the cart, update quantity if so
        for (CartItem existingItem : cartItems) {
            if (existingItem.getFoodItem().getName().equals(foodItem.getName())) {
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                return;
            }
        }

        // If the item is not in the cart, add it
        cartItems.add(newItem);
    }

    // Get the list of cart items
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Get or create an instance of the Cart
    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    // Method to clear the Cart
    public void clearCart() {
        Log.d("Cart.java:", " Cart items cleared");
        cartItems.clear();
    }

    // Inner class representing an item in the cart
    public class CartItem {

        // Food item in the cart
        private FoodItem foodItem;

        // Quantity of the food item
        private int quantity;

        // Constructor for a cart item
        public CartItem(FoodItem foodItem, int quantity) {
            this.foodItem = foodItem;
            this.quantity = quantity;
        }

        // Get the food item
        public FoodItem getFoodItem() {
            return foodItem;
        }

        // Get the quantity of the food item
        public int getQuantity() {
            return quantity;
        }

        // Set the quantity of the food item
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    }
}
