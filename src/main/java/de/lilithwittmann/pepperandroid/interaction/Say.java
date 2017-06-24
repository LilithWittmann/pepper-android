package de.lilithwittmann.pepperandroid.interaction;

import android.content.Context;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.FutureFunction;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.api.ALAnimatedSpeech;
import de.lilithwittmann.pepperandroid.api.ALDialogProxy;
import de.lilithwittmann.pepperandroid.api.ALTextToSpeech;

/**
 * Created by lilith on 6/17/17.
 */

public class Say {

    ALAnimatedSpeech alAnimatedSpeech;
    ALDialogProxy alDialogProxy;
    ALTextToSpeech alTextToSpeech;

    public static class LANGUAGE{
        public static String GERMAN  = "German";
        public static String ENGLISH = "English";
        public static String FRENCH = "French";

    }

    public static class BODY_LANGUAGE {
        public static Integer DISABLED = 0;
        public static Integer RANDOM = 1;
        public static Integer CONTEXTUAL = 2;
    }

    public Say(PepperSession session) throws Exception {
        this.alAnimatedSpeech = new ALAnimatedSpeech(session);
        this.alDialogProxy = new ALDialogProxy(session);
        this.alTextToSpeech = new ALTextToSpeech(session);
        this.alTextToSpeech.setLanguage(LANGUAGE.ENGLISH);
    }

    public Future say(String text) {
        return this.alAnimatedSpeech.say(text);
    }

    public Future setLanguage(String language) {
       return this.alTextToSpeech.setLanguage(language);
    }

    public Future<Object> getLanguage() {
        return this.alTextToSpeech.getLanguage();
    }

    public Future setBodyLanguage(Integer bodyLanguage) {
        return alAnimatedSpeech.setBodyLanguageMode(bodyLanguage);
    }
}
