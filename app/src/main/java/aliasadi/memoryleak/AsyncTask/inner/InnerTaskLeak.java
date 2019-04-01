package aliasadi.memoryleak.asynctask.inner;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import aliasadi.memoryleak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class InnerTaskLeak extends AppCompatActivity {
    private TextView textView;

    // This example will have memory leaks when you rotate the device or go to another activity within 20 seconds after it’s created.
    // The activity is destroyed on screen rotation, but since the AsyncTask is declared as non-static class,
    // the background task is still holding a reference of the activity which made the activity not eligible
    // for garbage collection, thus it becomes a memory leak.

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        textView = findViewById(R.id.text_view);

        new TaskExample().execute();
    }

    public void updateText() {
        textView.setText("INNER CLASS LEAK = DONE");
    }

    private class TaskExample extends AsyncTask<Void, Void, Void> {

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
                e.printStackTrace();
            }
        }
    }
}