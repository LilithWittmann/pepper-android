# pepper-android
A library that wraps some relevant Qi-APIs and simplifies these by abstraction layers. This library is under heavy development!

## setup 
Before you can use this lib on your Pepper you have to [jailbreak](https://github.com/LilithWittmann/pepper-tablet-jailbreak) them. Afterwards you can download the [Pepper SDK for Android Studio] (https://developer.softbankrobotics.com/us-en/downloads/pepper) and add this lib to your project.

## example

```java

// create a new pepper session
PepperSession pepper = new PepperSession();
pepper.connect();

ALAnimatedSpeech as = new ALAnimatedSpeech(pepper);

// let pepper do gestures while talking
as.setBodyLanguageMode(as.BODY_LANGUAGE_MODE_RANDOM);

// say something
sr.say("Welcome to Pepper Android");


// change language (has to be installed on your robot)
sr.setLanguage("German");
sr.say("Wilkommen bei Pepper für Android");

//set language back to english
sr.setLanguage("English");
```
