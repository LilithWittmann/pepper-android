package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;

/**
 * http://doc.aldebaran.com/2-5/naoqi/audio/altexttospeech.html
 */

public class ALTextToSpeech extends PepperAPI{

    public ALTextToSpeech(PepperSession session) throws Exception {
        super(session, "ALTextToSpeech");
    }

    public Future<Object> say(String text) {
        return this.API.call("say", text);
    }

    public Future<Object> getAvailableLanguages() {
        return this.API.call("getAvailableLanguages");
    }

    public Future<Object> setLanguage(String language) {
       return this.API.call("setLanguage", language);
    }


}
