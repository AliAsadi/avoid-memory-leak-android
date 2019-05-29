package aliasadi.memoryleak.leak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import aliasadi.memoryleak.leak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class SingletonActivity extends Activity {

    SingletonManager singletonLeak;

    public static void start(Context context) {
        Intent starter = new Intent(context, SingletonActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        /**Leak - SingletonManager **/
        //If you referenced context directly and that reference happened to be an Activity or View or any context that not application context
        //then you would have created a memory leak.

        //FIX1 : use getApplicationContext() instead of `this`
        singletonLeak = SingletonManager.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}