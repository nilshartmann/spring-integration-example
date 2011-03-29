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

import static com.google.common.base.Preconditions.checkNotNull;
import nh.examples.springintegration.order.framework.domain.EntityIdentifier;
import nh.examples.springintegration.order.framework.domain.events.Event;

/**
 * @author Nils Hartmann (nils@nilshartmann.net)
 * 
 */
public class LineItemAddedEvent extends Event {

	private final String _item;
	private final int _quantity;

	public LineItemAddedEvent(EntityIdentifier entityIdentifier, String item,
			int quantity) {
		super(entityIdentifier);
		_item = checkNotNull(item);
		_quantity = quantity;
	}

	public String getItem() {
		return _item;
	}

	public int getQuantity() {
		return _quantity;
	}

	@Override
	public String toString() {
		return "LineItemAddedEvent [_item=" + _item + ", _quantity="
				+ _quantity + "]";
	}
}
