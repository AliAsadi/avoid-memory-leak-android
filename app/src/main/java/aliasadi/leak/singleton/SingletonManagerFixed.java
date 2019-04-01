package aliasadi.leak.singleton;

import android.content.Context;

/**
 * Created by Ali Asadi on 13/02/2018.
 */
public class SingletonManagerFixed {

    private static SingletonManagerFixed singleton;
    private Context context;

    private SingletonManagerFixed(Context context) {
        this.context = context;
    }

    public synchronized static SingletonManagerFixed getInstance(Context context) {
        if (singleton == null) {
            singleton = new SingletonManagerFixed(context.getApplicationContext());
        }
        return singleton;
    }

    public void destroy() {
        context = null;
        singleton = null;
    }

}
