package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.interaction.Say;

/**
 * Created by lilith on 6/15/17.
 */

public class ALDialogProxy extends PepperAPI {
    public ALDialogProxy(PepperSession session) throws Exception {
        super(session, "ALDialog");
    }

    public Future<Object> runDialog(){
        return this.API.call("runDialog");
    }

    public Future<Object> stopDialog(){
        return this.API.call("stopDialog");
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
     * @param topic qichat code to load to the dialog engine. The string has to be formatted
     *              correctly (with end-of-line markers).
     * @return Name of the loaded topic. Any syntax errors in the topic file are thrown as
     * exceptions.
     */
    public Future<Object> loadTopicContent(String topic) {
        return this.API.call("loadTopicContent", topic);
    }

	/**
     * Unloads the specified topic and frees the associated memory.
     * @param topic the topic’s name returned previously by ALDialogProxy::loadTopic.
     * @return
     */
    public Future<Object> unloadTopic(String topic){
        return this.API.call("unloadTopic", topic);
    }

    public Future<Object> getAllLoadedTopics() {
        return this.API.call("getAllLoadedTopics");
    }

    public Future<Object> deactivateTopic(String topic_name) {
        return this.API.call("deactivateTopic", topic_name);
    }

	/**
     * Adds the specified topic to the list of the topics that are currently used by the dialog
     * engine to parse the human’s inputs. Several topics can be active at the same time but only
     * one will be used to generate proposals (this specific topic is said to have the focus).
     * @param topic_name
     * @return
     */
    public Future<Object> activateTopic(String topic_name) {
        return this.API.call("activateTopic", topic_name);
    }

    public Future<Object> getActivatedTopics() {
        return this.API.call("getActivatedTopics");
    }

    public Future<Object> setLanguage(String language){
        return this.API.call("setLanguage", language);
    }

    public Future<Object> subscribe(String subscriberName) {
        return this.API.call("subscribe", subscriberName);
    }

    /**
     * Gets all activated topics from ALDialogProxy and deactivates them.
     * @throws ExecutionException
     */
    public void clearActivatedTopics() throws ExecutionException {
        List<String> topicsList = (List<String>) this.getActivatedTopics().get();

        System.out.println("Activated Topics: " + topicsList);

        for(String topic : topicsList){
            System.out.println("Topic: " + topic);
            this.deactivateTopic(topic);
            System.out.println("Topic deleted");

        }
        System.out.println("Activated Topics: " + this.getActivatedTopics().get());
    }
}
