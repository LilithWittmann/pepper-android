package de.lilithwittmann.pepperandroid.movement;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.FutureFunction;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.api.ALAnimatedSpeech;
import de.lilithwittmann.pepperandroid.api.ALAnimationPlayer;
import de.lilithwittmann.pepperandroid.api.ALDialogProxy;
import de.lilithwittmann.pepperandroid.api.ALRobotPosture;
import de.lilithwittmann.pepperandroid.api.ALTextToSpeech;

/**
 * Created by lilith on 6/17/17.
 */

public class Gesture {

    ALAnimationPlayer animationPlayer;
    ALRobotPosture robotPosture;

    public Gesture(PepperSession session) throws Exception {
        this.animationPlayer = new ALAnimationPlayer(session);
        this.robotPosture = new ALRobotPosture(session);
    }


    /**
     * resets pepper to default Stand (Standnit in ALRobotPosture)
     * **/
    public Future<Object> reset() {

        return this.robotPosture.goToPosture("StandInit", Float.intBitsToFloat(0));
    }


    /**
     * move pepper (set gesture or animation e.g.phelphie-anims/animations/into_future)
     * @param gesture name/path of the gestur
     * */
    public Future<Object> setGesture(String gesture) {
        return this.setGesture(gesture);
    }

}
