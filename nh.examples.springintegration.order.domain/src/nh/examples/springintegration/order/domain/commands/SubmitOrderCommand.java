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
package nh.examples.springintegration.order.domain.commands;

import nh.examples.springintegration.order.domain.events.OrderSubmittedEvent;
import nh.examples.springintegration.order.framework.domain.EntityIdentifier;

/**
 * A <b>Command</b> that submits an <b>Order</b>
 * 
 * @see OrderSubmittedEvent
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class SubmitOrderCommand extends OrderCommand {

	/**
	 * Create a new {@link SubmitOrderCommand}
	 * 
	 * @param orderIdentifier
	 *            the identifier of the Order that will be submitted
	 */
	public SubmitOrderCommand(EntityIdentifier orderIdentifier) {
		super(orderIdentifier);
	}

}
