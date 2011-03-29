/**********************************************************************
 * Copyright (c) 2010 Nils Hartmann and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nils Hartmann initial implementation
 **********************************************************************/
package nh.examples.springintegration.order.client;

import nh.examples.springintegration.order.client.eventview.EventViewModel;
import nh.examples.springintegration.order.framework.domain.events.Event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class ClientEventListener {

	Logger _logger = LoggerFactory.getLogger(getClass());

	// ugly...
	private EventViewModel _eventViewModel;

	public void onEvent(Event event) {
		_logger.info("Event received: {}", event);
		if (_eventViewModel != null) {
			_eventViewModel.addEvent(event);
		}
	}

	public EventViewModel getEventViewModel() {
		return _eventViewModel;
	}

	public void setEventViewModel(EventViewModel eventViewModel) {
		_eventViewModel = eventViewModel;
	}

}
