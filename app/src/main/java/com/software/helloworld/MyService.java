package com.software.helloworld;

/**
 * Created by KCHU on 5/25/16.
 */
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MyService extends Service {
    Firebase mref1;
    Firebase mref2;

    public MyService() {

    }

    final class MyThread implements Runnable {
        int startId;
        public MyThread(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {

            synchronized (this) {
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mref2.setValue("Hello");
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mref2.setValue("from");
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mref2.setValue("user");
                try {
                    wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mref2.setValue("2");
            }
            stopSelf(startId);

        /*
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //User 1 Writing to  /send
                mref = new Firebase("https://chaorancheng-110.firebaseio.com/second");
                EditText editText = (EditText) findViewById(R.id.editText);
                String edit = editText.getText().toString();
                String value1 = "Hello";
                String value2 = "from";
                String value3 = "user";
                String value4 = "1";

                mref.setValue(edit);
            }
        });
        */
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "Sending message to user1", Toast.LENGTH_SHORT).show();

        //b1 = (Button) findViewById(R.id.sendButton);
        //User1 Reading from /first
        mref1 = new Firebase("https://kchu-110.firebaseio.com/second");

        mref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });

        // Writing to /second
        mref2 = new Firebase("https://kchu-110.firebaseio.com/first");
        Thread thread = new Thread(new MyThread(startId));
        thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

 
}

