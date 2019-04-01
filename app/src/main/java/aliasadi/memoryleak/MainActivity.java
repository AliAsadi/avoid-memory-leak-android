package aliasadi.memoryleak;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import aliasadi.memoryleak.asynctask.StaticTaskFixed;
import aliasadi.memoryleak.asynctask.StaticTaskLeak;
import aliasadi.memoryleak.asynctask.inner.InnerTaskFixed;
import aliasadi.memoryleak.asynctask.inner.InnerTaskLeak;
import aliasadi.memoryleak.handler.InnerHandlerFixed;
import aliasadi.memoryleak.handler.InnerHandlerLeak;
import aliasadi.memoryleak.singleton.SingletonActivityFixed;
import aliasadi.memoryleak.singleton.SingletonActivityLeak;
import aliasadi.memoryleak.thread.InnerThreadFixed;
import aliasadi.memoryleak.thread.InnerThreadLeak;

/**
 * Created by Ali Esa Assadi on 01/04/2019.
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

        findViewById(R.id.asyncTaskFixed).setOnClickListener(this);
        findViewById(R.id.staticAsyncTaskFixed).setOnClickListener(this);
        findViewById(R.id.threadFixed).setOnClickListener(this);
        findViewById(R.id.handlerFixed).setOnClickListener(this);
        findViewById(R.id.singletonFixed).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //How to cause a leak:
            //1. Start screen then click back button
            //2. Start Screen then rotate device

            case R.id.asyncTask:
                InnerTaskLeak.start(this);
                break;
            case R.id.staticAsyncTask:
                StaticTaskLeak.start(this);
                break;
            case R.id.thread:
                InnerThreadLeak.start(this);
                break;
            case R.id.handler:
                InnerHandlerLeak.start(this);
                break;
            case R.id.singleton:
                SingletonActivityLeak.start(this);
                break;

            case R.id.asyncTaskFixed:
                InnerTaskFixed.start(this);
                break;

            case R.id.staticAsyncTaskFixed:
                StaticTaskFixed.start(this);
                break;

            case R.id.handlerFixed:
                InnerHandlerFixed.start(this);
                break;

            case R.id.threadFixed:
                InnerThreadFixed.start(this);
                break;

            case R.id.singletonFixed:
                SingletonActivityFixed.start(this);
                break;


        }
    }
}
