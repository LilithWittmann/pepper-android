package de.lilithwittmann.pepperandroid.interaction;

import android.util.Log;

import com.aldebaran.qi.QiSignalConnection;
import com.aldebaran.qi.QiSignalListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.interaction.interfaces.SpeechInteractionCallback;
import de.lilithwittmann.pepperandroid.interaction.models.PhraseList;

/**
 * Created by lilith on 6/24/17.
 */

public class SpeechInteraction {

    PepperSession session;
    SpeechRecognition speechRecognition;
    private Map<PhraseList,SpeechInteractionCallback> actionMapping =
            new HashMap<PhraseList,SpeechInteractionCallback>();

    private Double speechRecognitionThreshold = 0.4;

    private QiSignalConnection speechRecognitionHandler;

    /**
     * highlevel interface to map recognized words (from the Pepper ASR) to functions inside of Fragment
     * */
    public SpeechInteraction(PepperSession session) {
        this.session = session;

        try {
            speechRecognition = new SpeechRecognition(this.session);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.startSpeechRecognition();
    }

    /**
     * add a voice command to the speechrecognition
     * @param phraseList a list of phrases that should be mapped to a callback
     * @param callback a SpeechInteractionCallback that will be executed as soon as the ASR understood one of the phrases
     *
     * **/
    public void addVoiceCommand(PhraseList phraseList, SpeechInteractionCallback callback) {
        Log.d("speechInteraction", "added "+phraseList.getPhrases().toString());
        this.actionMapping.put(phraseList, callback);
        this.updateVoiceCommands();
    }


    /**
     * extract all phrases from the actionMapping and adds the commands to the ASR
     * */
    private void updateVoiceCommands() {

        ArrayList<String> commands = new ArrayList<String>();
        for(PhraseList phrases: this.actionMapping.keySet()) {
            commands.addAll(phrases.getPhrases());
        }
        Log.d("speechCommandsLoaded", commands.toString());
        this.speechRecognition.setVocabulary(commands, true);

    }

    /**
     * starts the speech recognition
     * */
    public void startSpeechRecognition() {
        try {
            Log.d("speechInteraction", "init speech interaction");
            this.speechRecognitionHandler = this.speechRecognition.connectToSignalReceiver(new QiSignalListener() {
                @Override
                public void onSignalReceived(Object... objects) {
                    for (Object o : objects) {
                        List<Object> resultList = (List<Object>)o;
                        String result = resultList.get(0).toString();
                        Double resultProbability = Double.valueOf((Float)resultList.get(1));
                        result = result.replace("<...>", "").trim();
                        Log.d("speechRecognitionResult", result+"  "+resultProbability);
                        if(speechRecognitionThreshold <= resultProbability) {
                            for(PhraseList phraseList: actionMapping.keySet()) {
                                if(phraseList.matches(result)) {
                                    actionMapping.get(phraseList).run(SpeechInteraction.this, result);
                                }
                            }
                        }

                    }

                }
            });
        } catch (ExecutionException e) {
            Log.e("speechInteraction", e.getMessage());
            e.printStackTrace();
        }
    }

    /**b
    * stops the speech recognition
    * **/
    public void stopSpeechRecognition() {
        this.speechRecognitionHandler.disconnect();

    }

}
