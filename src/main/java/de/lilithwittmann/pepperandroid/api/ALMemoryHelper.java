package de.lilithwittmann.pepperandroid.api;

import android.app.usage.UsageEvents;
import android.view.inputmethod.InputMethodSession;

import com.aldebaran.qi.Future;
import com.aldebaran.qi.Session;

import de.lilithwittmann.pepperandroid.PepperAPI;
import de.lilithwittmann.pepperandroid.PepperSession;
import de.lilithwittmann.pepperandroid.api.Interfaces.EventCallback;

/**
 * Created by dea on 21/06/17.
 */
public abstract class ALMemoryHelper extends PepperAPI{
	public ALMemoryHelper(PepperSession session, String APIName) throws Exception {
		super(session, APIName);
	}

	public abstract Future<Object> subscribeToEvent(String event, EventCallback eventCallback);

	public abstract void unsubscribeToEvent(long eventId);

}
