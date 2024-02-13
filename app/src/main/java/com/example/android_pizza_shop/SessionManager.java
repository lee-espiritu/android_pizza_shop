package com.example.android_pizza_shop;

import java.util.ArrayList;
import java.util.List;

public class SessionManager {
    private static boolean userLoggedIn = false;
    private static String name = "";
    private static List<OnLoginStateChangedListener> listeners = new ArrayList<>();

    public interface OnLoginStateChangedListener {
        void onLoginStateChanged(boolean isLoggedIn);
    }

    public static boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public static void loginUser(String userEmail) {
        userLoggedIn = true;
        name = userEmail;

        // Notify listeners about the login state change
        notifyListeners();
    }

    public static void logoutUser() {
        userLoggedIn = false;
        name = "";

        // Notify listeners about the login state change
        notifyListeners();
    }

    public static String getUserName() {
        return name;
    }

    public static void addOnLoginStateChangedListener(OnLoginStateChangedListener listener) {
        listeners.add(listener);
    }

    public static void removeOnLoginStateChangedListener(OnLoginStateChangedListener listener) {
        listeners.remove(listener);
    }

    private static void notifyListeners() {
        for (OnLoginStateChangedListener listener : listeners) {
            listener.onLoginStateChanged(userLoggedIn);
        }
    }
}

