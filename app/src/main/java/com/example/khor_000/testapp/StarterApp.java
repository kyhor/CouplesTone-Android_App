package com.example.khor_000.testapp;

/**
 * Created by Khor_000 on 26/5/2016.
 */
import com.firebase.client.Firebase;

public class StarterApp extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}