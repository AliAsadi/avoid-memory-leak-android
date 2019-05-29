package aliasadi.memoryleak.leak;

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
public class StaticAsyncTaskActivity extends Activity {
    private TextView textView;

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        textView = findViewById(R.id.text_view);

        new TaskExample(this).execute();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, StaticAsyncTaskActivity.class);
        context.startActivity(starter);
    }

    public void updateText() {
        textView.setText("INNER STATIC CLASS LEAK = DONE");
    }

    private static class TaskExample extends AsyncTask<Void, Void, Void> {

        // this can lead a memory leak

        private StaticAsyncTaskActivity activity;

        // This example will have memory leaks when you rotate the device or go to another activity within 20 seconds after it’s created.
        // The activity is destroyed on screen rotation, but since the AsyncTask is still holding a reference of the activity,
        // which made the activity not eligible for garbage collection, thus it becomes a memory leak.

        //Non-static inner classes have an implicit reference to their outer class. If that outer class is for example a Fragment or Activity,
        //then this reference means that the long-running handler/loader/task will hold a reference to the activity which prevents it
        //from getting garbage collected.
        //Similarly, direct field references to activities and fragments from these longer running instances can cause leaks.
        //ViewModel classes should never point to Views or non-application Contexts.

        public TaskExample(StaticAsyncTaskActivity activity) {
            this.activity = activity;
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
                activity.updateText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}