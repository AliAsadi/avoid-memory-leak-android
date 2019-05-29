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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        /**
         * Passing a activity as a context to the singleton!
         * take a look at @SingletonManager class.
         * **/
        SingletonManager.getInstance(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SingletonActivity.class);
        context.startActivity(starter);
    }
}