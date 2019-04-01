package aliasadi.memoryleaks.asynctask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import aliasadi.memoryleaks.R;

/**
 * Created by Ali Asadi on 06/02/2018.
 */
public class TaskBestPractical extends AppCompatActivity {
    private TextView textView;
    private TaskExample taskExample;

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        textView = findViewById(R.id.text_view);
        taskExample = new TaskExample(this);
    }

    public void updateText() {
        textView.setText("TaskBestPractical");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIXED: should cancel the task in activity onDestroy() then its not call the onPostExecute() method
        taskExample.cancel(true);
    }
}
