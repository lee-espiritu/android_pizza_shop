package com.example.android_pizza_shop;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class SMSUtils {

    public static boolean isSmsAvailable(Context context) {
        PackageManager pm = context.getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY);
    }

    public static void sendSMS(Context context, String destinationAddress, String message) {
        if (isSmsAvailable(context)) {
            // Perform SMS sending here
            SendSMS sender = new SendSMS();
            boolean success = sender.sendSMSMessage(destinationAddress, message);

            Toast.makeText(context, "Message sent " + (success ? "successfully" : "unsuccessfully"),
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "SMS not available on this device", Toast.LENGTH_SHORT).show();
        }
    }
}