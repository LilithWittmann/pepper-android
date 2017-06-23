package de.lilithwittmann.pepperandroid.api.Interfaces;

import de.lilithwittmann.pepperandroid.api.exceptions.CallError;

/**
 * Created by dea on 21/06/17.
 */

public interface EventCallback<T> {

	// TODO: maybe add CallError to throws, according to NAOqi Doc

	/**
	 * Called when an Event is raised
	 * @param value the value return by the event, you can get the type in aldebaran doc
	 * @throws InterruptedException
	 */
	void onEvent(T value) throws InterruptedException, CallError;
}
