package com.example.android_pizza_shop;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeFragment extends Fragment {

    private TextInputEditText dateText;
    private TextInputEditText timeText;
    private Button confirmDateTime;
    boolean validTime = false;
    boolean validDate = false;
    private ImageButton btnBack;
    private TextView textViewTitle;

    public DateTimeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_time, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBack = view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> goBack());

        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTitle.setText("Time");

        confirmDateTime = view.findViewById(R.id.confirmDateTimeButton);
        dateText = view.findViewById(R.id.etDate);
        timeText = view.findViewById(R.id.etTime);

        dateText.setOnClickListener(v -> showDatePicker());
        timeText.setOnClickListener(v -> showTimePicker());
        confirmDateTime.setOnClickListener(v -> openFoodSelection());
    }

    public void goBack(){
        getParentFragmentManager().popBackStack();
    }

    private void enableConfirmButton() {
        if(validTime & validDate) {
            confirmDateTime.setEnabled(true);
            confirmDateTime.setTextColor(ContextCompat.getColor(requireContext(), R.color.nearwhite));
            confirmDateTime.setBackgroundResource(R.drawable.custom_confirm_date_time_background);
        }
    }

    private void showDatePicker() {
        Calendar today = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {
                    dateText.setText(getFormattedDate(year, month, dayOfMonth));
                    validDate = true;
                    enableConfirmButton();
                },
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.getDatePicker().setMinDate(today.getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(today.getTimeInMillis() + 7 * 24 * 60 * 60 * 1000); // One week in the future

        datePickerDialog.show();
    }

    private String getFormattedDate(int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return dateFormat.format(calendar.getTime());
    }

    private void showTimePicker() {
        MaterialTimePicker picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Time")
                .build();

        picker.addOnPositiveButtonClickListener(v -> {
            int selectedHour = picker.getHour();
            int selectedMinute = picker.getMinute();

            if (selectedHour < 9 || (selectedHour == 9 && selectedMinute < 1) || selectedHour > 17) {
                // If the selected time is earlier than 9:00 AM or later than 5:00 PM, show an error message
                Toast.makeText(requireContext(), "Invalid time selection", Toast.LENGTH_SHORT).show();
            } else {
                timeText.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
                validTime = true;
                enableConfirmButton();
            }
        });

        picker.show(getParentFragmentManager(), picker.toString());
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