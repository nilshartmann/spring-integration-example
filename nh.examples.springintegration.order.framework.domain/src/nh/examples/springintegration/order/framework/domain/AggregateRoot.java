package nh.examples.springintegration.order.framework.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Method;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import nh.examples.springintegration.order.framework.domain.events.Event;
import nh.examples.springintegration.order.framework.domain.internal.ReflectionHelper;
import nh.examples.springintegration.order.framework.eventstore.EventPublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

/**
 * Base class for an Entity representing an <b>AggregateRoot</b> entity
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
@MappedSuperclass
public class AggregateRoot {

	protected transient Logger _logger = LoggerFactory.getLogger(getClass());
	private transient EventPublisher _eventPublisher;

	@EmbeddedId
	private EntityIdentifier identifier;

	public AggregateRoot(EntityIdentifier identifier) {
		this.identifier = checkNotNull(identifier);
	}

	protected AggregateRoot() {
		// JPA only
	}

	public EntityIdentifier getIdentifier() {
		return identifier;
	}

	protected void apply(Event event) {
		checkNotNull(event);

		// apply internal on this object
		doApply(event);

		// publish event
		_logger.info("Publishing event {}", event);
		_eventPublisher.publish(event);
	}

	@Autowired
	public void setEventPublisher(EventPublisher eventPublisher) {
		checkNotNull(eventPublisher);

		_logger.info("setEventPublisher: {}", eventPublisher);

		_eventPublisher = eventPublisher;
	}

	public EventPublisher getEventPublisher() {
		return _eventPublisher;
	}

	/**
	 * Applies the given event by invoking the appropriate handle method
	 * 
	 * @param event
	 */
	void doApply(Event event) {
		checkNotNull(event);

		// Get Handle method
		Method handleMethod = ReflectionHelper.getAccessibleMethod(
				"applyEvent", getClass(), event);

		// Invoke handle method
		ReflectionUtils.invokeMethod(handleMethod, this, event);
	}
}
