package com.example.android_pizza_shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class CreateAccountFragment extends Fragment {

    TextInputLayout emailInputLayout;
    EditText emailEditText;
    TextInputLayout passwordInputLayout;
    EditText passwordEditText;
    TextInputLayout confirmPasswordInputLayout;
    EditText confirmPasswordEditText;
    Button createAccountButton;
    private DatabaseManager dbManager;


    public CreateAccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_account, container, false);

        initializeViews(view);
        setListeners(view);

        return view;
    }

    private void initializeViews(View view) {
        dbManager = new DatabaseManager(requireContext());
        emailInputLayout = view.findViewById(R.id.emailInputLayout);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordInputLayout = view.findViewById(R.id.confirmPasswordInputLayout);
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        createAccountButton = view.findViewById(R.id.createAccountButton);
        createAccountButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray));
        createAccountButton.setEnabled(false);
    }

    private void setListeners(View view) {
        // Add a TextWatcher to the EditText
        emailEditText.addTextChangedListener(new CreateAccountFragment.EmailTextWatcher());

        passwordEditText.addTextChangedListener(new PasswordTextWatcher());
        confirmPasswordEditText.addTextChangedListener(new ConfirmPasswordTextWatcher());

        Button goBackButton = view.findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(v -> goBack());

        createAccountButton.setOnClickListener(v -> createAccount());
    }

    public void createAccount(){
        // Retrieve entered email and password
        String enteredEmail = emailEditText.getText().toString().trim();
        String enteredPassword = passwordEditText.getText().toString().trim();

        // Check if the email already exists in the database
        if (isEmailAlreadyExists(enteredEmail)) {
            // Email already exists, show an alert dialog
            showAlertDialog("Account Creation Failed", "Email already exists. Please choose a different email.", false);
        } else {
            // Email does not exist, proceed with account creation
            // Perform your database insert operation here
            if (dbManager.insertUserIntoDatabase(enteredEmail, enteredPassword)) {
                // User successfully inserted into the database, set SessionManager as logged in
                SessionManager.loginUser(enteredEmail);

                // Show a success alert dialog
                showAlertDialog("Account Created", "Account created successfully!", true);
            } else {
                // Failed to insert user into the database
                showAlertDialog("Account Creation Failed", "Failed to create account. Please try again.", false);
            }
        }
    }

    private void showAlertDialog(String title, String message, final boolean navigateToHome) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button, do something if needed
                        if (navigateToHome) {
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            fragmentManager.popBackStack();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //Method to check if user email already exists
    private boolean isEmailAlreadyExists(String email) {
        // Open the database
        dbManager.open();

        // Check if the email exists in the database
        boolean emailExists = dbManager.checkEmailExists(email);

        // Close the database
        dbManager.close();

        return emailExists;
    }

    //Method to check if user email input passes basic email checks
    private boolean isValidEmail(String email) {
        // basic check for email
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //Method to check status of account creation
    private void updateCreateAccountButtonState() {
        boolean isEmailValid = isValidEmail(emailEditText.getText().toString().trim());
        boolean isPasswordValid = !passwordEditText.getText().toString().trim().isEmpty();
        boolean doPasswordsMatch = passwordEditText.getText().toString().trim()
                .equals(confirmPasswordEditText.getText().toString().trim());

        if (isEmailValid && isPasswordValid && doPasswordsMatch) {
            // Email is valid, password is not empty, and passwords match
            createAccountButton.setEnabled(true);
            createAccountButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light)); // Set the background color to green
        } else {
            // Either email is not valid, password is empty, or passwords don't match
            createAccountButton.setEnabled(false);
            createAccountButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray)); // Set the background color to gray
        }
    }

    //Method to go back by popping the top fragment of the back stack
    public void goBack() {
        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Pop the top fragment from the back stack, if any
        fragmentManager.popBackStack();
    }

    private class EmailTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateCreateAccountButtonState();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Check if the entered email is valid
            if (isValidEmail(editable.toString())) {
                emailInputLayout.setError(null); // Clear the error
            } else if(editable.toString().trim().isEmpty()){
                emailInputLayout.setError("This field is required");
            } else {
                emailInputLayout.setError("Please enter a valid email address");
            }
        }
    }

    private class PasswordTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateCreateAccountButtonState();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Check if the entered password is empty
            String password = editable.toString().trim();
            if (password.isEmpty()) {
                passwordInputLayout.setError("This field is required");
            } else {
                passwordInputLayout.setError(null); // Clear the error
            }

            updateCreateAccountButtonState();
        }
    }

    private class ConfirmPasswordTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateCreateAccountButtonState();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String confirmPassword = editable.toString().trim();
            //String confirmPassword = confirmPasswordEditText.getText().toString().trim();
            String password=passwordEditText.getText().toString().trim();
            if (!confirmPassword.isEmpty() && !password.equals(confirmPassword)) {
                confirmPasswordInputLayout.setError("Passwords must match");
            } else if (confirmPassword.isEmpty()) {
                confirmPasswordInputLayout.setError("This field is required");
            } else {
                confirmPasswordInputLayout.setError(null); // Clear the error
            }
        }
    }
}