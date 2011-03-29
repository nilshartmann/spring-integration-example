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

import static com.google.common.base.Preconditions.checkNotNull;
import nh.examples.springintegration.order.framework.domain.EntityIdentifier;
import nh.examples.springintegration.order.framework.domain.commands.Command;

/**
 * Base class for all commands that deal with <b>existing</b> Orders
 * 
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public abstract class OrderCommand extends Command {

	private final EntityIdentifier _orderIdentifier;

	OrderCommand(EntityIdentifier orderIdentifier) {
		_orderIdentifier = checkNotNull(orderIdentifier);
	}

	public EntityIdentifier getOrderIdentifier() {
		return _orderIdentifier;
	}

}
