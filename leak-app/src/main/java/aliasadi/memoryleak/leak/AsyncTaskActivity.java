package aliasadi.memoryleak.leak;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;

import aliasadi.memoryleak.leak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class AsyncTaskActivity extends Activity {

    /**
     * We will have memory leaks when we rotate/close the activity within 20 seconds after it’s created.
     * Since the AsyncTask is declared as non-static class it will hold the reference of
     * the activity which made the activity not eligible for garbage collection.
     *
     * NOTE : if the task done before rotate/close the activity every thing will be ok without leak.
     * **/

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        textView = findViewById(R.id.text_view);

        new DownloadTask().execute();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AsyncTaskActivity.class);
        context.startActivity(starter);
    }

    public void updateText() {
        textView.setText(R.string.hello);
    }

    @SuppressLint("StaticFieldLeak")
    private class DownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(2000 * 10);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            try {
                updateText();
            } catch (Exception e) {
                //doNothing
            }
        }
    }
}