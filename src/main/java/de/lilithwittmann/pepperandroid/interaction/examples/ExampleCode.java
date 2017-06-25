package de.lilithwittmann.pepperandroid.interaction.examples;

import android.util.Log;
import android.view.View;

import java.util.List;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.interaction.Dialog;
import de.lilithwittmann.pepperandroid.interaction.Say;

/**
 * This class holds examples on how e.g. the Dialog class can be used.
 * Mostly meant to be executed in an Android Activity.
 * Created by dea on 25/06/17.
 */

public class ExampleCode {

	private static final String TAG = "Example Code";

	/**
	 * Create a Dialog and create and set a topic. Originally a method from an Android Activity.
	 *
	 * @param pepper
	 * @throws Exception
	 */
	public void testDialog(PepperSession pepper) throws Exception {
		Dialog dialog = new Dialog(pepper);
		dialog.setLanguage(Say.LANGUAGE.ENGLISH);

		// clearing is done automatically in the constructor!
//		this.clearActivatedTopics(dialog);


		String topicString = "topic: ~mytopic()\n" +
				"language: enu\n" +
				"proposal: hello Dea\n" +
				"ul:(hi) nice to meet you on this nice sunday\n";

		String topicString2 = "topic: ~greetings()\n" +
				"language: enu\n" +
				"u: (Hello Nao how are you today) Hello human, I am fine thank you and " +
				"you?\n" +
				"u: ({\"Good morning\"} {Nao} did you sleep * well) No damn! You forgot to switch" +
				" me off!\n" +
				"proposal: human, are you going well ?\n" +
				"u1: (yes) I'm so happy!\n" +
				"u1: (no) I'm so sad\n";


		String topicName = dialog.loadTopicContent(topicString2);
		dialog.subscribe("testDialog");
		dialog.activateTopic(topicName);

		Log.d(TAG, "TopicName: " + topicName.toString());

		List<String> topicsList = dialog.getActivatedTopics();
		Log.d(TAG, "TopicName: " + topicsList);


		dialog.runDialog();

//		dialog.stopDialog();
//
//		dialog.deactivateTopic(topicName.toString());
//
//		dialog.unloadTopic(topicName.toString());
	}
}
