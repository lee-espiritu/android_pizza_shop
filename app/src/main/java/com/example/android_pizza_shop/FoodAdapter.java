package com.example.android_pizza_shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<FoodItem> foodItemList;
    private Context context;
    private OnItemClickListener mListener;

    public FoodAdapter(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_layout, parent, false);
        context = parent.getContext(); // Initialize context here
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);

        // Set data to views
        holder.foodImageView.setImageResource(context.getResources().getIdentifier(foodItem.getImageResource(), "drawable", context.getPackageName()));
        holder.kilojoulesTextView.setText("Kilojoules: " + foodItem.getKilojoules());
        holder.foodInfoTextView.setText("Description: " + foodItem.getFoodInfo());
        holder.priceInfoTextView.setText("Price: $" + foodItem.getPriceInfo());
        holder.nameTextView.setText(foodItem.getName());

    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImageView;
        TextView kilojoulesTextView;
        TextView foodInfoTextView;
        TextView priceInfoTextView;
        TextView nameTextView;
        Button addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            kilojoulesTextView = itemView.findViewById(R.id.kilojoulesTextView);
            foodInfoTextView = itemView.findViewById(R.id.foodInfoTextView);
            priceInfoTextView = itemView.findViewById(R.id.priceTextView);
            nameTextView = itemView.findViewById(R.id.foodNameTextView);
            addButton = itemView.findViewById(R.id.addButton);

            // Set OnClickListener for the "Add" button
            addButton.setOnClickListener(v -> {
                // Check if the listener is set
                if (mListener != null) {
                    // Call the listener's method
                    mListener.onAddButtonClick(foodItemList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onAddButtonClick(FoodItem food);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void updateData(List<FoodItem> newData) {
        foodItemList.clear();
        foodItemList.addAll(newData);
        notifyDataSetChanged();
    }

}
