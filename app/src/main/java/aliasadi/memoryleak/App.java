package aliasadi.memoryleak;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Ali Esa Assadi on 01/04/2019.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            Log.d("App","init failed");
            return;
        }
        LeakCanary.install(this);
        Log.d("App","init");


    }
}
