
package com.example.android_pizza_shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;


public class AddressFragment extends Fragment {

    private AutocompleteSupportFragment autocompleteFragment;
    private TextView selectedAddressTextView;
    private Button confirmAddressButton;
    private ImageButton btnBack;
    private TextView textViewTitle;

    public AddressFragment() {
        // Required empty public constructor
    }

    /**
     * Create a new instance of the AddressFragment with arguments.
     * @param store The Store object to be associated with the AddressFragment.
     * @return A new instance of AddressFragment.
     */
    public static AddressFragment newInstance(Store store) {
        // Create a new AddressFragment instance
        AddressFragment fragment = new AddressFragment();

        // Create a Bundle to pass arguments to the fragment
        Bundle args = new Bundle();

        // Put the Parcelable Store object into the Bundle with a key "store"
        args.putParcelable("store", store);

        // Set the arguments for the fragment
        fragment.setArguments(args);

        // Return the created fragment with arguments
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_address, container, false);

        // Find the "Back" button and set a click listener
        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());

        // Find the title TextView and set its text
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText("Address");

        // Initialize Places API for address autocomplete
        Places.initialize(requireContext(), getString(R.string.api_key));

        // Initialize member variables related to address autocomplete
        autocompleteFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        selectedAddressTextView = view.findViewById(R.id.selectedAddressTextView);
        confirmAddressButton = view.findViewById(R.id.confirmAddressButton);

        // Set up address autocomplete functionality
        setUpAutoComplete();

        // Retrieve the Store object from arguments
        //Store store = getArguments().getParcelable("store"); //NOT USED AT THE MOMENT

        return view;
    }

    //
    /**
     * Navigates back to the previous fragment in the fragment stack.
     */
    public void goBack() {
        // Use the parent fragment manager to pop the back stack
        getParentFragmentManager().popBackStack();
    }

    /**
     * Sets up the autocomplete functionality for the address input.
     */
    private void setUpAutoComplete() {
        // Set the fields to be retrieved for the selected place
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS));

        // Set the listener for place selection
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // Handle the selected place
                updateSelectedAddress(place.getAddress());
            }

            @Override
            public void onError(@NonNull Status status) {
                // Handle errors during autocomplete
                handleAutocompleteError(status);
            }
        });

        // Set a click listener for the confirm address button
        confirmAddressButton.setOnClickListener(v -> confirmAddressClicked());
    }


    /**
     * Updates the selected address TextView with the provided address, making it visible.
     * Also makes the confirm address button visible.
     *
     * @param address The address to be displayed.
     */
    private void updateSelectedAddress(String address) {
        selectedAddressTextView.setText("Selected Address: " + address);
        selectedAddressTextView.setVisibility(View.VISIBLE);
        confirmAddressButton.setVisibility(View.VISIBLE);
    }

    /**
     * Handles errors that occur during autocomplete by displaying a Toast message
     * with the provided error status message.
     *
     * @param status The error status containing the error message.
     */
    private void handleAutocompleteError(Status status) {
        Toast.makeText(requireContext(), "Error during autocomplete: " + status.getStatusMessage(), Toast.LENGTH_SHORT).show();
    }

    /**
     * Handles the click event when the user confirms the selected address.
     * Retrieves the selected address, displays a Toast message confirming the address,
     * and proceeds to open the DateTimeFragment.
     */
    private void confirmAddressClicked() {
        String selectedAddress = selectedAddressTextView.getText().toString();
        Toast.makeText(requireContext(), "Confirmed Address: " + selectedAddress, Toast.LENGTH_SHORT).show();
        openDateTime();
    }

    /**
     * Opens the DateTimeFragment by creating an instance, initiating a FragmentManager,
     * starting a FragmentTransaction, adding the DateTimeFragment to the container,
     * and committing the transaction to allow for proper navigation.
     */
    public void openDateTime() {
        DateTimeFragment dateTimeFragment = new DateTimeFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, dateTimeFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

