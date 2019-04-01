package aliasadi.leak.singleton;

import android.content.Context;

/**
 * Created by Ali Asadi on 13/02/2018.
 */
public class SingletonManagerLeak {

    private static SingletonManagerLeak singleton;
    private Context context;

    private SingletonManagerLeak(Context context) {
        this.context = context;
    }

    public synchronized static SingletonManagerLeak getInstance(Context context) {
        if (singleton == null || singleton.context == null) {
            singleton = new SingletonManagerLeak(context);
        }
        return singleton;
    }

}
