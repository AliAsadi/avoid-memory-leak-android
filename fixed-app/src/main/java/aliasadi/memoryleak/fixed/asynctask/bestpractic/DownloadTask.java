package aliasadi.memoryleak.fixed.asynctask.bestpractic;

import android.os.AsyncTask;
import android.os.SystemClock;

import java.lang.ref.WeakReference;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class DownloadTask extends AsyncTask<Void, Void, Void> {

    /**
     *  The WeakReference allows the Activity to be garbage collected.
     *  garbage collected dose not protect the weak reference from begin reclaimed.
     * **/
    private WeakReference<BestAsyncTaskActivity> activity;

    public DownloadTask(BestAsyncTaskActivity bestAsyncTaskActivity) {
        activity = new WeakReference<>(bestAsyncTaskActivity);
    }

    @Override
    protected Void doInBackground(Void... params) {
        /**
         * Check if cancelled.
         * **/
        while (!isCancelled()) {
            SystemClock.sleep(2000 * 10);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (activity.get() != null) {
            activity.get().updateText();
        }
    }
}