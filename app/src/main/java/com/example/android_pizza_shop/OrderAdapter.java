package com.example.android_pizza_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> orders = new ArrayList<>();

    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quick_order_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);

        // Bind order data to the view holder
        holder.orderDateTextView.setText("Order Date: " + order.getOrderDate());
        holder.totalAmountTextView.setText("Total Amount: $" + order.getTotalAmount());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Customize this based on your item_order.xml layout
        TextView orderDateTextView;
        TextView totalAmountTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize views from item_order.xml
            orderDateTextView = itemView.findViewById(R.id.orderDateTextView);
            totalAmountTextView = itemView.findViewById(R.id.totalAmountTextView);
        }
    }
}

