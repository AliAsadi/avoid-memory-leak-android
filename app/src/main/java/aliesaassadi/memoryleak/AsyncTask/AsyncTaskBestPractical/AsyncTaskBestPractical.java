package aliesaassadi.memoryleak.AsyncTask.AsyncTaskBestPractical;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import aliesaassadi.memoryleak.R;

/**
 * Created by Ali Esa Assadi on 06/02/2018.
 */
public class AsyncTaskBestPractical extends AppCompatActivity {
    private TextView textView;
    private TaskExample taskExample;

    // NOTE : if the task done before to move to another activity or rotate the device every thing will works fine with out leak.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        taskExample = new TaskExample(this);
    }

    public void updateText() {
        textView.setText("AsyncTaskBestPractical");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIXED: should cancel the task in activity onDestroy() then its not call the onPostExecute() method
        taskExample.cancel(true);
    }
}
