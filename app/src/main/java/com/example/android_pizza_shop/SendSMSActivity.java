package com.example.android_pizza_shop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SendSMSActivity extends Activity {
    SendSMS mSender = new SendSMS();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendOrder(View v) {
        // Get the order details from app
        String orderDetails = "Your order details go here.";

        // Specify the destination address (phone number)
        String destinationAddress = "123-456-7890";  // Replace with the actual destination address

        // Call the sendSMSMessage method to send the order
        boolean success = mSender.sendSMSMessage(destinationAddress, orderDetails);

        // Display a toast message indicating the result
        Toast.makeText(this, "Order sent " + (success ? "successfully" : "unsuccessfully"),
                Toast.LENGTH_SHORT).show();
    }
}
