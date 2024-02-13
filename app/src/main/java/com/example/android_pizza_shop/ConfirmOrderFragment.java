package com.example.android_pizza_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ConfirmOrderFragment extends Fragment {

    private RecyclerView orderSummaryRecyclerView;
    private Button confirmOrderButton;
    private CartAdapter orderSummaryAdapter;
    List<Cart.CartItem> orderItems;
    private ImageButton btnBack;
    private TextView textViewTitle;

    public ConfirmOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        // Initialize views
        orderSummaryRecyclerView = view.findViewById(R.id.orderSummaryRecyclerView);
        confirmOrderButton = view.findViewById(R.id.confirmOrderButton);

        //Set onClickListener for back button
        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());

        //Set the title text
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText("Confirm your Order");

        // Get order items from Cart (or wherever you store them)
        orderItems = Cart.getInstance().getCartItems();

        // Set up RecyclerView with CartAdapter (or a new adapter for order summary)
        orderSummaryAdapter = new CartAdapter(orderItems);
        orderSummaryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        orderSummaryRecyclerView.setAdapter(orderSummaryAdapter);
        orderSummaryAdapter.disableEditButtons();

        // Set OnClickListener for the "Confirm Order" button
        confirmOrderButton.setOnClickListener(v -> placeOrder());

        return view;
    }

    // Method to navigate back in the fragment stack
    public void goBack(){
        getParentFragmentManager().popBackStack();
    }
    // Method to place the order and update the database
    public void placeOrder(){
        if(SessionManager.isUserLoggedIn()){
            DatabaseManager dbManager = new DatabaseManager(requireContext());

            // Get user ID, order date, and total amount (you need to replace these values with actual data)
            String username = SessionManager.getUserName();
            String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            double totalAmount = calculateTotalAmount(orderItems);

            // Insert order into the database and get the order ID
            long orderId = dbManager.insertOrder(username, orderDate, totalAmount);

            // Insert each order item into the database
            for (Cart.CartItem item : orderItems) {
                dbManager.insertOrderItem(orderId, item.getFoodItem().getProductType(), item.getFoodItem().getName(), Double.parseDouble(item.getFoodItem().getKilojoules()),
                        item.getFoodItem().getFoodInfo(), item.getFoodItem().getImageResource(), Double.parseDouble(item.getFoodItem().getPriceInfo()), item.getQuantity());
            }

            // Close the database manager
            dbManager.close();
        }

        //Clear the cart after placing the order
        Cart.getInstance().clearCart();

        //Display briefly to user that order has been placed
        Toast.makeText(requireContext(), "Order placed", Toast.LENGTH_SHORT).show();

        //Return to home screen
        returnHome();
    }

    // Method to clear the fragment stack and return to the home screen
    public void returnHome(){
        FragmentManager fragmentManager = getParentFragmentManager();
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
    }

    // Method to calculate the total amount of the order
    private double calculateTotalAmount(List<Cart.CartItem> items) {
        double totalAmount = 0.0;
        for (Cart.CartItem item : items) {
            totalAmount += Double.parseDouble(item.getFoodItem().getPriceInfo()) * item.getQuantity();
        }
        return totalAmount;
    }
}
