package com.example.myfirstinstantapp.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.exceptions.CleverTapMetaDataNotFoundException;
import com.clevertap.android.sdk.exceptions.CleverTapPermissionsNotSatisfied;
import com.google.android.instantapps.InstantApps;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private CleverTapAPI clevertap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            clevertap = CleverTapAPI.getInstance(getApplicationContext());
        } catch (CleverTapMetaDataNotFoundException e) {
            // handle appropriately
        } catch (CleverTapPermissionsNotSatisfied e) {
            // handle appropriately
        }

        // Determine the current app context, either installed or instant and push on clevertap profile
        if (clevertap != null) {
            String appType = InstantApps.isInstantApp(this) ? "instant" : "installed";
            HashMap<String, Object> profile = new HashMap<>();
            profile.put("appType", appType);
            clevertap.profile.push(profile);
       }
    }
}
