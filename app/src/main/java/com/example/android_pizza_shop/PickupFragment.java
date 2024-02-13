package com.example.android_pizza_shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class PickupFragment extends Fragment implements StoreAdapter.OnItemClickListener {

    private List<Store> storeList;
    private RecyclerView recyclerViewPickupStores;
    private StoreAdapter storeAdapter;
    ImageButton btnBack;
    TextView textViewTitle;

    public PickupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pickup, container, false);

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText("Select a Store");

        // Initialize your storeList with data (you can retrieve it from the database)
        storeList = getPickupStoresData();

        recyclerViewPickupStores = view.findViewById(R.id.recyclerViewPickupStores);

        recyclerViewPickupStores.setLayoutManager(new LinearLayoutManager(requireContext()));
        storeAdapter = new StoreAdapter(storeList, this);
        recyclerViewPickupStores.setAdapter(storeAdapter);

        return view;
    }

    public void goBack(){
        getParentFragmentManager().popBackStack();
    }

    private List<Store> getPickupStoresData() {
        DatabaseManager dbManager = new DatabaseManager(requireContext());
        return dbManager.getPickupStores();
    }

    @Override
    public void onItemClick(Store store) {
        // Handle item click (replace with DeliveryFragment)
        Log.d("PickupFragment.java:", "onItemClick() called");
        openDelivery(store);
    }

    private void openDelivery(Store store) {
        Log.d("PickupFragment", "openDelivery method called.");
        AddressFragment deliveryFragment = AddressFragment.newInstance(store);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, deliveryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
