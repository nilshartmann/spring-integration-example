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
package nh.virgo.order.domain.events;

import nh.virgo.order.framework.domain.EntityIdentifier;
import nh.virgo.order.framework.domain.events.Event;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class OrderCreatedEvent extends Event {

	private final String _customerName;
	private final String _customerEmail;

	public OrderCreatedEvent(EntityIdentifier entityIdentifier,
			String customerName, String customerEmail) {
		super(entityIdentifier);
		_customerName = customerName;
		_customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return _customerName;
	}

	public String getCustomerEmail() {
		return _customerEmail;
	}

	@Override
	public String toString() {
		return "OrderCreatedEvent [_customerName=" + _customerName + "]";
	}

}
