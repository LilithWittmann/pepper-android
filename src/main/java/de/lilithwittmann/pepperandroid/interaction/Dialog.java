package de.lilithwittmann.pepperandroid.interaction;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.api.ALDialogProxy;

/**
 * Created by dea on 25/06/17.
 */

public class Dialog {

	private final ALDialogProxy dialogProxy;

	/**
	 * Creates a new dialog instance which connects to ALDialogProxy.
	 * To create an empty baseline to work with all activated topics get deactivated.
	 *
	 * @param pepperSession
	 * @throws Exception
	 */
	public Dialog(PepperSession pepperSession) throws Exception {
		dialogProxy = new ALDialogProxy(pepperSession);

		dialogProxy.clearActivatedTopics();
	}

	/**
	 * Gets all activated topics from ALDialogProxy and deactivates them.
	 * @throws ExecutionException
	 */
	public void clearActivatedTopics() throws ExecutionException {
		dialogProxy.clearActivatedTopics();
	}

	public void setLanguage(String language){
		dialogProxy.setLanguage(language);
	}

	/**
	 * Works exactly like ALDialogProxy::loadTopic, but instead of a topic’s path, its qichat
	 * code can be passed directly to the method.
	 *
	 * Example:
	 * topicContent = ("topic: ~mytopic()\n"
	 *  "language: enu\n"
	 *  "proposal: hello\n"
	 *  "u:(hi) nice to meet you\n")
	 *
	 * @param topicAsString qichat code to load to the dialog engine. The string has to be formatted
	 *              correctly (with end-of-line markers).
	 * @return Name of the loaded topic. Any syntax errors in the topic file are thrown as
	 * exceptions.
	 * @throws ExecutionException
	 */
	public String loadTopicContent(String topicAsString) throws ExecutionException {
		return dialogProxy.loadTopicContent(topicAsString).get().toString();
	}

	public void subscribe(String subscriberName) {
		dialogProxy.setLanguage(subscriberName);

	}

	/**
	 * Adds the specified topic to the list of the topics that are currently used by the dialog
	 * engine to parse the human’s inputs. Several topics can be active at the same time but only
	 * one will be used to generate proposals (this specific topic is said to have the focus).
	 * @param topicName
	 * @return
	 */
	public void activateTopic(String topicName) {
		dialogProxy.activateTopic(topicName);
	}

	public List<String> getActivatedTopics() throws ExecutionException {
		return (List<String>) dialogProxy.getActivatedTopics().get();
	}

	public void runDialog() {
		dialogProxy.runDialog();
	}
}
