package aliasadi.memoryleak.leak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import aliasadi.memoryleak.leak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class ThreadActivity extends Activity {

    /**
     * We will have memory leaks when we rotate/close the activity within 20 seconds after it’s created.
     * Since the AsyncTask is declared as non-static class it will hold the reference of
     * the activity which made the activity not eligible for garbage collection.
     *
     * NOTE : if the task done before rotate/close the activity every thing will be ok without leak.
     * **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        new DownloadTask().start();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ThreadActivity.class);
        context.startActivity(starter);
    }

    /**
     * non-static anonymous classes hold an implicit reference to their enclosing class.
     * **/
    private class DownloadTask extends Thread {
        @Override
        public void run() {
                SystemClock.sleep(2000 * 10);
        }
    }
}