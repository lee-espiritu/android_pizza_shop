package com.example.android_pizza_shop;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    // Define an interface to handle item clicks
    public interface OnItemClickListener {
        void onItemClick(Store store);
    }
    private List<Store> storeList;
    private OnItemClickListener listener;

    public StoreAdapter(List<Store> storeList, OnItemClickListener listener) {
        this.storeList = storeList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_item_layout, parent, false);
        Log.d("StoreAdapter.java: ", "onCreateViewHolder() called");
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = storeList.get(position);
        holder.bind(store);
        holder.itemView.setOnClickListener(v -> {listener.onItemClick(store);});
    }

    @Override
    public int getItemCount() {
        return storeList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder {
        private TextView storeNameTextView;
        private TextView storeAddressTextView;
        private TextView storePhoneTextView;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            storeNameTextView = itemView.findViewById(R.id.storeNameTextView);
            storeAddressTextView = itemView.findViewById(R.id.storeAddressTextView);
            storePhoneTextView = itemView.findViewById(R.id.storePhoneTextView);
        }

        public void bind(Store store) {
            storeNameTextView.setText(store.getStoreName());
            storeAddressTextView.setText(store.getStoreAddress());
            storePhoneTextView.setText(store.getStorePhone());
        }
    }
}
