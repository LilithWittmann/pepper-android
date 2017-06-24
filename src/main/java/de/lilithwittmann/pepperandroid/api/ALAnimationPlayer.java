package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;

/**
 * Created by lilith on 6/15/17.
 */

public class ALAnimationPlayer extends PepperAPI {

    public ALAnimationPlayer(PepperSession session) throws Exception {
        super(session, "ALAnimationPlayer");
    }

    public Future<Object> run(String animation) {
        return this.API.call("run", new Object[]{animation});
    }

    public Future<Object> runTag(String tag) {
        return this.API.call("runTag", tag);
    }
}
