package aliasadi.memoryleak.fixed.asynctask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import android.widget.TextView;

import java.lang.ref.WeakReference;

import aliasadi.memoryleak.fixed.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class StaticAsyncTaskActivity extends Activity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        textView = findViewById(R.id.text_view);

        new DownloadTask(this).execute();
    }

    public void updateText() {
        textView.setText("INNER STATIC CLASS LEAK = DONE");
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, StaticAsyncTaskActivity.class);
        context.startActivity(starter);
    }

    private static class DownloadTask extends AsyncTask<Void, Void, Void> {

        //FIX : The WeakReference allows the Activity to be garbage collected, so you don't have a memory leak.
        // GC dose not protect the reference from begin collection and reclaim by the GC
        private WeakReference<StaticAsyncTaskActivity> activity;

        //MORE -> https://developer.android.com/reference/java/lang/ref/WeakReference.html

        //Weak reference objects, which do not prevent their referents from being made finalizable, finalized, and then reclaimed.
        //Weak references are most often used to implement canonicalizing mappings.
        //Suppose that the garbage collector determines at a certain point in time that an object is weakly reachable.
        //At that time it will atomically clear all weak references to that object and all weak references to any other weakly-reachable
        //objects from which that object is reachable through a chain of strong and soft references. At the same time it will declare
        //all of the formerly weakly-reachable objects to be finalizable. At the same time or at some later time it will enqueue
        //those newly-cleared weak references that are registered with reference queues.


        private DownloadTask(StaticAsyncTaskActivity activity) {
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