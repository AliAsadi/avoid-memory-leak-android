package aliasadi.leak.thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import aliasadi.leak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class InnerThreadFixed extends AppCompatActivity {

    private MyThread myThread = new MyThread();

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

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