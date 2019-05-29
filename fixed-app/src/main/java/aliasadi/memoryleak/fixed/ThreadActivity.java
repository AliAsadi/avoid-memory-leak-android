package aliasadi.memoryleak.fixed;

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

    /**
     * if the task done before to move to another activity
     * or rotate the device every thing will works fine with out leak.
     * **/

    private DownloadTask thread = new DownloadTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /**
         * Interrupts/stops this thread.
         * **/
        thread.interrupt();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadActivity.class);
        context.startActivity(starter);
    }

    /**
     * make it static so it does not have referenced to the containing activity class
     * **/
    private static class DownloadTask extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                SystemClock.sleep(2000 * 10);
            }
        }
    }
}