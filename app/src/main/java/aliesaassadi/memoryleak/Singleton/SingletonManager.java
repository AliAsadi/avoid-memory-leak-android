package aliesaassadi.memoryleak.Singleton;

import android.content.Context;

/**
 * Created by Ali Esa Assadi on 13/02/2018.
 */
public class SingletonManager {

    private static SingletonManager sInstance;
    private Context mContext;

    private SingletonManager(Context context) {
        mContext = context;
    }

    public synchronized static SingletonManager getInstance(Context context) {
        if (sInstance == null || sInstance.mContext == null) {
            sInstance = new SingletonManager(context);
        }
        return sInstance;
    }

    public void unregister(Context context) {
        if (mContext == context) {
            mContext = null;
        }
    }

}
