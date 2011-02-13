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
package nh.virgo.order.client.swing.eventview;

import java.util.LinkedList;
import java.util.List;

import nh.client.framework.swing.mvc.Model;
import nh.virgo.order.client.swing.ClientContext;
import nh.virgo.order.client.swing.ClientEventListener;
import nh.virgo.order.framework.domain.events.Event;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class EventViewModel extends Model {

	private final List<Event> _events = new LinkedList<Event>();

	public EventViewModel() {
		ClientEventListener clientEventListener = ClientContext
				.getBean(ClientEventListener.class);
		clientEventListener.setEventViewModel(this);
	}

	public List<Event> getEvents() {
		return _events;
	}

	public void addEvent(Event event) {
		_events.add(event);
		fireModelChangedEvent();
	}

}
