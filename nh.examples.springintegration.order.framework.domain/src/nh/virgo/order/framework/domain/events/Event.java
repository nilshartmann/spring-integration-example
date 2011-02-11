package nh.virgo.order.framework.domain.events;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.atomic.AtomicLong;

import nh.virgo.order.framework.domain.EntityIdentifier;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class Event {

	private final static AtomicLong _idCounter = new AtomicLong();

	private final EntityIdentifier _aggregateRootIdentifier;
	private final String _eventId;

	protected Event(EntityIdentifier entityIdentifier) {
		_eventId = newId(getClass());
		_aggregateRootIdentifier = checkNotNull(entityIdentifier);
	}

	private static String newId(Class<? extends Event> type) {
		return type.getSimpleName() + "-" + _idCounter.incrementAndGet();
	}

	public EntityIdentifier getAggregateRootIdentifier() {
		return _aggregateRootIdentifier;
	}

	public String getEventName() {
		return getClass().getSimpleName();
	}

	public String getEventId() {
		return _eventId;
	}

}
