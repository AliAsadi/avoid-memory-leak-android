package aliasadi.memoryleak;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import aliasadi.memoryleak.leak.R;

import aliasadi.memoryleak.leak.AsyncTaskActivity;
import aliasadi.memoryleak.leak.HandlerActivity;
import aliasadi.memoryleak.leak.SingletonActivity;
import aliasadi.memoryleak.leak.StaticAsyncTaskActivity;
import aliasadi.memoryleak.leak.ThreadActivity;

/**
 * Created by Ali Asadi on 01/04/2019.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.asyncTask).setOnClickListener(this);
        findViewById(R.id.staticAsyncTask).setOnClickListener(this);
        findViewById(R.id.thread).setOnClickListener(this);
        findViewById(R.id.handler).setOnClickListener(this);
        findViewById(R.id.singleton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.asyncTask:
                AsyncTaskActivity.start(this);
                break;
            case R.id.staticAsyncTask:
                StaticAsyncTaskActivity.start(this);
                break;
            case R.id.thread:
                ThreadActivity.start(this);
                break;
            case R.id.handler:
                HandlerActivity.start(this);
                break;
            case R.id.singleton:
                SingletonActivity.start(this);
                break;
        }
    }
}
