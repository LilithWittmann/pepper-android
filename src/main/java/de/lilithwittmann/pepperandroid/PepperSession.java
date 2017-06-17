package de.lilithwittmann.pepperandroid;


import com.aldebaran.qi.AnyObject;
import com.aldebaran.qi.Session;

import java.util.concurrent.ExecutionException;

/**
 * Created by lilith on 6/15/17.
 */

public class PepperSession {


    //default pepper ip when connection between Tablet and Head is etablished
    private String pepperIP = "tcp://198.18.0.1:9559";

    public Session session;

    public void connect(String pepperIP) {
        session = new Session();
        session.connect(pepperIP).sync();

    }

    public void connect() {
        this.connect(this.pepperIP);
    }

    public AnyObject getService(String serviceName) throws ExecutionException {

        return session.service(serviceName).get();

    }

    public Boolean isConnected(){

        return session.isConnected();
    }

}
