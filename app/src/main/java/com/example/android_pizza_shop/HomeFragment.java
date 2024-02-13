package com.example.android_pizza_shop;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements SessionManager.OnLoginStateChangedListener {

    private Button loginButton;
    private Button signUpButton;
    private Button signOutButton;
    private Button quickOrderButton;
    private Button foodSelectionButton;
    private Button deliveryButton;
    private Button pickupButton;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> openLogin());

        signUpButton = view.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(v -> openSignUp());

        signOutButton = view.findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(v -> showSignOutAlertDialog());
        updateUI(); // Call the method to update the UI based on user login state

        quickOrderButton = view.findViewById(R.id.quickOrderButton);
        quickOrderButton.setOnClickListener(v -> openQuickOrder());

        foodSelectionButton = view.findViewById(R.id.foodSelectionButton);
        foodSelectionButton.setOnClickListener(v -> openFoodSelection());

        deliveryButton = view.findViewById(R.id.deliveryButton);
        deliveryButton.setOnClickListener(v -> openAddress());

        pickupButton = view.findViewById(R.id.pickupButton);
        pickupButton.setOnClickListener(v -> openPickup());

        // Register this fragment as a listener
        SessionManager.addOnLoginStateChangedListener(this);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onDestroyView() {
        // Unregister this fragment as a listener to prevent memory leaks
        SessionManager.removeOnLoginStateChangedListener(this);
        super.onDestroyView();
    }

    @Override
    public void onLoginStateChanged(boolean isLoggedIn) {
        // Update the UI based on the new login state
        updateUI();
    }

    private void showSignOutAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Sign out logic
                    SessionManager.logoutUser();
                    updateUI();
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void openQuickOrder(){
        if(SessionManager.isUserLoggedIn()){
            QuickOrderFragment quickOrderFragment = new QuickOrderFragment();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, quickOrderFragment, "quickOrderFragmentTag");
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else {
            // User is not logged in, show a prompt to create an account or log in
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Login Required")
                    .setMessage("To proceed, please log in or create an account.")
                    .setPositiveButton("Log In", (dialog, id) -> {
                        // User clicked Log In, navigate to the LoginFragment
                        openLogin();
                    })
                    .setNegativeButton("Create Account", (dialog, id) -> {
                        // User clicked Create Account, navigate to the CreateAccountFragment
                        openSignUp();
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void openLogin(){

        // Create a new instance of the LoginFragment
        LoginFragment loginFragment = new LoginFragment();

        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Begin a FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //Set Animation
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, 0, 0, R.anim.slide_down);

        // Replace the current fragment with the new LoginFragment
        fragmentTransaction.add(R.id.fragment_container, loginFragment);

        // Add the transaction to the back stack (optional)
        fragmentTransaction.addToBackStack(null);

        // Commit the transaction
        fragmentTransaction.commit();
    }

    public void openSignUp(){
        CreateAccountFragment createAccountFragment = new CreateAccountFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, 0, 0, R.anim.slide_down);
        fragmentTransaction.add(R.id.fragment_container, createAccountFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openFoodSelection(){
        FoodSelectionScreenFragment foodSelectionScreenFragment = new FoodSelectionScreenFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, 0, 0, R.anim.slide_down);
        fragmentTransaction.add(R.id.fragment_container, foodSelectionScreenFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openAddress(){
        AddressFragment addressFragment = new AddressFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_up, 0, 0, R.anim.slide_down);
        fragmentTransaction.add(R.id.fragment_container, addressFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openPickup(){
        PickupFragment pickupFragment = new PickupFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, pickupFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void updateUI() {
        if (SessionManager.isUserLoggedIn()) {
            // If user is logged in, hide login and sign-up buttons, show sign-out button
            loginButton.setVisibility(View.GONE);
            signUpButton.setVisibility(View.GONE);
            signOutButton.setVisibility(View.VISIBLE);
        } else {
            // If user is not logged in, show login and sign-up buttons, hide sign-out button
            loginButton.setVisibility(View.VISIBLE);
            signUpButton.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
        }
    }
}