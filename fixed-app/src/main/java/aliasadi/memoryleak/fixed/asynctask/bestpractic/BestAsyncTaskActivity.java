package aliasadi.memoryleak.fixed.asynctask.bestpractic;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;

import aliasadi.memoryleak.fixed.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class BestAsyncTaskActivity extends Activity {
    private TextView textView;
    private DownloadTask downloadTask;

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        textView = findViewById(R.id.text_view);
        downloadTask = new DownloadTask(this);
    }

    public void updateText() {
        textView.setText("BestAsyncTaskActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIXED: should cancel the task in activity onDestroy() then its not call the onPostExecute() method
        downloadTask.cancel(true);
    }
}
