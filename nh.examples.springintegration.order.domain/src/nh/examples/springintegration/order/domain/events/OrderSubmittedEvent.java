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
package nh.examples.springintegration.order.domain.events;

import nh.examples.springintegration.order.framework.domain.EntityIdentifier;
import nh.examples.springintegration.order.framework.domain.events.Event;

/**
 * An <b>Event</b> that is fired when an Order has been submitted
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderSubmittedEvent extends Event {

	public OrderSubmittedEvent(EntityIdentifier entityIdentifier) {
		super(entityIdentifier);
	}

	/**
	 * Returns the {@link EntityIdentifier} of the Order that has been submitted
	 * 
	 * @return
	 */
	public EntityIdentifier getSubmittedOrderIdentifier() {
		return getAggregateRootIdentifier();
	}

}
