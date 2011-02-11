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
package nh.virgo.order.framework.eventstore;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nh.virgo.order.framework.domain.EntityIdentifier;
import nh.virgo.order.framework.domain.events.Event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class EventStore {

	Logger _logger = LoggerFactory.getLogger(getClass());

	/**
	 * The "persistent" Store
	 */
	private Multimap<EntityIdentifier, Event> _events = ArrayListMultimap
			.create();

	private final EventPublisher _eventPublisher;

	public void setA(String a) {

	}

	public EventStore(EventPublisher eventPublisher) {
		checkNotNull(eventPublisher);
		_eventPublisher = eventPublisher;
	}

	public void add(EntityIdentifier identifier, Iterable<Event> events) {
		checkNotNull(identifier);
		checkNotNull(events);

		for (Event event : events) {
			_logger.trace("Adding event {} to repository", event);
			_events.put(identifier, event);
			_eventPublisher.publish(event);
		}
	}

	public Iterable<Event> getEvents(EntityIdentifier entityIdentifier) {
		checkNotNull(entityIdentifier);

		Collection<Event> collection = _events.get(entityIdentifier);
		return collection;
	}
}
