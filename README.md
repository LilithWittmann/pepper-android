# pepper-android
A library that wraps some relevant Qi-APIs and simplifies these by abstraction layers. This library is under heavy development!

## setup 
Before you can use this lib on your Pepper you have to [jailbreak](https://github.com/LilithWittmann/pepper-tablet-jailbreak) them. Afterwards you can download the [Pepper SDK for Android Studio] (https://developer.softbankrobotics.com/us-en/downloads/pepper) and add this lib to your project.

## example
First you have to change your App base class to "de.lilithwittmann.pepperandroid.RobotApplication" by setting an android:name in the AndroidManifest.xml. e.g.
```xml

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme"
        android:name="de.lilithwittmann.pepperandroid.RobotApplication"
        >
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

```

Afterwards you can access the robot by getting an RobotManager from the Application.

```java

// create or get a new Pepper Session from the application
final PepperSession session = ((RobotApplication)getActivity().getApplication()).getRobotManager().getSession();

final Say say = new Say(pepper);
        
// say something
say.say("Welcome to Pepper Android");
       

say.setLanguage(say.LANGUAGE_GERMAN).andThen(new FutureFunction<Object, Object>(){
	@Override
	public Future<Object> execute(Future<Object> future) throws Exception {
		say.say("Wilkommen bei Pepper für Android");
		return null;
	}
});

//subscribe to an event

AnyObject alm = session.getService("ALMemory");
// subscribe to the event
AnyObject touchEventListener = (AnyObject) alm.call("subscriber", "TouchChanged").get();

// connect to the signals
touchEventListener.connect("signal", new QiSignalListener() {
	@Override
	public void onSignalReceived(Object... objects) {

	    for(List<Object> o: (List<List<Object>>)objects[0]) {
		Log.d("Sensor", o.get(0).toString()+"  "+o.get(1).toString());
	    }
	    try {
		say.say("Oouuh");
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
});

```

