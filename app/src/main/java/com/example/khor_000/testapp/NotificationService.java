package com.example.khor_000.testapp;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class NotificationService extends Service {

    Firebase partnerFirebaseRef;
    String partnerName;

    public NotificationService() {
    }

    final class MyThread implements Runnable{

        int startId;
        public MyThread(int sd){
            startId = sd;
        }
        @Override
        public void run (){

            synchronized (this){
                try{

                    partnerFirebaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            LocationItem tohistList = (LocationItem) dataSnapshot.getValue();



                            Toast.makeText(getBaseContext(), "Partner visited " + tohistList.getName(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                    // wait();
                }
                catch(Exception e) /*(InterruptedException e)*/{
                    e.printStackTrace();
                }
                stopSelf(startId);
            }

        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        Toast.makeText(NotificationService.this, "Service Starts", Toast.LENGTH_SHORT).show();
        partnerName= intent.getStringExtra("partnername");
        // not included partner's fav loc list yet
        partnerFirebaseRef = new Firebase("https://coupletonescse110.firebaseio.com/"+ partnerName);
        if (partnerName == ""){
            Toast.makeText(NotificationService.this, "no partner", Toast.LENGTH_SHORT).show();
        }
        Thread thread = new Thread (new MyThread(startId));
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy(){
        Toast.makeText(NotificationService.this, "Service Stops", Toast.LENGTH_SHORT).show();
    }
}
