package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;

/**
 * Created by lilith on 6/15/17.
 */

public class ALRobotPosture extends PepperAPI {

    public ALRobotPosture(PepperSession session) throws Exception {
        super(session, "ALRobotPosture");
    }

    /**
     * returns a list of postures
     * */
    public Future<Object> getPostureList() {
        return this.API.call("getPostureList");
    }

    /**
     * get current posture - if the current posture is unknown it returns unknown
     * */
    public Future<Object> getPosture() {
        return this.API.call("getPosture");
    }

    /**
     * move to posture in given relative speed (start from current pose and the robot will calculate all needed steps)
     * @param postureName name of the posture (options can be retrieved by getPostureList)
     * @param speed  relative speed of movment
     * */
    public Future<Object> goToPosture(String postureName, Float speed) {
        return this.API.call("goToPosture", postureName, speed);
    }
}
