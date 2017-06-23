package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.FutureFunction;

import java.util.List;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;

/**
 * http://doc.aldebaran.com/2-5/naoqi/audio/altexttospeech.html
 */

public class ALSpeechRecognition extends PepperAPI{

    public ALSpeechRecognition(PepperSession session) throws Exception {
        super(session, "ALSpeechRecognition");
    }

    public Future<Object> setVocabulary(List<String> vocabulary, Boolean enableWordSpotting) {
        return this.API.call("setVocabulary", vocabulary, enableWordSpotting);
    }

    public Future<Object> getAvailableLanguages() {
        return this.API.call("getAvailableLanguages");
    }

    public Future<Object> setLanguage(String language) {
        return this.API.call("setLanguage", language);
    }

    public Future<Object> getLanguage() {
        return this.API.call("getLanguage");
    }
}
