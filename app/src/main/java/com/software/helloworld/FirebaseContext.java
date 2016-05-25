package com.software.helloworld;

/**
 * Created by KCHU on 5/25/16.
 */
import com.firebase.client.Firebase;

/**
 * Created by MSI on 5/23/2016.
 */
public class FirebaseContext extends android.app.Application{

    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
