package com.example.android_pizza_shop;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class FoodSelectionScreenFragment extends Fragment {

    // Find the toolbar and buttons
    ImageButton btnPizzas ;
    ImageButton btnSides ;
    ImageButton btnDrinks ;
    ImageButton btnDesserts ;
    ImageButton btnBack;
    Button btnPlaceOrder;
    TextView textViewTitle;

    boolean category_pizzas = true;
    boolean category_sides = false;
    boolean category_drinks = false;
    boolean category_desserts = false;
    RecyclerView recyclerView;
    private FoodAdapter foodAdapter;

    public FoodSelectionScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_selection_screen, container, false);

        btnPizzas = view.findViewById(R.id.btnPizzas);
        btnSides = view.findViewById(R.id.btnSides);
        btnDrinks = view.findViewById(R.id.btnDrinks);
        btnDesserts = view.findViewById(R.id.btnDesserts);

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());

        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder);
        btnPlaceOrder.setOnClickListener(v -> openConfirmOrder());

        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText("Select your Food");

        btnPizzas.setImageResource(R.drawable.toolbar_pizza_selected);

        //Set click listeners for buttons
        setClickListeners();

        // Find the RecyclerView in the fragment's layout
        recyclerView = view.findViewById(R.id.recyclerView);

        // Set up the RecyclerView with a GridLayoutManager
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2)); // 2 columns

        // Fetch pizzas from the database
        List<FoodItem> pizzaList = fetchPizzasFromDatabase();

        // Set up the RecyclerView's adapter with the list of fake food items
        foodAdapter = new FoodAdapter(pizzaList);
        recyclerView.setAdapter(foodAdapter);

        // Add spacing between items directly without creating a new class
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing); // Define the spacing dimension
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = spacingInPixels;
                outRect.left = spacingInPixels;
                outRect.right = spacingInPixels;
                outRect.bottom = spacingInPixels;
            }
        });

        // Set the OnItemClickListener
        foodAdapter.setOnItemClickListener(this::handleAddButtonClick);

        Button cartButton = view.findViewById(R.id.btnCart);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCart();
            }
        });

        return view;
    }

    public void goBack(){
        getParentFragmentManager().popBackStack();
    }

    public void setClickListeners(){
        btnPizzas.setOnClickListener(v -> {
            btnPizzas.setImageResource(R.drawable.toolbar_pizza_selected);
            btnSides.setImageResource(R.drawable.toolbar_sides);
            btnDrinks.setImageResource(R.drawable.toolbar_drinks);
            btnDesserts.setImageResource(R.drawable.toolbar_dessert);

            category_pizzas = true;
            category_sides = false;
            category_drinks = false;
            category_desserts = false;

            updateRecyclerView();
        });

        btnSides.setOnClickListener(v -> {
            btnPizzas.setImageResource(R.drawable.toolbar_pizza);
            btnSides.setImageResource(R.drawable.toolbar_sides_selected);
            btnDrinks.setImageResource(R.drawable.toolbar_drinks);
            btnDesserts.setImageResource(R.drawable.toolbar_dessert);

            category_pizzas = false;
            category_sides = true;
            category_drinks = false;
            category_desserts = false;

            updateRecyclerView();
        });

        btnDrinks.setOnClickListener(v -> {
            btnPizzas.setImageResource(R.drawable.toolbar_pizza);
            btnSides.setImageResource(R.drawable.toolbar_sides);
            btnDrinks.setImageResource(R.drawable.toolbar_drinks_selected);
            btnDesserts.setImageResource(R.drawable.toolbar_dessert);

            category_pizzas = false;
            category_sides = false;
            category_drinks = true;
            category_desserts = false;

            updateRecyclerView();
        });

        btnDesserts.setOnClickListener(v -> {
            btnPizzas.setImageResource(R.drawable.toolbar_pizza);
            btnSides.setImageResource(R.drawable.toolbar_sides);
            btnDrinks.setImageResource(R.drawable.toolbar_drinks);
            btnDesserts.setImageResource(R.drawable.toolbar_dessert_selected);

            category_pizzas = false;
            category_sides = false;
            category_drinks = false;
            category_desserts = true;

            updateRecyclerView();
        });
    }

    private void updateRecyclerView() {
        List<FoodItem> itemList;

        // Fetch data based on the selected category
        if (category_pizzas) {
            itemList = fetchPizzasFromDatabase();
        } else if (category_sides) {
            itemList = fetchSidesFromDatabase();
        } else if (category_drinks) {
            itemList = fetchDrinksFromDatabase();
        } else if (category_desserts) {
            itemList = fetchDessertsFromDatabase();
        } else {
            itemList = new ArrayList<>(); // Default to an empty list if no category is selected
        }

        // Update the RecyclerView's adapter with the new data
        foodAdapter.updateData(itemList);
    }



    private List<FoodItem> fetchPizzasFromDatabase() {
        List<FoodItem> pizzaList = new ArrayList<>();

        // Initialize DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager(requireContext());

        // Open the database
        databaseManager.open();

        // Fetch pizzas from the database
        pizzaList = databaseManager.getAllPizzas();

        // Close the database
        databaseManager.close();

        return pizzaList;
    }

    private List<FoodItem> fetchSidesFromDatabase() {
        List<FoodItem> sidesList = new ArrayList<>();

        // Initialize DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager(requireContext());

        // Open the database
        databaseManager.open();

        // Fetch pizzas from the database
        sidesList = databaseManager.getAllSides();

        // Close the database
        databaseManager.close();

        return sidesList;
    }

    private List<FoodItem> fetchDrinksFromDatabase() {
        List<FoodItem> drinksList = new ArrayList<>();

        // Initialize DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager(requireContext());

        // Open the database
        databaseManager.open();

        // Fetch pizzas from the database
        drinksList = databaseManager.getAllDrinks();

        // Close the database
        databaseManager.close();

        return drinksList;
    }

    private List<FoodItem> fetchDessertsFromDatabase() {
        List<FoodItem> dessertsList = new ArrayList<>();

        // Initialize DatabaseManager
        DatabaseManager databaseManager = new DatabaseManager(requireContext());

        // Open the database
        databaseManager.open();

        // Fetch pizzas from the database
        dessertsList = databaseManager.getAllDesserts();

        // Close the database
        databaseManager.close();

        return dessertsList;
    }

    private void handleAddButtonClick(FoodItem food) {
        // Create a bundle to pass information to FoodAddFragment
        Bundle bundle = new Bundle();
        bundle.putParcelable("foodItem", food);

        // Create FoodAddFragment and set arguments
        FoodAddFragment foodAddFragment = new FoodAddFragment();
        foodAddFragment.setArguments(bundle);

        // Navigate to FoodAddFragment
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, foodAddFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    //Method to open the Cart Fragment
    public void openCart(){
        CartFragment cartFragment = new CartFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, cartFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    //Method to open the ConfirmOrderFragment
    public void openConfirmOrder(){
        ConfirmOrderFragment confirmOrderFragment = new ConfirmOrderFragment();
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, confirmOrderFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}