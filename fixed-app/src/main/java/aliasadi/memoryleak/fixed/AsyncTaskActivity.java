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

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

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
        textView.setText("INNER CLASS LEAK = DONE");
    }

    // FIXED : use static class instead of inner class. Static class does not have reference to the
    // containing activity class
    private static class DownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            SystemClock.sleep(2000 * 10);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                // problem :
                // we still need a reference to activity or listener to run the updateText() method
                // what should i do ???
                // go to the next example -> (StaticTaskLeak) Class to learn how to fix this and made another leak

                /**updateText();**/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}