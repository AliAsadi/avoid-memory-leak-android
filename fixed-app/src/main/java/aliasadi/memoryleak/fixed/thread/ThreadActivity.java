package aliasadi.memoryleak.fixed.thread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;


import aliasadi.memoryleak.fixed.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class ThreadActivity extends Activity {

    private MyThread myThread = new MyThread();

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        myThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIXED: kill the thread in activity onDestroy
        myThread.interrupt();
    }

    // FIXED: make it static. So it does not have referenced to the containing activity class
    private static class MyThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                SystemClock.sleep(2000 * 10);
            }
        }
    }
}