# avoid-memory-leak-android

This project is all about shows common patterns of memory leaks in Android development and how to fix them

 [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-avoid--memory--leak--android-green.svg?style=flat )]( https://android-arsenal.com/details/1/6887 )
 
 #### How we can cause a leak when we use ?

* [AsyncTask](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/asynctask)

* [Handler](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/handler)

* [Singleton](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/singleton)

* [Thread](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/thread)


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
public class Dialog extends DialogFragment {

    private class MessageHandler extends Handler {
        //do some work 
    }
}
```

4. Avoid strong reference use WeakReference for listeners.

```Java
private static class MessageHandler extends Handler {
    private final WeakReference<Listener> listener;

    public MessageHandler(Listener listener) {
        super();
        listener = new WeakReference<>(listener);
    }

    public void handleMessage(Message msg) {
        if (listener.get() != null) {
            listener.get().doSomething();
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
