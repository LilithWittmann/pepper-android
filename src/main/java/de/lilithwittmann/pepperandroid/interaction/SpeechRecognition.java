package de.lilithwittmann.pepperandroid.interaction;

import android.util.Log;

import com.aldebaran.qi.AnyObject;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.QiSignalConnection;
import com.aldebaran.qi.QiSignalListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.api.ALDialogProxy;
import de.lilithwittmann.pepperandroid.api.ALMemory;
import de.lilithwittmann.pepperandroid.api.ALSpeechRecognition;

/**
 * Created by dea on 24/06/17.
 */

public class SpeechRecognition {
	private ALMemory alMemory;
	private ALSpeechRecognition alSpeechRecognition;
	private ALDialogProxy alDialogProxy;
	private AnyObject asrService;

	/**
	 * Connects to the proxies for speech recognition. Clears all activated topics and sets a
	 * "beep" to be played at the beginning of the recognition process (after a call to the
	 * subscribe method), and a sound is played when the user call the unsubscribe method.
	 * @param pepper
	 * @throws Exception
	 */
	public SpeechRecognition(PepperSession pepper) throws Exception {
		alMemory = new ALMemory(pepper);
		alSpeechRecognition = new ALSpeechRecognition(pepper);
		alDialogProxy = new ALDialogProxy(pepper);

		clearActivatedTopics();
		alSpeechRecognition.setAudioExpression(true);
	}


	/**
	 * Gets all activated topics from ALDialogProxy and deactivates them.
	 * @throws ExecutionException
	 */
	public void clearActivatedTopics() throws ExecutionException {
		List<String> topicsList = (List<String>) alDialogProxy.getActivatedTopics().get();

		System.out.println("Activated Topics: " + alDialogProxy.getActivatedTopics());

		for(String topic : topicsList){
			System.out.println("Topic: " + topic);
			alDialogProxy.deactivateTopic(topic);
			System.out.println("Topic deleted");

		}
		System.out.println("Activated Topics: " + alDialogProxy.getActivatedTopics());
	}

	/**
	 * Takes a list of strings and sets them as vocabulary for Pepper. It can recognize those words.
	 * @param vocabulary : words to be recognized
	 * @param enabledWordSpotting : If disabled, the engine expects to hear one of the specified
	 * words, nothing more, nothing less. If enabled, the specified words can be pronounced in
	 */
	public Future<Object> setVocabulary(ArrayList<String> vocabulary, boolean enabledWordSpotting) {
		return alSpeechRecognition.setVocabulary(vocabulary, enabledWordSpotting);

	}

	/**
	 * Stops and restarts the speech recognition engine according to the input parameter This can
	 * be used to add contexts, activate or deactivate rules of a context, add a words to a slot.
	 * @param bool true: pause recognition
	 *             false: resume recognition
	 * @return
	 */
	public Future<Object> pause(Boolean bool){
		return alSpeechRecognition.pause(bool);
	}

	/**
	 * Subscribes a signal listener. Connects to ALMemory and calls "subscriber" with
	 * "WordRecognized". Then connects: "signal" with the given signalListener.
	 *
	 * @param signalListener
	 * @throws ExecutionException
	 */
	public QiSignalConnection connectToSignalReceiver(QiSignalListener signalListener) throws
			ExecutionException {
		asrService = (AnyObject) alMemory.call("subscriber", "WordRecognized").get();
		return asrService.connect("signal", signalListener);
	}

	public void disconnectFromSignalReceiver(QiSignalConnection qiSignalConnection) {
		qiSignalConnection.disconnect();
	}
}
