package de.lilithwittmann.pepperandroid.interaction.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lilith on 6/24/17.
 */

public class PhraseList {

    private ArrayList<String> phrases = new ArrayList<String>();

    public PhraseList(String ... phrases){
        for(String phrase: phrases) {
            this.addPhrase(phrase);
        }
    }

    public void addPhrase(String phrase) {
        this.phrases.add(phrase.toLowerCase());
    }

    public Boolean matches(String check) {
        Log.d("ASR", check);
        if (phrases.contains(check.toLowerCase())) {
            return true;
        }
        //TODO: add more advanced checks here
        return false;
    }

    public ArrayList<String> getPhrases() {
        return this.phrases;
    }

}
