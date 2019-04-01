package aliasadi.leak.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import aliasadi.leak.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class StaticTaskFixed extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        textView = findViewById(R.id.text_view);

        new TaskExample(this).execute();
    }

    public void updateText() {
        textView.setText("INNER STATIC CLASS LEAK = DONE");
    }


    private static class TaskExample extends AsyncTask<Void, Void, Void> {

        //FIX : The WeakReference allows the Activity to be garbage collected, so you don't have a memory leak.
        // GC dose not protect the reference from begin collection and reclaim by the GC
        private WeakReference<StaticTaskFixed> activity;

        //MORE -> https://developer.android.com/reference/java/lang/ref/WeakReference.html

        //Weak reference objects, which do not prevent their referents from being made finalizable, finalized, and then reclaimed.
        //Weak references are most often used to implement canonicalizing mappings.
        //Suppose that the garbage collector determines at a certain point in time that an object is weakly reachable.
        //At that time it will atomically clear all weak references to that object and all weak references to any other weakly-reachable
        //objects from which that object is reachable through a chain of strong and soft references. At the same time it will declare
        //all of the formerly weakly-reachable objects to be finalizable. At the same time or at some later time it will enqueue
        //those newly-cleared weak references that are registered with reference queues.


        private TaskExample(StaticTaskFixed activity) {
            this.activity = new WeakReference<>(activity);
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
                activity.get().updateText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}