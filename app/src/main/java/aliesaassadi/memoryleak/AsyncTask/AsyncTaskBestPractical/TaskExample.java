package aliesaassadi.memoryleak.AsyncTask.AsyncTaskBestPractical;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.lang.ref.WeakReference;

import aliesaassadi.memoryleak.AsyncTask.StaticAsyncTaskLeakFixed;

/**
 * Created by Ali Esa Assadi on 06/02/2018.
 */
public class TaskExample extends AsyncTask<Void, Void, Void> {

    //FIX : The WeakReference allows the Activity to be garbage collected, so you don't have a memory leak.
    // GC dose not protect the reference from begin collection and reclaim by the GC
    private WeakReference<AsyncTaskBestPractical> activity;

    //MORE -> https://developer.android.com/reference/java/lang/ref/WeakReference.html

    //Weak reference objects, which do not prevent their referents from being made finalizable, finalized, and then reclaimed.
    //Weak references are most often used to implement canonicalizing mappings.
    //Suppose that the garbage collector determines at a certain point in time that an object is weakly reachable.
    //At that time it will atomically clear all weak references to that object and all weak references to any other weakly-reachable
    //objects from which that object is reachable through a chain of strong and soft references. At the same time it will declare
    //all of the formerly weakly-reachable objects to be finalizable. At the same time or at some later time it will enqueue
    //those newly-cleared weak references that are registered with reference queues.
    //
    public TaskExample(AsyncTaskBestPractical asyncTaskBestPractical) {
        activity = new WeakReference<>(asyncTaskBestPractical);
    }

    @Override
    protected Void doInBackground(Void... params) {
        //should check if cancelled before next loop,
        //cancel the task in activity onDestroy() then its not call the onPostExecute() method.
        while (!isCancelled()) {
            SystemClock.sleep(2000 * 10);
        }
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