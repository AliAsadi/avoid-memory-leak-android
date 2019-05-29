package aliasadi.memoryleak.fixed.asynctask.bestpractic;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;

import aliasadi.memoryleak.fixed.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class BestAsyncTaskActivity extends Activity {

    /**
     * NOTE : if the task done before rotate/close the activity every thing will be ok without leak.
     * **/

    private TextView textView;
    private DownloadTask downloadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);
        textView = findViewById(R.id.text_view);
        downloadTask = new DownloadTask(this);
    }

    public void updateText() {
        textView.setText(R.string.hello);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * cancel the task so it will no invoke onPostExecute().
         * **/
        downloadTask.cancel(true);
    }
}
