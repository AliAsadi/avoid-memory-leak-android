package aliasadi.memoryleak.leak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class StaticAsyncTaskActivity extends Activity implements DownloadListener {
    private TextView textView;

    /**
     * NOTE : if the task done before rotate/close the listener every thing will be ok without leak.
     * **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        textView = findViewById(R.id.text_view);

        new DownloadTask(this).execute();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, StaticAsyncTaskActivity.class);
        context.startActivity(starter);
    }

    public void updateText() {
        textView.setText(R.string.hello);
    }

    @Override
    public void onDownloadTaskDone() {
        updateText();
    }

    private static class DownloadTask extends AsyncTask<Void, Void, Void> {

        /**
         * Saving a strong reference of the listener, which made
         * the listener not eligible for garbage collection.
         * **/
        @SuppressLint("StaticFieldLeak")
        private DownloadListener listener;

        public DownloadTask(DownloadListener listener) {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(2000 * 10);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                listener.onDownloadTaskDone();
            } catch (Exception e) {
                //doNothing
            }
        }
    }
}