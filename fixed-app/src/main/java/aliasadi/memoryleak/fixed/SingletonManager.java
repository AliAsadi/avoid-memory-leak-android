package aliasadi.memoryleak.fixed;

import android.content.Context;

/**
 * Created by Ali Asadi on 13/02/2018.
 */
public class SingletonManager {

    private static SingletonManager singleton;
    private Context context;

    private SingletonManager(Context context) {
        this.context = context;
    }

    public synchronized static SingletonManager getInstance(Context context) {
        if (singleton == null) {
            /**
             * Use application Context to prevent leak.
             * **/
            singleton = new SingletonManager(context.getApplicationContext());
        }
        return singleton;
    }

}
