# avoid-memory-leak-android

This project is all about shows common patterns of memory leaks in Android development and how to fix them

 [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-avoid--memory--leak--android-green.svg?style=flat )]( https://android-arsenal.com/details/1/6887 )
 
 #### How we can cause a leak when we use?

* [AsyncTask, Handler, Singleton, Thread](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/leak-app/src/main/java/aliasadi/memoryleak/leak)

#### How we can fix the leak
* [AsyncTask, Handler, Singleton, Thread](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/fixed-app/src/main/java/aliasadi/memoryleak/fixed)

 ## Screenshot
<p align="center">
<img src="https://i.imgur.com/M1XGg1e.png" height="500" width="270">
<img src="https://i.imgur.com/2b31zYY.png" height="500" width="270">
<img src="https://i.imgur.com/icylNzW.png" height="500" width="270">
</p>


## How To Avoid Memory Leak?

1. Do not keep long-lived references to a context-activity

```Java
public static Context context;

public SampleClass(Activity activity) {
    context = (Context) activity;
}
```


2. Try using the context-application instead of a context-activity

```Java
Utils.doSomeLongRunningTask(getApplicationContext());
SingletoneManager.getInstance(getApplicationContext());
```

3. Avoid non-static inner classes

```Java
public class MainActivity extends Activity {

    private class DownloadTask extends Thread {
        //do some work 
    }
}
```

4. Avoid strong reference use WeakReference for listeners.

```Java
public class DownloadTask extends AsyncTask<Void, Void, Void> {

    private WeakReference<DownloadListener> listener;

    public DownloadTask(DownloadListener listener) {
        listener = new WeakReference<>(listener);
    }

    @Override
    protected Void doInBackground(Void... params) {
       ///do some work
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (listener.get() != null) {
            listener.get().onDownloadTaskDone();
        }
    }
}
}
```

5. Clean/Stop all your handlers, animation listeners onDestroy()/onStop().

```Java
protected void onStop() {
    super.onStop();
    handler.clearAllMessages();
    unregisterReceivers();
    view = null;
    listener = null;
}
```

6. Avoid Auto-Boxing

```Java
public Integer autoBoxing(){
    Integer result = 5;
    return result;
}
```

```Java
public Integer hiddenAutoBoxing(){
    return 5;
}
```
#### How to avoid Auto-Boxing:

```Java
public int autoBoxing(){
    int result = 5;
    return result;
}
```

```Java
public int hiddenAutoBoxing(){
    return 5;
}
```

7. Avoid Auto-Boxing in HashMap - Use SparseArray insead.

```Java
public Integer hiddenAutoBoxing(){
    HashMap<Integer, String> hashMap = new HashMap<>();
    hashMap.put(5,"Hi Android Academy");
}
```

#### How to avoid Auto-Boxing in HashMap:
 
```Java
public Integer noKeyAutoBoxing(){
    SparseArray<String> sparseArray = new SparseArray<>();
    sparseArray.put(5,"Hi Android Academy");
}
```

```Java
public Integer noValueAutoBoxing(){
    SparseIntArray sparseArray = new SparseIntArray();
    sparseArray.put(5,1000);
}
```

##  Tools which can help you identify leaks

* [LeakCanary](https://github.com/square/leakcanary) from Square is a good tool for detecting memory leaks in your app 

<p align="center">
<img src="https://github.com/square/leakcanary/wiki/assets/screenshot-2.0.png"/>
</p>
