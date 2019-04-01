package aliasadi.memoryleaks.thread;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import aliasadi.memoryleaks.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class InnerThreadLeak extends AppCompatActivity {

    // This example will have memory leaks when you rotate the device or go to another activity within 20 seconds after it’s created.
    // The activity is destroyed on screen rotation, but since the AsyncTask is declared as non-static class,
    // the background task is still holding a reference of the activity which made the activity not eligible
    // for garbage collection, thus it becomes a memory leak.

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        new MyThread().start();
    }

    // FIXME: non-static anonymous classes hold an implicit reference to their enclosing class.
    // Fix is to make it static. Also, close thread in activity onDestroy() to avoid thread leak. See `InnerThreadFixed`
    private class MyThread extends Thread {
        @Override
        public void run() {
                SystemClock.sleep(2000 * 10);
        }
    }
}