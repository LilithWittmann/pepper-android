# pepper-android
A library that wraps some relevant Qi-APIs and simplifies these by abstraction layers. This library is under heavy development!

## setup 
Before you can use this lib on your Pepper you have to [jailbreak](https://github.com/LilithWittmann/pepper-tablet-jailbreak) them. Afterwards you can download the [Pepper SDK for Android Studio] (https://developer.softbankrobotics.com/us-en/downloads/pepper) and add this lib to your project.

## example

```java

// create a new pepper session
PepperSession pepper = new PepperSession();
pepper.connect();

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

