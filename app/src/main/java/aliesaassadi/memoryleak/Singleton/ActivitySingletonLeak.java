package aliesaassadi.memoryleak.Singleton;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import aliesaassadi.memoryleak.R;

/**
 * Created by Ali Esa Assadi on 06/02/2018.
 */
public class ActivitySingletonLeak extends AppCompatActivity {

    SingletonManager instance;

    SingletonManagerFixed instanceFixed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Leak - SingletonManager **/
        //If you referenced context directly and that reference happened to be an Activity or View or any context that not application context
        //then you would have created a memory leak.

        //FIX1 : use getApplicationContext() instead of `this`
        instance = SingletonManager.getInstance(this);

        /**Fix - SingletonManagerFixed **/
        //You will not create a leak since you are only keeping a reference
        //to the application context by calling getApplicationContext() on the construct.
        instanceFixed = SingletonManagerFixed.getInstance(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIX2 : unregister it onDestroy.
        instance.unregister(this);
    }
}