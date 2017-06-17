package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;

/**
 * Created by lilith on 6/15/17.
 */

public class ALAnimatedSpeech extends PepperAPI {

    public Integer BODY_LANGUAGE_MODE_DISABLED = 0;
    public Integer BODY_LANGUAGE_MODE_RANDOM = 1;
    public Integer BODY_LANGUAGE_MODE_CONTEXTUAL = 2;

    public ALAnimatedSpeech(PepperSession session) throws Exception {
        super(session, "ALAnimatedSpeech");
    }

    public Future<Object> setBodyLanguageMode(Integer bodyLanguageMode) {
        return this.API.call("setBodyLanguageMode", bodyLanguageMode);
    }


}
