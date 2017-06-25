package de.lilithwittmann.pepperandroid.interaction;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.aldebaran.qi.Future;
import com.google.common.collect.ImmutableMap;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.api.ALAnimatedSpeech;
import de.lilithwittmann.pepperandroid.api.ALDialogProxy;
import de.lilithwittmann.pepperandroid.api.ALTextToSpeech;

/**
 * Created by lilith on 6/17/17.
 */

public class Say {

    private final PepperSession session;
    ALAnimatedSpeech alAnimatedSpeech;
    ALDialogProxy alDialogProxy;
    ALTextToSpeech alTextToSpeech;

    public static class LANGUAGE{
        public static String GERMAN  = "German";
        public static String ENGLISH = "English";
        public static String FRENCH = "French";

    }

    public static Map<String, Locale> LANGUAGE_TO_LOCALE = ImmutableMap.of(LANGUAGE.GERMAN, Locale.GERMAN,
            LANGUAGE.ENGLISH, Locale.ENGLISH, LANGUAGE.FRENCH, Locale.FRENCH);



    public static class BODY_LANGUAGE {
        public static Integer DISABLED = 0;
        public static Integer RANDOM = 1;
        public static Integer CONTEXTUAL = 2;
    }

    public Say(PepperSession session) throws Exception {
        this.session = session;

        this.alAnimatedSpeech = new ALAnimatedSpeech(session);
        this.alDialogProxy = new ALDialogProxy(session);
        this.alTextToSpeech = new ALTextToSpeech(session);
        this.alTextToSpeech.setLanguage(LANGUAGE.ENGLISH);
    }

    /**
     * say the given string (and use animations if activated)
     * @param text - the text pepper should say
     *
     * */
    public Future say(String text) {
        return this.alAnimatedSpeech.say(text);
    }

    /**
     * change the spoken language of pepper
     * @param language the language (e.g Say,LANGUAGE.GERMAN)
     * */
    public Future setLanguage(String language) {
       return this.alTextToSpeech.setLanguage(language);
    }

    /**
     * get the currently active language
     * */
    public Future<Object> getLanguage() {
        return this.alTextToSpeech.getLanguage();
    }

    /**
     * set the body language mode
     * @param bodyLanguage body language mode (e.g. Say.BODY_LANGUAGE.DISABLED, ay.BODY_LANGUAGE.RANDOM)
     * */
    public Future setBodyLanguage(Integer bodyLanguage) {
        return alAnimatedSpeech.setBodyLanguageMode(bodyLanguage);
    }


    /**
     * get String out of a string-value file
     *
     * @param resourceId id of string
     * @param useTTSLanguage set to true if the string should be said in the current language of the tts engine
     *
     * */
    public Future say(Integer resourceId, Boolean useTTSLanguage) {

        if(useTTSLanguage) {
            Resources standardResources = session.getContext().getResources();
            AssetManager assets = standardResources.getAssets();
            DisplayMetrics metrics = standardResources.getDisplayMetrics();
            Configuration config = new Configuration(standardResources.getConfiguration());
            try {
                config.locale = LANGUAGE_TO_LOCALE.get((String) this.getLanguage().get());
                Resources defaultResources = new Resources(assets, metrics, config);
                String text = defaultResources.getString(resourceId);
                return this.say(text);
            } catch (ExecutionException e) {
                Log.d("PepperAndroid.Say", e.getMessage());
            }
        }

        // Fallback to default language
        return this.say(this.session.getContext().getResources().getString(resourceId));


    }


    /**
     * get String out of a string-value file
     *
     * @param resourceId id of string
     * */
    public Future say(Integer resourceId) {

        return this.say(resourceId, false);
    }

}
