package de.lilithwittmann.pepperandroid.movement.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lilith on 6/23/17.
 */

public class PepperAnimation {

    public static String INTERNAL_TYPE_GESTURE = "gesture";
    public static String INTERNAL_TYPE_ANIMATION = "animation";
    // animation that is stored in the Value Field
    public static String INTERNAL_TYPE_DYNAMIC_ANIMATION = "dynamic_animation";

    public static class GESTURES{

        public static String STAND_ZERO = "StandZero";
        public static String STAND_CROUCH = "Crouch";
        public static String STAND = "Stand";
        public static String STAND_INIT = "StandInit";
    }

    public static List<String> GESTURE_OPTIONS = Arrays.asList(GESTURES.STAND, GESTURES.STAND_CROUCH,
            GESTURES.STAND_ZERO, GESTURES.STAND_INIT);
    ;

    public String internalType;
    public String value;
    public Double speed = null;


    /**
     * represents any type of Animation/Gesture/… esp for the setGesture method
     * @param value can be the name of a gesture/animation or a xml representation of an animation
     * @param speed a Double between 0 and 1 that represents the relative speed (won't work with gestures)
     *
     **/
    public void setValue(String value, Double speed) {

        if(GESTURE_OPTIONS.contains(value))
        {
            this.internalType = INTERNAL_TYPE_GESTURE;
        } else if (value.startsWith("<")) {
            // TODO: 6/23/17  find a better way to identify animation definitions
            this.internalType = INTERNAL_TYPE_DYNAMIC_ANIMATION;
        } else  {
            this.internalType = INTERNAL_TYPE_ANIMATION;
        }

        if(speed < 0 || speed > 1) {
            throw new IllegalArgumentException("speed has to be a Float between 0 and 1");
        }

        this.value = value;
        this.speed = speed;
    }

    /**
     * represents any type of Animation/Gesture/… esp for the setGesture method
     * @param value can be the name of a gesture/animation or a xml representation of an animation
     *
     **/
    public void setValue(String value) {
        this.setValue(value, 0.0);
    }
}
