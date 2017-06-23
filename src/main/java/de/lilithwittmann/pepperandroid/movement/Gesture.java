package de.lilithwittmann.pepperandroid.movement;

import android.content.Context;
import android.util.Log;

import com.aldebaran.qi.Future;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.api.ALAnimationPlayer;
import de.lilithwittmann.pepperandroid.api.ALRobotPosture;
import de.lilithwittmann.pepperandroid.movement.models.PepperAnimation;

/**
 * Created by lilith on 6/17/17.
 */

public class Gesture {

    private static final int BUFFER_SIZE = 1024*1024;
    private final Context context;
    ALAnimationPlayer animationPlayer;
    ALRobotPosture robotPosture;

    public Gesture(Context context, PepperSession session) throws Exception {
        this.context = context;
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
     * @param speed speed of movment (relative Value between 0 and 1)
     * */
    public Future<Object> setGesture(String gesture, Double speed) {

        PepperAnimation animation = new PepperAnimation();
        animation.setValue(gesture, speed);
        return this.setGesture(animation);
    }

    /**
     * move pepper (set gesture or animation e.g.phelphie-anims/animations/into_future)
     * @param gesture name/path of the gesture
     * */
    public Future<Object> setGesture(String gesture) {

        PepperAnimation animation = new PepperAnimation();
        animation.setValue(gesture);
        return this.setGesture(animation);
    }


    /**
     * run a PepperAnimation
     * @param animation a PepperAnimation object
     * */
    public Future<Object> setGesture(PepperAnimation animation) {

        if(animation.internalType == PepperAnimation.INTERNAL_TYPE_GESTURE) {
            return this.robotPosture.goToPosture(animation.value, animation.speed.floatValue());
        } else if(animation.internalType == PepperAnimation.INTERNAL_TYPE_ANIMATION) {
            return this.animationPlayer.run(animation.value);
        } else {
            return this.animationPlayer.run(animation.value);
        }
    }


    /**
     * set an android resource as an animation
     * @param resource the id of the android resource
     * */
    public Future<Object> setGesture(Integer resource) throws IOException {
        StringBuffer buffer = new StringBuffer();
        InputStream inputStream = context.getResources().openRawResource(resource);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append('\n');
        }
        String animation = buffer.toString();
        Log.d("animation", animation);
        PepperAnimation pepperAnimation = new PepperAnimation();
        pepperAnimation.internalType = PepperAnimation.INTERNAL_TYPE_DYNAMIC_ANIMATION;
        pepperAnimation.setValue(animation);
        return this.setGesture(animation);
    }






}
