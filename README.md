# avoid-memory-leak-android

This project is all about shows common patterns of memory leaks in Android development and how to fix them

 [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-avoid--memory--leak--android-green.svg?style=flat )]( https://android-arsenal.com/details/1/6887 )

#### How we can cause a leak when we use ?

* [AsyncTask](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/asynctask)

* [Handler](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/handler)

* [Singleton](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/singleton)

* [Thread](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/app/src/main/java/aliasadi/memoryleak/thread)


## How To Avoid Memory Leak?

1. Do not keep long-lived references to a context-activity

```Java
public static Context mContext;
	public NoLifeCycleClass(Activity myActivity) {
	mContext = (Context) myActivity;
}
```


2. Try using the context-application instead of a context-activity

```Java
StringUtilsUI.doSomeLongRunningTask(getApplicationContext());
```

3. Avoid non-static inner classes

```Java
public class DialogCountdown extends BaseDialogFragment {

private class CountDownHandler extends Handler {
	//do some work
  }
}
```

4. Avoid non-static inner classes using WeakReference.

```Java
private static class CountDownHandler extends Handler {
    private final WeakReference<DialogCountdown> mDialogCountdownWeakReference;

    public CountDownHandler(DialogCountdown dialogCountdown) {
        super();
        mDialogCountdownWeakReference = new WeakReference<>(dialogCountdown);
    }

    public void handleMessage(Message msg) {
        if (mDialogCountdownWeakReference.get() != null) {
            mDialogCountdownWeakReference.get().onCountDown();
        }
    }
}
```

5. Clean/Stop all your handlers, animation listeners onDestroy()/onStop().

```Java
@Override
protected void onStop() {
    super.onStop();
    mHandler.clearAllMessages();
    unregisterReceivers();
    heatMapsDone();
    if (mServiceBound) {
        mServiceBound = false;
        Services.unbindFromRidesService(this, this);
    }
    if (mMapStateMachine != null) {
        mMapStateMachine.stop();
        mMapStateMachine = null;
    }
}
```

6. Avoid Auto-Boxing

```Java
public Integer exampleAutoBoxing(){
	int a = 5;
	Integer result = a * a;
	return result;
}
```

```Java
public Integer hiddenAutoBoxing(){
	return 5;
}
```

7. Avoid Auto-Boxing in HashMap

```Java
public Integer hiddenAutoBoxing(){
	HashMap<Integer, String> hashMap = new HashMap<>();
	hashMap.put(5,"Hi Android Academy");
}
```

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
<img src="https://i.imgur.com/dKAqQC5.jpg"/>
</p>

