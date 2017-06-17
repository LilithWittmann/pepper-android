package de.lilithwittmann.pepperandroid;

import com.aldebaran.qi.AnyObject;

/**
 * Created by lilith on 6/15/17.
 */

public abstract class   PepperAPI {

    String APIName;
    public AnyObject API;

    public PepperAPI(PepperSession session, String APIName) throws Exception {
        this.APIName = APIName;
        if(session.isConnected())
        {
            this.API = session.getService(this.APIName);
        } else {
            throw new Exception("Session is not connected to pepper");
        }
    }
}
