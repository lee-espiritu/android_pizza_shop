package com.example.android_pizza_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;


public class CartFragment extends Fragment {

    private RecyclerView cartRecyclerView;
    private Button viewOrderSummaryButton;
    private CartAdapter cartAdapter;
    ImageButton btnBack;
    TextView textViewTitle;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize UI elements
        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());

        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText("Your Cart");

        // Initialize views
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        viewOrderSummaryButton = view.findViewById(R.id.viewOrderSummaryButton);

        // Get cart items from Cart singleton
        List<Cart.CartItem> cartItems = Cart.getInstance().getCartItems();

        // Set up RecyclerView with CartAdapter
        cartAdapter = new CartAdapter(cartItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecyclerView.setAdapter(cartAdapter);

        // Set OnClickListener for the "View Order Summary" button
        viewOrderSummaryButton.setOnClickListener(v -> openConfirmOrder());

        return view;
    }

    // Method to navigate back in the fragment stack
    public void goBack(){
        getParentFragmentManager().popBackStack();
    }

    // Method to open the ConfirmOrderFragment
    public void openConfirmOrder(){
        ConfirmOrderFragment confirmOrderFragment = new ConfirmOrderFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, confirmOrderFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}