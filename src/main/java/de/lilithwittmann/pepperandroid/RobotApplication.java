package de.lilithwittmann.pepperandroid;

import android.app.Application;

/**
 * Created by lilith on 6/24/17.
 */

public class RobotApplication extends Application {


    private RobotManager robotManager = null;


    /**
     * get the robot manager
     * */
    public RobotManager getRobotManager() {

        if(this.robotManager == null) {
            this.robotManager = new RobotManager(getApplicationContext());
            this.robotManager.connect();
        }

        return this.robotManager;
    }


    /**
     * get a robot manager for a specific robot IP
     * @param robotIP ip address of the robot e.g."tcp://198.18.0.1:9559"
     * */
    public RobotManager getRobotManager(String robotIP) {

        if(this.robotManager == null) {
            this.robotManager = new RobotManager(getApplicationContext());
        }


        this.robotManager.connect(robotIP);

        return this.robotManager;
    }

}
