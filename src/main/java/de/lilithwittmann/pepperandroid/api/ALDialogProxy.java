package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;

/**
 * Created by lilith on 6/15/17.
 */

public class ALDialogProxy extends PepperAPI {
    public ALDialogProxy(PepperSession session) throws Exception {
        super(session, "ALDialog");
    }

    public Future<Object> loadTopicContent(String topic) {
        return this.API.call("loadTopicContent", topic);
    }

    public Future<Object> getAllLoadedTopics() {
        return this.API.call("getAllLoadedTopics");
    }

    public Future<Object> deactivateTopic(String topic_name) {
        return this.API.call("deactivateTopic", topic_name);
    }

    public Future<Object> activateTopic(String topic_name) {
        return this.API.call("activateTopic", topic_name);
    }

    public Future<Object> getActivatedTopics() {
        return this.API.call("getActivatedTopics");
    }
}
