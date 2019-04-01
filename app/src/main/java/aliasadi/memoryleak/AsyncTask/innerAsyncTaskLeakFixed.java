package aliasadi.memoryleak.AsyncTask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import aliasadi.memoryleak.R;

/**
 * Created by Ali Esa Assadi on 06/02/2018.
 */
public class innerAsyncTaskLeakFixed extends AppCompatActivity {
    private TextView textView;

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);

        new TaskExample().execute();
    }

    public void updateText() {
        textView.setText("INNER CLASS LEAK = DONE");
    }

    // FIXED : use static class instead of inner class. Static class does not have reference to the
    // containing activity class
    private static class TaskExample extends AsyncTask<Void, Void, Void> {

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
                // go to the next example -> (StaticAsyncTaskLeak) Class to learn how to fix this and made another leak

                /**updateText();**/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}