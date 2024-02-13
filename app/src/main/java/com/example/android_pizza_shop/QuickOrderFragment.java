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

public class QuickOrderFragment extends Fragment {

    Button placeNewOrderButton;
    private ImageButton btnBack;
    private TextView textViewTitle;

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;

    public QuickOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_order, container, false);

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());

        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText("Previous Orders");

        placeNewOrderButton = view.findViewById(R.id.placeNewOrderButton);
        placeNewOrderButton.setOnClickListener(v -> openFoodSelection());

        recyclerView = view.findViewById(R.id.recyclerViewOrders);
        orderAdapter = new OrderAdapter();
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Fetch and display previous orders
        fetchPreviousOrders();

        return view;
    }

    private void fetchPreviousOrders() {
        // Retrieve the user's email
        String userEmail = SessionManager.getUserName();

        // Use the email to fetch previous orders from the database
        DatabaseManager dbManager = new DatabaseManager(requireContext());
        List<Order> previousOrders = dbManager.getPreviousOrders(userEmail);
        dbManager.close();

        // Update the adapter with the retrieved orders
        orderAdapter.setOrders(previousOrders);
    }

    public void goBack(){
        getParentFragmentManager().popBackStack();
    }

    public void openFoodSelection(){
        FoodSelectionScreenFragment foodSelectionScreenFragment = new FoodSelectionScreenFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, foodSelectionScreenFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}