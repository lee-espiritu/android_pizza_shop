package com.example.android_pizza_shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment {

    TextInputLayout emailInputLayout;
    EditText emailEditText;
    TextInputLayout passwordInputLayout;
    EditText passwordEditText;
    Button loginButton;
    private DatabaseManager dbManager;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        dbManager = new DatabaseManager(requireContext());

        emailInputLayout = view.findViewById(R.id.emailInputLayout);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordInputLayout = view.findViewById(R.id.passwordInputLayout);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton);
        loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray));
        loginButton.setEnabled(false);

        // Add a TextWatcher to the EditText
        emailEditText.addTextChangedListener(new EmailTextWatcher());

        // Add a TextWatcher to the password EditText
        passwordEditText.addTextChangedListener(new PasswordTextWatcher());

        Button goBackButton = view.findViewById(R.id.goBackButton);  // Replace with the actual ID of your button
        goBackButton.setOnClickListener(v -> goBack());

        loginButton.setOnClickListener(v -> {
            // Retrieve entered email and password
            String enteredEmail = emailEditText.getText().toString().trim();
            String enteredPassword = passwordEditText.getText().toString().trim();

            // Check credentials against the database
            if (isValidLogin(enteredEmail, enteredPassword)) {
                // Login successful

                // Set the user as logged in using the SessionManager
                SessionManager.loginUser(enteredEmail);

                // You may navigate to a new screen or perform other actions
                Log.d("LoginFragment", "Login successful");
                showAlertDialog("Login Successful", "Welcome!", true);
            } else {
                // Login failed
                Log.d("LoginFragment", "Login failed");
                showAlertDialog("Login Failed", "Invalid email or password", false);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private class EmailTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            updateLoginButtonState();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Check if the entered email is valid
            if (isValidEmail(editable.toString())) {
                emailInputLayout.setError(null); // Clear the error
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
            updateLoginButtonState();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Check if the entered password is empty
            if (editable.toString().trim().isEmpty()) {
                passwordInputLayout.setError("This field is required");
            } else {
                passwordInputLayout.setError(null); // Clear the error
            }
        }
    }

    // Function to validate email
    private boolean isValidEmail(String email) {
        // If the input is empty, consider it valid
        if (email.trim().isEmpty()) {
            return true;
        }

        // basic check for email
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Update login button state based on email and password validity
    private void updateLoginButtonState() {
        boolean isEmailValid = isValidEmail(emailEditText.getText().toString().trim());
        boolean isPasswordValid = !passwordEditText.getText().toString().trim().isEmpty();

        if (isEmailValid && isPasswordValid) {
            // Both email and password are valid
            loginButton.setEnabled(true);
            loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light)); // Set the background color to green
        } else {
            // Either email or password is not valid
            loginButton.setEnabled(false);
            loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.darker_gray)); // Set the background color to gray
        }
    }

    private boolean isValidLogin(String enteredEmail, String enteredPassword) {
        dbManager.open();

        // Open the database
        SQLiteDatabase db = dbManager.getDatabase();

        // Query the database to check if the entered email and password match
        String query = "SELECT * FROM Users WHERE email = ? AND password = ?";
        String[] selectionArgs = {enteredEmail, PasswordHasher.hashPassword(enteredPassword)};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        // Check if a row was returned (indicating a successful login)
        boolean isValid = cursor.moveToFirst();

        // Close the cursor (the database will be closed later when you call dbManager.close())

        return isValid;
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



    public void goBack() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }
}