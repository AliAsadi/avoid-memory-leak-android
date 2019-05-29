package aliasadi.memoryleak.fixed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import android.widget.TextView;

import aliasadi.memoryleak.fixed.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class AsyncTaskActivity extends Activity {

    private TextView textView;

    /**
     * NOTE : if the task done before rotate/close the activity every thing will be ok without leak.
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        textView = findViewById(R.id.text_view);

        new DownloadTask().execute();
    }

    public void updateText() {
        textView.setText(R.string.hello);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, AsyncTaskActivity.class);
        context.startActivity(starter);
    }

    /**
     * to fix this leak we use static class instead of inner class.
     * static class does not have reference to the containing activity class
     **/
    private static class DownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(2000 * 10);
            return null;
        }

        /**
         * Problem:
         * we still need a reference to activity or listener to run the updateText() method
         * what we should do? go to the next example at @StaticAsyncTask class to know how
         * to deal with this issue.
         **/
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //updateText();
        }
    }
}