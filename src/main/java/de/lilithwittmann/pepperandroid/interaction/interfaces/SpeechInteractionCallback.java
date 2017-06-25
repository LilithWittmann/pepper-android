package de.lilithwittmann.pepperandroid.interaction.interfaces;

import de.lilithwittmann.pepperandroid.interaction.SpeechInteraction;

/**
 * Created by lilith on 6/24/17.
 */

public interface SpeechInteractionCallback {

    public void run(SpeechInteraction speechInteraction, String phrase);
}
