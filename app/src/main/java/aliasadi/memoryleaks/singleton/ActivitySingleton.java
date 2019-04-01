package aliasadi.memoryleaks.singleton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import aliasadi.memoryleaks.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class ActivitySingleton extends AppCompatActivity {

    SingletonManagerLeak singletonLeak;
    SingletonManagerFixed singletonFixed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        /**Leak - SingletonManagerLeak **/
        //If you referenced context directly and that reference happened to be an Activity or View or any context that not application context
        //then you would have created a memory leak.

        //FIX1 : use getApplicationContext() instead of `this`
        singletonLeak = SingletonManagerLeak.getInstance(this);

        /**Fix - SingletonManagerFixed **/
        //You will not create a leak since you are only keeping a reference
        //to the application context by calling getApplicationContext() on the construct.
        singletonFixed = SingletonManagerFixed.getInstance(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIX2 : destroy it onDestroy.
        singletonFixed.destroy();
    }
}