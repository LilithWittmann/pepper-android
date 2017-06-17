package de.lilithwittmann.pepperandroid.api;

import com.aldebaran.qi.Future;
import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;

/**
 * http://doc.aldebaran.com/2-5/naoqi/audio/altexttospeech.html
 */

public class AutonomousLifeProxy extends PepperAPI {

    public String AUTONOMUS_LIFE_SOLITARY = "solitary";
    public String AUTONOMUS_LIFE_INTERACTIVE = "interactive";
    public String AUTONOMUS_LIFE_SAFEGUARD = "safeguard";
    public String AUTONOMUS_LIFE_DISABLED = "disabled";



    public AutonomousLifeProxy(PepperSession session) throws Exception {
        super(session, "AutonomousLife");
    }

    public Future<Object> setState(String state) {
        return this.API.call("setState", state);
    }

    public Future<Object> getState() {
        return this.API.call("getState");
    }




}
