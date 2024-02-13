package com.example.android_pizza_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Cart.CartItem> cartItems;
    private boolean editButtonsEnabled = true;

    // Constructor to receive the cart items
    public CartAdapter(List<Cart.CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the cart item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        // Get the current cart item
        Cart.CartItem cartItem = cartItems.get(position);

        // Bind data to views
        holder.foodNameTextView.setText(cartItem.getFoodItem().getName());
        holder.quantityTextView.setText("Quantity: " + cartItem.getQuantity());



        // Show or hide buttons based on the condition
        if (editButtonsEnabled) {
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.plusButton.setVisibility(View.VISIBLE);
            holder.minusButton.setVisibility(View.VISIBLE);
            // Add click listeners for delete, plus, and minus buttons
            holder.deleteButton.setOnClickListener(v -> onDeleteButtonClick(position));
            holder.plusButton.setOnClickListener(v -> onPlusButtonClick(position));
            holder.minusButton.setOnClickListener(v -> onMinusButtonClick(position));
        } else {
            holder.deleteButton.setVisibility(View.GONE);
            holder.plusButton.setVisibility(View.GONE);
            holder.minusButton.setVisibility(View.GONE);
        }

        // Calculate total price for the item and set it
        double totalPrice = cartItem.getQuantity() * Double.parseDouble(cartItem.getFoodItem().getPriceInfo());
        holder.totalPriceTextView.setText("Total Price: $" + totalPrice);
    }

    // Define button click methods
    private void onDeleteButtonClick(int position) {
        // Remove item from the cart and update the RecyclerView
        Cart.getInstance().getCartItems().remove(position);
        notifyItemRemoved(position);
        //updateTotalPrice(); // Update total price after item removal
    }

    private void onPlusButtonClick(int position) {
        // Increase quantity of the item and update the RecyclerView
        Cart.CartItem cartItem = Cart.getInstance().getCartItems().get(position);
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        notifyItemChanged(position);
        //updateTotalPrice(); // Update total price after quantity change
    }

    private void onMinusButtonClick(int position) {
        // Decrease quantity of the item, remove if zero, and update the RecyclerView
        Cart.CartItem cartItem = Cart.getInstance().getCartItems().get(position);
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            notifyItemChanged(position);
        } else {
            Cart.getInstance().getCartItems().remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
        //updateTotalPrice(); // Update total price after quantity change
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    // Add this method to disable edit buttons
    public void disableEditButtons() {
        editButtonsEnabled = false;
        notifyDataSetChanged(); // Refresh the RecyclerView to apply the changes
    }


    // ViewHolder class to hold and recycle views
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView;
        TextView quantityTextView;
        TextView totalPriceTextView;

        // Button views
        ImageButton deleteButton;
        ImageButton plusButton;
        ImageButton minusButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views
            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            totalPriceTextView = itemView.findViewById(R.id.totalPriceTextView);

            // Initialize button views
            deleteButton = itemView.findViewById(R.id.delete);
            plusButton = itemView.findViewById(R.id.plus);
            minusButton = itemView.findViewById(R.id.minus);
        }
    }
}