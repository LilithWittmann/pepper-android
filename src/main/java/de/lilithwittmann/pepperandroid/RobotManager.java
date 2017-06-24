package de.lilithwittmann.pepperandroid;

import android.content.Context;

/**
 * Created by lilith on 6/24/17.
 */

public class RobotManager {

    private final Context context;
    private PepperSession session;
    private String currentRobotUrl = null;


    public RobotManager(Context context) {

        this.context = context;
        this.session = new PepperSession(this.context);
    }


    public void connect(String robotUrl) {
        if(currentRobotUrl != robotUrl){
            this.session.connect(robotUrl);
        }

    }

    public void connect() {
        this.session.connect();
    }

    public PepperSession getSession() {
        if(session == null) {
            this.session.connect();
        }
        return this.session;
    }
}
