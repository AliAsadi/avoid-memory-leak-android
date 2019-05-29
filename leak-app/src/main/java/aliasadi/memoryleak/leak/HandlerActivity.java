package aliasadi.memoryleak.leak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import aliasadi.memoryleak.leak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class HandlerActivity extends Activity {

    /**
     * Since this Handler is declared as an inner class,
     * it may prevent the outer class from being garbage collected.
     *
     * NOTE: The handler attached to the main thread
     * **/
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("HandlerActivity", "new message");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        /**
         * Post a message and delay its execution for 10 minutes.
         *
         * that's mean post a message to the  queue, to be run after the specified amount of time elapses,
         * The downloadTask will be run on the thread to which this handler is attached on (MainThread)
         * **/
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("HandlerActivity", "task start");
            }
        }, 1000 * 60 * 10);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, HandlerActivity.class);
        context.startActivity(starter);
    }

}