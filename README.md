# avoid-memory-leak-android

This project is all about shows common patterns of memory leaks in Android development and how to fix them
 

 [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-avoid--memory--leak--android-green.svg?style=flat )]( https://android-arsenal.com/details/1/6887 )
  [![](  https://img.shields.io/badge/Medium-Everything%20you%20need%20to%20know%20about%20Memory%20Leaks%20in%20Android.-lightgrey )]( https://proandroiddev.com/everything-you-need-to-know-about-memory-leaks-in-android-d7a59faaf46a )
   
 #### There is 2 seperated modules:  
 <img src="https://github.com/AliAsadi/avoid-memory-leak-android/blob/master/screenshot/modules.png"/>
  

  1. [leak-app ](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/leak-app/src/main/java/aliasadi/memoryleak/leak)-> Describe and shows how to cause a leak when we use AsyncTask, Handler, Singleton, Thread.


 2. [fixed-app ](https://github.com/AliAsadi/avoid-memory-leak-android/tree/master/fixed-app/src/main/java/aliasadi/memoryleak/fixed)-> Describe and shows how to avoid/fix the leaks

In Android Studio choose which project you want to run on the top bar.

<img src="https://github.com/AliAsadi/avoid-memory-leak-android/blob/master/screenshot/run-app.png"/>

<p align="left">
</p>

 ## Screenshot
<p align="center">
<img src="https://github.com/AliAsadi/avoid-memory-leak-android/blob/master/screenshot/fixed-app.png" height="500" width="270">
<img src="https://github.com/AliAsadi/avoid-memory-leak-android/blob/master/screenshot/dump-memory.png" height="500" width="270">
<img src="https://github.com/AliAsadi/avoid-memory-leak-android/blob/master/screenshot/leaks.png" height="500" width="270">
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
<img src="https://github.com/AliAsadi/avoid-memory-leak-android/blob/master/screenshot/leakcanary.png"/>
</p>

* [Profiler](https://developer.android.com/studio/profile/android-profiler) View the Java heap and memory allocations with Memory Profiler

<p align="center">
<img src="https://github.com/AliAsadi/avoid-memory-leak-android/blob/master/screenshot/profiler.png"/>
</p>

### License
```
   Copyright (C) 2018 Ali Asadi
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
