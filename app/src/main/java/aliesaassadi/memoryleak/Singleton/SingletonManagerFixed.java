package aliesaassadi.memoryleak.Singleton;

import android.content.Context;

/**
 * Created by Ali Esa Assadi on 13/02/2018.
 */
public class SingletonManagerFixed {

    private static SingletonManagerFixed sInstance;
    private Context mContext;

    private SingletonManagerFixed(Context context) {
        mContext = context;
    }

    public synchronized static SingletonManagerFixed getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SingletonManagerFixed(context.getApplicationContext());
        }
        return sInstance;
    }

}
