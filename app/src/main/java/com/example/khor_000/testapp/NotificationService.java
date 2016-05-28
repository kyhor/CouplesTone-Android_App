package com.example.khor_000.testapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class NotificationService extends Service {

    Firebase myFirebaseRef;
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
                    myFirebaseRef = new Firebase("https://alex-110student.firebaseio.com/student");
                    //myFirebaseRef = new Firebase("https://alex-110student.firebaseio.com/student");

                    myFirebaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            String msg = dataSnapshot.getValue(String.class);

                            Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
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
